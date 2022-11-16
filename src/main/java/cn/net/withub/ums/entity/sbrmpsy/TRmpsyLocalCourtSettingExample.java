package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.List;

public class TRmpsyLocalCourtSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyLocalCourtSettingExample() {
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

        public Criteria andMaxCaseNumberIsNull() {
            addCriterion("max_case_number is null");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberIsNotNull() {
            addCriterion("max_case_number is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberEqualTo(Integer value) {
            addCriterion("max_case_number =", value, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberNotEqualTo(Integer value) {
            addCriterion("max_case_number <>", value, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberGreaterThan(Integer value) {
            addCriterion("max_case_number >", value, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_case_number >=", value, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberLessThan(Integer value) {
            addCriterion("max_case_number <", value, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberLessThanOrEqualTo(Integer value) {
            addCriterion("max_case_number <=", value, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberIn(List<Integer> values) {
            addCriterion("max_case_number in", values, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberNotIn(List<Integer> values) {
            addCriterion("max_case_number not in", values, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberBetween(Integer value1, Integer value2) {
            addCriterion("max_case_number between", value1, value2, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andMaxCaseNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("max_case_number not between", value1, value2, "maxCaseNumber");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitIsNull() {
            addCriterion("reply_time_limit is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitIsNotNull() {
            addCriterion("reply_time_limit is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitEqualTo(String value) {
            addCriterion("reply_time_limit =", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitNotEqualTo(String value) {
            addCriterion("reply_time_limit <>", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitGreaterThan(String value) {
            addCriterion("reply_time_limit >", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitGreaterThanOrEqualTo(String value) {
            addCriterion("reply_time_limit >=", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitLessThan(String value) {
            addCriterion("reply_time_limit <", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitLessThanOrEqualTo(String value) {
            addCriterion("reply_time_limit <=", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitLike(String value) {
            addCriterion("reply_time_limit like", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitNotLike(String value) {
            addCriterion("reply_time_limit not like", value, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitIn(List<String> values) {
            addCriterion("reply_time_limit in", values, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitNotIn(List<String> values) {
            addCriterion("reply_time_limit not in", values, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitBetween(String value1, String value2) {
            addCriterion("reply_time_limit between", value1, value2, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLimitNotBetween(String value1, String value2) {
            addCriterion("reply_time_limit not between", value1, value2, "replyTimeLimit");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(String value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(String value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(String value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(String value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(String value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(String value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLike(String value) {
            addCriterion("send_time like", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotLike(String value) {
            addCriterion("send_time not like", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<String> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<String> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(String value1, String value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(String value1, String value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
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