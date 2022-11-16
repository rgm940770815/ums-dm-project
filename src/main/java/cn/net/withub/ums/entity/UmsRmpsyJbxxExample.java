package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsRmpsyJbxxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsRmpsyJbxxExample() {
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

        public Criteria andCourtnoIsNull() {
            addCriterion("courtNo is null");
            return (Criteria) this;
        }

        public Criteria andCourtnoIsNotNull() {
            addCriterion("courtNo is not null");
            return (Criteria) this;
        }

        public Criteria andCourtnoEqualTo(Integer value) {
            addCriterion("courtNo =", value, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoNotEqualTo(Integer value) {
            addCriterion("courtNo <>", value, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoGreaterThan(Integer value) {
            addCriterion("courtNo >", value, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("courtNo >=", value, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoLessThan(Integer value) {
            addCriterion("courtNo <", value, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoLessThanOrEqualTo(Integer value) {
            addCriterion("courtNo <=", value, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoIn(List<Integer> values) {
            addCriterion("courtNo in", values, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoNotIn(List<Integer> values) {
            addCriterion("courtNo not in", values, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoBetween(Integer value1, Integer value2) {
            addCriterion("courtNo between", value1, value2, "courtno");
            return (Criteria) this;
        }

        public Criteria andCourtnoNotBetween(Integer value1, Integer value2) {
            addCriterion("courtNo not between", value1, value2, "courtno");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
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
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idcard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNotNull() {
            addCriterion("idcard is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEqualTo(String value) {
            addCriterion("idcard =", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotEqualTo(String value) {
            addCriterion("idcard <>", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThan(String value) {
            addCriterion("idcard >", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("idcard >=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThan(String value) {
            addCriterion("idcard <", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLessThanOrEqualTo(String value) {
            addCriterion("idcard <=", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardLike(String value) {
            addCriterion("idcard like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotLike(String value) {
            addCriterion("idcard not like", value, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardIn(List<String> values) {
            addCriterion("idcard in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotIn(List<String> values) {
            addCriterion("idcard not in", values, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardBetween(String value1, String value2) {
            addCriterion("idcard between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andIdcardNotBetween(String value1, String value2) {
            addCriterion("idcard not between", value1, value2, "idcard");
            return (Criteria) this;
        }

        public Criteria andNationIsNull() {
            addCriterion("nation is null");
            return (Criteria) this;
        }

        public Criteria andNationIsNotNull() {
            addCriterion("nation is not null");
            return (Criteria) this;
        }

        public Criteria andNationEqualTo(String value) {
            addCriterion("nation =", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotEqualTo(String value) {
            addCriterion("nation <>", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThan(String value) {
            addCriterion("nation >", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThanOrEqualTo(String value) {
            addCriterion("nation >=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThan(String value) {
            addCriterion("nation <", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThanOrEqualTo(String value) {
            addCriterion("nation <=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLike(String value) {
            addCriterion("nation like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotLike(String value) {
            addCriterion("nation not like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationIn(List<String> values) {
            addCriterion("nation in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotIn(List<String> values) {
            addCriterion("nation not in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationBetween(String value1, String value2) {
            addCriterion("nation between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotBetween(String value1, String value2) {
            addCriterion("nation not between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andPoliticalIsNull() {
            addCriterion("political is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalIsNotNull() {
            addCriterion("political is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalEqualTo(String value) {
            addCriterion("political =", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotEqualTo(String value) {
            addCriterion("political <>", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalGreaterThan(String value) {
            addCriterion("political >", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalGreaterThanOrEqualTo(String value) {
            addCriterion("political >=", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalLessThan(String value) {
            addCriterion("political <", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalLessThanOrEqualTo(String value) {
            addCriterion("political <=", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalLike(String value) {
            addCriterion("political like", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotLike(String value) {
            addCriterion("political not like", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalIn(List<String> values) {
            addCriterion("political in", values, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotIn(List<String> values) {
            addCriterion("political not in", values, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalBetween(String value1, String value2) {
            addCriterion("political between", value1, value2, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotBetween(String value1, String value2) {
            addCriterion("political not between", value1, value2, "political");
            return (Criteria) this;
        }

        public Criteria andHomeadressIsNull() {
            addCriterion("homeAdress is null");
            return (Criteria) this;
        }

        public Criteria andHomeadressIsNotNull() {
            addCriterion("homeAdress is not null");
            return (Criteria) this;
        }

        public Criteria andHomeadressEqualTo(String value) {
            addCriterion("homeAdress =", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressNotEqualTo(String value) {
            addCriterion("homeAdress <>", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressGreaterThan(String value) {
            addCriterion("homeAdress >", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressGreaterThanOrEqualTo(String value) {
            addCriterion("homeAdress >=", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressLessThan(String value) {
            addCriterion("homeAdress <", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressLessThanOrEqualTo(String value) {
            addCriterion("homeAdress <=", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressLike(String value) {
            addCriterion("homeAdress like", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressNotLike(String value) {
            addCriterion("homeAdress not like", value, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressIn(List<String> values) {
            addCriterion("homeAdress in", values, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressNotIn(List<String> values) {
            addCriterion("homeAdress not in", values, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressBetween(String value1, String value2) {
            addCriterion("homeAdress between", value1, value2, "homeadress");
            return (Criteria) this;
        }

        public Criteria andHomeadressNotBetween(String value1, String value2) {
            addCriterion("homeAdress not between", value1, value2, "homeadress");
            return (Criteria) this;
        }

        public Criteria andFixedtelIsNull() {
            addCriterion("fixedTel is null");
            return (Criteria) this;
        }

        public Criteria andFixedtelIsNotNull() {
            addCriterion("fixedTel is not null");
            return (Criteria) this;
        }

        public Criteria andFixedtelEqualTo(String value) {
            addCriterion("fixedTel =", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelNotEqualTo(String value) {
            addCriterion("fixedTel <>", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelGreaterThan(String value) {
            addCriterion("fixedTel >", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelGreaterThanOrEqualTo(String value) {
            addCriterion("fixedTel >=", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelLessThan(String value) {
            addCriterion("fixedTel <", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelLessThanOrEqualTo(String value) {
            addCriterion("fixedTel <=", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelLike(String value) {
            addCriterion("fixedTel like", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelNotLike(String value) {
            addCriterion("fixedTel not like", value, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelIn(List<String> values) {
            addCriterion("fixedTel in", values, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelNotIn(List<String> values) {
            addCriterion("fixedTel not in", values, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelBetween(String value1, String value2) {
            addCriterion("fixedTel between", value1, value2, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andFixedtelNotBetween(String value1, String value2) {
            addCriterion("fixedTel not between", value1, value2, "fixedtel");
            return (Criteria) this;
        }

        public Criteria andPhonenumIsNull() {
            addCriterion("phoneNum is null");
            return (Criteria) this;
        }

        public Criteria andPhonenumIsNotNull() {
            addCriterion("phoneNum is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenumEqualTo(String value) {
            addCriterion("phoneNum =", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotEqualTo(String value) {
            addCriterion("phoneNum <>", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThan(String value) {
            addCriterion("phoneNum >", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThanOrEqualTo(String value) {
            addCriterion("phoneNum >=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThan(String value) {
            addCriterion("phoneNum <", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThanOrEqualTo(String value) {
            addCriterion("phoneNum <=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLike(String value) {
            addCriterion("phoneNum like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotLike(String value) {
            addCriterion("phoneNum not like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumIn(List<String> values) {
            addCriterion("phoneNum in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotIn(List<String> values) {
            addCriterion("phoneNum not in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumBetween(String value1, String value2) {
            addCriterion("phoneNum between", value1, value2, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotBetween(String value1, String value2) {
            addCriterion("phoneNum not between", value1, value2, "phonenum");
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

        public Criteria andAreadistributionIsNull() {
            addCriterion("areaDistribution is null");
            return (Criteria) this;
        }

        public Criteria andAreadistributionIsNotNull() {
            addCriterion("areaDistribution is not null");
            return (Criteria) this;
        }

        public Criteria andAreadistributionEqualTo(String value) {
            addCriterion("areaDistribution =", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionNotEqualTo(String value) {
            addCriterion("areaDistribution <>", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionGreaterThan(String value) {
            addCriterion("areaDistribution >", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionGreaterThanOrEqualTo(String value) {
            addCriterion("areaDistribution >=", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionLessThan(String value) {
            addCriterion("areaDistribution <", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionLessThanOrEqualTo(String value) {
            addCriterion("areaDistribution <=", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionLike(String value) {
            addCriterion("areaDistribution like", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionNotLike(String value) {
            addCriterion("areaDistribution not like", value, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionIn(List<String> values) {
            addCriterion("areaDistribution in", values, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionNotIn(List<String> values) {
            addCriterion("areaDistribution not in", values, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionBetween(String value1, String value2) {
            addCriterion("areaDistribution between", value1, value2, "areadistribution");
            return (Criteria) this;
        }

        public Criteria andAreadistributionNotBetween(String value1, String value2) {
            addCriterion("areaDistribution not between", value1, value2, "areadistribution");
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

        public Criteria andWorkcompanyIsNull() {
            addCriterion("workCompany is null");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyIsNotNull() {
            addCriterion("workCompany is not null");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyEqualTo(String value) {
            addCriterion("workCompany =", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyNotEqualTo(String value) {
            addCriterion("workCompany <>", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyGreaterThan(String value) {
            addCriterion("workCompany >", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyGreaterThanOrEqualTo(String value) {
            addCriterion("workCompany >=", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyLessThan(String value) {
            addCriterion("workCompany <", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyLessThanOrEqualTo(String value) {
            addCriterion("workCompany <=", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyLike(String value) {
            addCriterion("workCompany like", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyNotLike(String value) {
            addCriterion("workCompany not like", value, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyIn(List<String> values) {
            addCriterion("workCompany in", values, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyNotIn(List<String> values) {
            addCriterion("workCompany not in", values, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyBetween(String value1, String value2) {
            addCriterion("workCompany between", value1, value2, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkcompanyNotBetween(String value1, String value2) {
            addCriterion("workCompany not between", value1, value2, "workcompany");
            return (Criteria) this;
        }

        public Criteria andWorkareaIsNull() {
            addCriterion("workArea is null");
            return (Criteria) this;
        }

        public Criteria andWorkareaIsNotNull() {
            addCriterion("workArea is not null");
            return (Criteria) this;
        }

        public Criteria andWorkareaEqualTo(String value) {
            addCriterion("workArea =", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaNotEqualTo(String value) {
            addCriterion("workArea <>", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaGreaterThan(String value) {
            addCriterion("workArea >", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaGreaterThanOrEqualTo(String value) {
            addCriterion("workArea >=", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaLessThan(String value) {
            addCriterion("workArea <", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaLessThanOrEqualTo(String value) {
            addCriterion("workArea <=", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaLike(String value) {
            addCriterion("workArea like", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaNotLike(String value) {
            addCriterion("workArea not like", value, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaIn(List<String> values) {
            addCriterion("workArea in", values, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaNotIn(List<String> values) {
            addCriterion("workArea not in", values, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaBetween(String value1, String value2) {
            addCriterion("workArea between", value1, value2, "workarea");
            return (Criteria) this;
        }

        public Criteria andWorkareaNotBetween(String value1, String value2) {
            addCriterion("workArea not between", value1, value2, "workarea");
            return (Criteria) this;
        }

        public Criteria andZytcIsNull() {
            addCriterion("zytc is null");
            return (Criteria) this;
        }

        public Criteria andZytcIsNotNull() {
            addCriterion("zytc is not null");
            return (Criteria) this;
        }

        public Criteria andZytcEqualTo(String value) {
            addCriterion("zytc =", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcNotEqualTo(String value) {
            addCriterion("zytc <>", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcGreaterThan(String value) {
            addCriterion("zytc >", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcGreaterThanOrEqualTo(String value) {
            addCriterion("zytc >=", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcLessThan(String value) {
            addCriterion("zytc <", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcLessThanOrEqualTo(String value) {
            addCriterion("zytc <=", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcLike(String value) {
            addCriterion("zytc like", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcNotLike(String value) {
            addCriterion("zytc not like", value, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcIn(List<String> values) {
            addCriterion("zytc in", values, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcNotIn(List<String> values) {
            addCriterion("zytc not in", values, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcBetween(String value1, String value2) {
            addCriterion("zytc between", value1, value2, "zytc");
            return (Criteria) this;
        }

        public Criteria andZytcNotBetween(String value1, String value2) {
            addCriterion("zytc not between", value1, value2, "zytc");
            return (Criteria) this;
        }

        public Criteria andRqkssjIsNull() {
            addCriterion("rqkssj is null");
            return (Criteria) this;
        }

        public Criteria andRqkssjIsNotNull() {
            addCriterion("rqkssj is not null");
            return (Criteria) this;
        }

        public Criteria andRqkssjEqualTo(Date value) {
            addCriterion("rqkssj =", value, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjNotEqualTo(Date value) {
            addCriterion("rqkssj <>", value, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjGreaterThan(Date value) {
            addCriterion("rqkssj >", value, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjGreaterThanOrEqualTo(Date value) {
            addCriterion("rqkssj >=", value, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjLessThan(Date value) {
            addCriterion("rqkssj <", value, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjLessThanOrEqualTo(Date value) {
            addCriterion("rqkssj <=", value, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjIn(List<Date> values) {
            addCriterion("rqkssj in", values, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjNotIn(List<Date> values) {
            addCriterion("rqkssj not in", values, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjBetween(Date value1, Date value2) {
            addCriterion("rqkssj between", value1, value2, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andRqkssjNotBetween(Date value1, Date value2) {
            addCriterion("rqkssj not between", value1, value2, "rqkssj");
            return (Criteria) this;
        }

        public Criteria andSfxzIsNull() {
            addCriterion("sfxz is null");
            return (Criteria) this;
        }

        public Criteria andSfxzIsNotNull() {
            addCriterion("sfxz is not null");
            return (Criteria) this;
        }

        public Criteria andSfxzEqualTo(String value) {
            addCriterion("sfxz =", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzNotEqualTo(String value) {
            addCriterion("sfxz <>", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzGreaterThan(String value) {
            addCriterion("sfxz >", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzGreaterThanOrEqualTo(String value) {
            addCriterion("sfxz >=", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzLessThan(String value) {
            addCriterion("sfxz <", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzLessThanOrEqualTo(String value) {
            addCriterion("sfxz <=", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzLike(String value) {
            addCriterion("sfxz like", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzNotLike(String value) {
            addCriterion("sfxz not like", value, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzIn(List<String> values) {
            addCriterion("sfxz in", values, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzNotIn(List<String> values) {
            addCriterion("sfxz not in", values, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzBetween(String value1, String value2) {
            addCriterion("sfxz between", value1, value2, "sfxz");
            return (Criteria) this;
        }

        public Criteria andSfxzNotBetween(String value1, String value2) {
            addCriterion("sfxz not between", value1, value2, "sfxz");
            return (Criteria) this;
        }

        public Criteria andJsIsNull() {
            addCriterion("js is null");
            return (Criteria) this;
        }

        public Criteria andJsIsNotNull() {
            addCriterion("js is not null");
            return (Criteria) this;
        }

        public Criteria andJsEqualTo(String value) {
            addCriterion("js =", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsNotEqualTo(String value) {
            addCriterion("js <>", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsGreaterThan(String value) {
            addCriterion("js >", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsGreaterThanOrEqualTo(String value) {
            addCriterion("js >=", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsLessThan(String value) {
            addCriterion("js <", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsLessThanOrEqualTo(String value) {
            addCriterion("js <=", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsLike(String value) {
            addCriterion("js like", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsNotLike(String value) {
            addCriterion("js not like", value, "js");
            return (Criteria) this;
        }

        public Criteria andJsIn(List<String> values) {
            addCriterion("js in", values, "js");
            return (Criteria) this;
        }

        public Criteria andJsNotIn(List<String> values) {
            addCriterion("js not in", values, "js");
            return (Criteria) this;
        }

        public Criteria andJsBetween(String value1, String value2) {
            addCriterion("js between", value1, value2, "js");
            return (Criteria) this;
        }

        public Criteria andJsNotBetween(String value1, String value2) {
            addCriterion("js not between", value1, value2, "js");
            return (Criteria) this;
        }

        public Criteria andCyajxzIsNull() {
            addCriterion("cyajxz is null");
            return (Criteria) this;
        }

        public Criteria andCyajxzIsNotNull() {
            addCriterion("cyajxz is not null");
            return (Criteria) this;
        }

        public Criteria andCyajxzEqualTo(String value) {
            addCriterion("cyajxz =", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzNotEqualTo(String value) {
            addCriterion("cyajxz <>", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzGreaterThan(String value) {
            addCriterion("cyajxz >", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzGreaterThanOrEqualTo(String value) {
            addCriterion("cyajxz >=", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzLessThan(String value) {
            addCriterion("cyajxz <", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzLessThanOrEqualTo(String value) {
            addCriterion("cyajxz <=", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzLike(String value) {
            addCriterion("cyajxz like", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzNotLike(String value) {
            addCriterion("cyajxz not like", value, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzIn(List<String> values) {
            addCriterion("cyajxz in", values, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzNotIn(List<String> values) {
            addCriterion("cyajxz not in", values, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzBetween(String value1, String value2) {
            addCriterion("cyajxz between", value1, value2, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzNotBetween(String value1, String value2) {
            addCriterion("cyajxz not between", value1, value2, "cyajxz");
            return (Criteria) this;
        }

        public Criteria andCyajxzaIsNull() {
            addCriterion("cyajxzA is null");
            return (Criteria) this;
        }

        public Criteria andCyajxzaIsNotNull() {
            addCriterion("cyajxzA is not null");
            return (Criteria) this;
        }

        public Criteria andCyajxzaEqualTo(String value) {
            addCriterion("cyajxzA =", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaNotEqualTo(String value) {
            addCriterion("cyajxzA <>", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaGreaterThan(String value) {
            addCriterion("cyajxzA >", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaGreaterThanOrEqualTo(String value) {
            addCriterion("cyajxzA >=", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaLessThan(String value) {
            addCriterion("cyajxzA <", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaLessThanOrEqualTo(String value) {
            addCriterion("cyajxzA <=", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaLike(String value) {
            addCriterion("cyajxzA like", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaNotLike(String value) {
            addCriterion("cyajxzA not like", value, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaIn(List<String> values) {
            addCriterion("cyajxzA in", values, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaNotIn(List<String> values) {
            addCriterion("cyajxzA not in", values, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaBetween(String value1, String value2) {
            addCriterion("cyajxzA between", value1, value2, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzaNotBetween(String value1, String value2) {
            addCriterion("cyajxzA not between", value1, value2, "cyajxza");
            return (Criteria) this;
        }

        public Criteria andCyajxzbIsNull() {
            addCriterion("cyajxzB is null");
            return (Criteria) this;
        }

        public Criteria andCyajxzbIsNotNull() {
            addCriterion("cyajxzB is not null");
            return (Criteria) this;
        }

        public Criteria andCyajxzbEqualTo(String value) {
            addCriterion("cyajxzB =", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbNotEqualTo(String value) {
            addCriterion("cyajxzB <>", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbGreaterThan(String value) {
            addCriterion("cyajxzB >", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbGreaterThanOrEqualTo(String value) {
            addCriterion("cyajxzB >=", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbLessThan(String value) {
            addCriterion("cyajxzB <", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbLessThanOrEqualTo(String value) {
            addCriterion("cyajxzB <=", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbLike(String value) {
            addCriterion("cyajxzB like", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbNotLike(String value) {
            addCriterion("cyajxzB not like", value, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbIn(List<String> values) {
            addCriterion("cyajxzB in", values, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbNotIn(List<String> values) {
            addCriterion("cyajxzB not in", values, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbBetween(String value1, String value2) {
            addCriterion("cyajxzB between", value1, value2, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andCyajxzbNotBetween(String value1, String value2) {
            addCriterion("cyajxzB not between", value1, value2, "cyajxzb");
            return (Criteria) this;
        }

        public Criteria andNdcscsIsNull() {
            addCriterion("ndcscs is null");
            return (Criteria) this;
        }

        public Criteria andNdcscsIsNotNull() {
            addCriterion("ndcscs is not null");
            return (Criteria) this;
        }

        public Criteria andNdcscsEqualTo(String value) {
            addCriterion("ndcscs =", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsNotEqualTo(String value) {
            addCriterion("ndcscs <>", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsGreaterThan(String value) {
            addCriterion("ndcscs >", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsGreaterThanOrEqualTo(String value) {
            addCriterion("ndcscs >=", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsLessThan(String value) {
            addCriterion("ndcscs <", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsLessThanOrEqualTo(String value) {
            addCriterion("ndcscs <=", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsLike(String value) {
            addCriterion("ndcscs like", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsNotLike(String value) {
            addCriterion("ndcscs not like", value, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsIn(List<String> values) {
            addCriterion("ndcscs in", values, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsNotIn(List<String> values) {
            addCriterion("ndcscs not in", values, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsBetween(String value1, String value2) {
            addCriterion("ndcscs between", value1, value2, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andNdcscsNotBetween(String value1, String value2) {
            addCriterion("ndcscs not between", value1, value2, "ndcscs");
            return (Criteria) this;
        }

        public Criteria andPsyztIsNull() {
            addCriterion("psyzt is null");
            return (Criteria) this;
        }

        public Criteria andPsyztIsNotNull() {
            addCriterion("psyzt is not null");
            return (Criteria) this;
        }

        public Criteria andPsyztEqualTo(String value) {
            addCriterion("psyzt =", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztNotEqualTo(String value) {
            addCriterion("psyzt <>", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztGreaterThan(String value) {
            addCriterion("psyzt >", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztGreaterThanOrEqualTo(String value) {
            addCriterion("psyzt >=", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztLessThan(String value) {
            addCriterion("psyzt <", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztLessThanOrEqualTo(String value) {
            addCriterion("psyzt <=", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztLike(String value) {
            addCriterion("psyzt like", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztNotLike(String value) {
            addCriterion("psyzt not like", value, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztIn(List<String> values) {
            addCriterion("psyzt in", values, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztNotIn(List<String> values) {
            addCriterion("psyzt not in", values, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztBetween(String value1, String value2) {
            addCriterion("psyzt between", value1, value2, "psyzt");
            return (Criteria) this;
        }

        public Criteria andPsyztNotBetween(String value1, String value2) {
            addCriterion("psyzt not between", value1, value2, "psyzt");
            return (Criteria) this;
        }

        public Criteria andTcfsIsNull() {
            addCriterion("tcfs is null");
            return (Criteria) this;
        }

        public Criteria andTcfsIsNotNull() {
            addCriterion("tcfs is not null");
            return (Criteria) this;
        }

        public Criteria andTcfsEqualTo(String value) {
            addCriterion("tcfs =", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsNotEqualTo(String value) {
            addCriterion("tcfs <>", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsGreaterThan(String value) {
            addCriterion("tcfs >", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsGreaterThanOrEqualTo(String value) {
            addCriterion("tcfs >=", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsLessThan(String value) {
            addCriterion("tcfs <", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsLessThanOrEqualTo(String value) {
            addCriterion("tcfs <=", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsLike(String value) {
            addCriterion("tcfs like", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsNotLike(String value) {
            addCriterion("tcfs not like", value, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsIn(List<String> values) {
            addCriterion("tcfs in", values, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsNotIn(List<String> values) {
            addCriterion("tcfs not in", values, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsBetween(String value1, String value2) {
            addCriterion("tcfs between", value1, value2, "tcfs");
            return (Criteria) this;
        }

        public Criteria andTcfsNotBetween(String value1, String value2) {
            addCriterion("tcfs not between", value1, value2, "tcfs");
            return (Criteria) this;
        }

        public Criteria andSfzlxIsNull() {
            addCriterion("sfzlx is null");
            return (Criteria) this;
        }

        public Criteria andSfzlxIsNotNull() {
            addCriterion("sfzlx is not null");
            return (Criteria) this;
        }

        public Criteria andSfzlxEqualTo(String value) {
            addCriterion("sfzlx =", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxNotEqualTo(String value) {
            addCriterion("sfzlx <>", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxGreaterThan(String value) {
            addCriterion("sfzlx >", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxGreaterThanOrEqualTo(String value) {
            addCriterion("sfzlx >=", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxLessThan(String value) {
            addCriterion("sfzlx <", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxLessThanOrEqualTo(String value) {
            addCriterion("sfzlx <=", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxLike(String value) {
            addCriterion("sfzlx like", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxNotLike(String value) {
            addCriterion("sfzlx not like", value, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxIn(List<String> values) {
            addCriterion("sfzlx in", values, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxNotIn(List<String> values) {
            addCriterion("sfzlx not in", values, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxBetween(String value1, String value2) {
            addCriterion("sfzlx between", value1, value2, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andSfzlxNotBetween(String value1, String value2) {
            addCriterion("sfzlx not between", value1, value2, "sfzlx");
            return (Criteria) this;
        }

        public Criteria andJgidIsNull() {
            addCriterion("jgID is null");
            return (Criteria) this;
        }

        public Criteria andJgidIsNotNull() {
            addCriterion("jgID is not null");
            return (Criteria) this;
        }

        public Criteria andJgidEqualTo(String value) {
            addCriterion("jgID =", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidNotEqualTo(String value) {
            addCriterion("jgID <>", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidGreaterThan(String value) {
            addCriterion("jgID >", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidGreaterThanOrEqualTo(String value) {
            addCriterion("jgID >=", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidLessThan(String value) {
            addCriterion("jgID <", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidLessThanOrEqualTo(String value) {
            addCriterion("jgID <=", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidLike(String value) {
            addCriterion("jgID like", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidNotLike(String value) {
            addCriterion("jgID not like", value, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidIn(List<String> values) {
            addCriterion("jgID in", values, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidNotIn(List<String> values) {
            addCriterion("jgID not in", values, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidBetween(String value1, String value2) {
            addCriterion("jgID between", value1, value2, "jgid");
            return (Criteria) this;
        }

        public Criteria andJgidNotBetween(String value1, String value2) {
            addCriterion("jgID not between", value1, value2, "jgid");
            return (Criteria) this;
        }

        public Criteria andScbjIsNull() {
            addCriterion("scbj is null");
            return (Criteria) this;
        }

        public Criteria andScbjIsNotNull() {
            addCriterion("scbj is not null");
            return (Criteria) this;
        }

        public Criteria andScbjEqualTo(Integer value) {
            addCriterion("scbj =", value, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjNotEqualTo(Integer value) {
            addCriterion("scbj <>", value, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjGreaterThan(Integer value) {
            addCriterion("scbj >", value, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjGreaterThanOrEqualTo(Integer value) {
            addCriterion("scbj >=", value, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjLessThan(Integer value) {
            addCriterion("scbj <", value, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjLessThanOrEqualTo(Integer value) {
            addCriterion("scbj <=", value, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjIn(List<Integer> values) {
            addCriterion("scbj in", values, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjNotIn(List<Integer> values) {
            addCriterion("scbj not in", values, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjBetween(Integer value1, Integer value2) {
            addCriterion("scbj between", value1, value2, "scbj");
            return (Criteria) this;
        }

        public Criteria andScbjNotBetween(Integer value1, Integer value2) {
            addCriterion("scbj not between", value1, value2, "scbj");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxIsNull() {
            addCriterion("sfdjcsxx is null");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxIsNotNull() {
            addCriterion("sfdjcsxx is not null");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxEqualTo(Integer value) {
            addCriterion("sfdjcsxx =", value, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxNotEqualTo(Integer value) {
            addCriterion("sfdjcsxx <>", value, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxGreaterThan(Integer value) {
            addCriterion("sfdjcsxx >", value, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxGreaterThanOrEqualTo(Integer value) {
            addCriterion("sfdjcsxx >=", value, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxLessThan(Integer value) {
            addCriterion("sfdjcsxx <", value, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxLessThanOrEqualTo(Integer value) {
            addCriterion("sfdjcsxx <=", value, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxIn(List<Integer> values) {
            addCriterion("sfdjcsxx in", values, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxNotIn(List<Integer> values) {
            addCriterion("sfdjcsxx not in", values, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxBetween(Integer value1, Integer value2) {
            addCriterion("sfdjcsxx between", value1, value2, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andSfdjcsxxNotBetween(Integer value1, Integer value2) {
            addCriterion("sfdjcsxx not between", value1, value2, "sfdjcsxx");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andNdpsfyIsNull() {
            addCriterion("ndpsfy is null");
            return (Criteria) this;
        }

        public Criteria andNdpsfyIsNotNull() {
            addCriterion("ndpsfy is not null");
            return (Criteria) this;
        }

        public Criteria andNdpsfyEqualTo(String value) {
            addCriterion("ndpsfy =", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyNotEqualTo(String value) {
            addCriterion("ndpsfy <>", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyGreaterThan(String value) {
            addCriterion("ndpsfy >", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyGreaterThanOrEqualTo(String value) {
            addCriterion("ndpsfy >=", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyLessThan(String value) {
            addCriterion("ndpsfy <", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyLessThanOrEqualTo(String value) {
            addCriterion("ndpsfy <=", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyLike(String value) {
            addCriterion("ndpsfy like", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyNotLike(String value) {
            addCriterion("ndpsfy not like", value, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyIn(List<String> values) {
            addCriterion("ndpsfy in", values, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyNotIn(List<String> values) {
            addCriterion("ndpsfy not in", values, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyBetween(String value1, String value2) {
            addCriterion("ndpsfy between", value1, value2, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andNdpsfyNotBetween(String value1, String value2) {
            addCriterion("ndpsfy not between", value1, value2, "ndpsfy");
            return (Criteria) this;
        }

        public Criteria andWorkareanameIsNull() {
            addCriterion("workareaname is null");
            return (Criteria) this;
        }

        public Criteria andWorkareanameIsNotNull() {
            addCriterion("workareaname is not null");
            return (Criteria) this;
        }

        public Criteria andWorkareanameEqualTo(String value) {
            addCriterion("workareaname =", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameNotEqualTo(String value) {
            addCriterion("workareaname <>", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameGreaterThan(String value) {
            addCriterion("workareaname >", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameGreaterThanOrEqualTo(String value) {
            addCriterion("workareaname >=", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameLessThan(String value) {
            addCriterion("workareaname <", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameLessThanOrEqualTo(String value) {
            addCriterion("workareaname <=", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameLike(String value) {
            addCriterion("workareaname like", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameNotLike(String value) {
            addCriterion("workareaname not like", value, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameIn(List<String> values) {
            addCriterion("workareaname in", values, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameNotIn(List<String> values) {
            addCriterion("workareaname not in", values, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameBetween(String value1, String value2) {
            addCriterion("workareaname between", value1, value2, "workareaname");
            return (Criteria) this;
        }

        public Criteria andWorkareanameNotBetween(String value1, String value2) {
            addCriterion("workareaname not between", value1, value2, "workareaname");
            return (Criteria) this;
        }

        public Criteria andCourtcodeIsNull() {
            addCriterion("courtcode is null");
            return (Criteria) this;
        }

        public Criteria andCourtcodeIsNotNull() {
            addCriterion("courtcode is not null");
            return (Criteria) this;
        }

        public Criteria andCourtcodeEqualTo(String value) {
            addCriterion("courtcode =", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeNotEqualTo(String value) {
            addCriterion("courtcode <>", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeGreaterThan(String value) {
            addCriterion("courtcode >", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeGreaterThanOrEqualTo(String value) {
            addCriterion("courtcode >=", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeLessThan(String value) {
            addCriterion("courtcode <", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeLessThanOrEqualTo(String value) {
            addCriterion("courtcode <=", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeLike(String value) {
            addCriterion("courtcode like", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeNotLike(String value) {
            addCriterion("courtcode not like", value, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeIn(List<String> values) {
            addCriterion("courtcode in", values, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeNotIn(List<String> values) {
            addCriterion("courtcode not in", values, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeBetween(String value1, String value2) {
            addCriterion("courtcode between", value1, value2, "courtcode");
            return (Criteria) this;
        }

        public Criteria andCourtcodeNotBetween(String value1, String value2) {
            addCriterion("courtcode not between", value1, value2, "courtcode");
            return (Criteria) this;
        }

        public Criteria andUsernoIsNull() {
            addCriterion("userno is null");
            return (Criteria) this;
        }

        public Criteria andUsernoIsNotNull() {
            addCriterion("userno is not null");
            return (Criteria) this;
        }

        public Criteria andUsernoEqualTo(Integer value) {
            addCriterion("userno =", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoNotEqualTo(Integer value) {
            addCriterion("userno <>", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoGreaterThan(Integer value) {
            addCriterion("userno >", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoGreaterThanOrEqualTo(Integer value) {
            addCriterion("userno >=", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoLessThan(Integer value) {
            addCriterion("userno <", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoLessThanOrEqualTo(Integer value) {
            addCriterion("userno <=", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoIn(List<Integer> values) {
            addCriterion("userno in", values, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoNotIn(List<Integer> values) {
            addCriterion("userno not in", values, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoBetween(Integer value1, Integer value2) {
            addCriterion("userno between", value1, value2, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoNotBetween(Integer value1, Integer value2) {
            addCriterion("userno not between", value1, value2, "userno");
            return (Criteria) this;
        }

        public Criteria andSortnoIsNull() {
            addCriterion("sortNo is null");
            return (Criteria) this;
        }

        public Criteria andSortnoIsNotNull() {
            addCriterion("sortNo is not null");
            return (Criteria) this;
        }

        public Criteria andSortnoEqualTo(Integer value) {
            addCriterion("sortNo =", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotEqualTo(Integer value) {
            addCriterion("sortNo <>", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoGreaterThan(Integer value) {
            addCriterion("sortNo >", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sortNo >=", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoLessThan(Integer value) {
            addCriterion("sortNo <", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoLessThanOrEqualTo(Integer value) {
            addCriterion("sortNo <=", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoIn(List<Integer> values) {
            addCriterion("sortNo in", values, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotIn(List<Integer> values) {
            addCriterion("sortNo not in", values, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoBetween(Integer value1, Integer value2) {
            addCriterion("sortNo between", value1, value2, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotBetween(Integer value1, Integer value2) {
            addCriterion("sortNo not between", value1, value2, "sortno");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNull() {
            addCriterion("isvalid is null");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNotNull() {
            addCriterion("isvalid is not null");
            return (Criteria) this;
        }

        public Criteria andIsvalidEqualTo(Integer value) {
            addCriterion("isvalid =", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotEqualTo(Integer value) {
            addCriterion("isvalid <>", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThan(Integer value) {
            addCriterion("isvalid >", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThanOrEqualTo(Integer value) {
            addCriterion("isvalid >=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThan(Integer value) {
            addCriterion("isvalid <", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThanOrEqualTo(Integer value) {
            addCriterion("isvalid <=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidIn(List<Integer> values) {
            addCriterion("isvalid in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotIn(List<Integer> values) {
            addCriterion("isvalid not in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidBetween(Integer value1, Integer value2) {
            addCriterion("isvalid between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotBetween(Integer value1, Integer value2) {
            addCriterion("isvalid not between", value1, value2, "isvalid");
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