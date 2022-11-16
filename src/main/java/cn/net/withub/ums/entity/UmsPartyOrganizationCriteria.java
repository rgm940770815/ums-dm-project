package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsPartyOrganizationCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsPartyOrganizationCriteria() {
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

        public Criteria andIsBuildIsNull() {
            addCriterion("is_build is null");
            return (Criteria) this;
        }

        public Criteria andIsBuildIsNotNull() {
            addCriterion("is_build is not null");
            return (Criteria) this;
        }

        public Criteria andIsBuildEqualTo(String value) {
            addCriterion("is_build =", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildNotEqualTo(String value) {
            addCriterion("is_build <>", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildGreaterThan(String value) {
            addCriterion("is_build >", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildGreaterThanOrEqualTo(String value) {
            addCriterion("is_build >=", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildLessThan(String value) {
            addCriterion("is_build <", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildLessThanOrEqualTo(String value) {
            addCriterion("is_build <=", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildLike(String value) {
            addCriterion("is_build like", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildNotLike(String value) {
            addCriterion("is_build not like", value, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildIn(List<String> values) {
            addCriterion("is_build in", values, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildNotIn(List<String> values) {
            addCriterion("is_build not in", values, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildBetween(String value1, String value2) {
            addCriterion("is_build between", value1, value2, "isBuild");
            return (Criteria) this;
        }

        public Criteria andIsBuildNotBetween(String value1, String value2) {
            addCriterion("is_build not between", value1, value2, "isBuild");
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

        public Criteria andOrgTypeEqualTo(String value) {
            addCriterion("org_type =", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotEqualTo(String value) {
            addCriterion("org_type <>", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThan(String value) {
            addCriterion("org_type >", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("org_type >=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThan(String value) {
            addCriterion("org_type <", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("org_type <=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLike(String value) {
            addCriterion("org_type like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotLike(String value) {
            addCriterion("org_type not like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIn(List<String> values) {
            addCriterion("org_type in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotIn(List<String> values) {
            addCriterion("org_type not in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeBetween(String value1, String value2) {
            addCriterion("org_type between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotBetween(String value1, String value2) {
            addCriterion("org_type not between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("org_name =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("org_name >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("org_name <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("org_name like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("org_name not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("org_name in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountIsNull() {
            addCriterion("org_person_count is null");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountIsNotNull() {
            addCriterion("org_person_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountEqualTo(Integer value) {
            addCriterion("org_person_count =", value, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountNotEqualTo(Integer value) {
            addCriterion("org_person_count <>", value, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountGreaterThan(Integer value) {
            addCriterion("org_person_count >", value, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_person_count >=", value, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountLessThan(Integer value) {
            addCriterion("org_person_count <", value, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountLessThanOrEqualTo(Integer value) {
            addCriterion("org_person_count <=", value, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountIn(List<Integer> values) {
            addCriterion("org_person_count in", values, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountNotIn(List<Integer> values) {
            addCriterion("org_person_count not in", values, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountBetween(Integer value1, Integer value2) {
            addCriterion("org_person_count between", value1, value2, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgPersonCountNotBetween(Integer value1, Integer value2) {
            addCriterion("org_person_count not between", value1, value2, "orgPersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountIsNull() {
            addCriterion("org_cadre_person_count is null");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountIsNotNull() {
            addCriterion("org_cadre_person_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountEqualTo(Integer value) {
            addCriterion("org_cadre_person_count =", value, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountNotEqualTo(Integer value) {
            addCriterion("org_cadre_person_count <>", value, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountGreaterThan(Integer value) {
            addCriterion("org_cadre_person_count >", value, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_cadre_person_count >=", value, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountLessThan(Integer value) {
            addCriterion("org_cadre_person_count <", value, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountLessThanOrEqualTo(Integer value) {
            addCriterion("org_cadre_person_count <=", value, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountIn(List<Integer> values) {
            addCriterion("org_cadre_person_count in", values, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountNotIn(List<Integer> values) {
            addCriterion("org_cadre_person_count not in", values, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountBetween(Integer value1, Integer value2) {
            addCriterion("org_cadre_person_count between", value1, value2, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgCadrePersonCountNotBetween(Integer value1, Integer value2) {
            addCriterion("org_cadre_person_count not between", value1, value2, "orgCadrePersonCount");
            return (Criteria) this;
        }

        public Criteria andOrgSituationIsNull() {
            addCriterion("org_situation is null");
            return (Criteria) this;
        }

        public Criteria andOrgSituationIsNotNull() {
            addCriterion("org_situation is not null");
            return (Criteria) this;
        }

        public Criteria andOrgSituationEqualTo(String value) {
            addCriterion("org_situation =", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationNotEqualTo(String value) {
            addCriterion("org_situation <>", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationGreaterThan(String value) {
            addCriterion("org_situation >", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationGreaterThanOrEqualTo(String value) {
            addCriterion("org_situation >=", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationLessThan(String value) {
            addCriterion("org_situation <", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationLessThanOrEqualTo(String value) {
            addCriterion("org_situation <=", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationLike(String value) {
            addCriterion("org_situation like", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationNotLike(String value) {
            addCriterion("org_situation not like", value, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationIn(List<String> values) {
            addCriterion("org_situation in", values, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationNotIn(List<String> values) {
            addCriterion("org_situation not in", values, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationBetween(String value1, String value2) {
            addCriterion("org_situation between", value1, value2, "orgSituation");
            return (Criteria) this;
        }

        public Criteria andOrgSituationNotBetween(String value1, String value2) {
            addCriterion("org_situation not between", value1, value2, "orgSituation");
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

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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