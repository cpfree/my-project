package cn.cpf.web.base.model.entity;

public class SysFieldExtend extends SysFieldExtendKey {
    private String label;

    private String dictType;

    private String showSearch;

    private String showList;

    private String showAdd;

    private String showEdit;

    private String showDetail;

    private Integer sort;

    private String comment;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}