


function HttpUtils() {
}

let serializeDateFun = date => date.getTime();

HttpUtils.httpGet = function(url, params) {
    if (params && params instanceof Object) {
        let _params = Qs.stringify(params, { arrayFormat: 'repeat', serializeDate:serializeDateFun });
        if (_params) {
            url += '?' + _params;
        }
    }
    // let result = {};
    // let rep = axios.get(url);
    // rep.then((data) => result = data.data)
    //     .catch(resp => {console.log(resp);});
    return axios.get(url).catch(resp => {console.log(resp.data);});
};


let $Info = {

    mapping : "/info",

    querySysDictItemByDictType : function (type) {
        return HttpUtils.httpGet('/info/dict-item/' + type);
    },

    querySysDictItemArray : function (typeArr) {
        return HttpUtils.httpGet('/info/dict-item', {type : typeArr});
    },

};


let $dict = {

    querySysDictTypeStructure : function () {
        return HttpUtils.httpGet('/sys/dict/type/structure');
    },

    querySysDictItemPageInfo(params) {
        return HttpUtils.httpGet('/sys/dict/item/page-info', params);
    },

    querySysDictItemByDictType(type) {
        return HttpUtils.httpGet('/sys/dict/item/' + type);
    }
};


