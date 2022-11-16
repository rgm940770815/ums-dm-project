package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsLevelInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsLevelInfoCriteria() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNull() {
            addCriterion("sort_no is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("sort_no is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(Integer value) {
            addCriterion("sort_no =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(Integer value) {
            addCriterion("sort_no <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(Integer value) {
            addCriterion("sort_no >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_no >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(Integer value) {
            addCriterion("sort_no <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(Integer value) {
            addCriterion("sort_no <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("sort_no in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("sort_no not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(Integer value1, Integer value2) {
            addCriterion("sort_no between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_no not between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andOldIdIsNull() {
            addCriterion("old_id is null");
            return (Criteria) this;
        }

        public Criteria andOldIdIsNotNull() {
            addCriterion("old_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldIdEqualTo(Long value) {
            addCriterion("old_id =", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotEqualTo(Long value) {
            addCriterion("old_id <>", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThan(Long value) {
            addCriterion("old_id >", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThanOrEqualTo(Long value) {
            addCriterion("old_id >=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThan(Long value) {
            addCriterion("old_id <", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThanOrEqualTo(Long value) {
            addCriterion("old_id <=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdIn(List<Long> values) {
            addCriterion("old_id in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotIn(List<Long> values) {
            addCriterion("old_id not in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdBetween(Long value1, Long value2) {
            addCriterion("old_id between", value1, value2, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotBetween(Long value1, Long value2) {
            addCriterion("old_id not between", value1, value2, "oldId");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNull() {
            addCriterion("user_no is null");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNotNull() {
            addCriterion("user_no is not null");
            return (Criteria) this;
        }

        public Criteria andUserNoEqualTo(Integer value) {
            addCriterion("user_no =", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotEqualTo(Integer value) {
            addCriterion("user_no <>", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThan(Integer value) {
            addCriterion("user_no >", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_no >=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThan(Integer value) {
            addCriterion("user_no <", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThanOrEqualTo(Integer value) {
            addCriterion("user_no <=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIn(List<Integer> values) {
            addCriterion("user_no in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotIn(List<Integer> values) {
            addCriterion("user_no not in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoBetween(Integer value1, Integer value2) {
            addCriterion("user_no between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotBetween(Integer value1, Integer value2) {
            addCriterion("user_no not between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoIsNull() {
            addCriterion("court_no is null");
            return (Criteria) this;
        }

        public Criteria andCourtNoIsNotNull() {
            addCriterion("court_no is not null");
            return (Criteria) this;
        }

        public Criteria andCourtNoEqualTo(Integer value) {
            addCriterion("court_no =", value, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoNotEqualTo(Integer value) {
            addCriterion("court_no <>", value, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoGreaterThan(Integer value) {
            addCriterion("court_no >", value, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_no >=", value, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoLessThan(Integer value) {
            addCriterion("court_no <", value, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoLessThanOrEqualTo(Integer value) {
            addCriterion("court_no <=", value, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoIn(List<Integer> values) {
            addCriterion("court_no in", values, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoNotIn(List<Integer> values) {
            addCriterion("court_no not in", values, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoBetween(Integer value1, Integer value2) {
            addCriterion("court_no between", value1, value2, "courtNo");
            return (Criteria) this;
        }

        public Criteria andCourtNoNotBetween(Integer value1, Integer value2) {
            addCriterion("court_no not between", value1, value2, "courtNo");
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

        public Criteria andNLevelTypeIsNull() {
            addCriterion("n_level_type is null");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeIsNotNull() {
            addCriterion("n_level_type is not null");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeEqualTo(Integer value) {
            addCriterion("n_level_type =", value, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeNotEqualTo(Integer value) {
            addCriterion("n_level_type <>", value, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeGreaterThan(Integer value) {
            addCriterion("n_level_type >", value, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("n_level_type >=", value, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeLessThan(Integer value) {
            addCriterion("n_level_type <", value, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("n_level_type <=", value, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeIn(List<Integer> values) {
            addCriterion("n_level_type in", values, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeNotIn(List<Integer> values) {
            addCriterion("n_level_type not in", values, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeBetween(Integer value1, Integer value2) {
            addCriterion("n_level_type between", value1, value2, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andNLevelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("n_level_type not between", value1, value2, "nLevelType");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelIsNull() {
            addCriterion("judge_level is null");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelIsNotNull() {
            addCriterion("judge_level is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelEqualTo(String value) {
            addCriterion("judge_level =", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelNotEqualTo(String value) {
            addCriterion("judge_level <>", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelGreaterThan(String value) {
            addCriterion("judge_level >", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelGreaterThanOrEqualTo(String value) {
            addCriterion("judge_level >=", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelLessThan(String value) {
            addCriterion("judge_level <", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelLessThanOrEqualTo(String value) {
            addCriterion("judge_level <=", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelLike(String value) {
            addCriterion("judge_level like", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelNotLike(String value) {
            addCriterion("judge_level not like", value, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelIn(List<String> values) {
            addCriterion("judge_level in", values, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelNotIn(List<String> values) {
            addCriterion("judge_level not in", values, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelBetween(String value1, String value2) {
            addCriterion("judge_level between", value1, value2, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andJudgeLevelNotBetween(String value1, String value2) {
            addCriterion("judge_level not between", value1, value2, "judgeLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelIsNull() {
            addCriterion("marshal_level is null");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelIsNotNull() {
            addCriterion("marshal_level is not null");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelEqualTo(String value) {
            addCriterion("marshal_level =", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelNotEqualTo(String value) {
            addCriterion("marshal_level <>", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelGreaterThan(String value) {
            addCriterion("marshal_level >", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelGreaterThanOrEqualTo(String value) {
            addCriterion("marshal_level >=", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelLessThan(String value) {
            addCriterion("marshal_level <", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelLessThanOrEqualTo(String value) {
            addCriterion("marshal_level <=", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelLike(String value) {
            addCriterion("marshal_level like", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelNotLike(String value) {
            addCriterion("marshal_level not like", value, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelIn(List<String> values) {
            addCriterion("marshal_level in", values, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelNotIn(List<String> values) {
            addCriterion("marshal_level not in", values, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelBetween(String value1, String value2) {
            addCriterion("marshal_level between", value1, value2, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andMarshalLevelNotBetween(String value1, String value2) {
            addCriterion("marshal_level not between", value1, value2, "marshalLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelIsNull() {
            addCriterion("helper_level is null");
            return (Criteria) this;
        }

        public Criteria andHelperLevelIsNotNull() {
            addCriterion("helper_level is not null");
            return (Criteria) this;
        }

        public Criteria andHelperLevelEqualTo(String value) {
            addCriterion("helper_level =", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelNotEqualTo(String value) {
            addCriterion("helper_level <>", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelGreaterThan(String value) {
            addCriterion("helper_level >", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelGreaterThanOrEqualTo(String value) {
            addCriterion("helper_level >=", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelLessThan(String value) {
            addCriterion("helper_level <", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelLessThanOrEqualTo(String value) {
            addCriterion("helper_level <=", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelLike(String value) {
            addCriterion("helper_level like", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelNotLike(String value) {
            addCriterion("helper_level not like", value, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelIn(List<String> values) {
            addCriterion("helper_level in", values, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelNotIn(List<String> values) {
            addCriterion("helper_level not in", values, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelBetween(String value1, String value2) {
            addCriterion("helper_level between", value1, value2, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andHelperLevelNotBetween(String value1, String value2) {
            addCriterion("helper_level not between", value1, value2, "helperLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelIsNull() {
            addCriterion("clerk_level is null");
            return (Criteria) this;
        }

        public Criteria andClerkLevelIsNotNull() {
            addCriterion("clerk_level is not null");
            return (Criteria) this;
        }

        public Criteria andClerkLevelEqualTo(String value) {
            addCriterion("clerk_level =", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelNotEqualTo(String value) {
            addCriterion("clerk_level <>", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelGreaterThan(String value) {
            addCriterion("clerk_level >", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelGreaterThanOrEqualTo(String value) {
            addCriterion("clerk_level >=", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelLessThan(String value) {
            addCriterion("clerk_level <", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelLessThanOrEqualTo(String value) {
            addCriterion("clerk_level <=", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelLike(String value) {
            addCriterion("clerk_level like", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelNotLike(String value) {
            addCriterion("clerk_level not like", value, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelIn(List<String> values) {
            addCriterion("clerk_level in", values, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelNotIn(List<String> values) {
            addCriterion("clerk_level not in", values, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelBetween(String value1, String value2) {
            addCriterion("clerk_level between", value1, value2, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andClerkLevelNotBetween(String value1, String value2) {
            addCriterion("clerk_level not between", value1, value2, "clerkLevel");
            return (Criteria) this;
        }

        public Criteria andDStartDateIsNull() {
            addCriterion("d_start_date is null");
            return (Criteria) this;
        }

        public Criteria andDStartDateIsNotNull() {
            addCriterion("d_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andDStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("d_start_date =", value, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("d_start_date <>", value, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("d_start_date >", value, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("d_start_date >=", value, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateLessThan(Date value) {
            addCriterionForJDBCDate("d_start_date <", value, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("d_start_date <=", value, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("d_start_date in", values, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("d_start_date not in", values, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("d_start_date between", value1, value2, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andDStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("d_start_date not between", value1, value2, "dStartDate");
            return (Criteria) this;
        }

        public Criteria andCCertNoIsNull() {
            addCriterion("c_cert_no is null");
            return (Criteria) this;
        }

        public Criteria andCCertNoIsNotNull() {
            addCriterion("c_cert_no is not null");
            return (Criteria) this;
        }

        public Criteria andCCertNoEqualTo(String value) {
            addCriterion("c_cert_no =", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoNotEqualTo(String value) {
            addCriterion("c_cert_no <>", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoGreaterThan(String value) {
            addCriterion("c_cert_no >", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoGreaterThanOrEqualTo(String value) {
            addCriterion("c_cert_no >=", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoLessThan(String value) {
            addCriterion("c_cert_no <", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoLessThanOrEqualTo(String value) {
            addCriterion("c_cert_no <=", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoLike(String value) {
            addCriterion("c_cert_no like", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoNotLike(String value) {
            addCriterion("c_cert_no not like", value, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoIn(List<String> values) {
            addCriterion("c_cert_no in", values, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoNotIn(List<String> values) {
            addCriterion("c_cert_no not in", values, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoBetween(String value1, String value2) {
            addCriterion("c_cert_no between", value1, value2, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andCCertNoNotBetween(String value1, String value2) {
            addCriterion("c_cert_no not between", value1, value2, "cCertNo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoIsNull() {
            addCriterion("n_present_info is null");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoIsNotNull() {
            addCriterion("n_present_info is not null");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoEqualTo(Integer value) {
            addCriterion("n_present_info =", value, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoNotEqualTo(Integer value) {
            addCriterion("n_present_info <>", value, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoGreaterThan(Integer value) {
            addCriterion("n_present_info >", value, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoGreaterThanOrEqualTo(Integer value) {
            addCriterion("n_present_info >=", value, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoLessThan(Integer value) {
            addCriterion("n_present_info <", value, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoLessThanOrEqualTo(Integer value) {
            addCriterion("n_present_info <=", value, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoIn(List<Integer> values) {
            addCriterion("n_present_info in", values, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoNotIn(List<Integer> values) {
            addCriterion("n_present_info not in", values, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoBetween(Integer value1, Integer value2) {
            addCriterion("n_present_info between", value1, value2, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andNPresentInfoNotBetween(Integer value1, Integer value2) {
            addCriterion("n_present_info not between", value1, value2, "nPresentInfo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoIsNull() {
            addCriterion("c_approval_doc_no is null");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoIsNotNull() {
            addCriterion("c_approval_doc_no is not null");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoEqualTo(String value) {
            addCriterion("c_approval_doc_no =", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoNotEqualTo(String value) {
            addCriterion("c_approval_doc_no <>", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoGreaterThan(String value) {
            addCriterion("c_approval_doc_no >", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoGreaterThanOrEqualTo(String value) {
            addCriterion("c_approval_doc_no >=", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoLessThan(String value) {
            addCriterion("c_approval_doc_no <", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoLessThanOrEqualTo(String value) {
            addCriterion("c_approval_doc_no <=", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoLike(String value) {
            addCriterion("c_approval_doc_no like", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoNotLike(String value) {
            addCriterion("c_approval_doc_no not like", value, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoIn(List<String> values) {
            addCriterion("c_approval_doc_no in", values, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoNotIn(List<String> values) {
            addCriterion("c_approval_doc_no not in", values, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoBetween(String value1, String value2) {
            addCriterion("c_approval_doc_no between", value1, value2, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andCApprovalDocNoNotBetween(String value1, String value2) {
            addCriterion("c_approval_doc_no not between", value1, value2, "cApprovalDocNo");
            return (Criteria) this;
        }

        public Criteria andNAltTypeIsNull() {
            addCriterion("n_alt_type is null");
            return (Criteria) this;
        }

        public Criteria andNAltTypeIsNotNull() {
            addCriterion("n_alt_type is not null");
            return (Criteria) this;
        }

        public Criteria andNAltTypeEqualTo(Integer value) {
            addCriterion("n_alt_type =", value, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeNotEqualTo(Integer value) {
            addCriterion("n_alt_type <>", value, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeGreaterThan(Integer value) {
            addCriterion("n_alt_type >", value, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("n_alt_type >=", value, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeLessThan(Integer value) {
            addCriterion("n_alt_type <", value, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeLessThanOrEqualTo(Integer value) {
            addCriterion("n_alt_type <=", value, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeIn(List<Integer> values) {
            addCriterion("n_alt_type in", values, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeNotIn(List<Integer> values) {
            addCriterion("n_alt_type not in", values, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeBetween(Integer value1, Integer value2) {
            addCriterion("n_alt_type between", value1, value2, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("n_alt_type not between", value1, value2, "nAltType");
            return (Criteria) this;
        }

        public Criteria andNAltReasonIsNull() {
            addCriterion("n_alt_reason is null");
            return (Criteria) this;
        }

        public Criteria andNAltReasonIsNotNull() {
            addCriterion("n_alt_reason is not null");
            return (Criteria) this;
        }

        public Criteria andNAltReasonEqualTo(Integer value) {
            addCriterion("n_alt_reason =", value, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonNotEqualTo(Integer value) {
            addCriterion("n_alt_reason <>", value, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonGreaterThan(Integer value) {
            addCriterion("n_alt_reason >", value, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("n_alt_reason >=", value, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonLessThan(Integer value) {
            addCriterion("n_alt_reason <", value, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonLessThanOrEqualTo(Integer value) {
            addCriterion("n_alt_reason <=", value, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonIn(List<Integer> values) {
            addCriterion("n_alt_reason in", values, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonNotIn(List<Integer> values) {
            addCriterion("n_alt_reason not in", values, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonBetween(Integer value1, Integer value2) {
            addCriterion("n_alt_reason between", value1, value2, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andNAltReasonNotBetween(Integer value1, Integer value2) {
            addCriterion("n_alt_reason not between", value1, value2, "nAltReason");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitIsNull() {
            addCriterion("c_approval_unit is null");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitIsNotNull() {
            addCriterion("c_approval_unit is not null");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitEqualTo(String value) {
            addCriterion("c_approval_unit =", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitNotEqualTo(String value) {
            addCriterion("c_approval_unit <>", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitGreaterThan(String value) {
            addCriterion("c_approval_unit >", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitGreaterThanOrEqualTo(String value) {
            addCriterion("c_approval_unit >=", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitLessThan(String value) {
            addCriterion("c_approval_unit <", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitLessThanOrEqualTo(String value) {
            addCriterion("c_approval_unit <=", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitLike(String value) {
            addCriterion("c_approval_unit like", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitNotLike(String value) {
            addCriterion("c_approval_unit not like", value, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitIn(List<String> values) {
            addCriterion("c_approval_unit in", values, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitNotIn(List<String> values) {
            addCriterion("c_approval_unit not in", values, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitBetween(String value1, String value2) {
            addCriterion("c_approval_unit between", value1, value2, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCApprovalUnitNotBetween(String value1, String value2) {
            addCriterion("c_approval_unit not between", value1, value2, "cApprovalUnit");
            return (Criteria) this;
        }

        public Criteria andCAltBasisIsNull() {
            addCriterion("c_alt_basis is null");
            return (Criteria) this;
        }

        public Criteria andCAltBasisIsNotNull() {
            addCriterion("c_alt_basis is not null");
            return (Criteria) this;
        }

        public Criteria andCAltBasisEqualTo(String value) {
            addCriterion("c_alt_basis =", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisNotEqualTo(String value) {
            addCriterion("c_alt_basis <>", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisGreaterThan(String value) {
            addCriterion("c_alt_basis >", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisGreaterThanOrEqualTo(String value) {
            addCriterion("c_alt_basis >=", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisLessThan(String value) {
            addCriterion("c_alt_basis <", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisLessThanOrEqualTo(String value) {
            addCriterion("c_alt_basis <=", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisLike(String value) {
            addCriterion("c_alt_basis like", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisNotLike(String value) {
            addCriterion("c_alt_basis not like", value, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisIn(List<String> values) {
            addCriterion("c_alt_basis in", values, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisNotIn(List<String> values) {
            addCriterion("c_alt_basis not in", values, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisBetween(String value1, String value2) {
            addCriterion("c_alt_basis between", value1, value2, "cAltBasis");
            return (Criteria) this;
        }

        public Criteria andCAltBasisNotBetween(String value1, String value2) {
            addCriterion("c_alt_basis not between", value1, value2, "cAltBasis");
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

        public Criteria andIsYefgIsNull() {
            addCriterion("is_yefg is null");
            return (Criteria) this;
        }

        public Criteria andIsYefgIsNotNull() {
            addCriterion("is_yefg is not null");
            return (Criteria) this;
        }

        public Criteria andIsYefgEqualTo(String value) {
            addCriterion("is_yefg =", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgNotEqualTo(String value) {
            addCriterion("is_yefg <>", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgGreaterThan(String value) {
            addCriterion("is_yefg >", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgGreaterThanOrEqualTo(String value) {
            addCriterion("is_yefg >=", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgLessThan(String value) {
            addCriterion("is_yefg <", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgLessThanOrEqualTo(String value) {
            addCriterion("is_yefg <=", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgLike(String value) {
            addCriterion("is_yefg like", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgNotLike(String value) {
            addCriterion("is_yefg not like", value, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgIn(List<String> values) {
            addCriterion("is_yefg in", values, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgNotIn(List<String> values) {
            addCriterion("is_yefg not in", values, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgBetween(String value1, String value2) {
            addCriterion("is_yefg between", value1, value2, "isYefg");
            return (Criteria) this;
        }

        public Criteria andIsYefgNotBetween(String value1, String value2) {
            addCriterion("is_yefg not between", value1, value2, "isYefg");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeIsNull() {
            addCriterion("yefg_start_time is null");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeIsNotNull() {
            addCriterion("yefg_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_start_time =", value, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_start_time <>", value, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("yefg_start_time >", value, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_start_time >=", value, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("yefg_start_time <", value, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_start_time <=", value, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("yefg_start_time in", values, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("yefg_start_time not in", values, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yefg_start_time between", value1, value2, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yefg_start_time not between", value1, value2, "yefgStartTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeIsNull() {
            addCriterion("yefg_end_time is null");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeIsNotNull() {
            addCriterion("yefg_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_end_time =", value, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_end_time <>", value, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("yefg_end_time >", value, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_end_time >=", value, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("yefg_end_time <", value, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("yefg_end_time <=", value, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("yefg_end_time in", values, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("yefg_end_time not in", values, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yefg_end_time between", value1, value2, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("yefg_end_time not between", value1, value2, "yefgEndTime");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonIsNull() {
            addCriterion("yefg_end_reason is null");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonIsNotNull() {
            addCriterion("yefg_end_reason is not null");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonEqualTo(String value) {
            addCriterion("yefg_end_reason =", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonNotEqualTo(String value) {
            addCriterion("yefg_end_reason <>", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonGreaterThan(String value) {
            addCriterion("yefg_end_reason >", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonGreaterThanOrEqualTo(String value) {
            addCriterion("yefg_end_reason >=", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonLessThan(String value) {
            addCriterion("yefg_end_reason <", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonLessThanOrEqualTo(String value) {
            addCriterion("yefg_end_reason <=", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonLike(String value) {
            addCriterion("yefg_end_reason like", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonNotLike(String value) {
            addCriterion("yefg_end_reason not like", value, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonIn(List<String> values) {
            addCriterion("yefg_end_reason in", values, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonNotIn(List<String> values) {
            addCriterion("yefg_end_reason not in", values, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonBetween(String value1, String value2) {
            addCriterion("yefg_end_reason between", value1, value2, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andYefgEndReasonNotBetween(String value1, String value2) {
            addCriterion("yefg_end_reason not between", value1, value2, "yefgEndReason");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceIsNull() {
            addCriterion("enter_job_sequence is null");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceIsNotNull() {
            addCriterion("enter_job_sequence is not null");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceEqualTo(Integer value) {
            addCriterion("enter_job_sequence =", value, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceNotEqualTo(Integer value) {
            addCriterion("enter_job_sequence <>", value, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceGreaterThan(Integer value) {
            addCriterion("enter_job_sequence >", value, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("enter_job_sequence >=", value, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceLessThan(Integer value) {
            addCriterion("enter_job_sequence <", value, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceLessThanOrEqualTo(Integer value) {
            addCriterion("enter_job_sequence <=", value, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceIn(List<Integer> values) {
            addCriterion("enter_job_sequence in", values, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceNotIn(List<Integer> values) {
            addCriterion("enter_job_sequence not in", values, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceBetween(Integer value1, Integer value2) {
            addCriterion("enter_job_sequence between", value1, value2, "enterJobSequence");
            return (Criteria) this;
        }

        public Criteria andEnterJobSequenceNotBetween(Integer value1, Integer value2) {
            addCriterion("enter_job_sequence not between", value1, value2, "enterJobSequence");
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