package cn.cpf.web.base.lang.base;

import cn.cpf.web.base.util.cast.JsonUtils;

import java.io.Serializable;

/**
 * <b>Description : </b> 简单数据封装对象, 用于对简单数据的封装, 作为 json 传输
 *
 * @author CPF
 * @date 2019/5/5 20:30
 **/
public class SimpleDataBean<T extends Serializable> implements Serializable {

    private String tag;

    private T data;

    public SimpleDataBean(String tag, T data) {
        this.tag = tag;
        this.data = data;
    }

    public SimpleDataBean() {

    }

    public static <D extends Serializable> String packageToJsonMessage(String tag, D data) {
        return new SimpleDataBean<>(tag, data).toJson();
    }

    public static SimpleDataBean fromJson(String jsonMessage) {
        return JsonUtils.convertJson2Object(jsonMessage, SimpleDataBean.class, "SimpleDataBean->convertFromJson : error!");
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toJson() {
        return JsonUtils.convertObject2Json(this, "SimpleDataBean->packageToJsonMessage : error!");
    }
}
