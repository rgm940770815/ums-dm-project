package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsCommunicationInformationCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsCommunicationInformationCriteria() {
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberIsNull() {
            addCriterion("fax_number is null");
            return (Criteria) this;
        }

        public Criteria andFaxNumberIsNotNull() {
            addCriterion("fax_number is not null");
            return (Criteria) this;
        }

        public Criteria andFaxNumberEqualTo(String value) {
            addCriterion("fax_number =", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotEqualTo(String value) {
            addCriterion("fax_number <>", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberGreaterThan(String value) {
            addCriterion("fax_number >", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberGreaterThanOrEqualTo(String value) {
            addCriterion("fax_number >=", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberLessThan(String value) {
            addCriterion("fax_number <", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberLessThanOrEqualTo(String value) {
            addCriterion("fax_number <=", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberLike(String value) {
            addCriterion("fax_number like", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotLike(String value) {
            addCriterion("fax_number not like", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberIn(List<String> values) {
            addCriterion("fax_number in", values, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotIn(List<String> values) {
            addCriterion("fax_number not in", values, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberBetween(String value1, String value2) {
            addCriterion("fax_number between", value1, value2, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotBetween(String value1, String value2) {
            addCriterion("fax_number not between", value1, value2, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNull() {
            addCriterion("postal_code is null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNotNull() {
            addCriterion("postal_code is not null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeEqualTo(String value) {
            addCriterion("postal_code =", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotEqualTo(String value) {
            addCriterion("postal_code <>", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThan(String value) {
            addCriterion("postal_code >", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("postal_code >=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThan(String value) {
            addCriterion("postal_code <", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThanOrEqualTo(String value) {
            addCriterion("postal_code <=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLike(String value) {
            addCriterion("postal_code like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotLike(String value) {
            addCriterion("postal_code not like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIn(List<String> values) {
            addCriterion("postal_code in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotIn(List<String> values) {
            addCriterion("postal_code not in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeBetween(String value1, String value2) {
            addCriterion("postal_code between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotBetween(String value1, String value2) {
            addCriterion("postal_code not between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andEmailAddressIsNull() {
            addCriterion("email_address is null");
            return (Criteria) this;
        }

        public Criteria andEmailAddressIsNotNull() {
            addCriterion("email_address is not null");
            return (Criteria) this;
        }

        public Criteria andEmailAddressEqualTo(String value) {
            addCriterion("email_address =", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotEqualTo(String value) {
            addCriterion("email_address <>", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressGreaterThan(String value) {
            addCriterion("email_address >", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressGreaterThanOrEqualTo(String value) {
            addCriterion("email_address >=", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressLessThan(String value) {
            addCriterion("email_address <", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressLessThanOrEqualTo(String value) {
            addCriterion("email_address <=", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressLike(String value) {
            addCriterion("email_address like", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotLike(String value) {
            addCriterion("email_address not like", value, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressIn(List<String> values) {
            addCriterion("email_address in", values, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotIn(List<String> values) {
            addCriterion("email_address not in", values, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressBetween(String value1, String value2) {
            addCriterion("email_address between", value1, value2, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andEmailAddressNotBetween(String value1, String value2) {
            addCriterion("email_address not between", value1, value2, "emailAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameIsNull() {
            addCriterion("office_contact_name is null");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameIsNotNull() {
            addCriterion("office_contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameEqualTo(String value) {
            addCriterion("office_contact_name =", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameNotEqualTo(String value) {
            addCriterion("office_contact_name <>", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameGreaterThan(String value) {
            addCriterion("office_contact_name >", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("office_contact_name >=", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameLessThan(String value) {
            addCriterion("office_contact_name <", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameLessThanOrEqualTo(String value) {
            addCriterion("office_contact_name <=", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameLike(String value) {
            addCriterion("office_contact_name like", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameNotLike(String value) {
            addCriterion("office_contact_name not like", value, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameIn(List<String> values) {
            addCriterion("office_contact_name in", values, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameNotIn(List<String> values) {
            addCriterion("office_contact_name not in", values, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameBetween(String value1, String value2) {
            addCriterion("office_contact_name between", value1, value2, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactNameNotBetween(String value1, String value2) {
            addCriterion("office_contact_name not between", value1, value2, "officeContactName");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberIsNull() {
            addCriterion("office_contact_phone_number is null");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberIsNotNull() {
            addCriterion("office_contact_phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberEqualTo(String value) {
            addCriterion("office_contact_phone_number =", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberNotEqualTo(String value) {
            addCriterion("office_contact_phone_number <>", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberGreaterThan(String value) {
            addCriterion("office_contact_phone_number >", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("office_contact_phone_number >=", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberLessThan(String value) {
            addCriterion("office_contact_phone_number <", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("office_contact_phone_number <=", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberLike(String value) {
            addCriterion("office_contact_phone_number like", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberNotLike(String value) {
            addCriterion("office_contact_phone_number not like", value, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberIn(List<String> values) {
            addCriterion("office_contact_phone_number in", values, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberNotIn(List<String> values) {
            addCriterion("office_contact_phone_number not in", values, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberBetween(String value1, String value2) {
            addCriterion("office_contact_phone_number between", value1, value2, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andOfficeContactPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("office_contact_phone_number not between", value1, value2, "officeContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameIsNull() {
            addCriterion("personnel_department_contact_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameIsNotNull() {
            addCriterion("personnel_department_contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameEqualTo(String value) {
            addCriterion("personnel_department_contact_name =", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameNotEqualTo(String value) {
            addCriterion("personnel_department_contact_name <>", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameGreaterThan(String value) {
            addCriterion("personnel_department_contact_name >", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("personnel_department_contact_name >=", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameLessThan(String value) {
            addCriterion("personnel_department_contact_name <", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameLessThanOrEqualTo(String value) {
            addCriterion("personnel_department_contact_name <=", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameLike(String value) {
            addCriterion("personnel_department_contact_name like", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameNotLike(String value) {
            addCriterion("personnel_department_contact_name not like", value, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameIn(List<String> values) {
            addCriterion("personnel_department_contact_name in", values, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameNotIn(List<String> values) {
            addCriterion("personnel_department_contact_name not in", values, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameBetween(String value1, String value2) {
            addCriterion("personnel_department_contact_name between", value1, value2, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactNameNotBetween(String value1, String value2) {
            addCriterion("personnel_department_contact_name not between", value1, value2, "personnelDepartmentContactName");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberIsNull() {
            addCriterion("personnel_department_contact_phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberIsNotNull() {
            addCriterion("personnel_department_contact_phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberEqualTo(String value) {
            addCriterion("personnel_department_contact_phone_number =", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberNotEqualTo(String value) {
            addCriterion("personnel_department_contact_phone_number <>", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberGreaterThan(String value) {
            addCriterion("personnel_department_contact_phone_number >", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("personnel_department_contact_phone_number >=", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberLessThan(String value) {
            addCriterion("personnel_department_contact_phone_number <", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("personnel_department_contact_phone_number <=", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberLike(String value) {
            addCriterion("personnel_department_contact_phone_number like", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberNotLike(String value) {
            addCriterion("personnel_department_contact_phone_number not like", value, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberIn(List<String> values) {
            addCriterion("personnel_department_contact_phone_number in", values, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberNotIn(List<String> values) {
            addCriterion("personnel_department_contact_phone_number not in", values, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberBetween(String value1, String value2) {
            addCriterion("personnel_department_contact_phone_number between", value1, value2, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("personnel_department_contact_phone_number not between", value1, value2, "personnelDepartmentContactPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressIsNull() {
            addCriterion("personnel_department_contact_email_address is null");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressIsNotNull() {
            addCriterion("personnel_department_contact_email_address is not null");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressEqualTo(String value) {
            addCriterion("personnel_department_contact_email_address =", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressNotEqualTo(String value) {
            addCriterion("personnel_department_contact_email_address <>", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressGreaterThan(String value) {
            addCriterion("personnel_department_contact_email_address >", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressGreaterThanOrEqualTo(String value) {
            addCriterion("personnel_department_contact_email_address >=", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressLessThan(String value) {
            addCriterion("personnel_department_contact_email_address <", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressLessThanOrEqualTo(String value) {
            addCriterion("personnel_department_contact_email_address <=", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressLike(String value) {
            addCriterion("personnel_department_contact_email_address like", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressNotLike(String value) {
            addCriterion("personnel_department_contact_email_address not like", value, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressIn(List<String> values) {
            addCriterion("personnel_department_contact_email_address in", values, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressNotIn(List<String> values) {
            addCriterion("personnel_department_contact_email_address not in", values, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressBetween(String value1, String value2) {
            addCriterion("personnel_department_contact_email_address between", value1, value2, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andPersonnelDepartmentContactEmailAddressNotBetween(String value1, String value2) {
            addCriterion("personnel_department_contact_email_address not between", value1, value2, "personnelDepartmentContactEmailAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressIsNull() {
            addCriterion("Internet_address is null");
            return (Criteria) this;
        }

        public Criteria andInternetAddressIsNotNull() {
            addCriterion("Internet_address is not null");
            return (Criteria) this;
        }

        public Criteria andInternetAddressEqualTo(String value) {
            addCriterion("Internet_address =", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressNotEqualTo(String value) {
            addCriterion("Internet_address <>", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressGreaterThan(String value) {
            addCriterion("Internet_address >", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressGreaterThanOrEqualTo(String value) {
            addCriterion("Internet_address >=", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressLessThan(String value) {
            addCriterion("Internet_address <", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressLessThanOrEqualTo(String value) {
            addCriterion("Internet_address <=", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressLike(String value) {
            addCriterion("Internet_address like", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressNotLike(String value) {
            addCriterion("Internet_address not like", value, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressIn(List<String> values) {
            addCriterion("Internet_address in", values, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressNotIn(List<String> values) {
            addCriterion("Internet_address not in", values, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressBetween(String value1, String value2) {
            addCriterion("Internet_address between", value1, value2, "internetAddress");
            return (Criteria) this;
        }

        public Criteria andInternetAddressNotBetween(String value1, String value2) {
            addCriterion("Internet_address not between", value1, value2, "internetAddress");
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