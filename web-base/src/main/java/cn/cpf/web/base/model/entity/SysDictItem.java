package cn.cpf.web.base.model.entity;

import java.util.Date;

public class SysDictItem extends SysDictItemKey {
    private String value;

    private String cnLabel;

    private String enLabel;

    private String parValue;

    private Integer level;

    private Integer ord;

    private String comment;

    private String permission;

    private Date addTime;

    private Date updateTime;

    private String state;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getCnLabel() {
        return cnLabel;
    }

    public void setCnLabel(String cnLabel) {
        this.cnLabel = cnLabel == null ? null : cnLabel.trim();
    }

    public String getEnLabel() {
        return enLabel;
    }

    public void setEnLabel(String enLabel) {
        this.enLabel = enLabel == null ? null : enLabel.trim();
    }

    public String getParValue() {
        return parValue;
    }

    public void setParValue(String parValue) {
        this.parValue = parValue == null ? null : parValue.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}