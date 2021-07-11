package cn.cpf.web.base.model.example;

import java.util.ArrayList;
import java.util.List;

public class SysFieldExtendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysFieldExtendExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSchemaTagIsNull() {
            addCriterion("schema_tag is null");
            return (Criteria) this;
        }

        public Criteria andSchemaTagIsNotNull() {
            addCriterion("schema_tag is not null");
            return (Criteria) this;
        }

        public Criteria andSchemaTagEqualTo(String value) {
            addCriterion("schema_tag =", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagNotEqualTo(String value) {
            addCriterion("schema_tag <>", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagGreaterThan(String value) {
            addCriterion("schema_tag >", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagGreaterThanOrEqualTo(String value) {
            addCriterion("schema_tag >=", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagLessThan(String value) {
            addCriterion("schema_tag <", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagLessThanOrEqualTo(String value) {
            addCriterion("schema_tag <=", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagLike(String value) {
            addCriterion("schema_tag like", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagNotLike(String value) {
            addCriterion("schema_tag not like", value, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagIn(List<String> values) {
            addCriterion("schema_tag in", values, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagNotIn(List<String> values) {
            addCriterion("schema_tag not in", values, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagBetween(String value1, String value2) {
            addCriterion("schema_tag between", value1, value2, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andSchemaTagNotBetween(String value1, String value2) {
            addCriterion("schema_tag not between", value1, value2, "schemaTag");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andDictTypeIsNull() {
            addCriterion("dict_type is null");
            return (Criteria) this;
        }

        public Criteria andDictTypeIsNotNull() {
            addCriterion("dict_type is not null");
            return (Criteria) this;
        }

        public Criteria andDictTypeEqualTo(String value) {
            addCriterion("dict_type =", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNotEqualTo(String value) {
            addCriterion("dict_type <>", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeGreaterThan(String value) {
            addCriterion("dict_type >", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeGreaterThanOrEqualTo(String value) {
            addCriterion("dict_type >=", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeLessThan(String value) {
            addCriterion("dict_type <", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeLessThanOrEqualTo(String value) {
            addCriterion("dict_type <=", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeLike(String value) {
            addCriterion("dict_type like", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNotLike(String value) {
            addCriterion("dict_type not like", value, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeIn(List<String> values) {
            addCriterion("dict_type in", values, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNotIn(List<String> values) {
            addCriterion("dict_type not in", values, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeBetween(String value1, String value2) {
            addCriterion("dict_type between", value1, value2, "dictType");
            return (Criteria) this;
        }

        public Criteria andDictTypeNotBetween(String value1, String value2) {
            addCriterion("dict_type not between", value1, value2, "dictType");
            return (Criteria) this;
        }

        public Criteria andShowSearchIsNull() {
            addCriterion("show_search is null");
            return (Criteria) this;
        }

        public Criteria andShowSearchIsNotNull() {
            addCriterion("show_search is not null");
            return (Criteria) this;
        }

        public Criteria andShowSearchEqualTo(String value) {
            addCriterion("show_search =", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchNotEqualTo(String value) {
            addCriterion("show_search <>", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchGreaterThan(String value) {
            addCriterion("show_search >", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchGreaterThanOrEqualTo(String value) {
            addCriterion("show_search >=", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchLessThan(String value) {
            addCriterion("show_search <", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchLessThanOrEqualTo(String value) {
            addCriterion("show_search <=", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchLike(String value) {
            addCriterion("show_search like", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchNotLike(String value) {
            addCriterion("show_search not like", value, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchIn(List<String> values) {
            addCriterion("show_search in", values, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchNotIn(List<String> values) {
            addCriterion("show_search not in", values, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchBetween(String value1, String value2) {
            addCriterion("show_search between", value1, value2, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowSearchNotBetween(String value1, String value2) {
            addCriterion("show_search not between", value1, value2, "showSearch");
            return (Criteria) this;
        }

        public Criteria andShowListIsNull() {
            addCriterion("show_list is null");
            return (Criteria) this;
        }

        public Criteria andShowListIsNotNull() {
            addCriterion("show_list is not null");
            return (Criteria) this;
        }

        public Criteria andShowListEqualTo(String value) {
            addCriterion("show_list =", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListNotEqualTo(String value) {
            addCriterion("show_list <>", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListGreaterThan(String value) {
            addCriterion("show_list >", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListGreaterThanOrEqualTo(String value) {
            addCriterion("show_list >=", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListLessThan(String value) {
            addCriterion("show_list <", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListLessThanOrEqualTo(String value) {
            addCriterion("show_list <=", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListLike(String value) {
            addCriterion("show_list like", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListNotLike(String value) {
            addCriterion("show_list not like", value, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListIn(List<String> values) {
            addCriterion("show_list in", values, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListNotIn(List<String> values) {
            addCriterion("show_list not in", values, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListBetween(String value1, String value2) {
            addCriterion("show_list between", value1, value2, "showList");
            return (Criteria) this;
        }

        public Criteria andShowListNotBetween(String value1, String value2) {
            addCriterion("show_list not between", value1, value2, "showList");
            return (Criteria) this;
        }

        public Criteria andShowAddIsNull() {
            addCriterion("show_add is null");
            return (Criteria) this;
        }

        public Criteria andShowAddIsNotNull() {
            addCriterion("show_add is not null");
            return (Criteria) this;
        }

        public Criteria andShowAddEqualTo(String value) {
            addCriterion("show_add =", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddNotEqualTo(String value) {
            addCriterion("show_add <>", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddGreaterThan(String value) {
            addCriterion("show_add >", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddGreaterThanOrEqualTo(String value) {
            addCriterion("show_add >=", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddLessThan(String value) {
            addCriterion("show_add <", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddLessThanOrEqualTo(String value) {
            addCriterion("show_add <=", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddLike(String value) {
            addCriterion("show_add like", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddNotLike(String value) {
            addCriterion("show_add not like", value, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddIn(List<String> values) {
            addCriterion("show_add in", values, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddNotIn(List<String> values) {
            addCriterion("show_add not in", values, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddBetween(String value1, String value2) {
            addCriterion("show_add between", value1, value2, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowAddNotBetween(String value1, String value2) {
            addCriterion("show_add not between", value1, value2, "showAdd");
            return (Criteria) this;
        }

        public Criteria andShowEditIsNull() {
            addCriterion("show_edit is null");
            return (Criteria) this;
        }

        public Criteria andShowEditIsNotNull() {
            addCriterion("show_edit is not null");
            return (Criteria) this;
        }

        public Criteria andShowEditEqualTo(String value) {
            addCriterion("show_edit =", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditNotEqualTo(String value) {
            addCriterion("show_edit <>", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditGreaterThan(String value) {
            addCriterion("show_edit >", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditGreaterThanOrEqualTo(String value) {
            addCriterion("show_edit >=", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditLessThan(String value) {
            addCriterion("show_edit <", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditLessThanOrEqualTo(String value) {
            addCriterion("show_edit <=", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditLike(String value) {
            addCriterion("show_edit like", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditNotLike(String value) {
            addCriterion("show_edit not like", value, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditIn(List<String> values) {
            addCriterion("show_edit in", values, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditNotIn(List<String> values) {
            addCriterion("show_edit not in", values, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditBetween(String value1, String value2) {
            addCriterion("show_edit between", value1, value2, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowEditNotBetween(String value1, String value2) {
            addCriterion("show_edit not between", value1, value2, "showEdit");
            return (Criteria) this;
        }

        public Criteria andShowDetailIsNull() {
            addCriterion("show_detail is null");
            return (Criteria) this;
        }

        public Criteria andShowDetailIsNotNull() {
            addCriterion("show_detail is not null");
            return (Criteria) this;
        }

        public Criteria andShowDetailEqualTo(String value) {
            addCriterion("show_detail =", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailNotEqualTo(String value) {
            addCriterion("show_detail <>", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailGreaterThan(String value) {
            addCriterion("show_detail >", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailGreaterThanOrEqualTo(String value) {
            addCriterion("show_detail >=", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailLessThan(String value) {
            addCriterion("show_detail <", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailLessThanOrEqualTo(String value) {
            addCriterion("show_detail <=", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailLike(String value) {
            addCriterion("show_detail like", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailNotLike(String value) {
            addCriterion("show_detail not like", value, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailIn(List<String> values) {
            addCriterion("show_detail in", values, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailNotIn(List<String> values) {
            addCriterion("show_detail not in", values, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailBetween(String value1, String value2) {
            addCriterion("show_detail between", value1, value2, "showDetail");
            return (Criteria) this;
        }

        public Criteria andShowDetailNotBetween(String value1, String value2) {
            addCriterion("show_detail not between", value1, value2, "showDetail");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private final String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private final String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}