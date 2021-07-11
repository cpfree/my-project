package cn.cpf.web.base.util.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/9/26 17:37
 **/
public class MybatisCommonExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MybatisCommonExample() {
        oredCriteria = new ArrayList<>();
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
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
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
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return !criteria.isEmpty();
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

        public Criteria andFieldIsNull(String field) {
            addCriterion(field + " is null");
            return (Criteria) this;
        }

        public Criteria andFieldIsNotNull(String field) {
            addCriterion(field + " is not null");
            return (Criteria) this;
        }

        public Criteria andFieldEqualTo(String field, Object value) {
            addCriterion(field + " =", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldNotEqualTo(String field, Object value) {
            addCriterion(field + " <>", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldGreaterThan(String field, Object value) {
            addCriterion(field + " >", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldGreaterThanOrEqualTo(String field, Object value) {
            addCriterion(field + " >=", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldLessThan(String field, Object value) {
            addCriterion(field + " <", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldLessThanOrEqualTo(String field, Object value) {
            addCriterion(field + " <=", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldLike(String field, String value) {
            addCriterion(field + " like", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldNotLike(String field, String value) {
            addCriterion(field + " not like", value, field);
            return (Criteria) this;
        }

        public Criteria andFieldIn(String field, List<Object> values) {
            addCriterion(field + " in", values, field);
            return (Criteria) this;
        }

        public Criteria andFieldNotIn(String field, List<Object> values) {
            addCriterion(field + " not in", values, field);
            return (Criteria) this;
        }

        public <T> Criteria andFieldBetween(String field, T value1, T value2) {
            addCriterion(field + " between", value1, value2, field);
            return (Criteria) this;
        }

        public <T> Criteria andFieldNotBetween(String field, T value1, T value2) {
            addCriterion(field + " not between", value1, value2, field);
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