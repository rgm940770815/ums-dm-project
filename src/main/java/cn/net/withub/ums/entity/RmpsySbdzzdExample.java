package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

public class RmpsySbdzzdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RmpsySbdzzdExample() {
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

        public Criteria andGlidIsNull() {
            addCriterion("glid is null");
            return (Criteria) this;
        }

        public Criteria andGlidIsNotNull() {
            addCriterion("glid is not null");
            return (Criteria) this;
        }

        public Criteria andGlidEqualTo(String value) {
            addCriterion("glid =", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidNotEqualTo(String value) {
            addCriterion("glid <>", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidGreaterThan(String value) {
            addCriterion("glid >", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidGreaterThanOrEqualTo(String value) {
            addCriterion("glid >=", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidLessThan(String value) {
            addCriterion("glid <", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidLessThanOrEqualTo(String value) {
            addCriterion("glid <=", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidLike(String value) {
            addCriterion("glid like", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidNotLike(String value) {
            addCriterion("glid not like", value, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidIn(List<String> values) {
            addCriterion("glid in", values, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidNotIn(List<String> values) {
            addCriterion("glid not in", values, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidBetween(String value1, String value2) {
            addCriterion("glid between", value1, value2, "glid");
            return (Criteria) this;
        }

        public Criteria andGlidNotBetween(String value1, String value2) {
            addCriterion("glid not between", value1, value2, "glid");
            return (Criteria) this;
        }

        public Criteria andOurzdIsNull() {
            addCriterion("ourZd is null");
            return (Criteria) this;
        }

        public Criteria andOurzdIsNotNull() {
            addCriterion("ourZd is not null");
            return (Criteria) this;
        }

        public Criteria andOurzdEqualTo(String value) {
            addCriterion("ourZd =", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdNotEqualTo(String value) {
            addCriterion("ourZd <>", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdGreaterThan(String value) {
            addCriterion("ourZd >", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdGreaterThanOrEqualTo(String value) {
            addCriterion("ourZd >=", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdLessThan(String value) {
            addCriterion("ourZd <", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdLessThanOrEqualTo(String value) {
            addCriterion("ourZd <=", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdLike(String value) {
            addCriterion("ourZd like", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdNotLike(String value) {
            addCriterion("ourZd not like", value, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdIn(List<String> values) {
            addCriterion("ourZd in", values, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdNotIn(List<String> values) {
            addCriterion("ourZd not in", values, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdBetween(String value1, String value2) {
            addCriterion("ourZd between", value1, value2, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdNotBetween(String value1, String value2) {
            addCriterion("ourZd not between", value1, value2, "ourzd");
            return (Criteria) this;
        }

        public Criteria andOurzdlxIsNull() {
            addCriterion("ourZdlx is null");
            return (Criteria) this;
        }

        public Criteria andOurzdlxIsNotNull() {
            addCriterion("ourZdlx is not null");
            return (Criteria) this;
        }

        public Criteria andOurzdlxEqualTo(String value) {
            addCriterion("ourZdlx =", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxNotEqualTo(String value) {
            addCriterion("ourZdlx <>", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxGreaterThan(String value) {
            addCriterion("ourZdlx >", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxGreaterThanOrEqualTo(String value) {
            addCriterion("ourZdlx >=", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxLessThan(String value) {
            addCriterion("ourZdlx <", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxLessThanOrEqualTo(String value) {
            addCriterion("ourZdlx <=", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxLike(String value) {
            addCriterion("ourZdlx like", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxNotLike(String value) {
            addCriterion("ourZdlx not like", value, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxIn(List<String> values) {
            addCriterion("ourZdlx in", values, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxNotIn(List<String> values) {
            addCriterion("ourZdlx not in", values, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxBetween(String value1, String value2) {
            addCriterion("ourZdlx between", value1, value2, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdlxNotBetween(String value1, String value2) {
            addCriterion("ourZdlx not between", value1, value2, "ourzdlx");
            return (Criteria) this;
        }

        public Criteria andOurzdmcIsNull() {
            addCriterion("ourZdmc is null");
            return (Criteria) this;
        }

        public Criteria andOurzdmcIsNotNull() {
            addCriterion("ourZdmc is not null");
            return (Criteria) this;
        }

        public Criteria andOurzdmcEqualTo(String value) {
            addCriterion("ourZdmc =", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcNotEqualTo(String value) {
            addCriterion("ourZdmc <>", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcGreaterThan(String value) {
            addCriterion("ourZdmc >", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcGreaterThanOrEqualTo(String value) {
            addCriterion("ourZdmc >=", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcLessThan(String value) {
            addCriterion("ourZdmc <", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcLessThanOrEqualTo(String value) {
            addCriterion("ourZdmc <=", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcLike(String value) {
            addCriterion("ourZdmc like", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcNotLike(String value) {
            addCriterion("ourZdmc not like", value, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcIn(List<String> values) {
            addCriterion("ourZdmc in", values, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcNotIn(List<String> values) {
            addCriterion("ourZdmc not in", values, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcBetween(String value1, String value2) {
            addCriterion("ourZdmc between", value1, value2, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andOurzdmcNotBetween(String value1, String value2) {
            addCriterion("ourZdmc not between", value1, value2, "ourzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdIsNull() {
            addCriterion("sbZd is null");
            return (Criteria) this;
        }

        public Criteria andSbzdIsNotNull() {
            addCriterion("sbZd is not null");
            return (Criteria) this;
        }

        public Criteria andSbzdEqualTo(String value) {
            addCriterion("sbZd =", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdNotEqualTo(String value) {
            addCriterion("sbZd <>", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdGreaterThan(String value) {
            addCriterion("sbZd >", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdGreaterThanOrEqualTo(String value) {
            addCriterion("sbZd >=", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdLessThan(String value) {
            addCriterion("sbZd <", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdLessThanOrEqualTo(String value) {
            addCriterion("sbZd <=", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdLike(String value) {
            addCriterion("sbZd like", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdNotLike(String value) {
            addCriterion("sbZd not like", value, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdIn(List<String> values) {
            addCriterion("sbZd in", values, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdNotIn(List<String> values) {
            addCriterion("sbZd not in", values, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdBetween(String value1, String value2) {
            addCriterion("sbZd between", value1, value2, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdNotBetween(String value1, String value2) {
            addCriterion("sbZd not between", value1, value2, "sbzd");
            return (Criteria) this;
        }

        public Criteria andSbzdlxIsNull() {
            addCriterion("sbZdlx is null");
            return (Criteria) this;
        }

        public Criteria andSbzdlxIsNotNull() {
            addCriterion("sbZdlx is not null");
            return (Criteria) this;
        }

        public Criteria andSbzdlxEqualTo(String value) {
            addCriterion("sbZdlx =", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxNotEqualTo(String value) {
            addCriterion("sbZdlx <>", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxGreaterThan(String value) {
            addCriterion("sbZdlx >", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxGreaterThanOrEqualTo(String value) {
            addCriterion("sbZdlx >=", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxLessThan(String value) {
            addCriterion("sbZdlx <", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxLessThanOrEqualTo(String value) {
            addCriterion("sbZdlx <=", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxLike(String value) {
            addCriterion("sbZdlx like", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxNotLike(String value) {
            addCriterion("sbZdlx not like", value, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxIn(List<String> values) {
            addCriterion("sbZdlx in", values, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxNotIn(List<String> values) {
            addCriterion("sbZdlx not in", values, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxBetween(String value1, String value2) {
            addCriterion("sbZdlx between", value1, value2, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdlxNotBetween(String value1, String value2) {
            addCriterion("sbZdlx not between", value1, value2, "sbzdlx");
            return (Criteria) this;
        }

        public Criteria andSbzdmcIsNull() {
            addCriterion("sbZdmc is null");
            return (Criteria) this;
        }

        public Criteria andSbzdmcIsNotNull() {
            addCriterion("sbZdmc is not null");
            return (Criteria) this;
        }

        public Criteria andSbzdmcEqualTo(String value) {
            addCriterion("sbZdmc =", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcNotEqualTo(String value) {
            addCriterion("sbZdmc <>", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcGreaterThan(String value) {
            addCriterion("sbZdmc >", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcGreaterThanOrEqualTo(String value) {
            addCriterion("sbZdmc >=", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcLessThan(String value) {
            addCriterion("sbZdmc <", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcLessThanOrEqualTo(String value) {
            addCriterion("sbZdmc <=", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcLike(String value) {
            addCriterion("sbZdmc like", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcNotLike(String value) {
            addCriterion("sbZdmc not like", value, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcIn(List<String> values) {
            addCriterion("sbZdmc in", values, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcNotIn(List<String> values) {
            addCriterion("sbZdmc not in", values, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcBetween(String value1, String value2) {
            addCriterion("sbZdmc between", value1, value2, "sbzdmc");
            return (Criteria) this;
        }

        public Criteria andSbzdmcNotBetween(String value1, String value2) {
            addCriterion("sbZdmc not between", value1, value2, "sbzdmc");
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