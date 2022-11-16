package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.List;

public class TRmpsyAssessorJoinRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyAssessorJoinRecordExample() {
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

        public Criteria andIsJoinLogoIsNull() {
            addCriterion("is_join_logo is null");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoIsNotNull() {
            addCriterion("is_join_logo is not null");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoEqualTo(String value) {
            addCriterion("is_join_logo =", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoNotEqualTo(String value) {
            addCriterion("is_join_logo <>", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoGreaterThan(String value) {
            addCriterion("is_join_logo >", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoGreaterThanOrEqualTo(String value) {
            addCriterion("is_join_logo >=", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoLessThan(String value) {
            addCriterion("is_join_logo <", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoLessThanOrEqualTo(String value) {
            addCriterion("is_join_logo <=", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoLike(String value) {
            addCriterion("is_join_logo like", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoNotLike(String value) {
            addCriterion("is_join_logo not like", value, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoIn(List<String> values) {
            addCriterion("is_join_logo in", values, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoNotIn(List<String> values) {
            addCriterion("is_join_logo not in", values, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoBetween(String value1, String value2) {
            addCriterion("is_join_logo between", value1, value2, "isJoinLogo");
            return (Criteria) this;
        }

        public Criteria andIsJoinLogoNotBetween(String value1, String value2) {
            addCriterion("is_join_logo not between", value1, value2, "isJoinLogo");
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

        public Criteria andIsJoinIsNull() {
            addCriterion("is_join is null");
            return (Criteria) this;
        }

        public Criteria andIsJoinIsNotNull() {
            addCriterion("is_join is not null");
            return (Criteria) this;
        }

        public Criteria andIsJoinEqualTo(String value) {
            addCriterion("is_join =", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinNotEqualTo(String value) {
            addCriterion("is_join <>", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinGreaterThan(String value) {
            addCriterion("is_join >", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinGreaterThanOrEqualTo(String value) {
            addCriterion("is_join >=", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinLessThan(String value) {
            addCriterion("is_join <", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinLessThanOrEqualTo(String value) {
            addCriterion("is_join <=", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinLike(String value) {
            addCriterion("is_join like", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinNotLike(String value) {
            addCriterion("is_join not like", value, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinIn(List<String> values) {
            addCriterion("is_join in", values, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinNotIn(List<String> values) {
            addCriterion("is_join not in", values, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinBetween(String value1, String value2) {
            addCriterion("is_join between", value1, value2, "isJoin");
            return (Criteria) this;
        }

        public Criteria andIsJoinNotBetween(String value1, String value2) {
            addCriterion("is_join not between", value1, value2, "isJoin");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonIsNull() {
            addCriterion("other_reason is null");
            return (Criteria) this;
        }

        public Criteria andOtherReasonIsNotNull() {
            addCriterion("other_reason is not null");
            return (Criteria) this;
        }

        public Criteria andOtherReasonEqualTo(String value) {
            addCriterion("other_reason =", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonNotEqualTo(String value) {
            addCriterion("other_reason <>", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonGreaterThan(String value) {
            addCriterion("other_reason >", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonGreaterThanOrEqualTo(String value) {
            addCriterion("other_reason >=", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonLessThan(String value) {
            addCriterion("other_reason <", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonLessThanOrEqualTo(String value) {
            addCriterion("other_reason <=", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonLike(String value) {
            addCriterion("other_reason like", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonNotLike(String value) {
            addCriterion("other_reason not like", value, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonIn(List<String> values) {
            addCriterion("other_reason in", values, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonNotIn(List<String> values) {
            addCriterion("other_reason not in", values, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonBetween(String value1, String value2) {
            addCriterion("other_reason between", value1, value2, "otherReason");
            return (Criteria) this;
        }

        public Criteria andOtherReasonNotBetween(String value1, String value2) {
            addCriterion("other_reason not between", value1, value2, "otherReason");
            return (Criteria) this;
        }

        public Criteria andIsAvoidIsNull() {
            addCriterion("is_avoid is null");
            return (Criteria) this;
        }

        public Criteria andIsAvoidIsNotNull() {
            addCriterion("is_avoid is not null");
            return (Criteria) this;
        }

        public Criteria andIsAvoidEqualTo(String value) {
            addCriterion("is_avoid =", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidNotEqualTo(String value) {
            addCriterion("is_avoid <>", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidGreaterThan(String value) {
            addCriterion("is_avoid >", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidGreaterThanOrEqualTo(String value) {
            addCriterion("is_avoid >=", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidLessThan(String value) {
            addCriterion("is_avoid <", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidLessThanOrEqualTo(String value) {
            addCriterion("is_avoid <=", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidLike(String value) {
            addCriterion("is_avoid like", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidNotLike(String value) {
            addCriterion("is_avoid not like", value, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidIn(List<String> values) {
            addCriterion("is_avoid in", values, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidNotIn(List<String> values) {
            addCriterion("is_avoid not in", values, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidBetween(String value1, String value2) {
            addCriterion("is_avoid between", value1, value2, "isAvoid");
            return (Criteria) this;
        }

        public Criteria andIsAvoidNotBetween(String value1, String value2) {
            addCriterion("is_avoid not between", value1, value2, "isAvoid");
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