package cn.cpf.web.base.model.entity;

public class SysField {
    private String guid;

    private String name;

    private String sysTableGuid;

    private String label;

    private String type;

    private Integer length;

    private String showSearch;

    private String showList;

    private String showAdd;

    private String showEdit;

    private String showDetail;

    private String comment;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSysTableGuid() {
        return sysTableGuid;
    }

    public void setSysTableGuid(String sysTableGuid) {
        this.sysTableGuid = sysTableGuid == null ? null : sysTableGuid.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getShowSearch() {
        return showSearch;
    }

    public void setShowSearch(String showSearch) {
        this.showSearch = showSearch == null ? null : showSearch.trim();
    }

    public String getShowList() {
        return showList;
    }

    public void setShowList(String showList) {
        this.showList = showList == null ? null : showList.trim();
    }

    public String getShowAdd() {
        return showAdd;
    }

    public void setShowAdd(String showAdd) {
        this.showAdd = showAdd == null ? null : showAdd.trim();
    }

    public String getShowEdit() {
        return showEdit;
    }

    public void setShowEdit(String showEdit) {
        this.showEdit = showEdit == null ? null : showEdit.trim();
    }

    public String getShowDetail() {
        return showDetail;
    }

    public void setShowDetail(String showDetail) {
        this.showDetail = showDetail == null ? null : showDetail.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}