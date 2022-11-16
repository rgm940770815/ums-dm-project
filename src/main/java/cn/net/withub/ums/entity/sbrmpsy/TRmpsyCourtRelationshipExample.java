package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.List;

public class TRmpsyCourtRelationshipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyCourtRelationshipExample() {
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

        public Criteria andWorkAreaCodeIsNull() {
            addCriterion("work_area_code is null");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeIsNotNull() {
            addCriterion("work_area_code is not null");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeEqualTo(String value) {
            addCriterion("work_area_code =", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeNotEqualTo(String value) {
            addCriterion("work_area_code <>", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeGreaterThan(String value) {
            addCriterion("work_area_code >", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("work_area_code >=", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeLessThan(String value) {
            addCriterion("work_area_code <", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("work_area_code <=", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeLike(String value) {
            addCriterion("work_area_code like", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeNotLike(String value) {
            addCriterion("work_area_code not like", value, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeIn(List<String> values) {
            addCriterion("work_area_code in", values, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeNotIn(List<String> values) {
            addCriterion("work_area_code not in", values, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeBetween(String value1, String value2) {
            addCriterion("work_area_code between", value1, value2, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andWorkAreaCodeNotBetween(String value1, String value2) {
            addCriterion("work_area_code not between", value1, value2, "workAreaCode");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCourtCodeIsNull() {
            addCriterion("court_code is null");
            return (Criteria) this;
        }

        public Criteria andCourtCodeIsNotNull() {
            addCriterion("court_code is not null");
            return (Criteria) this;
        }

        public Criteria andCourtCodeEqualTo(String value) {
            addCriterion("court_code =", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeNotEqualTo(String value) {
            addCriterion("court_code <>", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeGreaterThan(String value) {
            addCriterion("court_code >", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("court_code >=", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeLessThan(String value) {
            addCriterion("court_code <", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeLessThanOrEqualTo(String value) {
            addCriterion("court_code <=", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeLike(String value) {
            addCriterion("court_code like", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeNotLike(String value) {
            addCriterion("court_code not like", value, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeIn(List<String> values) {
            addCriterion("court_code in", values, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeNotIn(List<String> values) {
            addCriterion("court_code not in", values, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeBetween(String value1, String value2) {
            addCriterion("court_code between", value1, value2, "courtCode");
            return (Criteria) this;
        }

        public Criteria andCourtCodeNotBetween(String value1, String value2) {
            addCriterion("court_code not between", value1, value2, "courtCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeIsNull() {
            addCriterion("tribunal_code is null");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeIsNotNull() {
            addCriterion("tribunal_code is not null");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeEqualTo(String value) {
            addCriterion("tribunal_code =", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeNotEqualTo(String value) {
            addCriterion("tribunal_code <>", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeGreaterThan(String value) {
            addCriterion("tribunal_code >", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tribunal_code >=", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeLessThan(String value) {
            addCriterion("tribunal_code <", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeLessThanOrEqualTo(String value) {
            addCriterion("tribunal_code <=", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeLike(String value) {
            addCriterion("tribunal_code like", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeNotLike(String value) {
            addCriterion("tribunal_code not like", value, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeIn(List<String> values) {
            addCriterion("tribunal_code in", values, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeNotIn(List<String> values) {
            addCriterion("tribunal_code not in", values, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeBetween(String value1, String value2) {
            addCriterion("tribunal_code between", value1, value2, "tribunalCode");
            return (Criteria) this;
        }

        public Criteria andTribunalCodeNotBetween(String value1, String value2) {
            addCriterion("tribunal_code not between", value1, value2, "tribunalCode");
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