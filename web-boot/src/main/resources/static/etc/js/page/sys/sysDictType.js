// 用于遮罩
let mask;

let numberValid=(rule, value,callback)=>{
    if (value && !(/^\d+$/.test(value))){
        callback(new Error('请输入纯数字'))
    }else {
        callback();
    }
};

let vueContent = new Vue({
    el : '#vue-content',
    //钩子函数,页面加载执行
    created: function () {
        this.refresh();
    },
    //钩子函数,页面加载完成后执行
    mounted: function () {
    },
    data: {
        sysDictTypeGrid : {
            multipleSelection : [],
            conditions : {
                blurName : undefined,
                blurText : undefined,
                updateTimeRange : undefined,
                blurState : undefined
            },
            cols : [
                { width: '',prop: 'name', label: '字典代码' },
                { width: '',prop: 'text', label: '字典类型' },
                { width: '',prop: 'sort', label: '排序' },
                { width: '',prop: 'maxLevel', label: '最大级别' },
                { width: '',prop: 'comment', label: '描述' },
                { width: '',prop: 'updateTime', label: '更新时间' },
                { width: '', prop: 'id', label: '操作', component : 'cOperate'}
            ],
            pretreatment(row) {
                // row.addTimeText = new Date(row.addTime).format('yyyy-MM-dd hh:mm:ss');
            },
            data : [],
            pagination : {
                pageNumber: 1,
                pageSize: 10,
                total : 0
            },
            setTable : function(pageInfo) {
                if (! pageInfo) {
                    return;
                }
                this.clearTable();
                if (pageInfo.list) {
                    pageInfo.list.forEach(item => {
                        this.pretreatment(item);
                        this.data.push(item);
                    });
                }
                this.pagination.total = pageInfo.total;
            },
            clearTable : function(){
                if (this.data.length > 0){
                    this.data.splice(0, this.data.length);
                }
            }
        },
        addSysDictTypeModel : {
            dialogVisible : false,
            form : {
                name: undefined,
                text: undefined,
                sort: undefined,
                comment: undefined
            },
            formRule : {
                name:[
                    { required: true, message: '请输入字典代码', trigger: 'change' }
                ],
                text:[
                    { required: true, message: '请输入字典类型', trigger: 'change' }
                ],
                sort:[
                    { required: true, message: '请输入排序', trigger: 'change' }
                ],
                comment:[
                    { required: true, message: '请输入描述', trigger: 'change' }
                ]
            },
            closeAndClear() {
                this.dialogVisible = false;
                this.form.name = undefined;
                this.form.text = undefined;
                this.form.sort = undefined;
                this.form.comment = undefined;
            },
            preAddStatus() {
                this.dialogVisible = true;
            }
        },
        editSysDictTypeModel : {
            dialogVisible : false,
            form : {
                name: undefined,
                text: undefined,
                sort: undefined,
                comment: undefined,
                state: undefined
            },
            formRule : {
                name: [
                    { required: true, message: '请输入字典代码', trigger: 'change' }
                ],
                text: [
                    { required: true, message: '请输入字典类型', trigger: 'change' }
                ],
                sort: [
                    { required: true, message: '请输入排序', trigger: 'change' }
                ],
                comment: [
                    { required: true, message: '请输入描述', trigger: 'change' }
                ],
                state: [
                    { required: true, message: '请输入状态', trigger: 'change' }
                ]
            },
            closeAndClear() {
                this.dialogVisible = false;
                this.form.name = undefined;
                this.form.text = undefined;
                this.form.sort = undefined;
                this.form.comment = undefined;
                this.form.state = undefined;
            },
            preEditStatus(record) {
                this.form.guid = record.guid;
                this.form.name = record.name;
                this.form.text = record.text;
                this.form.sort = record.sort;
                this.form.comment = record.comment;
                this.form.state = record.state;
                this.dialogVisible = true;
            }
        }
    },
    methods: {
        refresh : function () {
            let _this = this;
            let params = jQuery.extend({}, this.sysDictTypeGrid.conditions);
            params.pageNumber = this.sysDictTypeGrid.pagination.pageNumber;
            params.pageSize = this.sysDictTypeGrid.pagination.pageSize;
                        if (params.updateTimeRange) {
                params.updateTimeMin = params.updateTimeRange[0];
                // 日期类型计算至下一天凌晨
                params.updateTimeMax = params.updateTimeRange[1] + 86400000;
                delete params.updateTimeRange;
            }
                    console.log(params);
            $.sendPostRequest('sysDictType/queryPageInfo', params, function (data) {
                _this.sysDictTypeGrid.setTable(data.pageInfo);
            }, true);
        },
        // 分页
        handleSizeChange : function (pageSize) {
            this.sysDictTypeGrid.pagination.pageSize = pageSize;
            this.refresh();
        },
        handleCurrentChange : function (pageNumber) {
            this.sysDictTypeGrid.pagination.pageNumber = pageNumber;
            this.refresh();
        },
        handleSelectionChange(val) {
            this.sysDictTypeGrid.multipleSelection = val;
        },
        thisCall : function(type, data) {
            let _this = this;
            if (type === 'edit') {
                $.sendPostRequest('sysDictType/queryOne/' + data, '', (data, status)=>{
                    _this.editSysDictTypeModel.preEditStatus(data.record);
                }, true);
            } else if (type === 'delete') {
                layer.confirm('确定删除记录吗？', {
                    btn: ['确定','取消']
                }, function(index){
                    layer.close(index);
                    $.sendPostRequest('sysDictType/remove', {guidArr : [data]}, (data, status)=>{
                        layer.alert(status.text, {icon: 1});
                        _this.refresh();
                    }, true);
                });
            }
        },
        addSysDictType : function(){
            let _this = this;
            this.$refs ['addSysDictTypeModel.form'].validate ((valid) => {
                if (valid) {
                    let formValue = $.extend({}, _this.addSysDictTypeModel.form);
                    $.sendPostRequest('sysDictType/add', formValue, function (data, status) {
                        layer.msg(status.text);
                        _this.addSysDictTypeModel.closeAndClear();
                        _this.refresh();
                    }, true);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        deleteSelection : function(){
            if (this.sysDictTypeGrid.multipleSelection.length <= 0) {
                layer.msg('请选择记录');
                return;
            }
            let _this = this;
            layer.confirm('确定删除记录吗？', {
                btn: ['确定','取消']
            }, function(index){
                layer.close(index);
                let params = {
                    guidArr : _this.sysDictTypeGrid.multipleSelection.map(it=>it.guid)
                };
                $.sendPostRequest('sysDictType/remove', params, (data, status)=>{
                    layer.alert(status.text, {icon: 1});
                    _this.refresh();
                }, true);
            });
        },
        editSysDictType : function(){
            let _this = this;
            this.$refs ['editSysDictTypeModel.form'].validate ((valid) => {
                if (valid) {
                    let formValue = $.extend({}, _this.editSysDictTypeModel.form);
                    $.sendPostRequest('sysDictType/edit', formValue, function (data, status) {
                        layer.msg(status.text);
                        _this.editSysDictTypeModel.closeAndClear();
                        _this.refresh();
                    }, true);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    },
    computed: {
    },
    components: {
        cText : {
            template: "<span>{{row[val] ? row[val] : '--'}}</span>",
            props: {row:{type: Object, required: true}, val:{type: String}}
        },
        cOperate : {
            template:
                '<div>' +
                '<span @click="editRecord" title="编辑" class="fa fa-lg fa-pencil" style="color:black; padding: 0 3px"></span>' +
                '<span @click="deleteRecord" title="删除" class="fa fa-lg fa-remove" style="color:black; padding: 0 3px"></span>' +
                '</div>',
            props: {row:{type: Object, required: true}},
            methods: {
                editRecord : function () {
                    this.$emit('callmethod', 'edit', this.row.guid);
                },
                deleteRecord : function () {
                    this.$emit('callmethod', 'delete', this.row.guid);
                }
            }
        }
    },
    watch: {
    }
});