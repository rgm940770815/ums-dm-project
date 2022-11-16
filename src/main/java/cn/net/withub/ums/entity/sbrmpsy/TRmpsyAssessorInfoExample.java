package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TRmpsyAssessorInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyAssessorInfoExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNull() {
            addCriterion("id_number is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("id_number is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("id_number =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("id_number <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("id_number >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("id_number >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("id_number <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("id_number <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("id_number like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("id_number not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("id_number in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("id_number not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("id_number between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("id_number not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andNationalIsNull() {
            addCriterion("national is null");
            return (Criteria) this;
        }

        public Criteria andNationalIsNotNull() {
            addCriterion("national is not null");
            return (Criteria) this;
        }

        public Criteria andNationalEqualTo(String value) {
            addCriterion("national =", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotEqualTo(String value) {
            addCriterion("national <>", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThan(String value) {
            addCriterion("national >", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThanOrEqualTo(String value) {
            addCriterion("national >=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThan(String value) {
            addCriterion("national <", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThanOrEqualTo(String value) {
            addCriterion("national <=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLike(String value) {
            addCriterion("national like", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotLike(String value) {
            addCriterion("national not like", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalIn(List<String> values) {
            addCriterion("national in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotIn(List<String> values) {
            addCriterion("national not in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalBetween(String value1, String value2) {
            addCriterion("national between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotBetween(String value1, String value2) {
            addCriterion("national not between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andRecordEducationIsNull() {
            addCriterion("record_education is null");
            return (Criteria) this;
        }

        public Criteria andRecordEducationIsNotNull() {
            addCriterion("record_education is not null");
            return (Criteria) this;
        }

        public Criteria andRecordEducationEqualTo(String value) {
            addCriterion("record_education =", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationNotEqualTo(String value) {
            addCriterion("record_education <>", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationGreaterThan(String value) {
            addCriterion("record_education >", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationGreaterThanOrEqualTo(String value) {
            addCriterion("record_education >=", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationLessThan(String value) {
            addCriterion("record_education <", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationLessThanOrEqualTo(String value) {
            addCriterion("record_education <=", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationLike(String value) {
            addCriterion("record_education like", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationNotLike(String value) {
            addCriterion("record_education not like", value, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationIn(List<String> values) {
            addCriterion("record_education in", values, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationNotIn(List<String> values) {
            addCriterion("record_education not in", values, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationBetween(String value1, String value2) {
            addCriterion("record_education between", value1, value2, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andRecordEducationNotBetween(String value1, String value2) {
            addCriterion("record_education not between", value1, value2, "recordEducation");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeIsNull() {
            addCriterion("political_landscape is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeIsNotNull() {
            addCriterion("political_landscape is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeEqualTo(String value) {
            addCriterion("political_landscape =", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeNotEqualTo(String value) {
            addCriterion("political_landscape <>", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeGreaterThan(String value) {
            addCriterion("political_landscape >", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeGreaterThanOrEqualTo(String value) {
            addCriterion("political_landscape >=", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeLessThan(String value) {
            addCriterion("political_landscape <", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeLessThanOrEqualTo(String value) {
            addCriterion("political_landscape <=", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeLike(String value) {
            addCriterion("political_landscape like", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeNotLike(String value) {
            addCriterion("political_landscape not like", value, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeIn(List<String> values) {
            addCriterion("political_landscape in", values, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeNotIn(List<String> values) {
            addCriterion("political_landscape not in", values, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeBetween(String value1, String value2) {
            addCriterion("political_landscape between", value1, value2, "politicalLandscape");
            return (Criteria) this;
        }

        public Criteria andPoliticalLandscapeNotBetween(String value1, String value2) {
            addCriterion("political_landscape not between", value1, value2, "politicalLandscape");
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

        public Criteria andFixedTelephoneIsNull() {
            addCriterion("fixed_telephone is null");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneIsNotNull() {
            addCriterion("fixed_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneEqualTo(String value) {
            addCriterion("fixed_telephone =", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneNotEqualTo(String value) {
            addCriterion("fixed_telephone <>", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneGreaterThan(String value) {
            addCriterion("fixed_telephone >", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_telephone >=", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneLessThan(String value) {
            addCriterion("fixed_telephone <", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneLessThanOrEqualTo(String value) {
            addCriterion("fixed_telephone <=", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneLike(String value) {
            addCriterion("fixed_telephone like", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneNotLike(String value) {
            addCriterion("fixed_telephone not like", value, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneIn(List<String> values) {
            addCriterion("fixed_telephone in", values, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneNotIn(List<String> values) {
            addCriterion("fixed_telephone not in", values, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneBetween(String value1, String value2) {
            addCriterion("fixed_telephone between", value1, value2, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andFixedTelephoneNotBetween(String value1, String value2) {
            addCriterion("fixed_telephone not between", value1, value2, "fixedTelephone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("mobile_phone is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("mobile_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("mobile_phone =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("mobile_phone <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("mobile_phone >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_phone >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("mobile_phone <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("mobile_phone <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("mobile_phone like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("mobile_phone not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("mobile_phone in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("mobile_phone not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("mobile_phone between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("mobile_phone not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionIsNull() {
            addCriterion("regional_distribution is null");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionIsNotNull() {
            addCriterion("regional_distribution is not null");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionEqualTo(String value) {
            addCriterion("regional_distribution =", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionNotEqualTo(String value) {
            addCriterion("regional_distribution <>", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionGreaterThan(String value) {
            addCriterion("regional_distribution >", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionGreaterThanOrEqualTo(String value) {
            addCriterion("regional_distribution >=", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionLessThan(String value) {
            addCriterion("regional_distribution <", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionLessThanOrEqualTo(String value) {
            addCriterion("regional_distribution <=", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionLike(String value) {
            addCriterion("regional_distribution like", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionNotLike(String value) {
            addCriterion("regional_distribution not like", value, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionIn(List<String> values) {
            addCriterion("regional_distribution in", values, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionNotIn(List<String> values) {
            addCriterion("regional_distribution not in", values, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionBetween(String value1, String value2) {
            addCriterion("regional_distribution between", value1, value2, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andRegionalDistributionNotBetween(String value1, String value2) {
            addCriterion("regional_distribution not between", value1, value2, "regionalDistribution");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNull() {
            addCriterion("occupation is null");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNotNull() {
            addCriterion("occupation is not null");
            return (Criteria) this;
        }

        public Criteria andOccupationEqualTo(String value) {
            addCriterion("occupation =", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotEqualTo(String value) {
            addCriterion("occupation <>", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThan(String value) {
            addCriterion("occupation >", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThanOrEqualTo(String value) {
            addCriterion("occupation >=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThan(String value) {
            addCriterion("occupation <", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThanOrEqualTo(String value) {
            addCriterion("occupation <=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLike(String value) {
            addCriterion("occupation like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotLike(String value) {
            addCriterion("occupation not like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationIn(List<String> values) {
            addCriterion("occupation in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotIn(List<String> values) {
            addCriterion("occupation not in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationBetween(String value1, String value2) {
            addCriterion("occupation between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotBetween(String value1, String value2) {
            addCriterion("occupation not between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNull() {
            addCriterion("work_unit is null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNotNull() {
            addCriterion("work_unit is not null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitEqualTo(String value) {
            addCriterion("work_unit =", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotEqualTo(String value) {
            addCriterion("work_unit <>", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThan(String value) {
            addCriterion("work_unit >", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThanOrEqualTo(String value) {
            addCriterion("work_unit >=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThan(String value) {
            addCriterion("work_unit <", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThanOrEqualTo(String value) {
            addCriterion("work_unit <=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLike(String value) {
            addCriterion("work_unit like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotLike(String value) {
            addCriterion("work_unit not like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIn(List<String> values) {
            addCriterion("work_unit in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotIn(List<String> values) {
            addCriterion("work_unit not in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitBetween(String value1, String value2) {
            addCriterion("work_unit between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotBetween(String value1, String value2) {
            addCriterion("work_unit not between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkAreaIsNull() {
            addCriterion("work_area is null");
            return (Criteria) this;
        }

        public Criteria andWorkAreaIsNotNull() {
            addCriterion("work_area is not null");
            return (Criteria) this;
        }

        public Criteria andWorkAreaEqualTo(String value) {
            addCriterion("work_area =", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaNotEqualTo(String value) {
            addCriterion("work_area <>", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaGreaterThan(String value) {
            addCriterion("work_area >", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaGreaterThanOrEqualTo(String value) {
            addCriterion("work_area >=", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaLessThan(String value) {
            addCriterion("work_area <", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaLessThanOrEqualTo(String value) {
            addCriterion("work_area <=", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaLike(String value) {
            addCriterion("work_area like", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaNotLike(String value) {
            addCriterion("work_area not like", value, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaIn(List<String> values) {
            addCriterion("work_area in", values, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaNotIn(List<String> values) {
            addCriterion("work_area not in", values, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaBetween(String value1, String value2) {
            addCriterion("work_area between", value1, value2, "workArea");
            return (Criteria) this;
        }

        public Criteria andWorkAreaNotBetween(String value1, String value2) {
            addCriterion("work_area not between", value1, value2, "workArea");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNull() {
            addCriterion("specialty is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNotNull() {
            addCriterion("specialty is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyEqualTo(String value) {
            addCriterion("specialty =", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotEqualTo(String value) {
            addCriterion("specialty <>", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThan(String value) {
            addCriterion("specialty >", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThanOrEqualTo(String value) {
            addCriterion("specialty >=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThan(String value) {
            addCriterion("specialty <", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThanOrEqualTo(String value) {
            addCriterion("specialty <=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLike(String value) {
            addCriterion("specialty like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotLike(String value) {
            addCriterion("specialty not like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIn(List<String> values) {
            addCriterion("specialty in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotIn(List<String> values) {
            addCriterion("specialty not in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyBetween(String value1, String value2) {
            addCriterion("specialty between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotBetween(String value1, String value2) {
            addCriterion("specialty not between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeIsNull() {
            addCriterion("term_start_time is null");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeIsNotNull() {
            addCriterion("term_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("term_start_time =", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("term_start_time <>", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("term_start_time >", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("term_start_time >=", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("term_start_time <", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("term_start_time <=", value, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("term_start_time in", values, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("term_start_time not in", values, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("term_start_time between", value1, value2, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andTermStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("term_start_time not between", value1, value2, "termStartTime");
            return (Criteria) this;
        }

        public Criteria andIsNewAddIsNull() {
            addCriterion("is_new_add is null");
            return (Criteria) this;
        }

        public Criteria andIsNewAddIsNotNull() {
            addCriterion("is_new_add is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewAddEqualTo(String value) {
            addCriterion("is_new_add =", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddNotEqualTo(String value) {
            addCriterion("is_new_add <>", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddGreaterThan(String value) {
            addCriterion("is_new_add >", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddGreaterThanOrEqualTo(String value) {
            addCriterion("is_new_add >=", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddLessThan(String value) {
            addCriterion("is_new_add <", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddLessThanOrEqualTo(String value) {
            addCriterion("is_new_add <=", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddLike(String value) {
            addCriterion("is_new_add like", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddNotLike(String value) {
            addCriterion("is_new_add not like", value, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddIn(List<String> values) {
            addCriterion("is_new_add in", values, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddNotIn(List<String> values) {
            addCriterion("is_new_add not in", values, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddBetween(String value1, String value2) {
            addCriterion("is_new_add between", value1, value2, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andIsNewAddNotBetween(String value1, String value2) {
            addCriterion("is_new_add not between", value1, value2, "isNewAdd");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNatureIsNull() {
            addCriterion("nature is null");
            return (Criteria) this;
        }

        public Criteria andNatureIsNotNull() {
            addCriterion("nature is not null");
            return (Criteria) this;
        }

        public Criteria andNatureEqualTo(String value) {
            addCriterion("nature =", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotEqualTo(String value) {
            addCriterion("nature <>", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThan(String value) {
            addCriterion("nature >", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureGreaterThanOrEqualTo(String value) {
            addCriterion("nature >=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThan(String value) {
            addCriterion("nature <", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLessThanOrEqualTo(String value) {
            addCriterion("nature <=", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureLike(String value) {
            addCriterion("nature like", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotLike(String value) {
            addCriterion("nature not like", value, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureIn(List<String> values) {
            addCriterion("nature in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotIn(List<String> values) {
            addCriterion("nature not in", values, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureBetween(String value1, String value2) {
            addCriterion("nature between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureNotBetween(String value1, String value2) {
            addCriterion("nature not between", value1, value2, "nature");
            return (Criteria) this;
        }

        public Criteria andNatureAIsNull() {
            addCriterion("nature_a is null");
            return (Criteria) this;
        }

        public Criteria andNatureAIsNotNull() {
            addCriterion("nature_a is not null");
            return (Criteria) this;
        }

        public Criteria andNatureAEqualTo(String value) {
            addCriterion("nature_a =", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureANotEqualTo(String value) {
            addCriterion("nature_a <>", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureAGreaterThan(String value) {
            addCriterion("nature_a >", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureAGreaterThanOrEqualTo(String value) {
            addCriterion("nature_a >=", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureALessThan(String value) {
            addCriterion("nature_a <", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureALessThanOrEqualTo(String value) {
            addCriterion("nature_a <=", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureALike(String value) {
            addCriterion("nature_a like", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureANotLike(String value) {
            addCriterion("nature_a not like", value, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureAIn(List<String> values) {
            addCriterion("nature_a in", values, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureANotIn(List<String> values) {
            addCriterion("nature_a not in", values, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureABetween(String value1, String value2) {
            addCriterion("nature_a between", value1, value2, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureANotBetween(String value1, String value2) {
            addCriterion("nature_a not between", value1, value2, "natureA");
            return (Criteria) this;
        }

        public Criteria andNatureBIsNull() {
            addCriterion("nature_b is null");
            return (Criteria) this;
        }

        public Criteria andNatureBIsNotNull() {
            addCriterion("nature_b is not null");
            return (Criteria) this;
        }

        public Criteria andNatureBEqualTo(String value) {
            addCriterion("nature_b =", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBNotEqualTo(String value) {
            addCriterion("nature_b <>", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBGreaterThan(String value) {
            addCriterion("nature_b >", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBGreaterThanOrEqualTo(String value) {
            addCriterion("nature_b >=", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBLessThan(String value) {
            addCriterion("nature_b <", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBLessThanOrEqualTo(String value) {
            addCriterion("nature_b <=", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBLike(String value) {
            addCriterion("nature_b like", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBNotLike(String value) {
            addCriterion("nature_b not like", value, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBIn(List<String> values) {
            addCriterion("nature_b in", values, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBNotIn(List<String> values) {
            addCriterion("nature_b not in", values, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBBetween(String value1, String value2) {
            addCriterion("nature_b between", value1, value2, "natureB");
            return (Criteria) this;
        }

        public Criteria andNatureBNotBetween(String value1, String value2) {
            addCriterion("nature_b not between", value1, value2, "natureB");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceIsNull() {
            addCriterion("annual_attendance is null");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceIsNotNull() {
            addCriterion("annual_attendance is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceEqualTo(Integer value) {
            addCriterion("annual_attendance =", value, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceNotEqualTo(Integer value) {
            addCriterion("annual_attendance <>", value, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceGreaterThan(Integer value) {
            addCriterion("annual_attendance >", value, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("annual_attendance >=", value, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceLessThan(Integer value) {
            addCriterion("annual_attendance <", value, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceLessThanOrEqualTo(Integer value) {
            addCriterion("annual_attendance <=", value, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceIn(List<Integer> values) {
            addCriterion("annual_attendance in", values, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceNotIn(List<Integer> values) {
            addCriterion("annual_attendance not in", values, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceBetween(Integer value1, Integer value2) {
            addCriterion("annual_attendance between", value1, value2, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andAnnualAttendanceNotBetween(Integer value1, Integer value2) {
            addCriterion("annual_attendance not between", value1, value2, "annualAttendance");
            return (Criteria) this;
        }

        public Criteria andJuryCostIsNull() {
            addCriterion("jury_cost is null");
            return (Criteria) this;
        }

        public Criteria andJuryCostIsNotNull() {
            addCriterion("jury_cost is not null");
            return (Criteria) this;
        }

        public Criteria andJuryCostEqualTo(Long value) {
            addCriterion("jury_cost =", value, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostNotEqualTo(Long value) {
            addCriterion("jury_cost <>", value, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostGreaterThan(Long value) {
            addCriterion("jury_cost >", value, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostGreaterThanOrEqualTo(Long value) {
            addCriterion("jury_cost >=", value, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostLessThan(Long value) {
            addCriterion("jury_cost <", value, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostLessThanOrEqualTo(Long value) {
            addCriterion("jury_cost <=", value, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostIn(List<Long> values) {
            addCriterion("jury_cost in", values, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostNotIn(List<Long> values) {
            addCriterion("jury_cost not in", values, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostBetween(Long value1, Long value2) {
            addCriterion("jury_cost between", value1, value2, "juryCost");
            return (Criteria) this;
        }

        public Criteria andJuryCostNotBetween(Long value1, Long value2) {
            addCriterion("jury_cost not between", value1, value2, "juryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostIsNull() {
            addCriterion("year_jury_cost is null");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostIsNotNull() {
            addCriterion("year_jury_cost is not null");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostEqualTo(Long value) {
            addCriterion("year_jury_cost =", value, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostNotEqualTo(Long value) {
            addCriterion("year_jury_cost <>", value, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostGreaterThan(Long value) {
            addCriterion("year_jury_cost >", value, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostGreaterThanOrEqualTo(Long value) {
            addCriterion("year_jury_cost >=", value, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostLessThan(Long value) {
            addCriterion("year_jury_cost <", value, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostLessThanOrEqualTo(Long value) {
            addCriterion("year_jury_cost <=", value, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostIn(List<Long> values) {
            addCriterion("year_jury_cost in", values, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostNotIn(List<Long> values) {
            addCriterion("year_jury_cost not in", values, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostBetween(Long value1, Long value2) {
            addCriterion("year_jury_cost between", value1, value2, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andYearJuryCostNotBetween(Long value1, Long value2) {
            addCriterion("year_jury_cost not between", value1, value2, "yearJuryCost");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExitWayIsNull() {
            addCriterion("exit_way is null");
            return (Criteria) this;
        }

        public Criteria andExitWayIsNotNull() {
            addCriterion("exit_way is not null");
            return (Criteria) this;
        }

        public Criteria andExitWayEqualTo(String value) {
            addCriterion("exit_way =", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayNotEqualTo(String value) {
            addCriterion("exit_way <>", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayGreaterThan(String value) {
            addCriterion("exit_way >", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayGreaterThanOrEqualTo(String value) {
            addCriterion("exit_way >=", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayLessThan(String value) {
            addCriterion("exit_way <", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayLessThanOrEqualTo(String value) {
            addCriterion("exit_way <=", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayLike(String value) {
            addCriterion("exit_way like", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayNotLike(String value) {
            addCriterion("exit_way not like", value, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayIn(List<String> values) {
            addCriterion("exit_way in", values, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayNotIn(List<String> values) {
            addCriterion("exit_way not in", values, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayBetween(String value1, String value2) {
            addCriterion("exit_way between", value1, value2, "exitWay");
            return (Criteria) this;
        }

        public Criteria andExitWayNotBetween(String value1, String value2) {
            addCriterion("exit_way not between", value1, value2, "exitWay");
            return (Criteria) this;
        }

        public Criteria andIdTypeIsNull() {
            addCriterion("id_type is null");
            return (Criteria) this;
        }

        public Criteria andIdTypeIsNotNull() {
            addCriterion("id_type is not null");
            return (Criteria) this;
        }

        public Criteria andIdTypeEqualTo(String value) {
            addCriterion("id_type =", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotEqualTo(String value) {
            addCriterion("id_type <>", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeGreaterThan(String value) {
            addCriterion("id_type >", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeGreaterThanOrEqualTo(String value) {
            addCriterion("id_type >=", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLessThan(String value) {
            addCriterion("id_type <", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLessThanOrEqualTo(String value) {
            addCriterion("id_type <=", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeLike(String value) {
            addCriterion("id_type like", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotLike(String value) {
            addCriterion("id_type not like", value, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeIn(List<String> values) {
            addCriterion("id_type in", values, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotIn(List<String> values) {
            addCriterion("id_type not in", values, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeBetween(String value1, String value2) {
            addCriterion("id_type between", value1, value2, "idType");
            return (Criteria) this;
        }

        public Criteria andIdTypeNotBetween(String value1, String value2) {
            addCriterion("id_type not between", value1, value2, "idType");
            return (Criteria) this;
        }

        public Criteria andStructureIdIsNull() {
            addCriterion("structure_id is null");
            return (Criteria) this;
        }

        public Criteria andStructureIdIsNotNull() {
            addCriterion("structure_id is not null");
            return (Criteria) this;
        }

        public Criteria andStructureIdEqualTo(String value) {
            addCriterion("structure_id =", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdNotEqualTo(String value) {
            addCriterion("structure_id <>", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdGreaterThan(String value) {
            addCriterion("structure_id >", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdGreaterThanOrEqualTo(String value) {
            addCriterion("structure_id >=", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdLessThan(String value) {
            addCriterion("structure_id <", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdLessThanOrEqualTo(String value) {
            addCriterion("structure_id <=", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdLike(String value) {
            addCriterion("structure_id like", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdNotLike(String value) {
            addCriterion("structure_id not like", value, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdIn(List<String> values) {
            addCriterion("structure_id in", values, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdNotIn(List<String> values) {
            addCriterion("structure_id not in", values, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdBetween(String value1, String value2) {
            addCriterion("structure_id between", value1, value2, "structureId");
            return (Criteria) this;
        }

        public Criteria andStructureIdNotBetween(String value1, String value2) {
            addCriterion("structure_id not between", value1, value2, "structureId");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Integer value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Integer value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Integer value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Integer value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Integer value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Integer> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Integer> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Integer value1, Integer value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoIsNull() {
            addCriterion("is_registed_diff_time_info is null");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoIsNotNull() {
            addCriterion("is_registed_diff_time_info is not null");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoEqualTo(Integer value) {
            addCriterion("is_registed_diff_time_info =", value, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoNotEqualTo(Integer value) {
            addCriterion("is_registed_diff_time_info <>", value, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoGreaterThan(Integer value) {
            addCriterion("is_registed_diff_time_info >", value, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_registed_diff_time_info >=", value, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoLessThan(Integer value) {
            addCriterion("is_registed_diff_time_info <", value, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoLessThanOrEqualTo(Integer value) {
            addCriterion("is_registed_diff_time_info <=", value, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoIn(List<Integer> values) {
            addCriterion("is_registed_diff_time_info in", values, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoNotIn(List<Integer> values) {
            addCriterion("is_registed_diff_time_info not in", values, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoBetween(Integer value1, Integer value2) {
            addCriterion("is_registed_diff_time_info between", value1, value2, "isRegistedDiffTimeInfo");
            return (Criteria) this;
        }

        public Criteria andIsRegistedDiffTimeInfoNotBetween(Integer value1, Integer value2) {
            addCriterion("is_registed_diff_time_info not between", value1, value2, "isRegistedDiffTimeInfo");
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