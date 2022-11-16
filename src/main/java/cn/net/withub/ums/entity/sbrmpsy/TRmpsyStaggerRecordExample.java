package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TRmpsyStaggerRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyStaggerRecordExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andAssessorIdIsNull() {
            addCriterion("assessor_id is null");
            return (Criteria) this;
        }

        public Criteria andAssessorIdIsNotNull() {
            addCriterion("assessor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssessorIdEqualTo(String value) {
            addCriterion("assessor_id =", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotEqualTo(String value) {
            addCriterion("assessor_id <>", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdGreaterThan(String value) {
            addCriterion("assessor_id >", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdGreaterThanOrEqualTo(String value) {
            addCriterion("assessor_id >=", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdLessThan(String value) {
            addCriterion("assessor_id <", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdLessThanOrEqualTo(String value) {
            addCriterion("assessor_id <=", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdLike(String value) {
            addCriterion("assessor_id like", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotLike(String value) {
            addCriterion("assessor_id not like", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdIn(List<String> values) {
            addCriterion("assessor_id in", values, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotIn(List<String> values) {
            addCriterion("assessor_id not in", values, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdBetween(String value1, String value2) {
            addCriterion("assessor_id between", value1, value2, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotBetween(String value1, String value2) {
            addCriterion("assessor_id not between", value1, value2, "assessorId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeIsNull() {
            addCriterion("stagger_start_time is null");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeIsNotNull() {
            addCriterion("stagger_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_start_time =", value, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_start_time <>", value, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("stagger_start_time >", value, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_start_time >=", value, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("stagger_start_time <", value, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_start_time <=", value, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("stagger_start_time in", values, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("stagger_start_time not in", values, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stagger_start_time between", value1, value2, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stagger_start_time not between", value1, value2, "staggerStartTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeIsNull() {
            addCriterion("stagger_end_time is null");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeIsNotNull() {
            addCriterion("stagger_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_end_time =", value, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_end_time <>", value, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("stagger_end_time >", value, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_end_time >=", value, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("stagger_end_time <", value, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stagger_end_time <=", value, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("stagger_end_time in", values, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("stagger_end_time not in", values, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stagger_end_time between", value1, value2, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andStaggerEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stagger_end_time not between", value1, value2, "staggerEndTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
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