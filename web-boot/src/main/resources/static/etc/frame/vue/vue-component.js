
Vue.component("vc-text", {
    template: "<span>{{row[val] ? row[val] : '--'}}</span>",
    props: {row: {type: Object, required: true}, val: {type: String}}
});

Vue.component("vc-select-dict", {
    props: ["placeholder", 'v-model'],
    template:
        '<el-select v-bind:value="value" v-on:input="$emit(\'input\', $event.target.value)" :placeholder=placeholder>' +
        '   <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>' +
        "</el-select>",
    mounted: function () {
        let promise = $Info.querySysDictItemByDictType(this.dictType);
        promise.then((data)=>{
            if (data.data.data) {
                data.data.data.forEach(item => {
                    this.options.push(item);
                });
            }
        })
    },
    data(){
        return {
            dictType: 'table_state',
            options : []
        }
    },
    // watch: {
    //     "dict-type0" : function(newVal,oldVal){
    //         if (newVal) {
    //             // this.dictType = newVal;
    //             let promise = $Info.querySysDictItemByDictType(newVal);
    //             promise.then((data)=>{
    //                 console.log(data.data);
    //             })
    //         }
    //     }
    // }
});


function childConvert(arr, idName, parName){
    // 根节点数组
    let rootArr = [];
    let map = {};
    arr.forEach(it=>{
        map[it[idName]] = it;
    });
    arr.forEach(it=>{
        let parNode = it[parName];
        if (parNode) {
            let mapElement = map[parNode];
            if (!mapElement) {
                console.warn("找不到父节点--> value : %s, parNode : %s", it[idName], parNode);
                console.warn(it);
            }
            mapElement.children = mapElement.children || [];
            mapElement.children.push(it);
        } else {
            // 加入根节点数组
            rootArr.push(it);
        }
    });
    return rootArr;
}

Vue.component("vc-tree", {
    props: ["title"],
    template:
        '<div>' +
        '   <h4>{{title}}</h4>' +
        '   <el-tree :data="treeData" :props="defaultProps" @node-click="handleNodeClick"></el-tree>' +
        "</div>",
    mounted: function () {
        $dict.querySysDictTypeStructure()
            .then(resp => {
                let dictItemList = resp.data.data;
                this.treeData = childConvert(dictItemList, 'value', 'pvalue');
            })
    },
    methods: {
        handleNodeClick: function (data) {
            this.$emit('ontreenodeclick', data);
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
});
