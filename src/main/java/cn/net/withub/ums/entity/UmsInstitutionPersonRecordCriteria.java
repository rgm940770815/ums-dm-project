package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

public class UmsInstitutionPersonRecordCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsInstitutionPersonRecordCriteria() {
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

        public Criteria andSpywjgsIsNull() {
            addCriterion("spywjgs is null");
            return (Criteria) this;
        }

        public Criteria andSpywjgsIsNotNull() {
            addCriterion("spywjgs is not null");
            return (Criteria) this;
        }

        public Criteria andSpywjgsEqualTo(Integer value) {
            addCriterion("spywjgs =", value, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsNotEqualTo(Integer value) {
            addCriterion("spywjgs <>", value, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsGreaterThan(Integer value) {
            addCriterion("spywjgs >", value, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsGreaterThanOrEqualTo(Integer value) {
            addCriterion("spywjgs >=", value, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsLessThan(Integer value) {
            addCriterion("spywjgs <", value, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsLessThanOrEqualTo(Integer value) {
            addCriterion("spywjgs <=", value, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsIn(List<Integer> values) {
            addCriterion("spywjgs in", values, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsNotIn(List<Integer> values) {
            addCriterion("spywjgs not in", values, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsBetween(Integer value1, Integer value2) {
            addCriterion("spywjgs between", value1, value2, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andSpywjgsNotBetween(Integer value1, Integer value2) {
            addCriterion("spywjgs not between", value1, value2, "spywjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsIsNull() {
            addCriterion("xzhqjgs is null");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsIsNotNull() {
            addCriterion("xzhqjgs is not null");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsEqualTo(Integer value) {
            addCriterion("xzhqjgs =", value, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsNotEqualTo(Integer value) {
            addCriterion("xzhqjgs <>", value, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsGreaterThan(Integer value) {
            addCriterion("xzhqjgs >", value, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsGreaterThanOrEqualTo(Integer value) {
            addCriterion("xzhqjgs >=", value, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsLessThan(Integer value) {
            addCriterion("xzhqjgs <", value, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsLessThanOrEqualTo(Integer value) {
            addCriterion("xzhqjgs <=", value, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsIn(List<Integer> values) {
            addCriterion("xzhqjgs in", values, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsNotIn(List<Integer> values) {
            addCriterion("xzhqjgs not in", values, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsBetween(Integer value1, Integer value2) {
            addCriterion("xzhqjgs between", value1, value2, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andXzhqjgsNotBetween(Integer value1, Integer value2) {
            addCriterion("xzhqjgs not between", value1, value2, "xzhqjgs");
            return (Criteria) this;
        }

        public Criteria andZybzsIsNull() {
            addCriterion("zybzs is null");
            return (Criteria) this;
        }

        public Criteria andZybzsIsNotNull() {
            addCriterion("zybzs is not null");
            return (Criteria) this;
        }

        public Criteria andZybzsEqualTo(Integer value) {
            addCriterion("zybzs =", value, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsNotEqualTo(Integer value) {
            addCriterion("zybzs <>", value, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsGreaterThan(Integer value) {
            addCriterion("zybzs >", value, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsGreaterThanOrEqualTo(Integer value) {
            addCriterion("zybzs >=", value, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsLessThan(Integer value) {
            addCriterion("zybzs <", value, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsLessThanOrEqualTo(Integer value) {
            addCriterion("zybzs <=", value, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsIn(List<Integer> values) {
            addCriterion("zybzs in", values, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsNotIn(List<Integer> values) {
            addCriterion("zybzs not in", values, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsBetween(Integer value1, Integer value2) {
            addCriterion("zybzs between", value1, value2, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzsNotBetween(Integer value1, Integer value2) {
            addCriterion("zybzs not between", value1, value2, "zybzs");
            return (Criteria) this;
        }

        public Criteria andZybzxysIsNull() {
            addCriterion("zybzxys is null");
            return (Criteria) this;
        }

        public Criteria andZybzxysIsNotNull() {
            addCriterion("zybzxys is not null");
            return (Criteria) this;
        }

        public Criteria andZybzxysEqualTo(Integer value) {
            addCriterion("zybzxys =", value, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysNotEqualTo(Integer value) {
            addCriterion("zybzxys <>", value, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysGreaterThan(Integer value) {
            addCriterion("zybzxys >", value, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysGreaterThanOrEqualTo(Integer value) {
            addCriterion("zybzxys >=", value, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysLessThan(Integer value) {
            addCriterion("zybzxys <", value, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysLessThanOrEqualTo(Integer value) {
            addCriterion("zybzxys <=", value, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysIn(List<Integer> values) {
            addCriterion("zybzxys in", values, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysNotIn(List<Integer> values) {
            addCriterion("zybzxys not in", values, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysBetween(Integer value1, Integer value2) {
            addCriterion("zybzxys between", value1, value2, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andZybzxysNotBetween(Integer value1, Integer value2) {
            addCriterion("zybzxys not between", value1, value2, "zybzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzsIsNull() {
            addCriterion("dfbzs is null");
            return (Criteria) this;
        }

        public Criteria andDfbzsIsNotNull() {
            addCriterion("dfbzs is not null");
            return (Criteria) this;
        }

        public Criteria andDfbzsEqualTo(Integer value) {
            addCriterion("dfbzs =", value, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsNotEqualTo(Integer value) {
            addCriterion("dfbzs <>", value, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsGreaterThan(Integer value) {
            addCriterion("dfbzs >", value, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsGreaterThanOrEqualTo(Integer value) {
            addCriterion("dfbzs >=", value, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsLessThan(Integer value) {
            addCriterion("dfbzs <", value, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsLessThanOrEqualTo(Integer value) {
            addCriterion("dfbzs <=", value, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsIn(List<Integer> values) {
            addCriterion("dfbzs in", values, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsNotIn(List<Integer> values) {
            addCriterion("dfbzs not in", values, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsBetween(Integer value1, Integer value2) {
            addCriterion("dfbzs between", value1, value2, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzsNotBetween(Integer value1, Integer value2) {
            addCriterion("dfbzs not between", value1, value2, "dfbzs");
            return (Criteria) this;
        }

        public Criteria andDfbzxysIsNull() {
            addCriterion("dfbzxys is null");
            return (Criteria) this;
        }

        public Criteria andDfbzxysIsNotNull() {
            addCriterion("dfbzxys is not null");
            return (Criteria) this;
        }

        public Criteria andDfbzxysEqualTo(Integer value) {
            addCriterion("dfbzxys =", value, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysNotEqualTo(Integer value) {
            addCriterion("dfbzxys <>", value, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysGreaterThan(Integer value) {
            addCriterion("dfbzxys >", value, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysGreaterThanOrEqualTo(Integer value) {
            addCriterion("dfbzxys >=", value, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysLessThan(Integer value) {
            addCriterion("dfbzxys <", value, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysLessThanOrEqualTo(Integer value) {
            addCriterion("dfbzxys <=", value, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysIn(List<Integer> values) {
            addCriterion("dfbzxys in", values, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysNotIn(List<Integer> values) {
            addCriterion("dfbzxys not in", values, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysBetween(Integer value1, Integer value2) {
            addCriterion("dfbzxys between", value1, value2, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andDfbzxysNotBetween(Integer value1, Integer value2) {
            addCriterion("dfbzxys not between", value1, value2, "dfbzxys");
            return (Criteria) this;
        }

        public Criteria andPyrsIsNull() {
            addCriterion("pyrs is null");
            return (Criteria) this;
        }

        public Criteria andPyrsIsNotNull() {
            addCriterion("pyrs is not null");
            return (Criteria) this;
        }

        public Criteria andPyrsEqualTo(Integer value) {
            addCriterion("pyrs =", value, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsNotEqualTo(Integer value) {
            addCriterion("pyrs <>", value, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsGreaterThan(Integer value) {
            addCriterion("pyrs >", value, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsGreaterThanOrEqualTo(Integer value) {
            addCriterion("pyrs >=", value, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsLessThan(Integer value) {
            addCriterion("pyrs <", value, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsLessThanOrEqualTo(Integer value) {
            addCriterion("pyrs <=", value, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsIn(List<Integer> values) {
            addCriterion("pyrs in", values, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsNotIn(List<Integer> values) {
            addCriterion("pyrs not in", values, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsBetween(Integer value1, Integer value2) {
            addCriterion("pyrs between", value1, value2, "pyrs");
            return (Criteria) this;
        }

        public Criteria andPyrsNotBetween(Integer value1, Integer value2) {
            addCriterion("pyrs not between", value1, value2, "pyrs");
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