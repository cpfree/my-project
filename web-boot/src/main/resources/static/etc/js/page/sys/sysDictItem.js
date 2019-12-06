// 用于遮罩
let mask;

let numberValid = (rule, value, callback) => {
    if (value && !(/^\d+$/.test(value))) {
        callback(new Error('请输入纯数字'))
    } else {
        callback();
    }
};

let vueContent = new Vue({
    el: '#vue-content',
    //钩子函数,页面加载执行
    created: function () {
        this.refresh();
    },
    //钩子函数,页面加载完成后执行
    mounted: function () {
    },
    data: {
        sysDictItemGrid: {
            multipleSelection: [],
            conditions: {
                blurParCode: undefined,
                blurCode: undefined,
                blurText: undefined,
                updateTimeRange: undefined,
                blurState: undefined
            },
            cols: [
                {width: '', prop: 'parCode', label: '父级代码'},
                {width: '', prop: 'code', label: '代码'},
                {width: '', prop: 'text', label: '标签'},
                {width: '', prop: 'sort', label: '排序'},
                {width: '', prop: 'level', label: '等级'},
                {width: '', prop: 'addTime', label: '添加时间'},
                {width: '', prop: 'updateTime', label: '更新时间'},
                {width: '', prop: 'state', label: '状态'},
                {width: '', prop: 'id', label: '操作', component: 'cOperate'}
            ],
            pretreatment(row) {
                // row.addTimeText = new Date(row.addTime).format('yyyy-MM-dd hh:mm:ss');
            },
            data: [],
            pagination: {
                pageNumber: 1,
                pageSize: 10,
                total: 0
            },
            setTable: function (pageInfo) {
                if (!pageInfo) {
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
            clearTable: function () {
                if (this.data.length > 0) {
                    this.data.splice(0, this.data.length);
                }
            }
        },
        addSysDictItemModel: {
            dialogVisible: false,
            form: {
                parCode: undefined,
                code: undefined,
                text: undefined,
                sort: undefined,
                level: undefined,
                comment: undefined,
                state: undefined
            },
            formRule: {
                parCode: [
                    {required: true, message: '请输入父级代码', trigger: 'change'}
                ],
                code: [
                    {required: true, message: '请输入代码', trigger: 'change'}
                ],
                text: [
                    {required: true, message: '请输入标签', trigger: 'change'}
                ],
                sort: [
                    {required: true, message: '请输入排序', trigger: 'change'}
                ],
                level: [
                    {required: true, message: '请输入等级', trigger: 'change'}
                ],
                comment: [
                    {required: true, message: '请输入描述', trigger: 'change'}
                ],
                state: [
                    {required: true, message: '请输入状态', trigger: 'change'}
                ]
            },
            closeAndClear() {
                this.dialogVisible = false;
                this.form.parCode = undefined;
                this.form.code = undefined;
                this.form.text = undefined;
                this.form.sort = undefined;
                this.form.level = undefined;
                this.form.comment = undefined;
                this.form.state = undefined;
            },
            preAddStatus() {
                this.dialogVisible = true;
            }
        },
        editSysDictItemModel: {
            dialogVisible: false,
            form: {
                parCode: undefined,
                code: undefined,
                text: undefined,
                sort: undefined,
                level: undefined,
                comment: undefined,
                state: undefined
            },
            formRule: {
                parCode: [
                    {required: true, message: '请输入父级代码', trigger: 'change'}
                ],
                code: [
                    {required: true, message: '请输入代码', trigger: 'change'}
                ],
                text: [
                    {required: true, message: '请输入标签', trigger: 'change'}
                ],
                sort: [
                    {required: true, message: '请输入排序', trigger: 'change'}
                ],
                level: [
                    {required: true, message: '请输入等级', trigger: 'change'}
                ],
                comment: [
                    {required: true, message: '请输入描述', trigger: 'change'}
                ],
                state: [
                    {required: true, message: '请输入状态', trigger: 'change'}
                ]
            },
            closeAndClear() {
                this.dialogVisible = false;
                this.form.parCode = undefined;
                this.form.code = undefined;
                this.form.text = undefined;
                this.form.sort = undefined;
                this.form.level = undefined;
                this.form.comment = undefined;
                this.form.state = undefined;
            },
            preEditStatus(record) {
                this.form.guid = record.guid;
                this.form.parCode = record.parCode;
                this.form.code = record.code;
                this.form.text = record.text;
                this.form.sort = record.sort;
                this.form.level = record.level;
                this.form.comment = record.comment;
                this.form.state = record.state;
                this.dialogVisible = true;
            }
        }
    },
    methods: {
        refresh: function () {
            let _this = this;
            let params = jQuery.extend({}, this.sysDictItemGrid.conditions);
            params.pageNumber = this.sysDictItemGrid.pagination.pageNumber;
            params.pageSize = this.sysDictItemGrid.pagination.pageSize;
            if (params.updateTimeRange) {
                params.updateTimeMin = params.updateTimeRange[0];
                // 日期类型计算至下一天凌晨
                params.updateTimeMax = params.updateTimeRange[1] + 86400000;
                delete params.updateTimeRange;
            }
            console.log(params);
            $.sendPostRequest('sysDictItem/queryPageInfo', params, function (data) {
                _this.sysDictItemGrid.setTable(data.pageInfo);
            }, true);
        },
        // 分页
        handleSizeChange: function (pageSize) {
            this.sysDictItemGrid.pagination.pageSize = pageSize;
            this.refresh();
        },
        handleCurrentChange: function (pageNumber) {
            this.sysDictItemGrid.pagination.pageNumber = pageNumber;
            this.refresh();
        },
        handleSelectionChange(val) {
            this.sysDictItemGrid.multipleSelection = val;
        },
        thisCall: function (type, data) {
            let _this = this;
            if (type === 'edit') {
                $.sendPostRequest('sysDictItem/queryOne/' + data, '', (data, status) => {
                    _this.editSysDictItemModel.preEditStatus(data.record);
                }, true);
            } else if (type === 'delete') {
                layer.confirm('确定删除记录吗？', {
                    btn: ['确定', '取消']
                }, function (index) {
                    layer.close(index);
                    $.sendPostRequest('sysDictItem/remove', {guidArr: [data]}, (data, status) => {
                        layer.alert(status.text, {icon: 1});
                        _this.refresh();
                    }, true);
                });
            }
        },
        addSysDictItem: function () {
            let _this = this;
            this.$refs ['addSysDictItemModel.form'].validate((valid) => {
                if (valid) {
                    let formValue = $.extend({}, _this.addSysDictItemModel.form);
                    $.sendPostRequest('sysDictItem/add', formValue, function (data, status) {
                        layer.msg(status.text);
                        _this.addSysDictItemModel.closeAndClear();
                        _this.refresh();
                    }, true);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        deleteSelection: function () {
            if (this.sysDictItemGrid.multipleSelection.length <= 0) {
                layer.msg('请选择记录');
                return;
            }
            let _this = this;
            layer.confirm('确定删除记录吗？', {
                btn: ['确定', '取消']
            }, function (index) {
                layer.close(index);
                let params = {
                    guidArr: _this.sysDictItemGrid.multipleSelection.map(it => it.guid)
                };
                $.sendPostRequest('sysDictItem/remove', params, (data, status) => {
                    layer.alert(status.text, {icon: 1});
                    _this.refresh();
                }, true);
            });
        },
        editSysDictItem: function () {
            let _this = this;
            this.$refs ['editSysDictItemModel.form'].validate((valid) => {
                if (valid) {
                    let formValue = $.extend({}, _this.editSysDictItemModel.form);
                    $.sendPostRequest('sysDictItem/edit', formValue, function (data, status) {
                        layer.msg(status.text);
                        _this.editSysDictItemModel.closeAndClear();
                        _this.refresh();
                    }, true);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    },
    computed: {},
    components: {
        cText: {
            template: "<span>{{row[val] ? row[val] : '--'}}</span>",
            props: {row: {type: Object, required: true}, val: {type: String}}
        },
        cOperate: {
            template:
                '<div>' +
                '<span @click="editRecord" title="编辑" class="fa fa-lg fa-pencil" style="color:black; padding: 0 3px"></span>' +
                '<span @click="deleteRecord" title="删除" class="fa fa-lg fa-remove" style="color:black; padding: 0 3px"></span>' +
                '</div>',
            props: {row: {type: Object, required: true}},
            methods: {
                editRecord: function () {
                    this.$emit('callmethod', 'edit', this.row.guid);
                },
                deleteRecord: function () {
                    this.$emit('callmethod', 'delete', this.row.guid);
                }
            }
        },
        "child-component": {
            props: ["message"],
            template: "<div><h1>{{message}}</h1><input  type='text' v-model='message'></input></div>"
        },
        "vo-sysdictitem": {
            template: '<el-tree :data="treeData" :props="defaultProps" @node-click="handleNodeClick"></el-tree>',
            mounted: function () {
                axios.get('/sys/dict/type/structure')
                    .then(resp => {
                        let dictItemList = resp.data.data;
                        // 根节点数组
                        let rootArr = [];
                        let map = {};
                        dictItemList.forEach(it=>{
                            it.label = it.text;
                            map[it.code] = it;
                        });
                        dictItemList.forEach(it=>{
                            if (it.parCode) {
                                let mapElement = map[it.parCode];
                                if (!mapElement) {
                                    console.warn("找不到父节点--> code : %s, text : %s, parCode : %s", it.code, it.text, it.parCode);
                                }
                                mapElement.children = mapElement.children || [];
                                mapElement.children.push(it);
                            } else {
                                // 加入根节点数组
                                rootArr.push(it);
                            }
                        });
                        this.treeData = rootArr;
                    })
                    .catch(resp => {
                        console.log(resp);
                    });
            },
            methods: {
                handleNodeClick: function (data) {
                    console.log(data);
                }
            },
            data: () => {
                return {
                    treeData: [],
                    defaultProps: {
                        children: 'children',
                        label: 'label'
                    }
                }
            }
        }
    },
    watch: {}
});