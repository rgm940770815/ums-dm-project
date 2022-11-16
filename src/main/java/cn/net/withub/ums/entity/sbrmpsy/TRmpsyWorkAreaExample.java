package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.List;

public class TRmpsyWorkAreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyWorkAreaExample() {
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

        public Criteria andInstitutionNumberIsNull() {
            addCriterion("institution_number is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberIsNotNull() {
            addCriterion("institution_number is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberEqualTo(String value) {
            addCriterion("institution_number =", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberNotEqualTo(String value) {
            addCriterion("institution_number <>", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberGreaterThan(String value) {
            addCriterion("institution_number >", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("institution_number >=", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberLessThan(String value) {
            addCriterion("institution_number <", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberLessThanOrEqualTo(String value) {
            addCriterion("institution_number <=", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberLike(String value) {
            addCriterion("institution_number like", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberNotLike(String value) {
            addCriterion("institution_number not like", value, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberIn(List<String> values) {
            addCriterion("institution_number in", values, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberNotIn(List<String> values) {
            addCriterion("institution_number not in", values, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberBetween(String value1, String value2) {
            addCriterion("institution_number between", value1, value2, "institutionNumber");
            return (Criteria) this;
        }

        public Criteria andInstitutionNumberNotBetween(String value1, String value2) {
            addCriterion("institution_number not between", value1, value2, "institutionNumber");
            return (Criteria) this;
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