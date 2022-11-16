package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsDepartmentCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsDepartmentCriteria() {
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andUnicodeIsNull() {
            addCriterion("unicode is null");
            return (Criteria) this;
        }

        public Criteria andUnicodeIsNotNull() {
            addCriterion("unicode is not null");
            return (Criteria) this;
        }

        public Criteria andUnicodeEqualTo(Integer value) {
            addCriterion("unicode =", value, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeNotEqualTo(Integer value) {
            addCriterion("unicode <>", value, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeGreaterThan(Integer value) {
            addCriterion("unicode >", value, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("unicode >=", value, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeLessThan(Integer value) {
            addCriterion("unicode <", value, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeLessThanOrEqualTo(Integer value) {
            addCriterion("unicode <=", value, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeIn(List<Integer> values) {
            addCriterion("unicode in", values, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeNotIn(List<Integer> values) {
            addCriterion("unicode not in", values, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeBetween(Integer value1, Integer value2) {
            addCriterion("unicode between", value1, value2, "unicode");
            return (Criteria) this;
        }

        public Criteria andUnicodeNotBetween(Integer value1, Integer value2) {
            addCriterion("unicode not between", value1, value2, "unicode");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameIsNull() {
            addCriterion("dept_st_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptStNameIsNotNull() {
            addCriterion("dept_st_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptStNameEqualTo(String value) {
            addCriterion("dept_st_name =", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameNotEqualTo(String value) {
            addCriterion("dept_st_name <>", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameGreaterThan(String value) {
            addCriterion("dept_st_name >", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_st_name >=", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameLessThan(String value) {
            addCriterion("dept_st_name <", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameLessThanOrEqualTo(String value) {
            addCriterion("dept_st_name <=", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameLike(String value) {
            addCriterion("dept_st_name like", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameNotLike(String value) {
            addCriterion("dept_st_name not like", value, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameIn(List<String> values) {
            addCriterion("dept_st_name in", values, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameNotIn(List<String> values) {
            addCriterion("dept_st_name not in", values, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameBetween(String value1, String value2) {
            addCriterion("dept_st_name between", value1, value2, "deptStName");
            return (Criteria) this;
        }

        public Criteria andDeptStNameNotBetween(String value1, String value2) {
            addCriterion("dept_st_name not between", value1, value2, "deptStName");
            return (Criteria) this;
        }

        public Criteria andLevelCodeIsNull() {
            addCriterion("level_code is null");
            return (Criteria) this;
        }

        public Criteria andLevelCodeIsNotNull() {
            addCriterion("level_code is not null");
            return (Criteria) this;
        }

        public Criteria andLevelCodeEqualTo(String value) {
            addCriterion("level_code =", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeNotEqualTo(String value) {
            addCriterion("level_code <>", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeGreaterThan(String value) {
            addCriterion("level_code >", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("level_code >=", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeLessThan(String value) {
            addCriterion("level_code <", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeLessThanOrEqualTo(String value) {
            addCriterion("level_code <=", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeLike(String value) {
            addCriterion("level_code like", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeNotLike(String value) {
            addCriterion("level_code not like", value, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeIn(List<String> values) {
            addCriterion("level_code in", values, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeNotIn(List<String> values) {
            addCriterion("level_code not in", values, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeBetween(String value1, String value2) {
            addCriterion("level_code between", value1, value2, "levelCode");
            return (Criteria) this;
        }

        public Criteria andLevelCodeNotBetween(String value1, String value2) {
            addCriterion("level_code not between", value1, value2, "levelCode");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionIsNull() {
            addCriterion("officer_position is null");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionIsNotNull() {
            addCriterion("officer_position is not null");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionEqualTo(Integer value) {
            addCriterion("officer_position =", value, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionNotEqualTo(Integer value) {
            addCriterion("officer_position <>", value, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionGreaterThan(Integer value) {
            addCriterion("officer_position >", value, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("officer_position >=", value, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionLessThan(Integer value) {
            addCriterion("officer_position <", value, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionLessThanOrEqualTo(Integer value) {
            addCriterion("officer_position <=", value, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionIn(List<Integer> values) {
            addCriterion("officer_position in", values, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionNotIn(List<Integer> values) {
            addCriterion("officer_position not in", values, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionBetween(Integer value1, Integer value2) {
            addCriterion("officer_position between", value1, value2, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andOfficerPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("officer_position not between", value1, value2, "officerPosition");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffIsNull() {
            addCriterion("num_of_staff is null");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffIsNotNull() {
            addCriterion("num_of_staff is not null");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffEqualTo(Integer value) {
            addCriterion("num_of_staff =", value, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffNotEqualTo(Integer value) {
            addCriterion("num_of_staff <>", value, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffGreaterThan(Integer value) {
            addCriterion("num_of_staff >", value, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_of_staff >=", value, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffLessThan(Integer value) {
            addCriterion("num_of_staff <", value, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffLessThanOrEqualTo(Integer value) {
            addCriterion("num_of_staff <=", value, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffIn(List<Integer> values) {
            addCriterion("num_of_staff in", values, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffNotIn(List<Integer> values) {
            addCriterion("num_of_staff not in", values, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffBetween(Integer value1, Integer value2) {
            addCriterion("num_of_staff between", value1, value2, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andNumOfStaffNotBetween(Integer value1, Integer value2) {
            addCriterion("num_of_staff not between", value1, value2, "numOfStaff");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNull() {
            addCriterion("is_valid is null");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNotNull() {
            addCriterion("is_valid is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidEqualTo(Integer value) {
            addCriterion("is_valid =", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotEqualTo(Integer value) {
            addCriterion("is_valid <>", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThan(Integer value) {
            addCriterion("is_valid >", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_valid >=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThan(Integer value) {
            addCriterion("is_valid <", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThanOrEqualTo(Integer value) {
            addCriterion("is_valid <=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidIn(List<Integer> values) {
            addCriterion("is_valid in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotIn(List<Integer> values) {
            addCriterion("is_valid not in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidBetween(Integer value1, Integer value2) {
            addCriterion("is_valid between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotBetween(Integer value1, Integer value2) {
            addCriterion("is_valid not between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIsNull() {
            addCriterion("is_visible is null");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIsNotNull() {
            addCriterion("is_visible is not null");
            return (Criteria) this;
        }

        public Criteria andIsVisibleEqualTo(Integer value) {
            addCriterion("is_visible =", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotEqualTo(Integer value) {
            addCriterion("is_visible <>", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleGreaterThan(Integer value) {
            addCriterion("is_visible >", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_visible >=", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleLessThan(Integer value) {
            addCriterion("is_visible <", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleLessThanOrEqualTo(Integer value) {
            addCriterion("is_visible <=", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIn(List<Integer> values) {
            addCriterion("is_visible in", values, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotIn(List<Integer> values) {
            addCriterion("is_visible not in", values, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleBetween(Integer value1, Integer value2) {
            addCriterion("is_visible between", value1, value2, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotBetween(Integer value1, Integer value2) {
            addCriterion("is_visible not between", value1, value2, "isVisible");
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

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(Integer value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(Integer value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(Integer value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(Integer value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(Integer value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<Integer> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<Integer> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(Integer value1, Integer value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNull() {
            addCriterion("org_type is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNotNull() {
            addCriterion("org_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeEqualTo(Integer value) {
            addCriterion("org_type =", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotEqualTo(Integer value) {
            addCriterion("org_type <>", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThan(Integer value) {
            addCriterion("org_type >", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_type >=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThan(Integer value) {
            addCriterion("org_type <", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThanOrEqualTo(Integer value) {
            addCriterion("org_type <=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIn(List<Integer> values) {
            addCriterion("org_type in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotIn(List<Integer> values) {
            addCriterion("org_type not in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeBetween(Integer value1, Integer value2) {
            addCriterion("org_type between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("org_type not between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameIsNull() {
            addCriterion("court_short_name is null");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameIsNotNull() {
            addCriterion("court_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameEqualTo(String value) {
            addCriterion("court_short_name =", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotEqualTo(String value) {
            addCriterion("court_short_name <>", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameGreaterThan(String value) {
            addCriterion("court_short_name >", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("court_short_name >=", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameLessThan(String value) {
            addCriterion("court_short_name <", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameLessThanOrEqualTo(String value) {
            addCriterion("court_short_name <=", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameLike(String value) {
            addCriterion("court_short_name like", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotLike(String value) {
            addCriterion("court_short_name not like", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameIn(List<String> values) {
            addCriterion("court_short_name in", values, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotIn(List<String> values) {
            addCriterion("court_short_name not in", values, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameBetween(String value1, String value2) {
            addCriterion("court_short_name between", value1, value2, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotBetween(String value1, String value2) {
            addCriterion("court_short_name not between", value1, value2, "courtShortName");
            return (Criteria) this;
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

        public Criteria andInstitutionCodeIsNull() {
            addCriterion("INSTITUTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeIsNotNull() {
            addCriterion("INSTITUTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeEqualTo(String value) {
            addCriterion("INSTITUTION_CODE =", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotEqualTo(String value) {
            addCriterion("INSTITUTION_CODE <>", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeGreaterThan(String value) {
            addCriterion("INSTITUTION_CODE >", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("INSTITUTION_CODE >=", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeLessThan(String value) {
            addCriterion("INSTITUTION_CODE <", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeLessThanOrEqualTo(String value) {
            addCriterion("INSTITUTION_CODE <=", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeLike(String value) {
            addCriterion("INSTITUTION_CODE like", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotLike(String value) {
            addCriterion("INSTITUTION_CODE not like", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeIn(List<String> values) {
            addCriterion("INSTITUTION_CODE in", values, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotIn(List<String> values) {
            addCriterion("INSTITUTION_CODE not in", values, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeBetween(String value1, String value2) {
            addCriterion("INSTITUTION_CODE between", value1, value2, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotBetween(String value1, String value2) {
            addCriterion("INSTITUTION_CODE not between", value1, value2, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andCourtLevelIsNull() {
            addCriterion("COURT_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andCourtLevelIsNotNull() {
            addCriterion("COURT_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andCourtLevelEqualTo(String value) {
            addCriterion("COURT_LEVEL =", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelNotEqualTo(String value) {
            addCriterion("COURT_LEVEL <>", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelGreaterThan(String value) {
            addCriterion("COURT_LEVEL >", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelGreaterThanOrEqualTo(String value) {
            addCriterion("COURT_LEVEL >=", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelLessThan(String value) {
            addCriterion("COURT_LEVEL <", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelLessThanOrEqualTo(String value) {
            addCriterion("COURT_LEVEL <=", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelLike(String value) {
            addCriterion("COURT_LEVEL like", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelNotLike(String value) {
            addCriterion("COURT_LEVEL not like", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelIn(List<String> values) {
            addCriterion("COURT_LEVEL in", values, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelNotIn(List<String> values) {
            addCriterion("COURT_LEVEL not in", values, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelBetween(String value1, String value2) {
            addCriterion("COURT_LEVEL between", value1, value2, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelNotBetween(String value1, String value2) {
            addCriterion("COURT_LEVEL not between", value1, value2, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andDeptTypeIsNull() {
            addCriterion("DEPT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDeptTypeIsNotNull() {
            addCriterion("DEPT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDeptTypeEqualTo(String value) {
            addCriterion("DEPT_TYPE =", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeNotEqualTo(String value) {
            addCriterion("DEPT_TYPE <>", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeGreaterThan(String value) {
            addCriterion("DEPT_TYPE >", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_TYPE >=", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeLessThan(String value) {
            addCriterion("DEPT_TYPE <", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeLessThanOrEqualTo(String value) {
            addCriterion("DEPT_TYPE <=", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeLike(String value) {
            addCriterion("DEPT_TYPE like", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeNotLike(String value) {
            addCriterion("DEPT_TYPE not like", value, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeIn(List<String> values) {
            addCriterion("DEPT_TYPE in", values, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeNotIn(List<String> values) {
            addCriterion("DEPT_TYPE not in", values, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeBetween(String value1, String value2) {
            addCriterion("DEPT_TYPE between", value1, value2, "deptType");
            return (Criteria) this;
        }

        public Criteria andDeptTypeNotBetween(String value1, String value2) {
            addCriterion("DEPT_TYPE not between", value1, value2, "deptType");
            return (Criteria) this;
        }

        public Criteria andIsPeplesIsNull() {
            addCriterion("IS_PEPLES is null");
            return (Criteria) this;
        }

        public Criteria andIsPeplesIsNotNull() {
            addCriterion("IS_PEPLES is not null");
            return (Criteria) this;
        }

        public Criteria andIsPeplesEqualTo(String value) {
            addCriterion("IS_PEPLES =", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesNotEqualTo(String value) {
            addCriterion("IS_PEPLES <>", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesGreaterThan(String value) {
            addCriterion("IS_PEPLES >", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PEPLES >=", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesLessThan(String value) {
            addCriterion("IS_PEPLES <", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesLessThanOrEqualTo(String value) {
            addCriterion("IS_PEPLES <=", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesLike(String value) {
            addCriterion("IS_PEPLES like", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesNotLike(String value) {
            addCriterion("IS_PEPLES not like", value, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesIn(List<String> values) {
            addCriterion("IS_PEPLES in", values, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesNotIn(List<String> values) {
            addCriterion("IS_PEPLES not in", values, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesBetween(String value1, String value2) {
            addCriterion("IS_PEPLES between", value1, value2, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsPeplesNotBetween(String value1, String value2) {
            addCriterion("IS_PEPLES not between", value1, value2, "isPeples");
            return (Criteria) this;
        }

        public Criteria andIsLeadersIsNull() {
            addCriterion("IS_LEADERS is null");
            return (Criteria) this;
        }

        public Criteria andIsLeadersIsNotNull() {
            addCriterion("IS_LEADERS is not null");
            return (Criteria) this;
        }

        public Criteria andIsLeadersEqualTo(String value) {
            addCriterion("IS_LEADERS =", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersNotEqualTo(String value) {
            addCriterion("IS_LEADERS <>", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersGreaterThan(String value) {
            addCriterion("IS_LEADERS >", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersGreaterThanOrEqualTo(String value) {
            addCriterion("IS_LEADERS >=", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersLessThan(String value) {
            addCriterion("IS_LEADERS <", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersLessThanOrEqualTo(String value) {
            addCriterion("IS_LEADERS <=", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersLike(String value) {
            addCriterion("IS_LEADERS like", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersNotLike(String value) {
            addCriterion("IS_LEADERS not like", value, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersIn(List<String> values) {
            addCriterion("IS_LEADERS in", values, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersNotIn(List<String> values) {
            addCriterion("IS_LEADERS not in", values, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersBetween(String value1, String value2) {
            addCriterion("IS_LEADERS between", value1, value2, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andIsLeadersNotBetween(String value1, String value2) {
            addCriterion("IS_LEADERS not between", value1, value2, "isLeaders");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyIsNull() {
            addCriterion("DEPT_PROPERTY is null");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyIsNotNull() {
            addCriterion("DEPT_PROPERTY is not null");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyEqualTo(String value) {
            addCriterion("DEPT_PROPERTY =", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotEqualTo(String value) {
            addCriterion("DEPT_PROPERTY <>", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyGreaterThan(String value) {
            addCriterion("DEPT_PROPERTY >", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_PROPERTY >=", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyLessThan(String value) {
            addCriterion("DEPT_PROPERTY <", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyLessThanOrEqualTo(String value) {
            addCriterion("DEPT_PROPERTY <=", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyLike(String value) {
            addCriterion("DEPT_PROPERTY like", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotLike(String value) {
            addCriterion("DEPT_PROPERTY not like", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyIn(List<String> values) {
            addCriterion("DEPT_PROPERTY in", values, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotIn(List<String> values) {
            addCriterion("DEPT_PROPERTY not in", values, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyBetween(String value1, String value2) {
            addCriterion("DEPT_PROPERTY between", value1, value2, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotBetween(String value1, String value2) {
            addCriterion("DEPT_PROPERTY not between", value1, value2, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNull() {
            addCriterion("header is null");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNotNull() {
            addCriterion("header is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderEqualTo(String value) {
            addCriterion("header =", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotEqualTo(String value) {
            addCriterion("header <>", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThan(String value) {
            addCriterion("header >", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThanOrEqualTo(String value) {
            addCriterion("header >=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThan(String value) {
            addCriterion("header <", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThanOrEqualTo(String value) {
            addCriterion("header <=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLike(String value) {
            addCriterion("header like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotLike(String value) {
            addCriterion("header not like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderIn(List<String> values) {
            addCriterion("header in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotIn(List<String> values) {
            addCriterion("header not in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderBetween(String value1, String value2) {
            addCriterion("header between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotBetween(String value1, String value2) {
            addCriterion("header not between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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