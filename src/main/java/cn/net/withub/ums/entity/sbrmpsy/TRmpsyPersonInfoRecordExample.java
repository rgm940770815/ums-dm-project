package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.List;

public class TRmpsyPersonInfoRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyPersonInfoRecordExample() {
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

        public Criteria andRecordTableIdIsNull() {
            addCriterion("record_table_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdIsNotNull() {
            addCriterion("record_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdEqualTo(String value) {
            addCriterion("record_table_id =", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotEqualTo(String value) {
            addCriterion("record_table_id <>", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdGreaterThan(String value) {
            addCriterion("record_table_id >", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdGreaterThanOrEqualTo(String value) {
            addCriterion("record_table_id >=", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdLessThan(String value) {
            addCriterion("record_table_id <", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdLessThanOrEqualTo(String value) {
            addCriterion("record_table_id <=", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdLike(String value) {
            addCriterion("record_table_id like", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotLike(String value) {
            addCriterion("record_table_id not like", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdIn(List<String> values) {
            addCriterion("record_table_id in", values, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotIn(List<String> values) {
            addCriterion("record_table_id not in", values, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdBetween(String value1, String value2) {
            addCriterion("record_table_id between", value1, value2, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotBetween(String value1, String value2) {
            addCriterion("record_table_id not between", value1, value2, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdIsNull() {
            addCriterion("record_log_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdIsNotNull() {
            addCriterion("record_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdEqualTo(String value) {
            addCriterion("record_log_id =", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdNotEqualTo(String value) {
            addCriterion("record_log_id <>", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdGreaterThan(String value) {
            addCriterion("record_log_id >", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdGreaterThanOrEqualTo(String value) {
            addCriterion("record_log_id >=", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdLessThan(String value) {
            addCriterion("record_log_id <", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdLessThanOrEqualTo(String value) {
            addCriterion("record_log_id <=", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdLike(String value) {
            addCriterion("record_log_id like", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdNotLike(String value) {
            addCriterion("record_log_id not like", value, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdIn(List<String> values) {
            addCriterion("record_log_id in", values, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdNotIn(List<String> values) {
            addCriterion("record_log_id not in", values, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdBetween(String value1, String value2) {
            addCriterion("record_log_id between", value1, value2, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andRecordLogIdNotBetween(String value1, String value2) {
            addCriterion("record_log_id not between", value1, value2, "recordLogId");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIsNull() {
            addCriterion("case_number is null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIsNotNull() {
            addCriterion("case_number is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberEqualTo(String value) {
            addCriterion("case_number =", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotEqualTo(String value) {
            addCriterion("case_number <>", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThan(String value) {
            addCriterion("case_number >", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("case_number >=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThan(String value) {
            addCriterion("case_number <", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThanOrEqualTo(String value) {
            addCriterion("case_number <=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLike(String value) {
            addCriterion("case_number like", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotLike(String value) {
            addCriterion("case_number not like", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIn(List<String> values) {
            addCriterion("case_number in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotIn(List<String> values) {
            addCriterion("case_number not in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberBetween(String value1, String value2) {
            addCriterion("case_number between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotBetween(String value1, String value2) {
            addCriterion("case_number not between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberIsNull() {
            addCriterion("assessor_number is null");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberIsNotNull() {
            addCriterion("assessor_number is not null");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberEqualTo(String value) {
            addCriterion("assessor_number =", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberNotEqualTo(String value) {
            addCriterion("assessor_number <>", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberGreaterThan(String value) {
            addCriterion("assessor_number >", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberGreaterThanOrEqualTo(String value) {
            addCriterion("assessor_number >=", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberLessThan(String value) {
            addCriterion("assessor_number <", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberLessThanOrEqualTo(String value) {
            addCriterion("assessor_number <=", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberLike(String value) {
            addCriterion("assessor_number like", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberNotLike(String value) {
            addCriterion("assessor_number not like", value, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberIn(List<String> values) {
            addCriterion("assessor_number in", values, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberNotIn(List<String> values) {
            addCriterion("assessor_number not in", values, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberBetween(String value1, String value2) {
            addCriterion("assessor_number between", value1, value2, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andAssessorNumberNotBetween(String value1, String value2) {
            addCriterion("assessor_number not between", value1, value2, "assessorNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberIsNull() {
            addCriterion("condition_number is null");
            return (Criteria) this;
        }

        public Criteria andConditionNumberIsNotNull() {
            addCriterion("condition_number is not null");
            return (Criteria) this;
        }

        public Criteria andConditionNumberEqualTo(String value) {
            addCriterion("condition_number =", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberNotEqualTo(String value) {
            addCriterion("condition_number <>", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberGreaterThan(String value) {
            addCriterion("condition_number >", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("condition_number >=", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberLessThan(String value) {
            addCriterion("condition_number <", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberLessThanOrEqualTo(String value) {
            addCriterion("condition_number <=", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberLike(String value) {
            addCriterion("condition_number like", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberNotLike(String value) {
            addCriterion("condition_number not like", value, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberIn(List<String> values) {
            addCriterion("condition_number in", values, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberNotIn(List<String> values) {
            addCriterion("condition_number not in", values, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberBetween(String value1, String value2) {
            addCriterion("condition_number between", value1, value2, "conditionNumber");
            return (Criteria) this;
        }

        public Criteria andConditionNumberNotBetween(String value1, String value2) {
            addCriterion("condition_number not between", value1, value2, "conditionNumber");
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

        public Criteria andReplyStatusIsNull() {
            addCriterion("reply_status is null");
            return (Criteria) this;
        }

        public Criteria andReplyStatusIsNotNull() {
            addCriterion("reply_status is not null");
            return (Criteria) this;
        }

        public Criteria andReplyStatusEqualTo(Integer value) {
            addCriterion("reply_status =", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusNotEqualTo(Integer value) {
            addCriterion("reply_status <>", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusGreaterThan(Integer value) {
            addCriterion("reply_status >", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_status >=", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusLessThan(Integer value) {
            addCriterion("reply_status <", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("reply_status <=", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusIn(List<Integer> values) {
            addCriterion("reply_status in", values, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusNotIn(List<Integer> values) {
            addCriterion("reply_status not in", values, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusBetween(Integer value1, Integer value2) {
            addCriterion("reply_status between", value1, value2, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_status not between", value1, value2, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andOpenCourtIsNull() {
            addCriterion("open_court is null");
            return (Criteria) this;
        }

        public Criteria andOpenCourtIsNotNull() {
            addCriterion("open_court is not null");
            return (Criteria) this;
        }

        public Criteria andOpenCourtEqualTo(Integer value) {
            addCriterion("open_court =", value, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtNotEqualTo(Integer value) {
            addCriterion("open_court <>", value, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtGreaterThan(Integer value) {
            addCriterion("open_court >", value, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtGreaterThanOrEqualTo(Integer value) {
            addCriterion("open_court >=", value, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtLessThan(Integer value) {
            addCriterion("open_court <", value, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtLessThanOrEqualTo(Integer value) {
            addCriterion("open_court <=", value, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtIn(List<Integer> values) {
            addCriterion("open_court in", values, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtNotIn(List<Integer> values) {
            addCriterion("open_court not in", values, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtBetween(Integer value1, Integer value2) {
            addCriterion("open_court between", value1, value2, "openCourt");
            return (Criteria) this;
        }

        public Criteria andOpenCourtNotBetween(Integer value1, Integer value2) {
            addCriterion("open_court not between", value1, value2, "openCourt");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNull() {
            addCriterion("msg_id is null");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNotNull() {
            addCriterion("msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andMsgIdEqualTo(String value) {
            addCriterion("msg_id =", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotEqualTo(String value) {
            addCriterion("msg_id <>", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThan(String value) {
            addCriterion("msg_id >", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThanOrEqualTo(String value) {
            addCriterion("msg_id >=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThan(String value) {
            addCriterion("msg_id <", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThanOrEqualTo(String value) {
            addCriterion("msg_id <=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLike(String value) {
            addCriterion("msg_id like", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotLike(String value) {
            addCriterion("msg_id not like", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdIn(List<String> values) {
            addCriterion("msg_id in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotIn(List<String> values) {
            addCriterion("msg_id not in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdBetween(String value1, String value2) {
            addCriterion("msg_id between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotBetween(String value1, String value2) {
            addCriterion("msg_id not between", value1, value2, "msgId");
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