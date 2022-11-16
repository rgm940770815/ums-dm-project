package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsUserOperationLogExample {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsUserOperationLogExample() {
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

        public Criteria andOperationUsernameIsNull() {
            addCriterion("operation_userName is null");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameIsNotNull() {
            addCriterion("operation_userName is not null");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameEqualTo(String value) {
            addCriterion("operation_userName =", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameNotEqualTo(String value) {
            addCriterion("operation_userName <>", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameGreaterThan(String value) {
            addCriterion("operation_userName >", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("operation_userName >=", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameLessThan(String value) {
            addCriterion("operation_userName <", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameLessThanOrEqualTo(String value) {
            addCriterion("operation_userName <=", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameLike(String value) {
            addCriterion("operation_userName like", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameNotLike(String value) {
            addCriterion("operation_userName not like", value, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameIn(List<String> values) {
            addCriterion("operation_userName in", values, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameNotIn(List<String> values) {
            addCriterion("operation_userName not in", values, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameBetween(String value1, String value2) {
            addCriterion("operation_userName between", value1, value2, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andOperationUsernameNotBetween(String value1, String value2) {
            addCriterion("operation_userName not between", value1, value2, "operationUsername");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridIsNull() {
            addCriterion("modified_userId is null");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridIsNotNull() {
            addCriterion("modified_userId is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridEqualTo(String value) {
            addCriterion("modified_userId =", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridNotEqualTo(String value) {
            addCriterion("modified_userId <>", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridGreaterThan(String value) {
            addCriterion("modified_userId >", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridGreaterThanOrEqualTo(String value) {
            addCriterion("modified_userId >=", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridLessThan(String value) {
            addCriterion("modified_userId <", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridLessThanOrEqualTo(String value) {
            addCriterion("modified_userId <=", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridLike(String value) {
            addCriterion("modified_userId like", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridNotLike(String value) {
            addCriterion("modified_userId not like", value, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridIn(List<String> values) {
            addCriterion("modified_userId in", values, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridNotIn(List<String> values) {
            addCriterion("modified_userId not in", values, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridBetween(String value1, String value2) {
            addCriterion("modified_userId between", value1, value2, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andModifiedUseridNotBetween(String value1, String value2) {
            addCriterion("modified_userId not between", value1, value2, "modifiedUserid");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeIsNull() {
            addCriterion("operation_typeCode is null");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeIsNotNull() {
            addCriterion("operation_typeCode is not null");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeEqualTo(Integer value) {
            addCriterion("operation_typeCode =", value, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeNotEqualTo(Integer value) {
            addCriterion("operation_typeCode <>", value, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeGreaterThan(Integer value) {
            addCriterion("operation_typeCode >", value, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operation_typeCode >=", value, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeLessThan(Integer value) {
            addCriterion("operation_typeCode <", value, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeLessThanOrEqualTo(Integer value) {
            addCriterion("operation_typeCode <=", value, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeIn(List<Integer> values) {
            addCriterion("operation_typeCode in", values, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeNotIn(List<Integer> values) {
            addCriterion("operation_typeCode not in", values, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeBetween(Integer value1, Integer value2) {
            addCriterion("operation_typeCode between", value1, value2, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypecodeNotBetween(Integer value1, Integer value2) {
            addCriterion("operation_typeCode not between", value1, value2, "operationTypecode");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailIsNull() {
            addCriterion("operation_typeDetail is null");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailIsNotNull() {
            addCriterion("operation_typeDetail is not null");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailEqualTo(String value) {
            addCriterion("operation_typeDetail =", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailNotEqualTo(String value) {
            addCriterion("operation_typeDetail <>", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailGreaterThan(String value) {
            addCriterion("operation_typeDetail >", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailGreaterThanOrEqualTo(String value) {
            addCriterion("operation_typeDetail >=", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailLessThan(String value) {
            addCriterion("operation_typeDetail <", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailLessThanOrEqualTo(String value) {
            addCriterion("operation_typeDetail <=", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailLike(String value) {
            addCriterion("operation_typeDetail like", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailNotLike(String value) {
            addCriterion("operation_typeDetail not like", value, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailIn(List<String> values) {
            addCriterion("operation_typeDetail in", values, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailNotIn(List<String> values) {
            addCriterion("operation_typeDetail not in", values, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailBetween(String value1, String value2) {
            addCriterion("operation_typeDetail between", value1, value2, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTypedetailNotBetween(String value1, String value2) {
            addCriterion("operation_typeDetail not between", value1, value2, "operationTypedetail");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIsNull() {
            addCriterion("operation_time is null");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIsNotNull() {
            addCriterion("operation_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperationTimeEqualTo(Date value) {
            addCriterion("operation_time =", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotEqualTo(Date value) {
            addCriterion("operation_time <>", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeGreaterThan(Date value) {
            addCriterion("operation_time >", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operation_time >=", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeLessThan(Date value) {
            addCriterion("operation_time <", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeLessThanOrEqualTo(Date value) {
            addCriterion("operation_time <=", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIn(List<Date> values) {
            addCriterion("operation_time in", values, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotIn(List<Date> values) {
            addCriterion("operation_time not in", values, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeBetween(Date value1, Date value2) {
            addCriterion("operation_time between", value1, value2, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotBetween(Date value1, Date value2) {
            addCriterion("operation_time not between", value1, value2, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationIpIsNull() {
            addCriterion("operation_ip is null");
            return (Criteria) this;
        }

        public Criteria andOperationIpIsNotNull() {
            addCriterion("operation_ip is not null");
            return (Criteria) this;
        }

        public Criteria andOperationIpEqualTo(String value) {
            addCriterion("operation_ip =", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpNotEqualTo(String value) {
            addCriterion("operation_ip <>", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpGreaterThan(String value) {
            addCriterion("operation_ip >", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpGreaterThanOrEqualTo(String value) {
            addCriterion("operation_ip >=", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpLessThan(String value) {
            addCriterion("operation_ip <", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpLessThanOrEqualTo(String value) {
            addCriterion("operation_ip <=", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpLike(String value) {
            addCriterion("operation_ip like", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpNotLike(String value) {
            addCriterion("operation_ip not like", value, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpIn(List<String> values) {
            addCriterion("operation_ip in", values, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpNotIn(List<String> values) {
            addCriterion("operation_ip not in", values, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpBetween(String value1, String value2) {
            addCriterion("operation_ip between", value1, value2, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationIpNotBetween(String value1, String value2) {
            addCriterion("operation_ip not between", value1, value2, "operationIp");
            return (Criteria) this;
        }

        public Criteria andOperationContentIsNull() {
            addCriterion("operation_content is null");
            return (Criteria) this;
        }

        public Criteria andOperationContentIsNotNull() {
            addCriterion("operation_content is not null");
            return (Criteria) this;
        }

        public Criteria andOperationContentEqualTo(String value) {
            addCriterion("operation_content =", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentNotEqualTo(String value) {
            addCriterion("operation_content <>", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentGreaterThan(String value) {
            addCriterion("operation_content >", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentGreaterThanOrEqualTo(String value) {
            addCriterion("operation_content >=", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentLessThan(String value) {
            addCriterion("operation_content <", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentLessThanOrEqualTo(String value) {
            addCriterion("operation_content <=", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentLike(String value) {
            addCriterion("operation_content like", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentNotLike(String value) {
            addCriterion("operation_content not like", value, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentIn(List<String> values) {
            addCriterion("operation_content in", values, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentNotIn(List<String> values) {
            addCriterion("operation_content not in", values, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentBetween(String value1, String value2) {
            addCriterion("operation_content between", value1, value2, "operationContent");
            return (Criteria) this;
        }

        public Criteria andOperationContentNotBetween(String value1, String value2) {
            addCriterion("operation_content not between", value1, value2, "operationContent");
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