package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.List;

public class TRmpsySelectConditionSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsySelectConditionSettingExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andSelectModeIsNull() {
            addCriterion("select_mode is null");
            return (Criteria) this;
        }

        public Criteria andSelectModeIsNotNull() {
            addCriterion("select_mode is not null");
            return (Criteria) this;
        }

        public Criteria andSelectModeEqualTo(String value) {
            addCriterion("select_mode =", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeNotEqualTo(String value) {
            addCriterion("select_mode <>", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeGreaterThan(String value) {
            addCriterion("select_mode >", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeGreaterThanOrEqualTo(String value) {
            addCriterion("select_mode >=", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeLessThan(String value) {
            addCriterion("select_mode <", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeLessThanOrEqualTo(String value) {
            addCriterion("select_mode <=", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeLike(String value) {
            addCriterion("select_mode like", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeNotLike(String value) {
            addCriterion("select_mode not like", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeIn(List<String> values) {
            addCriterion("select_mode in", values, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeNotIn(List<String> values) {
            addCriterion("select_mode not in", values, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeBetween(String value1, String value2) {
            addCriterion("select_mode between", value1, value2, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeNotBetween(String value1, String value2) {
            addCriterion("select_mode not between", value1, value2, "selectMode");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberIsNull() {
            addCriterion("alternative_number is null");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberIsNotNull() {
            addCriterion("alternative_number is not null");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberEqualTo(Integer value) {
            addCriterion("alternative_number =", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberNotEqualTo(Integer value) {
            addCriterion("alternative_number <>", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberGreaterThan(Integer value) {
            addCriterion("alternative_number >", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("alternative_number >=", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberLessThan(Integer value) {
            addCriterion("alternative_number <", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberLessThanOrEqualTo(Integer value) {
            addCriterion("alternative_number <=", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberIn(List<Integer> values) {
            addCriterion("alternative_number in", values, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberNotIn(List<Integer> values) {
            addCriterion("alternative_number not in", values, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberBetween(Integer value1, Integer value2) {
            addCriterion("alternative_number between", value1, value2, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("alternative_number not between", value1, value2, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andNationalIsNull() {
            addCriterion("national is null");
            return (Criteria) this;
        }

        public Criteria andNationalIsNotNull() {
            addCriterion("national is not null");
            return (Criteria) this;
        }

        public Criteria andNationalEqualTo(Integer value) {
            addCriterion("national =", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotEqualTo(Integer value) {
            addCriterion("national <>", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThan(Integer value) {
            addCriterion("national >", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThanOrEqualTo(Integer value) {
            addCriterion("national >=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThan(Integer value) {
            addCriterion("national <", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThanOrEqualTo(Integer value) {
            addCriterion("national <=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalIn(List<Integer> values) {
            addCriterion("national in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotIn(List<Integer> values) {
            addCriterion("national not in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalBetween(Integer value1, Integer value2) {
            addCriterion("national between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotBetween(Integer value1, Integer value2) {
            addCriterion("national not between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIsNull() {
            addCriterion("case_type is null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIsNotNull() {
            addCriterion("case_type is not null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeEqualTo(Integer value) {
            addCriterion("case_type =", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotEqualTo(Integer value) {
            addCriterion("case_type <>", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeGreaterThan(Integer value) {
            addCriterion("case_type >", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_type >=", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLessThan(Integer value) {
            addCriterion("case_type <", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("case_type <=", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIn(List<Integer> values) {
            addCriterion("case_type in", values, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotIn(List<Integer> values) {
            addCriterion("case_type not in", values, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeBetween(Integer value1, Integer value2) {
            addCriterion("case_type between", value1, value2, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("case_type not between", value1, value2, "caseType");
            return (Criteria) this;
        }

        public Criteria andIsNearestIsNull() {
            addCriterion("is_nearest is null");
            return (Criteria) this;
        }

        public Criteria andIsNearestIsNotNull() {
            addCriterion("is_nearest is not null");
            return (Criteria) this;
        }

        public Criteria andIsNearestEqualTo(Integer value) {
            addCriterion("is_nearest =", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestNotEqualTo(Integer value) {
            addCriterion("is_nearest <>", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestGreaterThan(Integer value) {
            addCriterion("is_nearest >", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_nearest >=", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestLessThan(Integer value) {
            addCriterion("is_nearest <", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestLessThanOrEqualTo(Integer value) {
            addCriterion("is_nearest <=", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestIn(List<Integer> values) {
            addCriterion("is_nearest in", values, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestNotIn(List<Integer> values) {
            addCriterion("is_nearest not in", values, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestBetween(Integer value1, Integer value2) {
            addCriterion("is_nearest between", value1, value2, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestNotBetween(Integer value1, Integer value2) {
            addCriterion("is_nearest not between", value1, value2, "isNearest");
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