package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

public class RmpsySbdzbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RmpsySbdzbExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOurformIsNull() {
            addCriterion("ourForm is null");
            return (Criteria) this;
        }

        public Criteria andOurformIsNotNull() {
            addCriterion("ourForm is not null");
            return (Criteria) this;
        }

        public Criteria andOurformEqualTo(String value) {
            addCriterion("ourForm =", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformNotEqualTo(String value) {
            addCriterion("ourForm <>", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformGreaterThan(String value) {
            addCriterion("ourForm >", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformGreaterThanOrEqualTo(String value) {
            addCriterion("ourForm >=", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformLessThan(String value) {
            addCriterion("ourForm <", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformLessThanOrEqualTo(String value) {
            addCriterion("ourForm <=", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformLike(String value) {
            addCriterion("ourForm like", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformNotLike(String value) {
            addCriterion("ourForm not like", value, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformIn(List<String> values) {
            addCriterion("ourForm in", values, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformNotIn(List<String> values) {
            addCriterion("ourForm not in", values, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformBetween(String value1, String value2) {
            addCriterion("ourForm between", value1, value2, "ourform");
            return (Criteria) this;
        }

        public Criteria andOurformNotBetween(String value1, String value2) {
            addCriterion("ourForm not between", value1, value2, "ourform");
            return (Criteria) this;
        }

        public Criteria andZgyformIsNull() {
            addCriterion("zgyForm is null");
            return (Criteria) this;
        }

        public Criteria andZgyformIsNotNull() {
            addCriterion("zgyForm is not null");
            return (Criteria) this;
        }

        public Criteria andZgyformEqualTo(String value) {
            addCriterion("zgyForm =", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformNotEqualTo(String value) {
            addCriterion("zgyForm <>", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformGreaterThan(String value) {
            addCriterion("zgyForm >", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformGreaterThanOrEqualTo(String value) {
            addCriterion("zgyForm >=", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformLessThan(String value) {
            addCriterion("zgyForm <", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformLessThanOrEqualTo(String value) {
            addCriterion("zgyForm <=", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformLike(String value) {
            addCriterion("zgyForm like", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformNotLike(String value) {
            addCriterion("zgyForm not like", value, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformIn(List<String> values) {
            addCriterion("zgyForm in", values, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformNotIn(List<String> values) {
            addCriterion("zgyForm not in", values, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformBetween(String value1, String value2) {
            addCriterion("zgyForm between", value1, value2, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformNotBetween(String value1, String value2) {
            addCriterion("zgyForm not between", value1, value2, "zgyform");
            return (Criteria) this;
        }

        public Criteria andZgyformnameIsNull() {
            addCriterion("zgyFormName is null");
            return (Criteria) this;
        }

        public Criteria andZgyformnameIsNotNull() {
            addCriterion("zgyFormName is not null");
            return (Criteria) this;
        }

        public Criteria andZgyformnameEqualTo(String value) {
            addCriterion("zgyFormName =", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameNotEqualTo(String value) {
            addCriterion("zgyFormName <>", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameGreaterThan(String value) {
            addCriterion("zgyFormName >", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameGreaterThanOrEqualTo(String value) {
            addCriterion("zgyFormName >=", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameLessThan(String value) {
            addCriterion("zgyFormName <", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameLessThanOrEqualTo(String value) {
            addCriterion("zgyFormName <=", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameLike(String value) {
            addCriterion("zgyFormName like", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameNotLike(String value) {
            addCriterion("zgyFormName not like", value, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameIn(List<String> values) {
            addCriterion("zgyFormName in", values, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameNotIn(List<String> values) {
            addCriterion("zgyFormName not in", values, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameBetween(String value1, String value2) {
            addCriterion("zgyFormName between", value1, value2, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andZgyformnameNotBetween(String value1, String value2) {
            addCriterion("zgyFormName not between", value1, value2, "zgyformname");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyIsNull() {
            addCriterion("primaryKey is null");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyIsNotNull() {
            addCriterion("primaryKey is not null");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyEqualTo(String value) {
            addCriterion("primaryKey =", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyNotEqualTo(String value) {
            addCriterion("primaryKey <>", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyGreaterThan(String value) {
            addCriterion("primaryKey >", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyGreaterThanOrEqualTo(String value) {
            addCriterion("primaryKey >=", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyLessThan(String value) {
            addCriterion("primaryKey <", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyLessThanOrEqualTo(String value) {
            addCriterion("primaryKey <=", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyLike(String value) {
            addCriterion("primaryKey like", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyNotLike(String value) {
            addCriterion("primaryKey not like", value, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyIn(List<String> values) {
            addCriterion("primaryKey in", values, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyNotIn(List<String> values) {
            addCriterion("primaryKey not in", values, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyBetween(String value1, String value2) {
            addCriterion("primaryKey between", value1, value2, "primarykey");
            return (Criteria) this;
        }

        public Criteria andPrimarykeyNotBetween(String value1, String value2) {
            addCriterion("primaryKey not between", value1, value2, "primarykey");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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