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
        inGirlGrid : {
            multipleSelection : [],
            conditions : {
                blurRoleType : undefined,
                blurPeopleType : undefined,
                blurName : undefined,
                blurNickname : undefined,
                blurWorld : undefined,
                blurLocation : undefined,
                blurRelationship : undefined
            },
            cols : [
                { width: '',prop: 'roleType', label: '角色类型' },
                { width: '',prop: 'peopleType', label: '人物类型' },
                { width: '',prop: 'name', label: '名字' },
                { width: '',prop: 'nickname', label: '昵称' },
                { width: '',prop: 'world', label: '来自' },
                { width: '',prop: 'location', label: '位于' },
                { width: '',prop: 'insertTime', label: '添加时间' },
                { width: '',prop: 'updateTime', label: '更新时间' },
                { width: '',prop: 'relationship', label: '关系' },
                { width: '',prop: 'summary', label: '简介' },
                { width: '',prop: 'des', label: '描述' },
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
        addInGirlModel : {
            dialogVisible : false,
            form : {
                roleType: undefined,
                peopleType: undefined,
                name: undefined,
                nickname: undefined,
                world: undefined,
                location: undefined,
                relationship: undefined,
                summary: undefined,
                des: undefined
            },
            formRule : {
                roleType:[
                    { required: true, message: '请输入角色类型', trigger: 'change' }
                ],
                peopleType:[
                    { required: true, message: '请输入人物类型', trigger: 'change' }
                ],
                name:[
                    { required: true, message: '请输入名字', trigger: 'change' }
                ],
                nickname:[
                    { required: true, message: '请输入昵称', trigger: 'change' }
                ],
                world:[
                    { required: true, message: '请输入来自', trigger: 'change' }
                ],
                location:[
                    { required: true, message: '请输入位于', trigger: 'change' }
                ],
                relationship:[
                    { required: true, message: '请输入关系', trigger: 'change' }
                ],
                summary:[
                    { required: true, message: '请输入简介', trigger: 'change' }
                ],
                des:[
                    { required: true, message: '请输入描述', trigger: 'change' }
                ]
            },
            closeAndClear() {
                this.dialogVisible = false;
                this.form.roleType = undefined;
                this.form.peopleType = undefined;
                this.form.name = undefined;
                this.form.nickname = undefined;
                this.form.world = undefined;
                this.form.location = undefined;
                this.form.relationship = undefined;
                this.form.summary = undefined;
                this.form.des = undefined;
            },
            preAddStatus() {
                this.dialogVisible = true;
            }
        },
        editInGirlModel : {
            dialogVisible : false,
            form : {
                roleType: undefined,
                peopleType: undefined,
                name: undefined,
                nickname: undefined,
                world: undefined,
                location: undefined,
                relationship: undefined,
                summary: undefined,
                des: undefined
            },
            formRule : {
                roleType: [
                    { required: true, message: '请输入角色类型', trigger: 'change' }
                ],
                peopleType: [
                    { required: true, message: '请输入人物类型', trigger: 'change' }
                ],
                name: [
                    { required: true, message: '请输入名字', trigger: 'change' }
                ],
                nickname: [
                    { required: true, message: '请输入昵称', trigger: 'change' }
                ],
                world: [
                    { required: true, message: '请输入来自', trigger: 'change' }
                ],
                location: [
                    { required: true, message: '请输入位于', trigger: 'change' }
                ],
                relationship: [
                    { required: true, message: '请输入关系', trigger: 'change' }
                ],
                summary: [
                    { required: true, message: '请输入简介', trigger: 'change' }
                ],
                des: [
                    { required: true, message: '请输入描述', trigger: 'change' }
                ]
            },
            closeAndClear() {
                this.dialogVisible = false;
                this.form.roleType = undefined;
                this.form.peopleType = undefined;
                this.form.name = undefined;
                this.form.nickname = undefined;
                this.form.world = undefined;
                this.form.location = undefined;
                this.form.relationship = undefined;
                this.form.summary = undefined;
                this.form.des = undefined;
            },
            preEditStatus(record) {
                this.form.id = record.id;
                this.form.roleType = record.roleType;
                this.form.peopleType = record.peopleType;
                this.form.name = record.name;
                this.form.nickname = record.nickname;
                this.form.world = record.world;
                this.form.location = record.location;
                this.form.relationship = record.relationship;
                this.form.summary = record.summary;
                this.form.des = record.des;
                this.dialogVisible = true;
            }
        }
    },
    methods: {
        refresh : function () {
            let _this = this;
            let params = jQuery.extend({}, this.inGirlGrid.conditions);
            params.pageNumber = this.inGirlGrid.pagination.pageNumber;
            params.pageSize = this.inGirlGrid.pagination.pageSize;
                                        console.log(params);
            $.sendPostRequest('/inGirl/queryPageInfo', params, function (data) {
                _this.inGirlGrid.setTable(data.pageInfo);
            }, true);
        },
        // 分页
        handleSizeChange : function (pageSize) {
            this.inGirlGrid.pagination.pageSize = pageSize;
            this.refresh();
        },
        handleCurrentChange : function (pageNumber) {
            this.inGirlGrid.pagination.pageNumber = pageNumber;
            this.refresh();
        },
        handleSelectionChange(val) {
            this.inGirlGrid.multipleSelection = val;
        },
        thisCall : function(type, data) {
            let _this = this;
            if (type === 'edit') {
                $.sendPostRequest('/inGirl/queryOne/' + data, '', (data, status)=>{
                    _this.editInGirlModel.preEditStatus(data.record);
                }, true);
            } else if (type === 'delete') {
                layer.confirm('确定删除记录吗？', {
                    btn: ['确定','取消']
                }, function(index){
                    layer.close(index);
                    $.sendPostRequest('/inGirl/remove', {idArr : [data]}, (data, status)=>{
                        layer.alert(status.text, {icon: 1});
                        _this.refresh();
                    }, true);
                });
            }
        },
        addInGirl : function(){
            let _this = this;
            this.$refs ['addInGirlModel.form'].validate ((valid) => {
                if (valid) {
                    let formValue = $.extend({}, _this.addInGirlModel.form);
                    $.sendPostRequest('/inGirl/add', formValue, function (data, status) {
                        layer.msg(status.text);
                        _this.addInGirlModel.closeAndClear();
                        _this.refresh();
                    }, true);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        deleteSelection : function(){
            if (this.inGirlGrid.multipleSelection.length <= 0) {
                layer.msg('请选择记录');
                return;
            }
            let _this = this;
            layer.confirm('确定删除记录吗？', {
                btn: ['确定','取消']
            }, function(index){
                layer.close(index);
                let params = {
                    idArr : _this.inGirlGrid.multipleSelection.map(it=>it.id)
                };
                $.sendPostRequest('/inGirl/remove', params, (data, status)=>{
                    layer.alert(status.text, {icon: 1});
                    _this.refresh();
                }, true);
            });
        },
        editInGirl : function(){
            let _this = this;
            this.$refs ['editInGirlModel.form'].validate ((valid) => {
                if (valid) {
                    let formValue = $.extend({}, _this.editInGirlModel.form);
                    $.sendPostRequest('/inGirl/edit', formValue, function (data, status) {
                        layer.msg(status.text);
                        _this.editInGirlModel.closeAndClear();
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
                    this.$emit('callmethod', 'edit', this.row.id);
                },
                deleteRecord : function () {
                    this.$emit('callmethod', 'delete', this.row.id);
                }
            }
        }
    },
    watch: {
    }
});