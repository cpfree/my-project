
// 将Dictbean 转换为 element 的 Cascader 级联选择器 的 options
function convertItyDictBeanToElementOptions (sArr){
    let filter1 = sArr.filter(it=>it.level === 1);
    let options = [];
    filter1.forEach(it=>{
        let tmp = {};
        tmp.label = it.text;
        tmp.value = it.code;
        if (tmp.value) {
            let children = convertItyDictBeanToElementOptionsRecursive(sArr, it.code);
            if (children.length > 0) {
                tmp.children = children;
            }
        }
        options.push(tmp);
    });
    return options;
}

// 将Dictbean 转换为 element 的 Cascader 级联选择器 的 options 的递归调用
function convertItyDictBeanToElementOptionsRecursive(sArr, parCode) {
    let arr = [];
    let filter = sArr.filter(it=>it.parCode === parCode);
    filter.forEach(it=>{
        let tmp = {};
        tmp.label = it.text;
        tmp.value = it.code;
        if (tmp.value) {
            let children = convertItyDictBeanToElementOptionsRecursive(sArr, it.code);
            if (children.length > 0) {
                tmp.children = children;
            }
        }
        arr.push(tmp);
    });
    return arr;
}