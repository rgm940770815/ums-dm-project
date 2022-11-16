package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsUserInfoViewCriteria extends UmsExtendUserInfoViewCriteria {

    private Integer courtNo;

    public Integer getCourtNo() {
        return courtNo;
    }

    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsUserInfoViewCriteria() {
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

        protected void addCriterionForGroup(String condition, Object value, String property,boolean isGroup) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value,isGroup));
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

        public Criteria andXtptIdIsNull() {
            addCriterion("xtpt_id is null");
            return (Criteria) this;
        }

        public Criteria andXtptIdIsNotNull() {
            addCriterion("xtpt_id is not null");
            return (Criteria) this;
        }

        public Criteria andXtptIdEqualTo(Integer value) {
            addCriterion("xtpt_id =", value, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdNotEqualTo(Integer value) {
            addCriterion("xtpt_id <>", value, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdGreaterThan(Integer value) {
            addCriterion("xtpt_id >", value, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("xtpt_id >=", value, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdLessThan(Integer value) {
            addCriterion("xtpt_id <", value, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdLessThanOrEqualTo(Integer value) {
            addCriterion("xtpt_id <=", value, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdIn(List<Integer> values) {
            addCriterion("xtpt_id in", values, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdNotIn(List<Integer> values) {
            addCriterion("xtpt_id not in", values, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdBetween(Integer value1, Integer value2) {
            addCriterion("xtpt_id between", value1, value2, "xtptId");
            return (Criteria) this;
        }

        public Criteria andXtptIdNotBetween(Integer value1, Integer value2) {
            addCriterion("xtpt_id not between", value1, value2, "xtptId");
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

        public Criteria andACourtNoIn(List<Integer> values) {
            addCriterion("a.court_no in", values, "courtNo");
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

        public Criteria andIsInfoCompleteIsNull() {
            addCriterion("is_info_complete is null");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteIsNotNull() {
            addCriterion("is_info_complete is not null");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteEqualTo(Integer value) {
            addCriterion("is_info_complete =", value, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteNotEqualTo(Integer value) {
            addCriterionForGroup("is_info_complete is null or is_info_complete <>", value, "isInfoComplete",true);
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteGreaterThan(Integer value) {
            addCriterion("is_info_complete >", value, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_info_complete >=", value, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteLessThan(Integer value) {
            addCriterion("is_info_complete <", value, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_info_complete <=", value, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteIn(List<Integer> values) {
            addCriterion("is_info_complete in", values, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteNotIn(List<Integer> values) {
            addCriterion("is_info_complete not in", values, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteBetween(Integer value1, Integer value2) {
            addCriterion("is_info_complete between", value1, value2, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andIsInfoCompleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_info_complete not between", value1, value2, "isInfoComplete");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("salt is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("salt is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("salt =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("salt <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("salt >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("salt >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("salt <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("salt <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("salt like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("salt not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("salt in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("salt not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("salt between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("salt not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwIsNull() {
            addCriterion("c_ps_xlxw is null");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwIsNotNull() {
            addCriterion("c_ps_xlxw is not null");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwEqualTo(Integer value) {
            addCriterion("c_ps_xlxw =", value, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwNotEqualTo(Integer value) {
            addCriterion("c_ps_xlxw <>", value, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwGreaterThan(Integer value) {
            addCriterion("c_ps_xlxw >", value, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_ps_xlxw >=", value, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwLessThan(Integer value) {
            addCriterion("c_ps_xlxw <", value, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwLessThanOrEqualTo(Integer value) {
            addCriterion("c_ps_xlxw <=", value, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwIn(List<Integer> values) {
            addCriterion("c_ps_xlxw in", values, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwNotIn(List<Integer> values) {
            addCriterion("c_ps_xlxw not in", values, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_xlxw between", value1, value2, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andCPsXlxwNotBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_xlxw not between", value1, value2, "cPsXlxw");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("(phone_number is null or phone_number = '')");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("(phone_number is not null and phone_number != '')");
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNull() {
            addCriterion("user_code is null");
            return (Criteria) this;
        }

        public Criteria andUserCodeIsNotNull() {
            addCriterion("user_code is not null");
            return (Criteria) this;
        }

        public Criteria andUserCodeEqualTo(String value) {
            addCriterion("user_code =", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotEqualTo(String value) {
            addCriterion("user_code <>", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThan(String value) {
            addCriterion("user_code >", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeGreaterThanOrEqualTo(String value) {
            addCriterion("user_code >=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThan(String value) {
            addCriterion("user_code <", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLessThanOrEqualTo(String value) {
            addCriterion("user_code <=", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeLike(String value) {
            addCriterion("user_code like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotLike(String value) {
            addCriterion("user_code not like", value, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeIn(List<String> values) {
            addCriterion("user_code in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotIn(List<String> values) {
            addCriterion("user_code not in", values, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeBetween(String value1, String value2) {
            addCriterion("user_code between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserCodeNotBetween(String value1, String value2) {
            addCriterion("user_code not between", value1, value2, "userCode");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1IsNull() {
            addCriterion("C_CODE_JG1 is null");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1IsNotNull() {
            addCriterion("C_CODE_JG1 is not null");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1EqualTo(String value) {
            addCriterion("C_CODE_JG1 =", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1NotEqualTo(String value) {
            addCriterion("C_CODE_JG1 <>", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1GreaterThan(String value) {
            addCriterion("C_CODE_JG1 >", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1GreaterThanOrEqualTo(String value) {
            addCriterion("C_CODE_JG1 >=", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1LessThan(String value) {
            addCriterion("C_CODE_JG1 <", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1LessThanOrEqualTo(String value) {
            addCriterion("C_CODE_JG1 <=", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1Like(String value) {
            addCriterion("C_CODE_JG1 like", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1NotLike(String value) {
            addCriterion("C_CODE_JG1 not like", value, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1In(List<String> values) {
            addCriterion("C_CODE_JG1 in", values, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1NotIn(List<String> values) {
            addCriterion("C_CODE_JG1 not in", values, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1Between(String value1, String value2) {
            addCriterion("C_CODE_JG1 between", value1, value2, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg1NotBetween(String value1, String value2) {
            addCriterion("C_CODE_JG1 not between", value1, value2, "cCodeJg1");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2IsNull() {
            addCriterion("C_CODE_JG2 is null");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2IsNotNull() {
            addCriterion("C_CODE_JG2 is not null");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2EqualTo(String value) {
            addCriterion("C_CODE_JG2 =", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2NotEqualTo(String value) {
            addCriterion("C_CODE_JG2 <>", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2GreaterThan(String value) {
            addCriterion("C_CODE_JG2 >", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2GreaterThanOrEqualTo(String value) {
            addCriterion("C_CODE_JG2 >=", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2LessThan(String value) {
            addCriterion("C_CODE_JG2 <", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2LessThanOrEqualTo(String value) {
            addCriterion("C_CODE_JG2 <=", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2Like(String value) {
            addCriterion("C_CODE_JG2 like", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2NotLike(String value) {
            addCriterion("C_CODE_JG2 not like", value, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2In(List<String> values) {
            addCriterion("C_CODE_JG2 in", values, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2NotIn(List<String> values) {
            addCriterion("C_CODE_JG2 not in", values, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2Between(String value1, String value2) {
            addCriterion("C_CODE_JG2 between", value1, value2, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg2NotBetween(String value1, String value2) {
            addCriterion("C_CODE_JG2 not between", value1, value2, "cCodeJg2");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3IsNull() {
            addCriterion("C_CODE_JG3 is null");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3IsNotNull() {
            addCriterion("C_CODE_JG3 is not null");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3EqualTo(String value) {
            addCriterion("C_CODE_JG3 =", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3NotEqualTo(String value) {
            addCriterion("C_CODE_JG3 <>", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3GreaterThan(String value) {
            addCriterion("C_CODE_JG3 >", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3GreaterThanOrEqualTo(String value) {
            addCriterion("C_CODE_JG3 >=", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3LessThan(String value) {
            addCriterion("C_CODE_JG3 <", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3LessThanOrEqualTo(String value) {
            addCriterion("C_CODE_JG3 <=", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3Like(String value) {
            addCriterion("C_CODE_JG3 like", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3NotLike(String value) {
            addCriterion("C_CODE_JG3 not like", value, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3In(List<String> values) {
            addCriterion("C_CODE_JG3 in", values, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3NotIn(List<String> values) {
            addCriterion("C_CODE_JG3 not in", values, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3Between(String value1, String value2) {
            addCriterion("C_CODE_JG3 between", value1, value2, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andCCodeJg3NotBetween(String value1, String value2) {
            addCriterion("C_CODE_JG3 not between", value1, value2, "cCodeJg3");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNull() {
            addCriterion("fullname is null");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNotNull() {
            addCriterion("fullname is not null");
            return (Criteria) this;
        }

        public Criteria andFullnameEqualTo(String value) {
            addCriterion("fullname =", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotEqualTo(String value) {
            addCriterion("fullname <>", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThan(String value) {
            addCriterion("fullname >", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("fullname >=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThan(String value) {
            addCriterion("fullname <", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThanOrEqualTo(String value) {
            addCriterion("fullname <=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLike(String value) {
            addCriterion("fullname like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotLike(String value) {
            addCriterion("fullname not like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameIn(List<String> values) {
            addCriterion("fullname in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotIn(List<String> values) {
            addCriterion("fullname not in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameBetween(String value1, String value2) {
            addCriterion("fullname between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotBetween(String value1, String value2) {
            addCriterion("fullname not between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andFormerNameIsNull() {
            addCriterion("former_name is null");
            return (Criteria) this;
        }

        public Criteria andFormerNameIsNotNull() {
            addCriterion("former_name is not null");
            return (Criteria) this;
        }

        public Criteria andFormerNameEqualTo(String value) {
            addCriterion("former_name =", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameNotEqualTo(String value) {
            addCriterion("former_name <>", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameGreaterThan(String value) {
            addCriterion("former_name >", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameGreaterThanOrEqualTo(String value) {
            addCriterion("former_name >=", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameLessThan(String value) {
            addCriterion("former_name <", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameLessThanOrEqualTo(String value) {
            addCriterion("former_name <=", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameLike(String value) {
            addCriterion("former_name like", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameNotLike(String value) {
            addCriterion("former_name not like", value, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameIn(List<String> values) {
            addCriterion("former_name in", values, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameNotIn(List<String> values) {
            addCriterion("former_name not in", values, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameBetween(String value1, String value2) {
            addCriterion("former_name between", value1, value2, "formerName");
            return (Criteria) this;
        }

        public Criteria andFormerNameNotBetween(String value1, String value2) {
            addCriterion("former_name not between", value1, value2, "formerName");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIllegalValidate() {
            addCriterion(" ( gender is null or gender not IN (1 , 2) )");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(Integer value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(Integer value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(Integer value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(Integer value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(Integer value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(Integer value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<Integer> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<Integer> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(Integer value1, Integer value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(Integer value1, Integer value2) {
            addCriterion("department not between", value1, value2, "department");
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

        public Criteria andPositionNatureIsNull() {
            addCriterion("position_nature is null");
            return (Criteria) this;
        }

        public Criteria andPositionNatureIsNotNull() {
            addCriterion("position_nature is not null");
            return (Criteria) this;
        }

        public Criteria andPositionNatureEqualTo(Integer value) {
            addCriterion("position_nature =", value, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureNotEqualTo(Integer value) {
            addCriterion("position_nature <>", value, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureGreaterThan(Integer value) {
            addCriterion("position_nature >", value, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureGreaterThanOrEqualTo(Integer value) {
            addCriterion("position_nature >=", value, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureLessThan(Integer value) {
            addCriterion("position_nature <", value, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureLessThanOrEqualTo(Integer value) {
            addCriterion("position_nature <=", value, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureIn(List<Integer> values) {
            addCriterion("position_nature in", values, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureNotIn(List<Integer> values) {
            addCriterion("position_nature not in", values, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureBetween(Integer value1, Integer value2) {
            addCriterion("position_nature between", value1, value2, "positionNature");
            return (Criteria) this;
        }

        public Criteria andPositionNatureNotBetween(Integer value1, Integer value2) {
            addCriterion("position_nature not between", value1, value2, "positionNature");
            return (Criteria) this;
        }

        public Criteria andHometownIsNull() {
            addCriterion("hometown is null");
            return (Criteria) this;
        }

        public Criteria andHometownIsNotNull() {
            addCriterion("hometown is not null");
            return (Criteria) this;
        }

        public Criteria andHometownEqualTo(String value) {
            addCriterion("hometown =", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownNotEqualTo(String value) {
            addCriterion("hometown <>", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownGreaterThan(String value) {
            addCriterion("hometown >", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownGreaterThanOrEqualTo(String value) {
            addCriterion("hometown >=", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownLessThan(String value) {
            addCriterion("hometown <", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownLessThanOrEqualTo(String value) {
            addCriterion("hometown <=", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownLike(String value) {
            addCriterion("hometown like", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownNotLike(String value) {
            addCriterion("hometown not like", value, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownIn(List<String> values) {
            addCriterion("hometown in", values, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownNotIn(List<String> values) {
            addCriterion("hometown not in", values, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownBetween(String value1, String value2) {
            addCriterion("hometown between", value1, value2, "hometown");
            return (Criteria) this;
        }

        public Criteria andHometownNotBetween(String value1, String value2) {
            addCriterion("hometown not between", value1, value2, "hometown");
            return (Criteria) this;
        }

        public Criteria andBirthplaceIsNull() {
            addCriterion("birthplace is null");
            return (Criteria) this;
        }

        public Criteria andBirthplaceIsNotNull() {
            addCriterion("birthplace is not null");
            return (Criteria) this;
        }

        public Criteria andBirthplaceEqualTo(String value) {
            addCriterion("birthplace =", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceNotEqualTo(String value) {
            addCriterion("birthplace <>", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceGreaterThan(String value) {
            addCriterion("birthplace >", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceGreaterThanOrEqualTo(String value) {
            addCriterion("birthplace >=", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceLessThan(String value) {
            addCriterion("birthplace <", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceLessThanOrEqualTo(String value) {
            addCriterion("birthplace <=", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceLike(String value) {
            addCriterion("birthplace like", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceNotLike(String value) {
            addCriterion("birthplace not like", value, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceIn(List<String> values) {
            addCriterion("birthplace in", values, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceNotIn(List<String> values) {
            addCriterion("birthplace not in", values, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceBetween(String value1, String value2) {
            addCriterion("birthplace between", value1, value2, "birthplace");
            return (Criteria) this;
        }

        public Criteria andBirthplaceNotBetween(String value1, String value2) {
            addCriterion("birthplace not between", value1, value2, "birthplace");
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

        public Criteria andPhysicalConditionIsNull() {
            addCriterion("physical_condition is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionIsNotNull() {
            addCriterion("physical_condition is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionEqualTo(Integer value) {
            addCriterion("physical_condition =", value, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionNotEqualTo(Integer value) {
            addCriterion("physical_condition <>", value, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionGreaterThan(Integer value) {
            addCriterion("physical_condition >", value, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionGreaterThanOrEqualTo(Integer value) {
            addCriterion("physical_condition >=", value, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionLessThan(Integer value) {
            addCriterion("physical_condition <", value, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionLessThanOrEqualTo(Integer value) {
            addCriterion("physical_condition <=", value, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionIn(List<Integer> values) {
            addCriterion("physical_condition in", values, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionNotIn(List<Integer> values) {
            addCriterion("physical_condition not in", values, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionBetween(Integer value1, Integer value2) {
            addCriterion("physical_condition between", value1, value2, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionNotBetween(Integer value1, Integer value2) {
            addCriterion("physical_condition not between", value1, value2, "physicalCondition");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNull() {
            addCriterion("marital_status is null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNotNull() {
            addCriterion("marital_status is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusEqualTo(Integer value) {
            addCriterion("marital_status =", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotEqualTo(Integer value) {
            addCriterion("marital_status <>", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThan(Integer value) {
            addCriterion("marital_status >", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("marital_status >=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThan(Integer value) {
            addCriterion("marital_status <", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThanOrEqualTo(Integer value) {
            addCriterion("marital_status <=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIn(List<Integer> values) {
            addCriterion("marital_status in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotIn(List<Integer> values) {
            addCriterion("marital_status not in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusBetween(Integer value1, Integer value2) {
            addCriterion("marital_status between", value1, value2, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("marital_status not between", value1, value2, "maritalStatus");
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

        public Criteria andNationEqualTo(Integer value) {
            addCriterion("nation =", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotEqualTo(Integer value) {
            addCriterion("nation <>", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThan(Integer value) {
            addCriterion("nation >", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThanOrEqualTo(Integer value) {
            addCriterion("nation >=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThan(Integer value) {
            addCriterion("nation <", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThanOrEqualTo(Integer value) {
            addCriterion("nation <=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationIn(List<Integer> values) {
            addCriterion("nation in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotIn(List<Integer> values) {
            addCriterion("nation not in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationBetween(Integer value1, Integer value2) {
            addCriterion("nation between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotBetween(Integer value1, Integer value2) {
            addCriterion("nation not between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andIdcardIsNull() {
            addCriterion("idcard is null");
            return (Criteria) this;
        }

        public Criteria andIdcardIllegalValidate() {
            addCriterionForGroup("idcard is null or idcard not REGEXP  " , "[0-9]{18}|[0-9]{17}X|[0-9]{15}" , "" , true);
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

        public Criteria andPreparationIsNull() {
            addCriterion("preparation is null");
            return (Criteria) this;
        }

        public Criteria andPreparationIsNotNull() {
            addCriterion("preparation is not null");
            return (Criteria) this;
        }

        public Criteria andPreparationEqualTo(Integer value) {
            addCriterion("preparation =", value, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationNotEqualTo(Integer value) {
            addCriterion("preparation <>", value, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationGreaterThan(Integer value) {
            addCriterion("preparation >", value, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationGreaterThanOrEqualTo(Integer value) {
            addCriterion("preparation >=", value, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationLessThan(Integer value) {
            addCriterion("preparation <", value, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationLessThanOrEqualTo(Integer value) {
            addCriterion("preparation <=", value, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationIn(List<Integer> values) {
            addCriterion("preparation in", values, "preparation");
            return (Criteria) this;
        }
        //自定义查询
        public Criteria orPreparationIn(List<Integer> values) {
            String sql = " (preparation in (";
            for (Integer num:values)
            {
                sql+=" "+num +",";
            }
            int lenghth = sql.length();
            sql = sql.substring(0,lenghth-1);
            sql+=") or preparation is null) ";
            addCriterion(sql);
            return (Criteria) this;
        }

        public Criteria andPreparationNotIn(List<Integer> values) {
            addCriterion("preparation not in", values, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationBetween(Integer value1, Integer value2) {
            addCriterion("preparation between", value1, value2, "preparation");
            return (Criteria) this;
        }

        public Criteria andPreparationNotBetween(Integer value1, Integer value2) {
            addCriterion("preparation not between", value1, value2, "preparation");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIsNull() {
            addCriterion("position_type is null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIsNotNull() {
            addCriterion("position_type is not null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeEqualTo(Integer value) {
            addCriterion("position_type =", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotEqualTo(Integer value) {
            addCriterion("position_type <>", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeGreaterThan(Integer value) {
            addCriterion("position_type >", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("position_type >=", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLessThan(Integer value) {
            addCriterion("position_type <", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("position_type <=", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIn(List<Integer> values) {
            addCriterion("position_type in", values, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotIn(List<Integer> values) {
            addCriterion("position_type not in", values, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeBetween(Integer value1, Integer value2) {
            addCriterion("position_type between", value1, value2, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("position_type not between", value1, value2, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateIsNull() {
            addCriterion("position_type_date is null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateIsNotNull() {
            addCriterion("position_type_date is not null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateEqualTo(Date value) {
            addCriterionForJDBCDate("position_type_date =", value, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("position_type_date <>", value, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateGreaterThan(Date value) {
            addCriterionForJDBCDate("position_type_date >", value, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("position_type_date >=", value, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateLessThan(Date value) {
            addCriterionForJDBCDate("position_type_date <", value, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("position_type_date <=", value, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateIn(List<Date> values) {
            addCriterionForJDBCDate("position_type_date in", values, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("position_type_date not in", values, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("position_type_date between", value1, value2, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andPositionTypeDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("position_type_date not between", value1, value2, "positionTypeDate");
            return (Criteria) this;
        }

        public Criteria andAssignIsNull() {
            addCriterion("assign is null");
            return (Criteria) this;
        }

        public Criteria andAssignIsNotNull() {
            addCriterion("assign is not null");
            return (Criteria) this;
        }

        public Criteria andAssignEqualTo(Integer value) {
            addCriterion("assign =", value, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignNotEqualTo(Integer value) {
            addCriterion("assign <>", value, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignGreaterThan(Integer value) {
            addCriterion("assign >", value, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignGreaterThanOrEqualTo(Integer value) {
            addCriterion("assign >=", value, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignLessThan(Integer value) {
            addCriterion("assign <", value, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignLessThanOrEqualTo(Integer value) {
            addCriterion("assign <=", value, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignIn(List<Integer> values) {
            addCriterion("assign in", values, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignNotIn(List<Integer> values) {
            addCriterion("assign not in", values, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignBetween(Integer value1, Integer value2) {
            addCriterion("assign between", value1, value2, "assign");
            return (Criteria) this;
        }

        public Criteria andAssignNotBetween(Integer value1, Integer value2) {
            addCriterion("assign not between", value1, value2, "assign");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundIsNull() {
            addCriterion("education_background is null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundIsNotNull() {
            addCriterion("education_background is not null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundEqualTo(Integer value) {
            addCriterion("education_background =", value, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundNotEqualTo(Integer value) {
            addCriterion("education_background <>", value, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundGreaterThan(Integer value) {
            addCriterion("education_background >", value, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundGreaterThanOrEqualTo(Integer value) {
            addCriterion("education_background >=", value, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundLessThan(Integer value) {
            addCriterion("education_background <", value, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundLessThanOrEqualTo(Integer value) {
            addCriterion("education_background <=", value, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundIn(List<Integer> values) {
            addCriterion("education_background in", values, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundNotIn(List<Integer> values) {
            addCriterion("education_background not in", values, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundBetween(Integer value1, Integer value2) {
            addCriterion("education_background between", value1, value2, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundNotBetween(Integer value1, Integer value2) {
            addCriterion("education_background not between", value1, value2, "educationBackground");
            return (Criteria) this;
        }

        public Criteria andMajorIsNull() {
            addCriterion("major is null");
            return (Criteria) this;
        }

        public Criteria andMajorIsNotNull() {
            addCriterion("major is not null");
            return (Criteria) this;
        }

        public Criteria andMajorEqualTo(Integer value) {
            addCriterion("major =", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotEqualTo(Integer value) {
            addCriterion("major <>", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThan(Integer value) {
            addCriterion("major >", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThanOrEqualTo(Integer value) {
            addCriterion("major >=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThan(Integer value) {
            addCriterion("major <", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThanOrEqualTo(Integer value) {
            addCriterion("major <=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorIn(List<Integer> values) {
            addCriterion("major in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotIn(List<Integer> values) {
            addCriterion("major not in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorBetween(Integer value1, Integer value2) {
            addCriterion("major between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotBetween(Integer value1, Integer value2) {
            addCriterion("major not between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNull() {
            addCriterion("degree is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNotNull() {
            addCriterion("degree is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeEqualTo(Integer value) {
            addCriterion("degree =", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotEqualTo(Integer value) {
            addCriterion("degree <>", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThan(Integer value) {
            addCriterion("degree >", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("degree >=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThan(Integer value) {
            addCriterion("degree <", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanOrEqualTo(Integer value) {
            addCriterion("degree <=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeIn(List<Integer> values) {
            addCriterion("degree in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotIn(List<Integer> values) {
            addCriterion("degree not in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeBetween(Integer value1, Integer value2) {
            addCriterion("degree between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotBetween(Integer value1, Integer value2) {
            addCriterion("degree not between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeDateIsNull() {
            addCriterion("degree_date is null");
            return (Criteria) this;
        }

        public Criteria andDegreeDateIsNotNull() {
            addCriterion("degree_date is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeDateEqualTo(Date value) {
            addCriterionForJDBCDate("degree_date =", value, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("degree_date <>", value, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateGreaterThan(Date value) {
            addCriterionForJDBCDate("degree_date >", value, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("degree_date >=", value, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateLessThan(Date value) {
            addCriterionForJDBCDate("degree_date <", value, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("degree_date <=", value, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateIn(List<Date> values) {
            addCriterionForJDBCDate("degree_date in", values, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("degree_date not in", values, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("degree_date between", value1, value2, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andDegreeDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("degree_date not between", value1, value2, "degreeDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNull() {
            addCriterion("work_date is null");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNotNull() {
            addCriterion("work_date is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDateEqualTo(Date value) {
            addCriterionForJDBCDate("work_date =", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("work_date <>", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThan(Date value) {
            addCriterionForJDBCDate("work_date >", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("work_date >=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThan(Date value) {
            addCriterionForJDBCDate("work_date <", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("work_date <=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateIn(List<Date> values) {
            addCriterionForJDBCDate("work_date in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("work_date not in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("work_date between", value1, value2, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("work_date not between", value1, value2, "workDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateIsNull() {
            addCriterion("enter_date is null");
            return (Criteria) this;
        }

        public Criteria andEnterDateIsNotNull() {
            addCriterion("enter_date is not null");
            return (Criteria) this;
        }

        public Criteria andEnterDateEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date =", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date <>", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateGreaterThan(Date value) {
            addCriterionForJDBCDate("enter_date >", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date >=", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateLessThan(Date value) {
            addCriterionForJDBCDate("enter_date <", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date <=", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateIn(List<Date> values) {
            addCriterionForJDBCDate("enter_date in", values, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("enter_date not in", values, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("enter_date between", value1, value2, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("enter_date not between", value1, value2, "enterDate");
            return (Criteria) this;
        }

        public Criteria andProCertIsNull() {
            addCriterion("pro_cert is null");
            return (Criteria) this;
        }

        public Criteria andProCertIsNotNull() {
            addCriterion("pro_cert is not null");
            return (Criteria) this;
        }

        public Criteria andProCertEqualTo(Integer value) {
            addCriterion("pro_cert =", value, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertNotEqualTo(Integer value) {
            addCriterion("pro_cert <>", value, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertGreaterThan(Integer value) {
            addCriterion("pro_cert >", value, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertGreaterThanOrEqualTo(Integer value) {
            addCriterion("pro_cert >=", value, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertLessThan(Integer value) {
            addCriterion("pro_cert <", value, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertLessThanOrEqualTo(Integer value) {
            addCriterion("pro_cert <=", value, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertIn(List<Integer> values) {
            addCriterion("pro_cert in", values, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertNotIn(List<Integer> values) {
            addCriterion("pro_cert not in", values, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertBetween(Integer value1, Integer value2) {
            addCriterion("pro_cert between", value1, value2, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertNotBetween(Integer value1, Integer value2) {
            addCriterion("pro_cert not between", value1, value2, "proCert");
            return (Criteria) this;
        }

        public Criteria andProCertDateIsNull() {
            addCriterion("pro_cert_date is null");
            return (Criteria) this;
        }

        public Criteria andProCertDateIsNotNull() {
            addCriterion("pro_cert_date is not null");
            return (Criteria) this;
        }

        public Criteria andProCertDateEqualTo(Date value) {
            addCriterionForJDBCDate("pro_cert_date =", value, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("pro_cert_date <>", value, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateGreaterThan(Date value) {
            addCriterionForJDBCDate("pro_cert_date >", value, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pro_cert_date >=", value, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateLessThan(Date value) {
            addCriterionForJDBCDate("pro_cert_date <", value, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pro_cert_date <=", value, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateIn(List<Date> values) {
            addCriterionForJDBCDate("pro_cert_date in", values, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("pro_cert_date not in", values, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pro_cert_date between", value1, value2, "proCertDate");
            return (Criteria) this;
        }

        public Criteria andProCertDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pro_cert_date not between", value1, value2, "proCertDate");
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

        public Criteria andPoliticalEqualTo(Integer value) {
            addCriterion("political =", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotEqualTo(Integer value) {
            addCriterion("political <>", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalGreaterThan(Integer value) {
            addCriterion("political >", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalGreaterThanOrEqualTo(Integer value) {
            addCriterion("political >=", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalLessThan(Integer value) {
            addCriterion("political <", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalLessThanOrEqualTo(Integer value) {
            addCriterion("political <=", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalIn(List<Integer> values) {
            addCriterion("political in", values, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotIn(List<Integer> values) {
            addCriterion("political not in", values, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalBetween(Integer value1, Integer value2) {
            addCriterion("political between", value1, value2, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotBetween(Integer value1, Integer value2) {
            addCriterion("political not between", value1, value2, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateIsNull() {
            addCriterion("political_date is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateIsNotNull() {
            addCriterion("political_date is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateEqualTo(Date value) {
            addCriterionForJDBCDate("political_date =", value, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("political_date <>", value, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateGreaterThan(Date value) {
            addCriterionForJDBCDate("political_date >", value, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("political_date >=", value, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateLessThan(Date value) {
            addCriterionForJDBCDate("political_date <", value, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("political_date <=", value, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateIn(List<Date> values) {
            addCriterionForJDBCDate("political_date in", values, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("political_date not in", values, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("political_date between", value1, value2, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("political_date not between", value1, value2, "politicalDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateIsNull() {
            addCriterion("politic_law_work_date is null");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateIsNotNull() {
            addCriterion("politic_law_work_date is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateEqualTo(Date value) {
            addCriterionForJDBCDate("politic_law_work_date =", value, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("politic_law_work_date <>", value, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateGreaterThan(Date value) {
            addCriterionForJDBCDate("politic_law_work_date >", value, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("politic_law_work_date >=", value, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateLessThan(Date value) {
            addCriterionForJDBCDate("politic_law_work_date <", value, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("politic_law_work_date <=", value, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateIn(List<Date> values) {
            addCriterionForJDBCDate("politic_law_work_date in", values, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("politic_law_work_date not in", values, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("politic_law_work_date between", value1, value2, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andPoliticLawWorkDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("politic_law_work_date not between", value1, value2, "politicLawWorkDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionIsNull() {
            addCriterion("administration_position is null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionIsNotNull() {
            addCriterion("administration_position is not null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionEqualTo(Integer value) {
            addCriterion("administration_position =", value, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionNotEqualTo(Integer value) {
            addCriterion("administration_position <>", value, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionGreaterThan(Integer value) {
            addCriterion("administration_position >", value, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("administration_position >=", value, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionLessThan(Integer value) {
            addCriterion("administration_position <", value, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionLessThanOrEqualTo(Integer value) {
            addCriterion("administration_position <=", value, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionIn(List<Integer> values) {
            addCriterion("administration_position in", values, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionNotIn(List<Integer> values) {
            addCriterion("administration_position not in", values, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionBetween(Integer value1, Integer value2) {
            addCriterion("administration_position between", value1, value2, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("administration_position not between", value1, value2, "administrationPosition");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateIsNull() {
            addCriterion("administration_position_date is null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateIsNotNull() {
            addCriterion("administration_position_date is not null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateEqualTo(Date value) {
            addCriterionForJDBCDate("administration_position_date =", value, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("administration_position_date <>", value, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateGreaterThan(Date value) {
            addCriterionForJDBCDate("administration_position_date >", value, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("administration_position_date >=", value, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateLessThan(Date value) {
            addCriterionForJDBCDate("administration_position_date <", value, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("administration_position_date <=", value, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateIn(List<Date> values) {
            addCriterionForJDBCDate("administration_position_date in", values, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("administration_position_date not in", values, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("administration_position_date between", value1, value2, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("administration_position_date not between", value1, value2, "administrationPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionIsNull() {
            addCriterion("law_position is null");
            return (Criteria) this;
        }

        public Criteria andLawPositionIsNotNull() {
            addCriterion("law_position is not null");
            return (Criteria) this;
        }

        public Criteria andLawPositionEqualTo(Integer value) {
            addCriterion("law_position =", value, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionNotEqualTo(Integer value) {
            addCriterion("law_position <>", value, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionGreaterThan(Integer value) {
            addCriterion("law_position >", value, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("law_position >=", value, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionLessThan(Integer value) {
            addCriterion("law_position <", value, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionLessThanOrEqualTo(Integer value) {
            addCriterion("law_position <=", value, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionIn(List<Integer> values) {
            addCriterion("law_position in", values, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionNotIn(List<Integer> values) {
            addCriterion("law_position not in", values, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionBetween(Integer value1, Integer value2) {
            addCriterion("law_position between", value1, value2, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("law_position not between", value1, value2, "lawPosition");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateIsNull() {
            addCriterion("law_position_date is null");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateIsNotNull() {
            addCriterion("law_position_date is not null");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateEqualTo(Date value) {
            addCriterionForJDBCDate("law_position_date =", value, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("law_position_date <>", value, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateGreaterThan(Date value) {
            addCriterionForJDBCDate("law_position_date >", value, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("law_position_date >=", value, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateLessThan(Date value) {
            addCriterionForJDBCDate("law_position_date <", value, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("law_position_date <=", value, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateIn(List<Date> values) {
            addCriterionForJDBCDate("law_position_date in", values, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("law_position_date not in", values, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("law_position_date between", value1, value2, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andLawPositionDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("law_position_date not between", value1, value2, "lawPositionDate");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeIsNull() {
            addCriterion("is_parttime_presiding_judge is null");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeIsNotNull() {
            addCriterion("is_parttime_presiding_judge is not null");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeEqualTo(Integer value) {
            addCriterion("is_parttime_presiding_judge =", value, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeNotEqualTo(Integer value) {
            addCriterion("is_parttime_presiding_judge <>", value, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeGreaterThan(Integer value) {
            addCriterion("is_parttime_presiding_judge >", value, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_parttime_presiding_judge >=", value, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeLessThan(Integer value) {
            addCriterion("is_parttime_presiding_judge <", value, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeLessThanOrEqualTo(Integer value) {
            addCriterion("is_parttime_presiding_judge <=", value, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeIn(List<Integer> values) {
            addCriterion("is_parttime_presiding_judge in", values, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeNotIn(List<Integer> values) {
            addCriterion("is_parttime_presiding_judge not in", values, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeBetween(Integer value1, Integer value2) {
            addCriterion("is_parttime_presiding_judge between", value1, value2, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_parttime_presiding_judge not between", value1, value2, "isParttimePresidingJudge");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeIsNull() {
            addCriterion("party_office is null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeIsNotNull() {
            addCriterion("party_office is not null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeEqualTo(Integer value) {
            addCriterion("party_office =", value, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeNotEqualTo(Integer value) {
            addCriterion("party_office <>", value, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeGreaterThan(Integer value) {
            addCriterion("party_office >", value, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeGreaterThanOrEqualTo(Integer value) {
            addCriterion("party_office >=", value, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeLessThan(Integer value) {
            addCriterion("party_office <", value, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeLessThanOrEqualTo(Integer value) {
            addCriterion("party_office <=", value, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeIn(List<Integer> values) {
            addCriterion("party_office in", values, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeNotIn(List<Integer> values) {
            addCriterion("party_office not in", values, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeBetween(Integer value1, Integer value2) {
            addCriterion("party_office between", value1, value2, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeNotBetween(Integer value1, Integer value2) {
            addCriterion("party_office not between", value1, value2, "partyOffice");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateIsNull() {
            addCriterion("party_office_date is null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateIsNotNull() {
            addCriterion("party_office_date is not null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateEqualTo(Date value) {
            addCriterionForJDBCDate("party_office_date =", value, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("party_office_date <>", value, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateGreaterThan(Date value) {
            addCriterionForJDBCDate("party_office_date >", value, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("party_office_date >=", value, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateLessThan(Date value) {
            addCriterionForJDBCDate("party_office_date <", value, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("party_office_date <=", value, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateIn(List<Date> values) {
            addCriterionForJDBCDate("party_office_date in", values, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("party_office_date not in", values, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("party_office_date between", value1, value2, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("party_office_date not between", value1, value2, "partyOfficeDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateIsNull() {
            addCriterion("lawyer_date is null");
            return (Criteria) this;
        }

        public Criteria andLawyerDateIsNotNull() {
            addCriterion("lawyer_date is not null");
            return (Criteria) this;
        }

        public Criteria andLawyerDateEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_date =", value, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_date <>", value, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateGreaterThan(Date value) {
            addCriterionForJDBCDate("lawyer_date >", value, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_date >=", value, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateLessThan(Date value) {
            addCriterionForJDBCDate("lawyer_date <", value, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_date <=", value, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateIn(List<Date> values) {
            addCriterionForJDBCDate("lawyer_date in", values, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("lawyer_date not in", values, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lawyer_date between", value1, value2, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andLawyerDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lawyer_date not between", value1, value2, "lawyerDate");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityIsNull() {
            addCriterion("extra_seniority is null");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityIsNotNull() {
            addCriterion("extra_seniority is not null");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityEqualTo(Integer value) {
            addCriterion("extra_seniority =", value, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityNotEqualTo(Integer value) {
            addCriterion("extra_seniority <>", value, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityGreaterThan(Integer value) {
            addCriterion("extra_seniority >", value, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("extra_seniority >=", value, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityLessThan(Integer value) {
            addCriterion("extra_seniority <", value, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityLessThanOrEqualTo(Integer value) {
            addCriterion("extra_seniority <=", value, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityIn(List<Integer> values) {
            addCriterion("extra_seniority in", values, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityNotIn(List<Integer> values) {
            addCriterion("extra_seniority not in", values, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityBetween(Integer value1, Integer value2) {
            addCriterion("extra_seniority between", value1, value2, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andExtraSeniorityNotBetween(Integer value1, Integer value2) {
            addCriterion("extra_seniority not between", value1, value2, "extraSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityIsNull() {
            addCriterion("deduction_seniority is null");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityIsNotNull() {
            addCriterion("deduction_seniority is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityEqualTo(Integer value) {
            addCriterion("deduction_seniority =", value, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityNotEqualTo(Integer value) {
            addCriterion("deduction_seniority <>", value, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityGreaterThan(Integer value) {
            addCriterion("deduction_seniority >", value, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("deduction_seniority >=", value, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityLessThan(Integer value) {
            addCriterion("deduction_seniority <", value, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityLessThanOrEqualTo(Integer value) {
            addCriterion("deduction_seniority <=", value, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityIn(List<Integer> values) {
            addCriterion("deduction_seniority in", values, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityNotIn(List<Integer> values) {
            addCriterion("deduction_seniority not in", values, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityBetween(Integer value1, Integer value2) {
            addCriterion("deduction_seniority between", value1, value2, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andDeductionSeniorityNotBetween(Integer value1, Integer value2) {
            addCriterion("deduction_seniority not between", value1, value2, "deductionSeniority");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearIsNull() {
            addCriterion("before_court_work_year is null");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearIsNotNull() {
            addCriterion("before_court_work_year is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearEqualTo(Integer value) {
            addCriterion("before_court_work_year =", value, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearNotEqualTo(Integer value) {
            addCriterion("before_court_work_year <>", value, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearGreaterThan(Integer value) {
            addCriterion("before_court_work_year >", value, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("before_court_work_year >=", value, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearLessThan(Integer value) {
            addCriterion("before_court_work_year <", value, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearLessThanOrEqualTo(Integer value) {
            addCriterion("before_court_work_year <=", value, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearIn(List<Integer> values) {
            addCriterion("before_court_work_year in", values, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearNotIn(List<Integer> values) {
            addCriterion("before_court_work_year not in", values, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearBetween(Integer value1, Integer value2) {
            addCriterion("before_court_work_year between", value1, value2, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeforeCourtWorkYearNotBetween(Integer value1, Integer value2) {
            addCriterion("before_court_work_year not between", value1, value2, "beforeCourtWorkYear");
            return (Criteria) this;
        }

        public Criteria andRankIsNull() {
            addCriterion("rank is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("rank is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(Integer value) {
            addCriterion("rank =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(Integer value) {
            addCriterion("rank <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(Integer value) {
            addCriterion("rank >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("rank >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(Integer value) {
            addCriterion("rank <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(Integer value) {
            addCriterion("rank <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<Integer> values) {
            addCriterion("rank in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<Integer> values) {
            addCriterion("rank not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(Integer value1, Integer value2) {
            addCriterion("rank between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(Integer value1, Integer value2) {
            addCriterion("rank not between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankDateIsNull() {
            addCriterion("rank_date is null");
            return (Criteria) this;
        }

        public Criteria andRankDateIsNotNull() {
            addCriterion("rank_date is not null");
            return (Criteria) this;
        }

        public Criteria andRankDateEqualTo(Date value) {
            addCriterionForJDBCDate("rank_date =", value, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("rank_date <>", value, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateGreaterThan(Date value) {
            addCriterionForJDBCDate("rank_date >", value, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("rank_date >=", value, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateLessThan(Date value) {
            addCriterionForJDBCDate("rank_date <", value, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("rank_date <=", value, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateIn(List<Date> values) {
            addCriterionForJDBCDate("rank_date in", values, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("rank_date not in", values, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("rank_date between", value1, value2, "rankDate");
            return (Criteria) this;
        }

        public Criteria andRankDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("rank_date not between", value1, value2, "rankDate");
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

        public Criteria andLevelDateIsNull() {
            addCriterion("level_date is null");
            return (Criteria) this;
        }

        public Criteria andLevelDateIsNotNull() {
            addCriterion("level_date is not null");
            return (Criteria) this;
        }

        public Criteria andLevelDateEqualTo(Date value) {
            addCriterionForJDBCDate("level_date =", value, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("level_date <>", value, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateGreaterThan(Date value) {
            addCriterionForJDBCDate("level_date >", value, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("level_date >=", value, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateLessThan(Date value) {
            addCriterionForJDBCDate("level_date <", value, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("level_date <=", value, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateIn(List<Date> values) {
            addCriterionForJDBCDate("level_date in", values, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("level_date not in", values, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("level_date between", value1, value2, "levelDate");
            return (Criteria) this;
        }

        public Criteria andLevelDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("level_date not between", value1, value2, "levelDate");
            return (Criteria) this;
        }

        public Criteria andEnterWayIsNull() {
            addCriterion("enter_way is null");
            return (Criteria) this;
        }

        public Criteria andEnterWayIsNotNull() {
            addCriterion("enter_way is not null");
            return (Criteria) this;
        }

        public Criteria andEnterWayEqualTo(Integer value) {
            addCriterion("enter_way =", value, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayNotEqualTo(Integer value) {
            addCriterion("enter_way <>", value, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayGreaterThan(Integer value) {
            addCriterion("enter_way >", value, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("enter_way >=", value, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayLessThan(Integer value) {
            addCriterion("enter_way <", value, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayLessThanOrEqualTo(Integer value) {
            addCriterion("enter_way <=", value, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayIn(List<Integer> values) {
            addCriterion("enter_way in", values, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayNotIn(List<Integer> values) {
            addCriterion("enter_way not in", values, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayBetween(Integer value1, Integer value2) {
            addCriterion("enter_way between", value1, value2, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterWayNotBetween(Integer value1, Integer value2) {
            addCriterion("enter_way not between", value1, value2, "enterWay");
            return (Criteria) this;
        }

        public Criteria andEnterSourceIsNull() {
            addCriterion("enter_source is null");
            return (Criteria) this;
        }

        public Criteria andEnterSourceIsNotNull() {
            addCriterion("enter_source is not null");
            return (Criteria) this;
        }

        public Criteria andEnterSourceEqualTo(Integer value) {
            addCriterion("enter_source =", value, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceNotEqualTo(Integer value) {
            addCriterion("enter_source <>", value, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceGreaterThan(Integer value) {
            addCriterion("enter_source >", value, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("enter_source >=", value, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceLessThan(Integer value) {
            addCriterion("enter_source <", value, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceLessThanOrEqualTo(Integer value) {
            addCriterion("enter_source <=", value, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceIn(List<Integer> values) {
            addCriterion("enter_source in", values, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceNotIn(List<Integer> values) {
            addCriterion("enter_source not in", values, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceBetween(Integer value1, Integer value2) {
            addCriterion("enter_source between", value1, value2, "enterSource");
            return (Criteria) this;
        }

        public Criteria andEnterSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("enter_source not between", value1, value2, "enterSource");
            return (Criteria) this;
        }

        public Criteria andFormerPostIsNull() {
            addCriterion("former_post is null");
            return (Criteria) this;
        }

        public Criteria andFormerPostIsNotNull() {
            addCriterion("former_post is not null");
            return (Criteria) this;
        }

        public Criteria andFormerPostEqualTo(Integer value) {
            addCriterion("former_post =", value, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostNotEqualTo(Integer value) {
            addCriterion("former_post <>", value, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostGreaterThan(Integer value) {
            addCriterion("former_post >", value, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostGreaterThanOrEqualTo(Integer value) {
            addCriterion("former_post >=", value, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostLessThan(Integer value) {
            addCriterion("former_post <", value, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostLessThanOrEqualTo(Integer value) {
            addCriterion("former_post <=", value, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostIn(List<Integer> values) {
            addCriterion("former_post in", values, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostNotIn(List<Integer> values) {
            addCriterion("former_post not in", values, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostBetween(Integer value1, Integer value2) {
            addCriterion("former_post between", value1, value2, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerPostNotBetween(Integer value1, Integer value2) {
            addCriterion("former_post not between", value1, value2, "formerPost");
            return (Criteria) this;
        }

        public Criteria andFormerRankIsNull() {
            addCriterion("former_rank is null");
            return (Criteria) this;
        }

        public Criteria andFormerRankIsNotNull() {
            addCriterion("former_rank is not null");
            return (Criteria) this;
        }

        public Criteria andFormerRankEqualTo(Integer value) {
            addCriterion("former_rank =", value, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankNotEqualTo(Integer value) {
            addCriterion("former_rank <>", value, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankGreaterThan(Integer value) {
            addCriterion("former_rank >", value, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("former_rank >=", value, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankLessThan(Integer value) {
            addCriterion("former_rank <", value, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankLessThanOrEqualTo(Integer value) {
            addCriterion("former_rank <=", value, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankIn(List<Integer> values) {
            addCriterion("former_rank in", values, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankNotIn(List<Integer> values) {
            addCriterion("former_rank not in", values, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankBetween(Integer value1, Integer value2) {
            addCriterion("former_rank between", value1, value2, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerRankNotBetween(Integer value1, Integer value2) {
            addCriterion("former_rank not between", value1, value2, "formerRank");
            return (Criteria) this;
        }

        public Criteria andFormerUnitIsNull() {
            addCriterion("former_unit is null");
            return (Criteria) this;
        }

        public Criteria andFormerUnitIsNotNull() {
            addCriterion("former_unit is not null");
            return (Criteria) this;
        }

        public Criteria andFormerUnitEqualTo(String value) {
            addCriterion("former_unit =", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitNotEqualTo(String value) {
            addCriterion("former_unit <>", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitGreaterThan(String value) {
            addCriterion("former_unit >", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitGreaterThanOrEqualTo(String value) {
            addCriterion("former_unit >=", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitLessThan(String value) {
            addCriterion("former_unit <", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitLessThanOrEqualTo(String value) {
            addCriterion("former_unit <=", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitLike(String value) {
            addCriterion("former_unit like", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitNotLike(String value) {
            addCriterion("former_unit not like", value, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitIn(List<String> values) {
            addCriterion("former_unit in", values, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitNotIn(List<String> values) {
            addCriterion("former_unit not in", values, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitBetween(String value1, String value2) {
            addCriterion("former_unit between", value1, value2, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andFormerUnitNotBetween(String value1, String value2) {
            addCriterion("former_unit not between", value1, value2, "formerUnit");
            return (Criteria) this;
        }

        public Criteria andVerifyDateIsNull() {
            addCriterion("verify_date is null");
            return (Criteria) this;
        }

        public Criteria andVerifyDateIsNotNull() {
            addCriterion("verify_date is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyDateEqualTo(Date value) {
            addCriterionForJDBCDate("verify_date =", value, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("verify_date <>", value, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("verify_date >", value, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("verify_date >=", value, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateLessThan(Date value) {
            addCriterionForJDBCDate("verify_date <", value, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("verify_date <=", value, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateIn(List<Date> values) {
            addCriterionForJDBCDate("verify_date in", values, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("verify_date not in", values, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("verify_date between", value1, value2, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andVerifyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("verify_date not between", value1, value2, "verifyDate");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIsNull() {
            addCriterion("leave_reason is null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIsNotNull() {
            addCriterion("leave_reason is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonEqualTo(Integer value) {
            addCriterion("leave_reason =", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotEqualTo(Integer value) {
            addCriterion("leave_reason <>", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonGreaterThan(Integer value) {
            addCriterion("leave_reason >", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_reason >=", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLessThan(Integer value) {
            addCriterion("leave_reason <", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLessThanOrEqualTo(Integer value) {
            addCriterion("leave_reason <=", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIn(List<Integer> values) {
            addCriterion("leave_reason in", values, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotIn(List<Integer> values) {
            addCriterion("leave_reason not in", values, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonBetween(Integer value1, Integer value2) {
            addCriterion("leave_reason between", value1, value2, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_reason not between", value1, value2, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveDateIsNull() {
            addCriterion("leave_date is null");
            return (Criteria) this;
        }

        public Criteria andLeaveDateIsNotNull() {
            addCriterion("leave_date is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveDateEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date =", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date <>", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("leave_date >", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date >=", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateLessThan(Date value) {
            addCriterionForJDBCDate("leave_date <", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("leave_date <=", value, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateIn(List<Date> values) {
            addCriterionForJDBCDate("leave_date in", values, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("leave_date not in", values, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("leave_date between", value1, value2, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("leave_date not between", value1, value2, "leaveDate");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationIsNull() {
            addCriterion("leave_destination is null");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationIsNotNull() {
            addCriterion("leave_destination is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationEqualTo(Integer value) {
            addCriterion("leave_destination =", value, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationNotEqualTo(Integer value) {
            addCriterion("leave_destination <>", value, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationGreaterThan(Integer value) {
            addCriterion("leave_destination >", value, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationGreaterThanOrEqualTo(Integer value) {
            addCriterion("leave_destination >=", value, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationLessThan(Integer value) {
            addCriterion("leave_destination <", value, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationLessThanOrEqualTo(Integer value) {
            addCriterion("leave_destination <=", value, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationIn(List<Integer> values) {
            addCriterion("leave_destination in", values, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationNotIn(List<Integer> values) {
            addCriterion("leave_destination not in", values, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationBetween(Integer value1, Integer value2) {
            addCriterion("leave_destination between", value1, value2, "leaveDestination");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationNotBetween(Integer value1, Integer value2) {
            addCriterion("leave_destination not between", value1, value2, "leaveDestination");
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

        public Criteria andAdditionalDurationIsNull() {
            addCriterion("additional_duration is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationIsNotNull() {
            addCriterion("additional_duration is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationEqualTo(Integer value) {
            addCriterion("additional_duration =", value, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationNotEqualTo(Integer value) {
            addCriterion("additional_duration <>", value, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationGreaterThan(Integer value) {
            addCriterion("additional_duration >", value, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("additional_duration >=", value, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationLessThan(Integer value) {
            addCriterion("additional_duration <", value, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationLessThanOrEqualTo(Integer value) {
            addCriterion("additional_duration <=", value, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationIn(List<Integer> values) {
            addCriterion("additional_duration in", values, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationNotIn(List<Integer> values) {
            addCriterion("additional_duration not in", values, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationBetween(Integer value1, Integer value2) {
            addCriterion("additional_duration between", value1, value2, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andAdditionalDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("additional_duration not between", value1, value2, "additionalDuration");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateIsNull() {
            addCriterion("lawyer_cert_date is null");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateIsNotNull() {
            addCriterion("lawyer_cert_date is not null");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_cert_date =", value, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_cert_date <>", value, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateGreaterThan(Date value) {
            addCriterionForJDBCDate("lawyer_cert_date >", value, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_cert_date >=", value, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateLessThan(Date value) {
            addCriterionForJDBCDate("lawyer_cert_date <", value, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lawyer_cert_date <=", value, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateIn(List<Date> values) {
            addCriterionForJDBCDate("lawyer_cert_date in", values, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("lawyer_cert_date not in", values, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lawyer_cert_date between", value1, value2, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lawyer_cert_date not between", value1, value2, "lawyerCertDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelIsNull() {
            addCriterion("servant_level is null");
            return (Criteria) this;
        }

        public Criteria andServantLevelIsNotNull() {
            addCriterion("servant_level is not null");
            return (Criteria) this;
        }

        public Criteria andServantLevelEqualTo(Integer value) {
            addCriterion("servant_level =", value, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelNotEqualTo(Integer value) {
            addCriterion("servant_level <>", value, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelGreaterThan(Integer value) {
            addCriterion("servant_level >", value, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("servant_level >=", value, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelLessThan(Integer value) {
            addCriterion("servant_level <", value, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelLessThanOrEqualTo(Integer value) {
            addCriterion("servant_level <=", value, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelIn(List<Integer> values) {
            addCriterion("servant_level in", values, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelNotIn(List<Integer> values) {
            addCriterion("servant_level not in", values, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelBetween(Integer value1, Integer value2) {
            addCriterion("servant_level between", value1, value2, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("servant_level not between", value1, value2, "servantLevel");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateIsNull() {
            addCriterion("servant_level_date is null");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateIsNotNull() {
            addCriterion("servant_level_date is not null");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateEqualTo(Date value) {
            addCriterionForJDBCDate("servant_level_date =", value, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("servant_level_date <>", value, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateGreaterThan(Date value) {
            addCriterionForJDBCDate("servant_level_date >", value, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("servant_level_date >=", value, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateLessThan(Date value) {
            addCriterionForJDBCDate("servant_level_date <", value, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("servant_level_date <=", value, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateIn(List<Date> values) {
            addCriterionForJDBCDate("servant_level_date in", values, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("servant_level_date not in", values, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("servant_level_date between", value1, value2, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andServantLevelDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("servant_level_date not between", value1, value2, "servantLevelDate");
            return (Criteria) this;
        }

        public Criteria andLawyerCertIsNull() {
            addCriterion("lawyer_cert is null");
            return (Criteria) this;
        }

        public Criteria andLawyerCertIsNotNull() {
            addCriterion("lawyer_cert is not null");
            return (Criteria) this;
        }

        public Criteria andLawyerCertEqualTo(Integer value) {
            addCriterion("lawyer_cert =", value, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertNotEqualTo(Integer value) {
            addCriterion("lawyer_cert <>", value, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertGreaterThan(Integer value) {
            addCriterion("lawyer_cert >", value, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertGreaterThanOrEqualTo(Integer value) {
            addCriterion("lawyer_cert >=", value, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertLessThan(Integer value) {
            addCriterion("lawyer_cert <", value, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertLessThanOrEqualTo(Integer value) {
            addCriterion("lawyer_cert <=", value, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertIn(List<Integer> values) {
            addCriterion("lawyer_cert in", values, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertNotIn(List<Integer> values) {
            addCriterion("lawyer_cert not in", values, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertBetween(Integer value1, Integer value2) {
            addCriterion("lawyer_cert between", value1, value2, "lawyerCert");
            return (Criteria) this;
        }

        public Criteria andLawyerCertNotBetween(Integer value1, Integer value2) {
            addCriterion("lawyer_cert not between", value1, value2, "lawyerCert");
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(Integer value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(Integer value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(Integer value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(Integer value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(Integer value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<Integer> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<Integer> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(Integer value1, Integer value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(Integer value1, Integer value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
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

        public Criteria andUkNoIsNull() {
            addCriterion("uk_no is null");
            return (Criteria) this;
        }

        public Criteria andUkNoIsNotNull() {
            addCriterion("uk_no is not null");
            return (Criteria) this;
        }

        public Criteria andUkNoEqualTo(String value) {
            addCriterion("uk_no =", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotEqualTo(String value) {
            addCriterion("uk_no <>", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoGreaterThan(String value) {
            addCriterion("uk_no >", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoGreaterThanOrEqualTo(String value) {
            addCriterion("uk_no >=", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoLessThan(String value) {
            addCriterion("uk_no <", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoLessThanOrEqualTo(String value) {
            addCriterion("uk_no <=", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoLike(String value) {
            addCriterion("uk_no like", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotLike(String value) {
            addCriterion("uk_no not like", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoIn(List<String> values) {
            addCriterion("uk_no in", values, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotIn(List<String> values) {
            addCriterion("uk_no not in", values, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoBetween(String value1, String value2) {
            addCriterion("uk_no between", value1, value2, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotBetween(String value1, String value2) {
            addCriterion("uk_no not between", value1, value2, "ukNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoIsNull() {
            addCriterion("work_no is null");
            return (Criteria) this;
        }

        public Criteria andWorkNoIsNotNull() {
            addCriterion("work_no is not null");
            return (Criteria) this;
        }

        public Criteria andWorkNoEqualTo(String value) {
            addCriterion("work_no =", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotEqualTo(String value) {
            addCriterion("work_no <>", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoGreaterThan(String value) {
            addCriterion("work_no >", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoGreaterThanOrEqualTo(String value) {
            addCriterion("work_no >=", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLessThan(String value) {
            addCriterion("work_no <", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLessThanOrEqualTo(String value) {
            addCriterion("work_no <=", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLike(String value) {
            addCriterion("work_no like", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotLike(String value) {
            addCriterion("work_no not like", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoIn(List<String> values) {
            addCriterion("work_no in", values, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotIn(List<String> values) {
            addCriterion("work_no not in", values, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoBetween(String value1, String value2) {
            addCriterion("work_no between", value1, value2, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotBetween(String value1, String value2) {
            addCriterion("work_no not between", value1, value2, "workNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoIsNull() {
            addCriterion("fanka_no is null");
            return (Criteria) this;
        }

        public Criteria andFankaNoIsNotNull() {
            addCriterion("fanka_no is not null");
            return (Criteria) this;
        }

        public Criteria andFankaNoEqualTo(String value) {
            addCriterion("fanka_no =", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotEqualTo(String value) {
            addCriterion("fanka_no <>", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoGreaterThan(String value) {
            addCriterion("fanka_no >", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoGreaterThanOrEqualTo(String value) {
            addCriterion("fanka_no >=", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoLessThan(String value) {
            addCriterion("fanka_no <", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoLessThanOrEqualTo(String value) {
            addCriterion("fanka_no <=", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoLike(String value) {
            addCriterion("fanka_no like", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotLike(String value) {
            addCriterion("fanka_no not like", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoIn(List<String> values) {
            addCriterion("fanka_no in", values, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotIn(List<String> values) {
            addCriterion("fanka_no not in", values, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoBetween(String value1, String value2) {
            addCriterion("fanka_no between", value1, value2, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotBetween(String value1, String value2) {
            addCriterion("fanka_no not between", value1, value2, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoIsNull() {
            addCriterion("official_no is null");
            return (Criteria) this;
        }

        public Criteria andOfficialNoIsNotNull() {
            addCriterion("official_no is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialNoEqualTo(String value) {
            addCriterion("official_no =", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotEqualTo(String value) {
            addCriterion("official_no <>", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoGreaterThan(String value) {
            addCriterion("official_no >", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoGreaterThanOrEqualTo(String value) {
            addCriterion("official_no >=", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoLessThan(String value) {
            addCriterion("official_no <", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoLessThanOrEqualTo(String value) {
            addCriterion("official_no <=", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoLike(String value) {
            addCriterion("official_no like", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotLike(String value) {
            addCriterion("official_no not like", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoIn(List<String> values) {
            addCriterion("official_no in", values, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotIn(List<String> values) {
            addCriterion("official_no not in", values, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoBetween(String value1, String value2) {
            addCriterion("official_no between", value1, value2, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotBetween(String value1, String value2) {
            addCriterion("official_no not between", value1, value2, "officialNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
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

        public Criteria andDeptOrgCodeIsNull() {
            addCriterion("dept_org_code is null");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeIsNotNull() {
            addCriterion("dept_org_code is not null");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeEqualTo(String value) {
            addCriterion("dept_org_code =", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeNotEqualTo(String value) {
            addCriterion("dept_org_code <>", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeGreaterThan(String value) {
            addCriterion("dept_org_code >", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dept_org_code >=", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeLessThan(String value) {
            addCriterion("dept_org_code <", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("dept_org_code <=", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeLike(String value) {
            addCriterion("dept_org_code like", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeNotLike(String value) {
            addCriterion("dept_org_code not like", value, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeIn(List<String> values) {
            addCriterion("dept_org_code in", values, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeNotIn(List<String> values) {
            addCriterion("dept_org_code not in", values, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeBetween(String value1, String value2) {
            addCriterion("dept_org_code between", value1, value2, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andDeptOrgCodeNotBetween(String value1, String value2) {
            addCriterion("dept_org_code not between", value1, value2, "deptOrgCode");
            return (Criteria) this;
        }

        public Criteria andYefgIsNull() {
            addCriterion("yefg is null");
            return (Criteria) this;
        }

        public Criteria andYefgIsNotNull() {
            addCriterion("yefg is not null");
            return (Criteria) this;
        }

        public Criteria andYefgEqualTo(Integer value) {
            addCriterion("yefg =", value, "yefg");
            return (Criteria) this;
        }

        // 判断是否是员额法官，是有两个条件，1 主表中yefg=1 2 子表中当前信息时员额法官
        public Criteria andYefgEqualTo1() {
            addCriterion(" (yefg = 1 or exists (select 1 from ums_level_info where user_id = a.id and n_level_type = 1 and is_yefg = 1 and n_present_info = 1)) ");
            return (Criteria) this;
        }

        public Criteria andYefgNotEqualTo(Integer value) {
            addCriterion("yefg <>", value, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgGreaterThan(Integer value) {
            addCriterion("yefg >", value, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgGreaterThanOrEqualTo(Integer value) {
            addCriterion("yefg >=", value, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgLessThan(Integer value) {
            addCriterion("yefg <", value, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgLessThanOrEqualTo(Integer value) {
            addCriterion("yefg <=", value, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgIn(List<Integer> values) {
            addCriterion("yefg in", values, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgNotIn(List<Integer> values) {
            addCriterion("yefg not in", values, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgBetween(Integer value1, Integer value2) {
            addCriterion("yefg between", value1, value2, "yefg");
            return (Criteria) this;
        }

        public Criteria andYefgNotBetween(Integer value1, Integer value2) {
            addCriterion("yefg not between", value1, value2, "yefg");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextIsNull() {
            addCriterion("user_type_text is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextIsNotNull() {
            addCriterion("user_type_text is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextEqualTo(String value) {
            addCriterion("user_type_text =", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextNotEqualTo(String value) {
            addCriterion("user_type_text <>", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextGreaterThan(String value) {
            addCriterion("user_type_text >", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextGreaterThanOrEqualTo(String value) {
            addCriterion("user_type_text >=", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextLessThan(String value) {
            addCriterion("user_type_text <", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextLessThanOrEqualTo(String value) {
            addCriterion("user_type_text <=", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextLike(String value) {
            addCriterion("user_type_text like", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextNotLike(String value) {
            addCriterion("user_type_text not like", value, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextIn(List<String> values) {
            addCriterion("user_type_text in", values, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextNotIn(List<String> values) {
            addCriterion("user_type_text not in", values, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextBetween(String value1, String value2) {
            addCriterion("user_type_text between", value1, value2, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andUserTypeTextNotBetween(String value1, String value2) {
            addCriterion("user_type_text not between", value1, value2, "userTypeText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextIsNull() {
            addCriterion("court_no_text is null");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextIsNotNull() {
            addCriterion("court_no_text is not null");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextEqualTo(String value) {
            addCriterion("court_no_text =", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextNotEqualTo(String value) {
            addCriterion("court_no_text <>", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextGreaterThan(String value) {
            addCriterion("court_no_text >", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextGreaterThanOrEqualTo(String value) {
            addCriterion("court_no_text >=", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextLessThan(String value) {
            addCriterion("court_no_text <", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextLessThanOrEqualTo(String value) {
            addCriterion("court_no_text <=", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextLike(String value) {
            addCriterion("court_no_text like", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextNotLike(String value) {
            addCriterion("court_no_text not like", value, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextIn(List<String> values) {
            addCriterion("court_no_text in", values, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextNotIn(List<String> values) {
            addCriterion("court_no_text not in", values, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextBetween(String value1, String value2) {
            addCriterion("court_no_text between", value1, value2, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andCourtNoTextNotBetween(String value1, String value2) {
            addCriterion("court_no_text not between", value1, value2, "courtNoText");
            return (Criteria) this;
        }

        public Criteria andGenderTextIsNull() {
            addCriterion("gender_text is null");
            return (Criteria) this;
        }

        public Criteria andGenderTextIsNotNull() {
            addCriterion("gender_text is not null");
            return (Criteria) this;
        }

        public Criteria andGenderTextEqualTo(String value) {
            addCriterion("gender_text =", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextNotEqualTo(String value) {
            addCriterion("gender_text <>", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextGreaterThan(String value) {
            addCriterion("gender_text >", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextGreaterThanOrEqualTo(String value) {
            addCriterion("gender_text >=", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextLessThan(String value) {
            addCriterion("gender_text <", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextLessThanOrEqualTo(String value) {
            addCriterion("gender_text <=", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextLike(String value) {
            addCriterion("gender_text like", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextNotLike(String value) {
            addCriterion("gender_text not like", value, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextIn(List<String> values) {
            addCriterion("gender_text in", values, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextNotIn(List<String> values) {
            addCriterion("gender_text not in", values, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextBetween(String value1, String value2) {
            addCriterion("gender_text between", value1, value2, "genderText");
            return (Criteria) this;
        }

        public Criteria andGenderTextNotBetween(String value1, String value2) {
            addCriterion("gender_text not between", value1, value2, "genderText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextIsNull() {
            addCriterion("department_text is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextIsNotNull() {
            addCriterion("department_text is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextEqualTo(String value) {
            addCriterion("department_text =", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextNotEqualTo(String value) {
            addCriterion("department_text <>", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextGreaterThan(String value) {
            addCriterion("department_text >", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextGreaterThanOrEqualTo(String value) {
            addCriterion("department_text >=", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextLessThan(String value) {
            addCriterion("department_text <", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextLessThanOrEqualTo(String value) {
            addCriterion("department_text <=", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextLike(String value) {
            addCriterion("department_text like", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextNotLike(String value) {
            addCriterion("department_text not like", value, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextIn(List<String> values) {
            addCriterion("department_text in", values, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextNotIn(List<String> values) {
            addCriterion("department_text not in", values, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextBetween(String value1, String value2) {
            addCriterion("department_text between", value1, value2, "departmentText");
            return (Criteria) this;
        }

        public Criteria andDepartmentTextNotBetween(String value1, String value2) {
            addCriterion("department_text not between", value1, value2, "departmentText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextIsNull() {
            addCriterion("position_nature_text is null");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextIsNotNull() {
            addCriterion("position_nature_text is not null");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextEqualTo(String value) {
            addCriterion("position_nature_text =", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextNotEqualTo(String value) {
            addCriterion("position_nature_text <>", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextGreaterThan(String value) {
            addCriterion("position_nature_text >", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextGreaterThanOrEqualTo(String value) {
            addCriterion("position_nature_text >=", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextLessThan(String value) {
            addCriterion("position_nature_text <", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextLessThanOrEqualTo(String value) {
            addCriterion("position_nature_text <=", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextLike(String value) {
            addCriterion("position_nature_text like", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextNotLike(String value) {
            addCriterion("position_nature_text not like", value, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextIn(List<String> values) {
            addCriterion("position_nature_text in", values, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextNotIn(List<String> values) {
            addCriterion("position_nature_text not in", values, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextBetween(String value1, String value2) {
            addCriterion("position_nature_text between", value1, value2, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPositionNatureTextNotBetween(String value1, String value2) {
            addCriterion("position_nature_text not between", value1, value2, "positionNatureText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextIsNull() {
            addCriterion("physical_condition_text is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextIsNotNull() {
            addCriterion("physical_condition_text is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextEqualTo(String value) {
            addCriterion("physical_condition_text =", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextNotEqualTo(String value) {
            addCriterion("physical_condition_text <>", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextGreaterThan(String value) {
            addCriterion("physical_condition_text >", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextGreaterThanOrEqualTo(String value) {
            addCriterion("physical_condition_text >=", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextLessThan(String value) {
            addCriterion("physical_condition_text <", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextLessThanOrEqualTo(String value) {
            addCriterion("physical_condition_text <=", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextLike(String value) {
            addCriterion("physical_condition_text like", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextNotLike(String value) {
            addCriterion("physical_condition_text not like", value, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextIn(List<String> values) {
            addCriterion("physical_condition_text in", values, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextNotIn(List<String> values) {
            addCriterion("physical_condition_text not in", values, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextBetween(String value1, String value2) {
            addCriterion("physical_condition_text between", value1, value2, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andPhysicalConditionTextNotBetween(String value1, String value2) {
            addCriterion("physical_condition_text not between", value1, value2, "physicalConditionText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextIsNull() {
            addCriterion("marital_status_text is null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextIsNotNull() {
            addCriterion("marital_status_text is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextEqualTo(String value) {
            addCriterion("marital_status_text =", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextNotEqualTo(String value) {
            addCriterion("marital_status_text <>", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextGreaterThan(String value) {
            addCriterion("marital_status_text >", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextGreaterThanOrEqualTo(String value) {
            addCriterion("marital_status_text >=", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextLessThan(String value) {
            addCriterion("marital_status_text <", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextLessThanOrEqualTo(String value) {
            addCriterion("marital_status_text <=", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextLike(String value) {
            addCriterion("marital_status_text like", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextNotLike(String value) {
            addCriterion("marital_status_text not like", value, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextIn(List<String> values) {
            addCriterion("marital_status_text in", values, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextNotIn(List<String> values) {
            addCriterion("marital_status_text not in", values, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextBetween(String value1, String value2) {
            addCriterion("marital_status_text between", value1, value2, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusTextNotBetween(String value1, String value2) {
            addCriterion("marital_status_text not between", value1, value2, "maritalStatusText");
            return (Criteria) this;
        }

        public Criteria andNationTextIsNull() {
            addCriterion("nation_text is null");
            return (Criteria) this;
        }

        public Criteria andNationTextIsNotNull() {
            addCriterion("nation_text is not null");
            return (Criteria) this;
        }

        public Criteria andNationTextEqualTo(String value) {
            addCriterion("nation_text =", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextNotEqualTo(String value) {
            addCriterion("nation_text <>", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextGreaterThan(String value) {
            addCriterion("nation_text >", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextGreaterThanOrEqualTo(String value) {
            addCriterion("nation_text >=", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextLessThan(String value) {
            addCriterion("nation_text <", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextLessThanOrEqualTo(String value) {
            addCriterion("nation_text <=", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextLike(String value) {
            addCriterion("nation_text like", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextNotLike(String value) {
            addCriterion("nation_text not like", value, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextIn(List<String> values) {
            addCriterion("nation_text in", values, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextNotIn(List<String> values) {
            addCriterion("nation_text not in", values, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextBetween(String value1, String value2) {
            addCriterion("nation_text between", value1, value2, "nationText");
            return (Criteria) this;
        }

        public Criteria andNationTextNotBetween(String value1, String value2) {
            addCriterion("nation_text not between", value1, value2, "nationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextIsNull() {
            addCriterion("preparation_text is null");
            return (Criteria) this;
        }

        public Criteria andPreparationTextIsNotNull() {
            addCriterion("preparation_text is not null");
            return (Criteria) this;
        }

        public Criteria andPreparationTextEqualTo(String value) {
            addCriterion("preparation_text =", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextNotEqualTo(String value) {
            addCriterion("preparation_text <>", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextGreaterThan(String value) {
            addCriterion("preparation_text >", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextGreaterThanOrEqualTo(String value) {
            addCriterion("preparation_text >=", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextLessThan(String value) {
            addCriterion("preparation_text <", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextLessThanOrEqualTo(String value) {
            addCriterion("preparation_text <=", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextLike(String value) {
            addCriterion("preparation_text like", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextNotLike(String value) {
            addCriterion("preparation_text not like", value, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextIn(List<String> values) {
            addCriterion("preparation_text in", values, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextNotIn(List<String> values) {
            addCriterion("preparation_text not in", values, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextBetween(String value1, String value2) {
            addCriterion("preparation_text between", value1, value2, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPreparationTextNotBetween(String value1, String value2) {
            addCriterion("preparation_text not between", value1, value2, "preparationText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextIsNull() {
            addCriterion("position_type_text is null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextIsNotNull() {
            addCriterion("position_type_text is not null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextEqualTo(String value) {
            addCriterion("position_type_text =", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextNotEqualTo(String value) {
            addCriterion("position_type_text <>", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextGreaterThan(String value) {
            addCriterion("position_type_text >", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextGreaterThanOrEqualTo(String value) {
            addCriterion("position_type_text >=", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextLessThan(String value) {
            addCriterion("position_type_text <", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextLessThanOrEqualTo(String value) {
            addCriterion("position_type_text <=", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextLike(String value) {
            addCriterion("position_type_text like", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextNotLike(String value) {
            addCriterion("position_type_text not like", value, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextIn(List<String> values) {
            addCriterion("position_type_text in", values, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextNotIn(List<String> values) {
            addCriterion("position_type_text not in", values, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextBetween(String value1, String value2) {
            addCriterion("position_type_text between", value1, value2, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andPositionTypeTextNotBetween(String value1, String value2) {
            addCriterion("position_type_text not between", value1, value2, "positionTypeText");
            return (Criteria) this;
        }

        public Criteria andAssignTextIsNull() {
            addCriterion("assign_text is null");
            return (Criteria) this;
        }

        public Criteria andAssignTextIsNotNull() {
            addCriterion("assign_text is not null");
            return (Criteria) this;
        }

        public Criteria andAssignTextEqualTo(String value) {
            addCriterion("assign_text =", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextNotEqualTo(String value) {
            addCriterion("assign_text <>", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextGreaterThan(String value) {
            addCriterion("assign_text >", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextGreaterThanOrEqualTo(String value) {
            addCriterion("assign_text >=", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextLessThan(String value) {
            addCriterion("assign_text <", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextLessThanOrEqualTo(String value) {
            addCriterion("assign_text <=", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextLike(String value) {
            addCriterion("assign_text like", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextNotLike(String value) {
            addCriterion("assign_text not like", value, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextIn(List<String> values) {
            addCriterion("assign_text in", values, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextNotIn(List<String> values) {
            addCriterion("assign_text not in", values, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextBetween(String value1, String value2) {
            addCriterion("assign_text between", value1, value2, "assignText");
            return (Criteria) this;
        }

        public Criteria andAssignTextNotBetween(String value1, String value2) {
            addCriterion("assign_text not between", value1, value2, "assignText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextIsNull() {
            addCriterion("education_background_text is null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextIsNotNull() {
            addCriterion("education_background_text is not null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextEqualTo(String value) {
            addCriterion("education_background_text =", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextNotEqualTo(String value) {
            addCriterion("education_background_text <>", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextGreaterThan(String value) {
            addCriterion("education_background_text >", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextGreaterThanOrEqualTo(String value) {
            addCriterion("education_background_text >=", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextLessThan(String value) {
            addCriterion("education_background_text <", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextLessThanOrEqualTo(String value) {
            addCriterion("education_background_text <=", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextLike(String value) {
            addCriterion("education_background_text like", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextNotLike(String value) {
            addCriterion("education_background_text not like", value, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextIn(List<String> values) {
            addCriterion("education_background_text in", values, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextNotIn(List<String> values) {
            addCriterion("education_background_text not in", values, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextBetween(String value1, String value2) {
            addCriterion("education_background_text between", value1, value2, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundTextNotBetween(String value1, String value2) {
            addCriterion("education_background_text not between", value1, value2, "educationBackgroundText");
            return (Criteria) this;
        }

        public Criteria andMajorTextIsNull() {
            addCriterion("major_text is null");
            return (Criteria) this;
        }

        public Criteria andMajorTextIsNotNull() {
            addCriterion("major_text is not null");
            return (Criteria) this;
        }

        public Criteria andMajorTextEqualTo(String value) {
            addCriterion("major_text =", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextNotEqualTo(String value) {
            addCriterion("major_text <>", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextGreaterThan(String value) {
            addCriterion("major_text >", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextGreaterThanOrEqualTo(String value) {
            addCriterion("major_text >=", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextLessThan(String value) {
            addCriterion("major_text <", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextLessThanOrEqualTo(String value) {
            addCriterion("major_text <=", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextLike(String value) {
            addCriterion("major_text like", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextNotLike(String value) {
            addCriterion("major_text not like", value, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextIn(List<String> values) {
            addCriterion("major_text in", values, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextNotIn(List<String> values) {
            addCriterion("major_text not in", values, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextBetween(String value1, String value2) {
            addCriterion("major_text between", value1, value2, "majorText");
            return (Criteria) this;
        }

        public Criteria andMajorTextNotBetween(String value1, String value2) {
            addCriterion("major_text not between", value1, value2, "majorText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextIsNull() {
            addCriterion("degree_text is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTextIsNotNull() {
            addCriterion("degree_text is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTextEqualTo(String value) {
            addCriterion("degree_text =", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextNotEqualTo(String value) {
            addCriterion("degree_text <>", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextGreaterThan(String value) {
            addCriterion("degree_text >", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextGreaterThanOrEqualTo(String value) {
            addCriterion("degree_text >=", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextLessThan(String value) {
            addCriterion("degree_text <", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextLessThanOrEqualTo(String value) {
            addCriterion("degree_text <=", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextLike(String value) {
            addCriterion("degree_text like", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextNotLike(String value) {
            addCriterion("degree_text not like", value, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextIn(List<String> values) {
            addCriterion("degree_text in", values, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextNotIn(List<String> values) {
            addCriterion("degree_text not in", values, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextBetween(String value1, String value2) {
            addCriterion("degree_text between", value1, value2, "degreeText");
            return (Criteria) this;
        }

        public Criteria andDegreeTextNotBetween(String value1, String value2) {
            addCriterion("degree_text not between", value1, value2, "degreeText");
            return (Criteria) this;
        }

        public Criteria andProCertTextIsNull() {
            addCriterion("pro_cert_text is null");
            return (Criteria) this;
        }

        public Criteria andProCertTextIsNotNull() {
            addCriterion("pro_cert_text is not null");
            return (Criteria) this;
        }

        public Criteria andProCertTextEqualTo(String value) {
            addCriterion("pro_cert_text =", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextNotEqualTo(String value) {
            addCriterion("pro_cert_text <>", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextGreaterThan(String value) {
            addCriterion("pro_cert_text >", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextGreaterThanOrEqualTo(String value) {
            addCriterion("pro_cert_text >=", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextLessThan(String value) {
            addCriterion("pro_cert_text <", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextLessThanOrEqualTo(String value) {
            addCriterion("pro_cert_text <=", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextLike(String value) {
            addCriterion("pro_cert_text like", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextNotLike(String value) {
            addCriterion("pro_cert_text not like", value, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextIn(List<String> values) {
            addCriterion("pro_cert_text in", values, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextNotIn(List<String> values) {
            addCriterion("pro_cert_text not in", values, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextBetween(String value1, String value2) {
            addCriterion("pro_cert_text between", value1, value2, "proCertText");
            return (Criteria) this;
        }

        public Criteria andProCertTextNotBetween(String value1, String value2) {
            addCriterion("pro_cert_text not between", value1, value2, "proCertText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextIsNull() {
            addCriterion("political_text is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextIsNotNull() {
            addCriterion("political_text is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextEqualTo(String value) {
            addCriterion("political_text =", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextNotEqualTo(String value) {
            addCriterion("political_text <>", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextGreaterThan(String value) {
            addCriterion("political_text >", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextGreaterThanOrEqualTo(String value) {
            addCriterion("political_text >=", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextLessThan(String value) {
            addCriterion("political_text <", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextLessThanOrEqualTo(String value) {
            addCriterion("political_text <=", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextLike(String value) {
            addCriterion("political_text like", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextNotLike(String value) {
            addCriterion("political_text not like", value, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextIn(List<String> values) {
            addCriterion("political_text in", values, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextNotIn(List<String> values) {
            addCriterion("political_text not in", values, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextBetween(String value1, String value2) {
            addCriterion("political_text between", value1, value2, "politicalText");
            return (Criteria) this;
        }

        public Criteria andPoliticalTextNotBetween(String value1, String value2) {
            addCriterion("political_text not between", value1, value2, "politicalText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextIsNull() {
            addCriterion("administration_position_text is null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextIsNotNull() {
            addCriterion("administration_position_text is not null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextEqualTo(String value) {
            addCriterion("administration_position_text =", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextNotEqualTo(String value) {
            addCriterion("administration_position_text <>", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextGreaterThan(String value) {
            addCriterion("administration_position_text >", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextGreaterThanOrEqualTo(String value) {
            addCriterion("administration_position_text >=", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextLessThan(String value) {
            addCriterion("administration_position_text <", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextLessThanOrEqualTo(String value) {
            addCriterion("administration_position_text <=", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextLike(String value) {
            addCriterion("administration_position_text like", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextNotLike(String value) {
            addCriterion("administration_position_text not like", value, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextIn(List<String> values) {
            addCriterion("administration_position_text in", values, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextNotIn(List<String> values) {
            addCriterion("administration_position_text not in", values, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextBetween(String value1, String value2) {
            addCriterion("administration_position_text between", value1, value2, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionTextNotBetween(String value1, String value2) {
            addCriterion("administration_position_text not between", value1, value2, "administrationPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextIsNull() {
            addCriterion("law_position_text is null");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextIsNotNull() {
            addCriterion("law_position_text is not null");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextEqualTo(String value) {
            addCriterion("law_position_text =", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextNotEqualTo(String value) {
            addCriterion("law_position_text <>", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextGreaterThan(String value) {
            addCriterion("law_position_text >", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextGreaterThanOrEqualTo(String value) {
            addCriterion("law_position_text >=", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextLessThan(String value) {
            addCriterion("law_position_text <", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextLessThanOrEqualTo(String value) {
            addCriterion("law_position_text <=", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextLike(String value) {
            addCriterion("law_position_text like", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextNotLike(String value) {
            addCriterion("law_position_text not like", value, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextIn(List<String> values) {
            addCriterion("law_position_text in", values, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextNotIn(List<String> values) {
            addCriterion("law_position_text not in", values, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextBetween(String value1, String value2) {
            addCriterion("law_position_text between", value1, value2, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andLawPositionTextNotBetween(String value1, String value2) {
            addCriterion("law_position_text not between", value1, value2, "lawPositionText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextIsNull() {
            addCriterion("is_parttime_presiding_judge_text is null");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextIsNotNull() {
            addCriterion("is_parttime_presiding_judge_text is not null");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextEqualTo(String value) {
            addCriterion("is_parttime_presiding_judge_text =", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextNotEqualTo(String value) {
            addCriterion("is_parttime_presiding_judge_text <>", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextGreaterThan(String value) {
            addCriterion("is_parttime_presiding_judge_text >", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextGreaterThanOrEqualTo(String value) {
            addCriterion("is_parttime_presiding_judge_text >=", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextLessThan(String value) {
            addCriterion("is_parttime_presiding_judge_text <", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextLessThanOrEqualTo(String value) {
            addCriterion("is_parttime_presiding_judge_text <=", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextLike(String value) {
            addCriterion("is_parttime_presiding_judge_text like", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextNotLike(String value) {
            addCriterion("is_parttime_presiding_judge_text not like", value, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextIn(List<String> values) {
            addCriterion("is_parttime_presiding_judge_text in", values, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextNotIn(List<String> values) {
            addCriterion("is_parttime_presiding_judge_text not in", values, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextBetween(String value1, String value2) {
            addCriterion("is_parttime_presiding_judge_text between", value1, value2, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andIsParttimePresidingJudgeTextNotBetween(String value1, String value2) {
            addCriterion("is_parttime_presiding_judge_text not between", value1, value2, "isParttimePresidingJudgeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextIsNull() {
            addCriterion("party_office_text is null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextIsNotNull() {
            addCriterion("party_office_text is not null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextEqualTo(String value) {
            addCriterion("party_office_text =", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextNotEqualTo(String value) {
            addCriterion("party_office_text <>", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextGreaterThan(String value) {
            addCriterion("party_office_text >", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextGreaterThanOrEqualTo(String value) {
            addCriterion("party_office_text >=", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextLessThan(String value) {
            addCriterion("party_office_text <", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextLessThanOrEqualTo(String value) {
            addCriterion("party_office_text <=", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextLike(String value) {
            addCriterion("party_office_text like", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextNotLike(String value) {
            addCriterion("party_office_text not like", value, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextIn(List<String> values) {
            addCriterion("party_office_text in", values, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextNotIn(List<String> values) {
            addCriterion("party_office_text not in", values, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextBetween(String value1, String value2) {
            addCriterion("party_office_text between", value1, value2, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeTextNotBetween(String value1, String value2) {
            addCriterion("party_office_text not between", value1, value2, "partyOfficeText");
            return (Criteria) this;
        }

        public Criteria andRankTextIsNull() {
            addCriterion("rank_text is null");
            return (Criteria) this;
        }

        public Criteria andRankTextIsNotNull() {
            addCriterion("rank_text is not null");
            return (Criteria) this;
        }

        public Criteria andRankTextEqualTo(String value) {
            addCriterion("rank_text =", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextNotEqualTo(String value) {
            addCriterion("rank_text <>", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextGreaterThan(String value) {
            addCriterion("rank_text >", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextGreaterThanOrEqualTo(String value) {
            addCriterion("rank_text >=", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextLessThan(String value) {
            addCriterion("rank_text <", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextLessThanOrEqualTo(String value) {
            addCriterion("rank_text <=", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextLike(String value) {
            addCriterion("rank_text like", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextNotLike(String value) {
            addCriterion("rank_text not like", value, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextIn(List<String> values) {
            addCriterion("rank_text in", values, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextNotIn(List<String> values) {
            addCriterion("rank_text not in", values, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextBetween(String value1, String value2) {
            addCriterion("rank_text between", value1, value2, "rankText");
            return (Criteria) this;
        }

        public Criteria andRankTextNotBetween(String value1, String value2) {
            addCriterion("rank_text not between", value1, value2, "rankText");
            return (Criteria) this;
        }

        public Criteria andLevelTextIsNull() {
            addCriterion("level_text is null");
            return (Criteria) this;
        }

        public Criteria andLevelTextIsNotNull() {
            addCriterion("level_text is not null");
            return (Criteria) this;
        }

        public Criteria andLevelTextEqualTo(String value) {
            addCriterion("level_text =", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextNotEqualTo(String value) {
            addCriterion("level_text <>", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextGreaterThan(String value) {
            addCriterion("level_text >", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextGreaterThanOrEqualTo(String value) {
            addCriterion("level_text >=", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextLessThan(String value) {
            addCriterion("level_text <", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextLessThanOrEqualTo(String value) {
            addCriterion("level_text <=", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextLike(String value) {
            addCriterion("level_text like", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextNotLike(String value) {
            addCriterion("level_text not like", value, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextIn(List<String> values) {
            addCriterion("level_text in", values, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextNotIn(List<String> values) {
            addCriterion("level_text not in", values, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextBetween(String value1, String value2) {
            addCriterion("level_text between", value1, value2, "levelText");
            return (Criteria) this;
        }

        public Criteria andLevelTextNotBetween(String value1, String value2) {
            addCriterion("level_text not between", value1, value2, "levelText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextIsNull() {
            addCriterion("enter_way_text is null");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextIsNotNull() {
            addCriterion("enter_way_text is not null");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextEqualTo(String value) {
            addCriterion("enter_way_text =", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextNotEqualTo(String value) {
            addCriterion("enter_way_text <>", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextGreaterThan(String value) {
            addCriterion("enter_way_text >", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextGreaterThanOrEqualTo(String value) {
            addCriterion("enter_way_text >=", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextLessThan(String value) {
            addCriterion("enter_way_text <", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextLessThanOrEqualTo(String value) {
            addCriterion("enter_way_text <=", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextLike(String value) {
            addCriterion("enter_way_text like", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextNotLike(String value) {
            addCriterion("enter_way_text not like", value, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextIn(List<String> values) {
            addCriterion("enter_way_text in", values, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextNotIn(List<String> values) {
            addCriterion("enter_way_text not in", values, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextBetween(String value1, String value2) {
            addCriterion("enter_way_text between", value1, value2, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterWayTextNotBetween(String value1, String value2) {
            addCriterion("enter_way_text not between", value1, value2, "enterWayText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextIsNull() {
            addCriterion("enter_source_text is null");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextIsNotNull() {
            addCriterion("enter_source_text is not null");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextEqualTo(String value) {
            addCriterion("enter_source_text =", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextNotEqualTo(String value) {
            addCriterion("enter_source_text <>", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextGreaterThan(String value) {
            addCriterion("enter_source_text >", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextGreaterThanOrEqualTo(String value) {
            addCriterion("enter_source_text >=", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextLessThan(String value) {
            addCriterion("enter_source_text <", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextLessThanOrEqualTo(String value) {
            addCriterion("enter_source_text <=", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextLike(String value) {
            addCriterion("enter_source_text like", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextNotLike(String value) {
            addCriterion("enter_source_text not like", value, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextIn(List<String> values) {
            addCriterion("enter_source_text in", values, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextNotIn(List<String> values) {
            addCriterion("enter_source_text not in", values, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextBetween(String value1, String value2) {
            addCriterion("enter_source_text between", value1, value2, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andEnterSourceTextNotBetween(String value1, String value2) {
            addCriterion("enter_source_text not between", value1, value2, "enterSourceText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextIsNull() {
            addCriterion("former_post_text is null");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextIsNotNull() {
            addCriterion("former_post_text is not null");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextEqualTo(String value) {
            addCriterion("former_post_text =", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextNotEqualTo(String value) {
            addCriterion("former_post_text <>", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextGreaterThan(String value) {
            addCriterion("former_post_text >", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextGreaterThanOrEqualTo(String value) {
            addCriterion("former_post_text >=", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextLessThan(String value) {
            addCriterion("former_post_text <", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextLessThanOrEqualTo(String value) {
            addCriterion("former_post_text <=", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextLike(String value) {
            addCriterion("former_post_text like", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextNotLike(String value) {
            addCriterion("former_post_text not like", value, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextIn(List<String> values) {
            addCriterion("former_post_text in", values, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextNotIn(List<String> values) {
            addCriterion("former_post_text not in", values, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextBetween(String value1, String value2) {
            addCriterion("former_post_text between", value1, value2, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerPostTextNotBetween(String value1, String value2) {
            addCriterion("former_post_text not between", value1, value2, "formerPostText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextIsNull() {
            addCriterion("former_rank_text is null");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextIsNotNull() {
            addCriterion("former_rank_text is not null");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextEqualTo(String value) {
            addCriterion("former_rank_text =", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextNotEqualTo(String value) {
            addCriterion("former_rank_text <>", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextGreaterThan(String value) {
            addCriterion("former_rank_text >", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextGreaterThanOrEqualTo(String value) {
            addCriterion("former_rank_text >=", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextLessThan(String value) {
            addCriterion("former_rank_text <", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextLessThanOrEqualTo(String value) {
            addCriterion("former_rank_text <=", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextLike(String value) {
            addCriterion("former_rank_text like", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextNotLike(String value) {
            addCriterion("former_rank_text not like", value, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextIn(List<String> values) {
            addCriterion("former_rank_text in", values, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextNotIn(List<String> values) {
            addCriterion("former_rank_text not in", values, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextBetween(String value1, String value2) {
            addCriterion("former_rank_text between", value1, value2, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andFormerRankTextNotBetween(String value1, String value2) {
            addCriterion("former_rank_text not between", value1, value2, "formerRankText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextIsNull() {
            addCriterion("leave_reason_text is null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextIsNotNull() {
            addCriterion("leave_reason_text is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextEqualTo(String value) {
            addCriterion("leave_reason_text =", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextNotEqualTo(String value) {
            addCriterion("leave_reason_text <>", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextGreaterThan(String value) {
            addCriterion("leave_reason_text >", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextGreaterThanOrEqualTo(String value) {
            addCriterion("leave_reason_text >=", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextLessThan(String value) {
            addCriterion("leave_reason_text <", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextLessThanOrEqualTo(String value) {
            addCriterion("leave_reason_text <=", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextLike(String value) {
            addCriterion("leave_reason_text like", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextNotLike(String value) {
            addCriterion("leave_reason_text not like", value, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextIn(List<String> values) {
            addCriterion("leave_reason_text in", values, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextNotIn(List<String> values) {
            addCriterion("leave_reason_text not in", values, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextBetween(String value1, String value2) {
            addCriterion("leave_reason_text between", value1, value2, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonTextNotBetween(String value1, String value2) {
            addCriterion("leave_reason_text not between", value1, value2, "leaveReasonText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextIsNull() {
            addCriterion("leave_destination_text is null");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextIsNotNull() {
            addCriterion("leave_destination_text is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextEqualTo(String value) {
            addCriterion("leave_destination_text =", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextNotEqualTo(String value) {
            addCriterion("leave_destination_text <>", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextGreaterThan(String value) {
            addCriterion("leave_destination_text >", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextGreaterThanOrEqualTo(String value) {
            addCriterion("leave_destination_text >=", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextLessThan(String value) {
            addCriterion("leave_destination_text <", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextLessThanOrEqualTo(String value) {
            addCriterion("leave_destination_text <=", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextLike(String value) {
            addCriterion("leave_destination_text like", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextNotLike(String value) {
            addCriterion("leave_destination_text not like", value, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextIn(List<String> values) {
            addCriterion("leave_destination_text in", values, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextNotIn(List<String> values) {
            addCriterion("leave_destination_text not in", values, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextBetween(String value1, String value2) {
            addCriterion("leave_destination_text between", value1, value2, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andLeaveDestinationTextNotBetween(String value1, String value2) {
            addCriterion("leave_destination_text not between", value1, value2, "leaveDestinationText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextIsNull() {
            addCriterion("is_valid_text is null");
            return (Criteria) this;
        }

        public Criteria andIsValidTextIsNotNull() {
            addCriterion("is_valid_text is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidTextEqualTo(String value) {
            addCriterion("is_valid_text =", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextNotEqualTo(String value) {
            addCriterion("is_valid_text <>", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextGreaterThan(String value) {
            addCriterion("is_valid_text >", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextGreaterThanOrEqualTo(String value) {
            addCriterion("is_valid_text >=", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextLessThan(String value) {
            addCriterion("is_valid_text <", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextLessThanOrEqualTo(String value) {
            addCriterion("is_valid_text <=", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextLike(String value) {
            addCriterion("is_valid_text like", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextNotLike(String value) {
            addCriterion("is_valid_text not like", value, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextIn(List<String> values) {
            addCriterion("is_valid_text in", values, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextNotIn(List<String> values) {
            addCriterion("is_valid_text not in", values, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextBetween(String value1, String value2) {
            addCriterion("is_valid_text between", value1, value2, "isValidText");
            return (Criteria) this;
        }

        public Criteria andIsValidTextNotBetween(String value1, String value2) {
            addCriterion("is_valid_text not between", value1, value2, "isValidText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextIsNull() {
            addCriterion("servant_level_text is null");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextIsNotNull() {
            addCriterion("servant_level_text is not null");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextEqualTo(String value) {
            addCriterion("servant_level_text =", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextNotEqualTo(String value) {
            addCriterion("servant_level_text <>", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextGreaterThan(String value) {
            addCriterion("servant_level_text >", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextGreaterThanOrEqualTo(String value) {
            addCriterion("servant_level_text >=", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextLessThan(String value) {
            addCriterion("servant_level_text <", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextLessThanOrEqualTo(String value) {
            addCriterion("servant_level_text <=", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextLike(String value) {
            addCriterion("servant_level_text like", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextNotLike(String value) {
            addCriterion("servant_level_text not like", value, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextIn(List<String> values) {
            addCriterion("servant_level_text in", values, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextNotIn(List<String> values) {
            addCriterion("servant_level_text not in", values, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextBetween(String value1, String value2) {
            addCriterion("servant_level_text between", value1, value2, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andServantLevelTextNotBetween(String value1, String value2) {
            addCriterion("servant_level_text not between", value1, value2, "servantLevelText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextIsNull() {
            addCriterion("lawyer_cert_text is null");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextIsNotNull() {
            addCriterion("lawyer_cert_text is not null");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextEqualTo(String value) {
            addCriterion("lawyer_cert_text =", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextNotEqualTo(String value) {
            addCriterion("lawyer_cert_text <>", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextGreaterThan(String value) {
            addCriterion("lawyer_cert_text >", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextGreaterThanOrEqualTo(String value) {
            addCriterion("lawyer_cert_text >=", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextLessThan(String value) {
            addCriterion("lawyer_cert_text <", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextLessThanOrEqualTo(String value) {
            addCriterion("lawyer_cert_text <=", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextLike(String value) {
            addCriterion("lawyer_cert_text like", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextNotLike(String value) {
            addCriterion("lawyer_cert_text not like", value, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextIn(List<String> values) {
            addCriterion("lawyer_cert_text in", values, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextNotIn(List<String> values) {
            addCriterion("lawyer_cert_text not in", values, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextBetween(String value1, String value2) {
            addCriterion("lawyer_cert_text between", value1, value2, "lawyerCertText");
            return (Criteria) this;
        }

        public Criteria andLawyerCertTextNotBetween(String value1, String value2) {
            addCriterion("lawyer_cert_text not between", value1, value2, "lawyerCertText");
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

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterionForJDBCDate("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterionForJDBCDate("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andLocalAddressIsNull() {
            addCriterion("local_address is null");
            return (Criteria) this;
        }

        public Criteria andLocalAddressIsNotNull() {
            addCriterion("local_address is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAddressEqualTo(String value) {
            addCriterion("local_address =", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressNotEqualTo(String value) {
            addCriterion("local_address <>", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressGreaterThan(String value) {
            addCriterion("local_address >", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressGreaterThanOrEqualTo(String value) {
            addCriterion("local_address >=", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressLessThan(String value) {
            addCriterion("local_address <", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressLessThanOrEqualTo(String value) {
            addCriterion("local_address <=", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressLike(String value) {
            addCriterion("local_address like", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressNotLike(String value) {
            addCriterion("local_address not like", value, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressIn(List<String> values) {
            addCriterion("local_address in", values, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressNotIn(List<String> values) {
            addCriterion("local_address not in", values, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressBetween(String value1, String value2) {
            addCriterion("local_address between", value1, value2, "localAddress");
            return (Criteria) this;
        }

        public Criteria andLocalAddressNotBetween(String value1, String value2) {
            addCriterion("local_address not between", value1, value2, "localAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressIsNull() {
            addCriterion("postal_address is null");
            return (Criteria) this;
        }

        public Criteria andPostalAddressIsNotNull() {
            addCriterion("postal_address is not null");
            return (Criteria) this;
        }

        public Criteria andPostalAddressEqualTo(String value) {
            addCriterion("postal_address =", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotEqualTo(String value) {
            addCriterion("postal_address <>", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressGreaterThan(String value) {
            addCriterion("postal_address >", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressGreaterThanOrEqualTo(String value) {
            addCriterion("postal_address >=", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressLessThan(String value) {
            addCriterion("postal_address <", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressLessThanOrEqualTo(String value) {
            addCriterion("postal_address <=", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressLike(String value) {
            addCriterion("postal_address like", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotLike(String value) {
            addCriterion("postal_address not like", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressIn(List<String> values) {
            addCriterion("postal_address in", values, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotIn(List<String> values) {
            addCriterion("postal_address not in", values, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressBetween(String value1, String value2) {
            addCriterion("postal_address between", value1, value2, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotBetween(String value1, String value2) {
            addCriterion("postal_address not between", value1, value2, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andJobIsNull() {
            addCriterion("job is null");
            return (Criteria) this;
        }

        public Criteria andJobIsNotNull() {
            addCriterion("job is not null");
            return (Criteria) this;
        }

        public Criteria andJobEqualTo(String value) {
            addCriterion("job =", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotEqualTo(String value) {
            addCriterion("job <>", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThan(String value) {
            addCriterion("job >", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobGreaterThanOrEqualTo(String value) {
            addCriterion("job >=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThan(String value) {
            addCriterion("job <", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLessThanOrEqualTo(String value) {
            addCriterion("job <=", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobLike(String value) {
            addCriterion("job like", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotLike(String value) {
            addCriterion("job not like", value, "job");
            return (Criteria) this;
        }

        public Criteria andJobIn(List<String> values) {
            addCriterion("job in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotIn(List<String> values) {
            addCriterion("job not in", values, "job");
            return (Criteria) this;
        }

        public Criteria andJobBetween(String value1, String value2) {
            addCriterion("job between", value1, value2, "job");
            return (Criteria) this;
        }

        public Criteria andJobNotBetween(String value1, String value2) {
            addCriterion("job not between", value1, value2, "job");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextIsNull() {
            addCriterion("juror_edu_text is null");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextIsNotNull() {
            addCriterion("juror_edu_text is not null");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextEqualTo(String value) {
            addCriterion("juror_edu_text =", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextNotEqualTo(String value) {
            addCriterion("juror_edu_text <>", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextGreaterThan(String value) {
            addCriterion("juror_edu_text >", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextGreaterThanOrEqualTo(String value) {
            addCriterion("juror_edu_text >=", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextLessThan(String value) {
            addCriterion("juror_edu_text <", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextLessThanOrEqualTo(String value) {
            addCriterion("juror_edu_text <=", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextLike(String value) {
            addCriterion("juror_edu_text like", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextNotLike(String value) {
            addCriterion("juror_edu_text not like", value, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextIn(List<String> values) {
            addCriterion("juror_edu_text in", values, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextNotIn(List<String> values) {
            addCriterion("juror_edu_text not in", values, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextBetween(String value1, String value2) {
            addCriterion("juror_edu_text between", value1, value2, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andJurorEduTextNotBetween(String value1, String value2) {
            addCriterion("juror_edu_text not between", value1, value2, "jurorEduText");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoIsNull() {
            addCriterion("dept_sortNo is null");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoIsNotNull() {
            addCriterion("dept_sortNo is not null");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoEqualTo(Long value) {
            addCriterion("dept_sortNo =", value, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoNotEqualTo(Long value) {
            addCriterion("dept_sortNo <>", value, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoGreaterThan(Long value) {
            addCriterion("dept_sortNo >", value, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoGreaterThanOrEqualTo(Long value) {
            addCriterion("dept_sortNo >=", value, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoLessThan(Long value) {
            addCriterion("dept_sortNo <", value, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoLessThanOrEqualTo(Long value) {
            addCriterion("dept_sortNo <=", value, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoIn(List<Long> values) {
            addCriterion("dept_sortNo in", values, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoNotIn(List<Long> values) {
            addCriterion("dept_sortNo not in", values, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoBetween(Long value1, Long value2) {
            addCriterion("dept_sortNo between", value1, value2, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptSortnoNotBetween(Long value1, Long value2) {
            addCriterion("dept_sortNo not between", value1, value2, "deptSortno");
            return (Criteria) this;
        }

        public Criteria andDeptLevelIsNull() {
            addCriterion("dept_level is null");
            return (Criteria) this;
        }

        public Criteria andDeptLevelIsNotNull() {
            addCriterion("dept_level is not null");
            return (Criteria) this;
        }

        public Criteria andDeptLevelEqualTo(Long value) {
            addCriterion("dept_level =", value, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelNotEqualTo(Long value) {
            addCriterion("dept_level <>", value, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelGreaterThan(Long value) {
            addCriterion("dept_level >", value, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelGreaterThanOrEqualTo(Long value) {
            addCriterion("dept_level >=", value, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelLessThan(Long value) {
            addCriterion("dept_level <", value, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelLessThanOrEqualTo(Long value) {
            addCriterion("dept_level <=", value, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelIn(List<Long> values) {
            addCriterion("dept_level in", values, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelNotIn(List<Long> values) {
            addCriterion("dept_level not in", values, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelBetween(Long value1, Long value2) {
            addCriterion("dept_level between", value1, value2, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andDeptLevelNotBetween(Long value1, Long value2) {
            addCriterion("dept_level not between", value1, value2, "deptLevel");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationIsNull() {
            addCriterion("personnel_classification is null");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationIsNotNull() {
            addCriterion("personnel_classification is not null");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationEqualTo(String value) {
            addCriterion("personnel_classification =", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationNotEqualTo(String value) {
            addCriterion("personnel_classification <>", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationGreaterThan(String value) {
            addCriterion("personnel_classification >", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationGreaterThanOrEqualTo(String value) {
            addCriterion("personnel_classification >=", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationLessThan(String value) {
            addCriterion("personnel_classification <", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationLessThanOrEqualTo(String value) {
            addCriterion("personnel_classification <=", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationLike(String value) {
            addCriterion("personnel_classification like", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationNotLike(String value) {
            addCriterion("personnel_classification not like", value, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationIn(List<String> values) {
            addCriterion("personnel_classification in", values, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationNotIn(List<String> values) {
            addCriterion("personnel_classification not in", values, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationBetween(String value1, String value2) {
            addCriterion("personnel_classification between", value1, value2, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andPersonnelClassificationNotBetween(String value1, String value2) {
            addCriterion("personnel_classification not between", value1, value2, "personnelClassification");
            return (Criteria) this;
        }

        public Criteria andNationReportIsNull() {
            addCriterion("nation_report is null");
            return (Criteria) this;
        }

        public Criteria andNationReportIsNotNull() {
            addCriterion("nation_report is not null");
            return (Criteria) this;
        }

        public Criteria andNationReportEqualTo(String value) {
            addCriterion("nation_report =", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportNotEqualTo(String value) {
            addCriterion("nation_report <>", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportGreaterThan(String value) {
            addCriterion("nation_report >", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportGreaterThanOrEqualTo(String value) {
            addCriterion("nation_report >=", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportLessThan(String value) {
            addCriterion("nation_report <", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportLessThanOrEqualTo(String value) {
            addCriterion("nation_report <=", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportLike(String value) {
            addCriterion("nation_report like", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportNotLike(String value) {
            addCriterion("nation_report not like", value, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportIn(List<String> values) {
            addCriterion("nation_report in", values, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportNotIn(List<String> values) {
            addCriterion("nation_report not in", values, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportBetween(String value1, String value2) {
            addCriterion("nation_report between", value1, value2, "nationReport");
            return (Criteria) this;
        }

        public Criteria andNationReportNotBetween(String value1, String value2) {
            addCriterion("nation_report not between", value1, value2, "nationReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportIsNull() {
            addCriterion("education_background_report is null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportIsNotNull() {
            addCriterion("education_background_report is not null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportEqualTo(String value) {
            addCriterion("education_background_report =", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportNotEqualTo(String value) {
            addCriterion("education_background_report <>", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportGreaterThan(String value) {
            addCriterion("education_background_report >", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportGreaterThanOrEqualTo(String value) {
            addCriterion("education_background_report >=", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportLessThan(String value) {
            addCriterion("education_background_report <", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportLessThanOrEqualTo(String value) {
            addCriterion("education_background_report <=", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportLike(String value) {
            addCriterion("education_background_report like", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportNotLike(String value) {
            addCriterion("education_background_report not like", value, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportIn(List<String> values) {
            addCriterion("education_background_report in", values, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportNotIn(List<String> values) {
            addCriterion("education_background_report not in", values, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportBetween(String value1, String value2) {
            addCriterion("education_background_report between", value1, value2, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportNotBetween(String value1, String value2) {
            addCriterion("education_background_report not between", value1, value2, "educationBackgroundReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportIsNull() {
            addCriterion("administration_position_report is null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportIsNotNull() {
            addCriterion("administration_position_report is not null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportEqualTo(String value) {
            addCriterion("administration_position_report =", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportNotEqualTo(String value) {
            addCriterion("administration_position_report <>", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportGreaterThan(String value) {
            addCriterion("administration_position_report >", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportGreaterThanOrEqualTo(String value) {
            addCriterion("administration_position_report >=", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportLessThan(String value) {
            addCriterion("administration_position_report <", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportLessThanOrEqualTo(String value) {
            addCriterion("administration_position_report <=", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportLike(String value) {
            addCriterion("administration_position_report like", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportNotLike(String value) {
            addCriterion("administration_position_report not like", value, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportIn(List<String> values) {
            addCriterion("administration_position_report in", values, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportNotIn(List<String> values) {
            addCriterion("administration_position_report not in", values, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportBetween(String value1, String value2) {
            addCriterion("administration_position_report between", value1, value2, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportNotBetween(String value1, String value2) {
            addCriterion("administration_position_report not between", value1, value2, "administrationPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportIsNull() {
            addCriterion("law_position_report is null");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportIsNotNull() {
            addCriterion("law_position_report is not null");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportEqualTo(String value) {
            addCriterion("law_position_report =", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportNotEqualTo(String value) {
            addCriterion("law_position_report <>", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportGreaterThan(String value) {
            addCriterion("law_position_report >", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportGreaterThanOrEqualTo(String value) {
            addCriterion("law_position_report >=", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportLessThan(String value) {
            addCriterion("law_position_report <", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportLessThanOrEqualTo(String value) {
            addCriterion("law_position_report <=", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportLike(String value) {
            addCriterion("law_position_report like", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportNotLike(String value) {
            addCriterion("law_position_report not like", value, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportIn(List<String> values) {
            addCriterion("law_position_report in", values, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportNotIn(List<String> values) {
            addCriterion("law_position_report not in", values, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportBetween(String value1, String value2) {
            addCriterion("law_position_report between", value1, value2, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportNotBetween(String value1, String value2) {
            addCriterion("law_position_report not between", value1, value2, "lawPositionReport");
            return (Criteria) this;
        }

        public Criteria andRankReportIsNull() {
            addCriterion("rank_report is null");
            return (Criteria) this;
        }

        public Criteria andRankReportIsNotNull() {
            addCriterion("rank_report is not null");
            return (Criteria) this;
        }

        public Criteria andRankReportEqualTo(String value) {
            addCriterion("rank_report =", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportNotEqualTo(String value) {
            addCriterion("rank_report <>", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportGreaterThan(String value) {
            addCriterion("rank_report >", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportGreaterThanOrEqualTo(String value) {
            addCriterion("rank_report >=", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportLessThan(String value) {
            addCriterion("rank_report <", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportLessThanOrEqualTo(String value) {
            addCriterion("rank_report <=", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportLike(String value) {
            addCriterion("rank_report like", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportNotLike(String value) {
            addCriterion("rank_report not like", value, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportIn(List<String> values) {
            addCriterion("rank_report in", values, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportNotIn(List<String> values) {
            addCriterion("rank_report not in", values, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportBetween(String value1, String value2) {
            addCriterion("rank_report between", value1, value2, "rankReport");
            return (Criteria) this;
        }

        public Criteria andRankReportNotBetween(String value1, String value2) {
            addCriterion("rank_report not between", value1, value2, "rankReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportIsNull() {
            addCriterion("political_report is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportIsNotNull() {
            addCriterion("political_report is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportEqualTo(String value) {
            addCriterion("political_report =", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportNotEqualTo(String value) {
            addCriterion("political_report <>", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportGreaterThan(String value) {
            addCriterion("political_report >", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportGreaterThanOrEqualTo(String value) {
            addCriterion("political_report >=", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportLessThan(String value) {
            addCriterion("political_report <", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportLessThanOrEqualTo(String value) {
            addCriterion("political_report <=", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportLike(String value) {
            addCriterion("political_report like", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportNotLike(String value) {
            addCriterion("political_report not like", value, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportIn(List<String> values) {
            addCriterion("political_report in", values, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportNotIn(List<String> values) {
            addCriterion("political_report not in", values, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportBetween(String value1, String value2) {
            addCriterion("political_report between", value1, value2, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportNotBetween(String value1, String value2) {
            addCriterion("political_report not between", value1, value2, "politicalReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportIsNull() {
            addCriterion("party_office_report is null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportIsNotNull() {
            addCriterion("party_office_report is not null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportEqualTo(String value) {
            addCriterion("party_office_report =", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportNotEqualTo(String value) {
            addCriterion("party_office_report <>", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportGreaterThan(String value) {
            addCriterion("party_office_report >", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportGreaterThanOrEqualTo(String value) {
            addCriterion("party_office_report >=", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportLessThan(String value) {
            addCriterion("party_office_report <", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportLessThanOrEqualTo(String value) {
            addCriterion("party_office_report <=", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportLike(String value) {
            addCriterion("party_office_report like", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportNotLike(String value) {
            addCriterion("party_office_report not like", value, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportIn(List<String> values) {
            addCriterion("party_office_report in", values, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportNotIn(List<String> values) {
            addCriterion("party_office_report not in", values, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportBetween(String value1, String value2) {
            addCriterion("party_office_report between", value1, value2, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportNotBetween(String value1, String value2) {
            addCriterion("party_office_report not between", value1, value2, "partyOfficeReport");
            return (Criteria) this;
        }

        public Criteria andNationReportTextIsNull() {
            addCriterion("nation_report_text is null");
            return (Criteria) this;
        }

        public Criteria andNationReportTextIsNotNull() {
            addCriterion("nation_report_text is not null");
            return (Criteria) this;
        }

        public Criteria andNationReportTextEqualTo(String value) {
            addCriterion("nation_report_text =", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextNotEqualTo(String value) {
            addCriterion("nation_report_text <>", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextGreaterThan(String value) {
            addCriterion("nation_report_text >", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextGreaterThanOrEqualTo(String value) {
            addCriterion("nation_report_text >=", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextLessThan(String value) {
            addCriterion("nation_report_text <", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextLessThanOrEqualTo(String value) {
            addCriterion("nation_report_text <=", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextLike(String value) {
            addCriterion("nation_report_text like", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextNotLike(String value) {
            addCriterion("nation_report_text not like", value, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextIn(List<String> values) {
            addCriterion("nation_report_text in", values, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextNotIn(List<String> values) {
            addCriterion("nation_report_text not in", values, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextBetween(String value1, String value2) {
            addCriterion("nation_report_text between", value1, value2, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andNationReportTextNotBetween(String value1, String value2) {
            addCriterion("nation_report_text not between", value1, value2, "nationReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextIsNull() {
            addCriterion("education_background_report_text is null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextIsNotNull() {
            addCriterion("education_background_report_text is not null");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextEqualTo(String value) {
            addCriterion("education_background_report_text =", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextNotEqualTo(String value) {
            addCriterion("education_background_report_text <>", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextGreaterThan(String value) {
            addCriterion("education_background_report_text >", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextGreaterThanOrEqualTo(String value) {
            addCriterion("education_background_report_text >=", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextLessThan(String value) {
            addCriterion("education_background_report_text <", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextLessThanOrEqualTo(String value) {
            addCriterion("education_background_report_text <=", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextLike(String value) {
            addCriterion("education_background_report_text like", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextNotLike(String value) {
            addCriterion("education_background_report_text not like", value, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextIn(List<String> values) {
            addCriterion("education_background_report_text in", values, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextNotIn(List<String> values) {
            addCriterion("education_background_report_text not in", values, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextBetween(String value1, String value2) {
            addCriterion("education_background_report_text between", value1, value2, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andEducationBackgroundReportTextNotBetween(String value1, String value2) {
            addCriterion("education_background_report_text not between", value1, value2, "educationBackgroundReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextIsNull() {
            addCriterion("administration_position_report_text is null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextIsNotNull() {
            addCriterion("administration_position_report_text is not null");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextEqualTo(String value) {
            addCriterion("administration_position_report_text =", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextNotEqualTo(String value) {
            addCriterion("administration_position_report_text <>", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextGreaterThan(String value) {
            addCriterion("administration_position_report_text >", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextGreaterThanOrEqualTo(String value) {
            addCriterion("administration_position_report_text >=", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextLessThan(String value) {
            addCriterion("administration_position_report_text <", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextLessThanOrEqualTo(String value) {
            addCriterion("administration_position_report_text <=", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextLike(String value) {
            addCriterion("administration_position_report_text like", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextNotLike(String value) {
            addCriterion("administration_position_report_text not like", value, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextIn(List<String> values) {
            addCriterion("administration_position_report_text in", values, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextNotIn(List<String> values) {
            addCriterion("administration_position_report_text not in", values, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextBetween(String value1, String value2) {
            addCriterion("administration_position_report_text between", value1, value2, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andAdministrationPositionReportTextNotBetween(String value1, String value2) {
            addCriterion("administration_position_report_text not between", value1, value2, "administrationPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextIsNull() {
            addCriterion("law_position_report_text is null");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextIsNotNull() {
            addCriterion("law_position_report_text is not null");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextEqualTo(String value) {
            addCriterion("law_position_report_text =", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextNotEqualTo(String value) {
            addCriterion("law_position_report_text <>", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextGreaterThan(String value) {
            addCriterion("law_position_report_text >", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextGreaterThanOrEqualTo(String value) {
            addCriterion("law_position_report_text >=", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextLessThan(String value) {
            addCriterion("law_position_report_text <", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextLessThanOrEqualTo(String value) {
            addCriterion("law_position_report_text <=", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextLike(String value) {
            addCriterion("law_position_report_text like", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextNotLike(String value) {
            addCriterion("law_position_report_text not like", value, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextIn(List<String> values) {
            addCriterion("law_position_report_text in", values, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextNotIn(List<String> values) {
            addCriterion("law_position_report_text not in", values, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextBetween(String value1, String value2) {
            addCriterion("law_position_report_text between", value1, value2, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andLawPositionReportTextNotBetween(String value1, String value2) {
            addCriterion("law_position_report_text not between", value1, value2, "lawPositionReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextIsNull() {
            addCriterion("rank_report_text is null");
            return (Criteria) this;
        }

        public Criteria andRankReportTextIsNotNull() {
            addCriterion("rank_report_text is not null");
            return (Criteria) this;
        }

        public Criteria andRankReportTextEqualTo(String value) {
            addCriterion("rank_report_text =", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextNotEqualTo(String value) {
            addCriterion("rank_report_text <>", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextGreaterThan(String value) {
            addCriterion("rank_report_text >", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextGreaterThanOrEqualTo(String value) {
            addCriterion("rank_report_text >=", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextLessThan(String value) {
            addCriterion("rank_report_text <", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextLessThanOrEqualTo(String value) {
            addCriterion("rank_report_text <=", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextLike(String value) {
            addCriterion("rank_report_text like", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextNotLike(String value) {
            addCriterion("rank_report_text not like", value, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextIn(List<String> values) {
            addCriterion("rank_report_text in", values, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextNotIn(List<String> values) {
            addCriterion("rank_report_text not in", values, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextBetween(String value1, String value2) {
            addCriterion("rank_report_text between", value1, value2, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andRankReportTextNotBetween(String value1, String value2) {
            addCriterion("rank_report_text not between", value1, value2, "rankReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextIsNull() {
            addCriterion("political_report_text is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextIsNotNull() {
            addCriterion("political_report_text is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextEqualTo(String value) {
            addCriterion("political_report_text =", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextNotEqualTo(String value) {
            addCriterion("political_report_text <>", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextGreaterThan(String value) {
            addCriterion("political_report_text >", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextGreaterThanOrEqualTo(String value) {
            addCriterion("political_report_text >=", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextLessThan(String value) {
            addCriterion("political_report_text <", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextLessThanOrEqualTo(String value) {
            addCriterion("political_report_text <=", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextLike(String value) {
            addCriterion("political_report_text like", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextNotLike(String value) {
            addCriterion("political_report_text not like", value, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextIn(List<String> values) {
            addCriterion("political_report_text in", values, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextNotIn(List<String> values) {
            addCriterion("political_report_text not in", values, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextBetween(String value1, String value2) {
            addCriterion("political_report_text between", value1, value2, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andPoliticalReportTextNotBetween(String value1, String value2) {
            addCriterion("political_report_text not between", value1, value2, "politicalReportText");
            return (Criteria) this;
        }

        public Criteria andExistCondition(String condition) {
            addCriterion(condition);
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextIsNull() {
            addCriterion("party_office_report_text is null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextIsNotNull() {
            addCriterion("party_office_report_text is not null");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextEqualTo(String value) {
            addCriterion("party_office_report_text =", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextNotEqualTo(String value) {
            addCriterion("party_office_report_text <>", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextGreaterThan(String value) {
            addCriterion("party_office_report_text >", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextGreaterThanOrEqualTo(String value) {
            addCriterion("party_office_report_text >=", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextLessThan(String value) {
            addCriterion("party_office_report_text <", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextLessThanOrEqualTo(String value) {
            addCriterion("party_office_report_text <=", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextLike(String value) {
            addCriterion("party_office_report_text like", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextNotLike(String value) {
            addCriterion("party_office_report_text not like", value, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextIn(List<String> values) {
            addCriterion("party_office_report_text in", values, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextNotIn(List<String> values) {
            addCriterion("party_office_report_text not in", values, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextBetween(String value1, String value2) {
            addCriterion("party_office_report_text between", value1, value2, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andPartyOfficeReportTextNotBetween(String value1, String value2) {
            addCriterion("party_office_report_text not between", value1, value2, "partyOfficeReportText");
            return (Criteria) this;
        }

        public Criteria andBzcyIsNull() {
            addCriterion("bzcy is null");
            return (Criteria) this;
        }

        public Criteria andBzcyIsNotNull() {
            addCriterion("bzcy is not null");
            return (Criteria) this;
        }

        public Criteria andBzcyEqualTo(Integer value) {
            addCriterion("bzcy =", value, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyNotEqualTo(Integer value) {
            addCriterion("bzcy <>", value, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyGreaterThan(Integer value) {
            addCriterion("bzcy >", value, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyGreaterThanOrEqualTo(Integer value) {
            addCriterion("bzcy >=", value, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyLessThan(Integer value) {
            addCriterion("bzcy <", value, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyLessThanOrEqualTo(Integer value) {
            addCriterion("bzcy <=", value, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyIn(List<Integer> values) {
            addCriterion("bzcy in", values, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyNotIn(List<Integer> values) {
            addCriterion("bzcy not in", values, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyBetween(Integer value1, Integer value2) {
            addCriterion("bzcy between", value1, value2, "bzcy");
            return (Criteria) this;
        }

        public Criteria andBzcyNotBetween(Integer value1, Integer value2) {
            addCriterion("bzcy not between", value1, value2, "bzcy");
            return (Criteria) this;
        }

        //自定义查询
        public Criteria orColumnIn(String column, List<String> values) {
            String sql = "";
            if (values != null && values.size() > 0) {
                sql = " (" + column + " in (";
                for (String value : values) {
                    sql += " '" + value + "',";
                }
                int lenghth = sql.length();
                sql = sql.substring(0, lenghth - 1);
                sql += ") or " + column + " is null or " + column + " = '') ";
            } else {
                sql = " (" + column + " is null or " + column + " = '')";
            }
            addCriterion(sql);
            return (Criteria) this;
        }

        public Criteria orBirthdayBetween(List<String> starts, List<String> ends, int s, int e) {
            String sql = "";
            if (starts != null && starts.size() > 0 && ends != null && ends.size() > 0) {
                sql = " (";
                for (int i = 0; i < starts.size(); i++) {
                    sql += "birthday between '" + starts.get(i) + "' and '" + ends.get(i) + "' or ";
                }
                int lenghth = sql.length();
                sql = sql.substring(0, lenghth - 3);
                sql += " or birthday is null or birthday = '' " +
                        "or (YEAR (now()) - YEAR (birthday) - IF( DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(birthday, '%m%d'), 1, 0 )) < " + s + " " +
                        "or (YEAR (now()) - YEAR (birthday) - IF( DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(birthday, '%m%d'), 1, 0 )) > " + e + ") ";
            } else {
                sql = " (birthday is null or birthday = '' " +
                        "or (YEAR (now()) - YEAR (birthday) - IF( DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(birthday, '%m%d'), 1, 0 )) < " + s + " " +
                        "or (YEAR (now()) - YEAR (birthday) - IF( DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(birthday, '%m%d'), 1, 0 )) > " + e + ")";
            }
            addCriterion(sql);
            return (Criteria) this;
        }

        public Criteria orBirthdayNull(int start, int end) {
            String sql = " (birthday is null " +
                        "or (YEAR (now()) - YEAR (birthday) - IF( DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(birthday, '%m%d'), 1, 0 )) < " + start + " " +
                        "or (YEAR (now()) - YEAR (birthday) - IF( DATE_FORMAT(NOW(), '%m%d') < DATE_FORMAT(birthday, '%m%d'), 1, 0 )) > " + end + ")";
            addCriterion(sql);
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

        private boolean groupValue;

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

        public boolean isGroupValue() {
            return groupValue;
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


        protected Criterion(String condition, Object value,boolean isgroup) {
            super();
            this.condition = condition;
            this.value = value;
            this.groupValue = true;
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