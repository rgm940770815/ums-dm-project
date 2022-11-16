package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsSubitemRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsSubitemRuleExample() {
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

        public Criteria andTableKeyIsNull() {
            addCriterion("table_key is null");
            return (Criteria) this;
        }

        public Criteria andTableKeyIsNotNull() {
            addCriterion("table_key is not null");
            return (Criteria) this;
        }

        public Criteria andTableKeyEqualTo(String value) {
            addCriterion("table_key =", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotEqualTo(String value) {
            addCriterion("table_key <>", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyGreaterThan(String value) {
            addCriterion("table_key >", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyGreaterThanOrEqualTo(String value) {
            addCriterion("table_key >=", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLessThan(String value) {
            addCriterion("table_key <", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLessThanOrEqualTo(String value) {
            addCriterion("table_key <=", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyLike(String value) {
            addCriterion("table_key like", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotLike(String value) {
            addCriterion("table_key not like", value, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyIn(List<String> values) {
            addCriterion("table_key in", values, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotIn(List<String> values) {
            addCriterion("table_key not in", values, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyBetween(String value1, String value2) {
            addCriterion("table_key between", value1, value2, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableKeyNotBetween(String value1, String value2) {
            addCriterion("table_key not between", value1, value2, "tableKey");
            return (Criteria) this;
        }

        public Criteria andTableTextIsNull() {
            addCriterion("table_text is null");
            return (Criteria) this;
        }

        public Criteria andTableTextIsNotNull() {
            addCriterion("table_text is not null");
            return (Criteria) this;
        }

        public Criteria andTableTextEqualTo(String value) {
            addCriterion("table_text =", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextNotEqualTo(String value) {
            addCriterion("table_text <>", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextGreaterThan(String value) {
            addCriterion("table_text >", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextGreaterThanOrEqualTo(String value) {
            addCriterion("table_text >=", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextLessThan(String value) {
            addCriterion("table_text <", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextLessThanOrEqualTo(String value) {
            addCriterion("table_text <=", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextLike(String value) {
            addCriterion("table_text like", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextNotLike(String value) {
            addCriterion("table_text not like", value, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextIn(List<String> values) {
            addCriterion("table_text in", values, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextNotIn(List<String> values) {
            addCriterion("table_text not in", values, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextBetween(String value1, String value2) {
            addCriterion("table_text between", value1, value2, "tableText");
            return (Criteria) this;
        }

        public Criteria andTableTextNotBetween(String value1, String value2) {
            addCriterion("table_text not between", value1, value2, "tableText");
            return (Criteria) this;
        }

        public Criteria andFilterRuleIsNull() {
            addCriterion("filter_rule is null");
            return (Criteria) this;
        }

        public Criteria andFilterRuleIsNotNull() {
            addCriterion("filter_rule is not null");
            return (Criteria) this;
        }

        public Criteria andFilterRuleEqualTo(String value) {
            addCriterion("filter_rule =", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleNotEqualTo(String value) {
            addCriterion("filter_rule <>", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleGreaterThan(String value) {
            addCriterion("filter_rule >", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleGreaterThanOrEqualTo(String value) {
            addCriterion("filter_rule >=", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleLessThan(String value) {
            addCriterion("filter_rule <", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleLessThanOrEqualTo(String value) {
            addCriterion("filter_rule <=", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleLike(String value) {
            addCriterion("filter_rule like", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleNotLike(String value) {
            addCriterion("filter_rule not like", value, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleIn(List<String> values) {
            addCriterion("filter_rule in", values, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleNotIn(List<String> values) {
            addCriterion("filter_rule not in", values, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleBetween(String value1, String value2) {
            addCriterion("filter_rule between", value1, value2, "filterRule");
            return (Criteria) this;
        }

        public Criteria andFilterRuleNotBetween(String value1, String value2) {
            addCriterion("filter_rule not between", value1, value2, "filterRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleIsNull() {
            addCriterion("insert_rule is null");
            return (Criteria) this;
        }

        public Criteria andInsertRuleIsNotNull() {
            addCriterion("insert_rule is not null");
            return (Criteria) this;
        }

        public Criteria andInsertRuleEqualTo(String value) {
            addCriterion("insert_rule =", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleNotEqualTo(String value) {
            addCriterion("insert_rule <>", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleGreaterThan(String value) {
            addCriterion("insert_rule >", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleGreaterThanOrEqualTo(String value) {
            addCriterion("insert_rule >=", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleLessThan(String value) {
            addCriterion("insert_rule <", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleLessThanOrEqualTo(String value) {
            addCriterion("insert_rule <=", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleLike(String value) {
            addCriterion("insert_rule like", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleNotLike(String value) {
            addCriterion("insert_rule not like", value, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleIn(List<String> values) {
            addCriterion("insert_rule in", values, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleNotIn(List<String> values) {
            addCriterion("insert_rule not in", values, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleBetween(String value1, String value2) {
            addCriterion("insert_rule between", value1, value2, "insertRule");
            return (Criteria) this;
        }

        public Criteria andInsertRuleNotBetween(String value1, String value2) {
            addCriterion("insert_rule not between", value1, value2, "insertRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleIsNull() {
            addCriterion("update_rule is null");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleIsNotNull() {
            addCriterion("update_rule is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleEqualTo(String value) {
            addCriterion("update_rule =", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleNotEqualTo(String value) {
            addCriterion("update_rule <>", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleGreaterThan(String value) {
            addCriterion("update_rule >", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleGreaterThanOrEqualTo(String value) {
            addCriterion("update_rule >=", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleLessThan(String value) {
            addCriterion("update_rule <", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleLessThanOrEqualTo(String value) {
            addCriterion("update_rule <=", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleLike(String value) {
            addCriterion("update_rule like", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleNotLike(String value) {
            addCriterion("update_rule not like", value, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleIn(List<String> values) {
            addCriterion("update_rule in", values, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleNotIn(List<String> values) {
            addCriterion("update_rule not in", values, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleBetween(String value1, String value2) {
            addCriterion("update_rule between", value1, value2, "updateRule");
            return (Criteria) this;
        }

        public Criteria andUpdateRuleNotBetween(String value1, String value2) {
            addCriterion("update_rule not between", value1, value2, "updateRule");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("enable is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("enable is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Boolean value) {
            addCriterion("enable =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Boolean value) {
            addCriterion("enable <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Boolean value) {
            addCriterion("enable >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("enable >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Boolean value) {
            addCriterion("enable <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("enable <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Boolean> values) {
            addCriterion("enable in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Boolean> values) {
            addCriterion("enable not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("enable between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("enable not between", value1, value2, "enable");
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

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
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