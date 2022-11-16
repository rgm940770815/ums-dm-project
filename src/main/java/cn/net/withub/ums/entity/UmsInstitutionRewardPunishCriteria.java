package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsInstitutionRewardPunishCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsInstitutionRewardPunishCriteria() {
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

        public Criteria andInstitutionIdIsNull() {
            addCriterion("institution_id is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdIsNotNull() {
            addCriterion("institution_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdEqualTo(String value) {
            addCriterion("institution_id =", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdNotEqualTo(String value) {
            addCriterion("institution_id <>", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdGreaterThan(String value) {
            addCriterion("institution_id >", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdGreaterThanOrEqualTo(String value) {
            addCriterion("institution_id >=", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdLessThan(String value) {
            addCriterion("institution_id <", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdLessThanOrEqualTo(String value) {
            addCriterion("institution_id <=", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdLike(String value) {
            addCriterion("institution_id like", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdNotLike(String value) {
            addCriterion("institution_id not like", value, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdIn(List<String> values) {
            addCriterion("institution_id in", values, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdNotIn(List<String> values) {
            addCriterion("institution_id not in", values, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdBetween(String value1, String value2) {
            addCriterion("institution_id between", value1, value2, "institutionId");
            return (Criteria) this;
        }

        public Criteria andInstitutionIdNotBetween(String value1, String value2) {
            addCriterion("institution_id not between", value1, value2, "institutionId");
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

        public Criteria andCourtStdNoIsNull() {
            addCriterion("court_std_no is null");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoIsNotNull() {
            addCriterion("court_std_no is not null");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoEqualTo(Integer value) {
            addCriterion("court_std_no =", value, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoNotEqualTo(Integer value) {
            addCriterion("court_std_no <>", value, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoGreaterThan(Integer value) {
            addCriterion("court_std_no >", value, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_std_no >=", value, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoLessThan(Integer value) {
            addCriterion("court_std_no <", value, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoLessThanOrEqualTo(Integer value) {
            addCriterion("court_std_no <=", value, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoIn(List<Integer> values) {
            addCriterion("court_std_no in", values, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoNotIn(List<Integer> values) {
            addCriterion("court_std_no not in", values, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoBetween(Integer value1, Integer value2) {
            addCriterion("court_std_no between", value1, value2, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andCourtStdNoNotBetween(Integer value1, Integer value2) {
            addCriterion("court_std_no not between", value1, value2, "courtStdNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoIsNull() {
            addCriterion("dept_no is null");
            return (Criteria) this;
        }

        public Criteria andDeptNoIsNotNull() {
            addCriterion("dept_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNoEqualTo(Integer value) {
            addCriterion("dept_no =", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoNotEqualTo(Integer value) {
            addCriterion("dept_no <>", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoGreaterThan(Integer value) {
            addCriterion("dept_no >", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("dept_no >=", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoLessThan(Integer value) {
            addCriterion("dept_no <", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoLessThanOrEqualTo(Integer value) {
            addCriterion("dept_no <=", value, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoIn(List<Integer> values) {
            addCriterion("dept_no in", values, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoNotIn(List<Integer> values) {
            addCriterion("dept_no not in", values, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoBetween(Integer value1, Integer value2) {
            addCriterion("dept_no between", value1, value2, "deptNo");
            return (Criteria) this;
        }

        public Criteria andDeptNoNotBetween(Integer value1, Integer value2) {
            addCriterion("dept_no not between", value1, value2, "deptNo");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeIsNull() {
            addCriterion("grant_unit_code is null");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeIsNotNull() {
            addCriterion("grant_unit_code is not null");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeEqualTo(String value) {
            addCriterion("grant_unit_code =", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeNotEqualTo(String value) {
            addCriterion("grant_unit_code <>", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeGreaterThan(String value) {
            addCriterion("grant_unit_code >", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeGreaterThanOrEqualTo(String value) {
            addCriterion("grant_unit_code >=", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeLessThan(String value) {
            addCriterion("grant_unit_code <", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeLessThanOrEqualTo(String value) {
            addCriterion("grant_unit_code <=", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeLike(String value) {
            addCriterion("grant_unit_code like", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeNotLike(String value) {
            addCriterion("grant_unit_code not like", value, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeIn(List<String> values) {
            addCriterion("grant_unit_code in", values, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeNotIn(List<String> values) {
            addCriterion("grant_unit_code not in", values, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeBetween(String value1, String value2) {
            addCriterion("grant_unit_code between", value1, value2, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitCodeNotBetween(String value1, String value2) {
            addCriterion("grant_unit_code not between", value1, value2, "grantUnitCode");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendIsNull() {
            addCriterion("grant_unit_name_append is null");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendIsNotNull() {
            addCriterion("grant_unit_name_append is not null");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendEqualTo(String value) {
            addCriterion("grant_unit_name_append =", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendNotEqualTo(String value) {
            addCriterion("grant_unit_name_append <>", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendGreaterThan(String value) {
            addCriterion("grant_unit_name_append >", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendGreaterThanOrEqualTo(String value) {
            addCriterion("grant_unit_name_append >=", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendLessThan(String value) {
            addCriterion("grant_unit_name_append <", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendLessThanOrEqualTo(String value) {
            addCriterion("grant_unit_name_append <=", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendLike(String value) {
            addCriterion("grant_unit_name_append like", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendNotLike(String value) {
            addCriterion("grant_unit_name_append not like", value, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendIn(List<String> values) {
            addCriterion("grant_unit_name_append in", values, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendNotIn(List<String> values) {
            addCriterion("grant_unit_name_append not in", values, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendBetween(String value1, String value2) {
            addCriterion("grant_unit_name_append between", value1, value2, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andGrantUnitNameAppendNotBetween(String value1, String value2) {
            addCriterion("grant_unit_name_append not between", value1, value2, "grantUnitNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeIsNull() {
            addCriterion("reward_type_code is null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeIsNotNull() {
            addCriterion("reward_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeEqualTo(String value) {
            addCriterion("reward_type_code =", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeNotEqualTo(String value) {
            addCriterion("reward_type_code <>", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeGreaterThan(String value) {
            addCriterion("reward_type_code >", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("reward_type_code >=", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeLessThan(String value) {
            addCriterion("reward_type_code <", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("reward_type_code <=", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeLike(String value) {
            addCriterion("reward_type_code like", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeNotLike(String value) {
            addCriterion("reward_type_code not like", value, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeIn(List<String> values) {
            addCriterion("reward_type_code in", values, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeNotIn(List<String> values) {
            addCriterion("reward_type_code not in", values, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeBetween(String value1, String value2) {
            addCriterion("reward_type_code between", value1, value2, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardTypeCodeNotBetween(String value1, String value2) {
            addCriterion("reward_type_code not between", value1, value2, "rewardTypeCode");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendIsNull() {
            addCriterion("reward_name_append is null");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendIsNotNull() {
            addCriterion("reward_name_append is not null");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendEqualTo(String value) {
            addCriterion("reward_name_append =", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendNotEqualTo(String value) {
            addCriterion("reward_name_append <>", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendGreaterThan(String value) {
            addCriterion("reward_name_append >", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendGreaterThanOrEqualTo(String value) {
            addCriterion("reward_name_append >=", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendLessThan(String value) {
            addCriterion("reward_name_append <", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendLessThanOrEqualTo(String value) {
            addCriterion("reward_name_append <=", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendLike(String value) {
            addCriterion("reward_name_append like", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendNotLike(String value) {
            addCriterion("reward_name_append not like", value, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendIn(List<String> values) {
            addCriterion("reward_name_append in", values, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendNotIn(List<String> values) {
            addCriterion("reward_name_append not in", values, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendBetween(String value1, String value2) {
            addCriterion("reward_name_append between", value1, value2, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andRewardNameAppendNotBetween(String value1, String value2) {
            addCriterion("reward_name_append not between", value1, value2, "rewardNameAppend");
            return (Criteria) this;
        }

        public Criteria andPunishNameIsNull() {
            addCriterion("punish_name is null");
            return (Criteria) this;
        }

        public Criteria andPunishNameIsNotNull() {
            addCriterion("punish_name is not null");
            return (Criteria) this;
        }

        public Criteria andPunishNameEqualTo(String value) {
            addCriterion("punish_name =", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameNotEqualTo(String value) {
            addCriterion("punish_name <>", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameGreaterThan(String value) {
            addCriterion("punish_name >", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameGreaterThanOrEqualTo(String value) {
            addCriterion("punish_name >=", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameLessThan(String value) {
            addCriterion("punish_name <", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameLessThanOrEqualTo(String value) {
            addCriterion("punish_name <=", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameLike(String value) {
            addCriterion("punish_name like", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameNotLike(String value) {
            addCriterion("punish_name not like", value, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameIn(List<String> values) {
            addCriterion("punish_name in", values, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameNotIn(List<String> values) {
            addCriterion("punish_name not in", values, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameBetween(String value1, String value2) {
            addCriterion("punish_name between", value1, value2, "punishName");
            return (Criteria) this;
        }

        public Criteria andPunishNameNotBetween(String value1, String value2) {
            addCriterion("punish_name not between", value1, value2, "punishName");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldIsNull() {
            addCriterion("recognition_field is null");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldIsNotNull() {
            addCriterion("recognition_field is not null");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldEqualTo(String value) {
            addCriterion("recognition_field =", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldNotEqualTo(String value) {
            addCriterion("recognition_field <>", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldGreaterThan(String value) {
            addCriterion("recognition_field >", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldGreaterThanOrEqualTo(String value) {
            addCriterion("recognition_field >=", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldLessThan(String value) {
            addCriterion("recognition_field <", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldLessThanOrEqualTo(String value) {
            addCriterion("recognition_field <=", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldLike(String value) {
            addCriterion("recognition_field like", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldNotLike(String value) {
            addCriterion("recognition_field not like", value, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldIn(List<String> values) {
            addCriterion("recognition_field in", values, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldNotIn(List<String> values) {
            addCriterion("recognition_field not in", values, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldBetween(String value1, String value2) {
            addCriterion("recognition_field between", value1, value2, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecognitionFieldNotBetween(String value1, String value2) {
            addCriterion("recognition_field not between", value1, value2, "recognitionField");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNull() {
            addCriterion("record_date is null");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNotNull() {
            addCriterion("record_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecordDateEqualTo(Date value) {
            addCriterionForJDBCDate("record_date =", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("record_date <>", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThan(Date value) {
            addCriterionForJDBCDate("record_date >", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("record_date >=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThan(Date value) {
            addCriterionForJDBCDate("record_date <", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("record_date <=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateIn(List<Date> values) {
            addCriterionForJDBCDate("record_date in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("record_date not in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("record_date between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("record_date not between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelIsNull() {
            addCriterion("approval_authority_level is null");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelIsNotNull() {
            addCriterion("approval_authority_level is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelEqualTo(String value) {
            addCriterion("approval_authority_level =", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelNotEqualTo(String value) {
            addCriterion("approval_authority_level <>", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelGreaterThan(String value) {
            addCriterion("approval_authority_level >", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelGreaterThanOrEqualTo(String value) {
            addCriterion("approval_authority_level >=", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelLessThan(String value) {
            addCriterion("approval_authority_level <", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelLessThanOrEqualTo(String value) {
            addCriterion("approval_authority_level <=", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelLike(String value) {
            addCriterion("approval_authority_level like", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelNotLike(String value) {
            addCriterion("approval_authority_level not like", value, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelIn(List<String> values) {
            addCriterion("approval_authority_level in", values, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelNotIn(List<String> values) {
            addCriterion("approval_authority_level not in", values, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelBetween(String value1, String value2) {
            addCriterion("approval_authority_level between", value1, value2, "approvalAuthorityLevel");
            return (Criteria) this;
        }

        public Criteria andApprovalAuthorityLevelNotBetween(String value1, String value2) {
            addCriterion("approval_authority_level not between", value1, value2, "approvalAuthorityLevel");
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

        public Criteria andInsertTimeIsNull() {
            addCriterion("insert_time is null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNotNull() {
            addCriterion("insert_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeEqualTo(Date value) {
            addCriterion("insert_time =", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("insert_time <>", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("insert_time >", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_time >=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("insert_time <", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("insert_time <=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIn(List<Date> values) {
            addCriterion("insert_time in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotIn(List<Date> values) {
            addCriterion("insert_time not in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeBetween(Date value1, Date value2) {
            addCriterion("insert_time between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotBetween(Date value1, Date value2) {
            addCriterion("insert_time not between", value1, value2, "insertTime");
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