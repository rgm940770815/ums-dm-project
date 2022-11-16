package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsUserInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsUserInfoCriteria() {
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

        public Criteria andMachineNumberIsNull() {
            addCriterion("machine_number is null");
            return (Criteria) this;
        }

        public Criteria andMachineNumberIsNotNull() {
            addCriterion("machine_number is not null");
            return (Criteria) this;
        }

        public Criteria andMachineNumberEqualTo(String value) {
            addCriterion("machine_number =", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberNotEqualTo(String value) {
            addCriterion("machine_number <>", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberGreaterThan(String value) {
            addCriterion("machine_number >", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberGreaterThanOrEqualTo(String value) {
            addCriterion("machine_number >=", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberLessThan(String value) {
            addCriterion("machine_number <", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberLessThanOrEqualTo(String value) {
            addCriterion("machine_number <=", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberLike(String value) {
            addCriterion("machine_number like", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberNotLike(String value) {
            addCriterion("machine_number not like", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberIn(List<String> values) {
            addCriterion("machine_number in", values, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberNotIn(List<String> values) {
            addCriterion("machine_number not in", values, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberBetween(String value1, String value2) {
            addCriterion("machine_number between", value1, value2, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberNotBetween(String value1, String value2) {
            addCriterion("machine_number not between", value1, value2, "machineNumber");
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(Integer value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(Integer value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(Integer value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(Integer value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(Integer value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(Integer value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<Integer> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<Integer> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(Integer value1, Integer value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(Integer value1, Integer value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(Integer value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(Integer value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(Integer value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(Integer value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(Integer value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(Integer value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<Integer> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<Integer> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(Integer value1, Integer value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(Integer value1, Integer value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(Integer value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(Integer value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(Integer value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(Integer value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(Integer value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<Integer> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<Integer> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(Integer value1, Integer value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("area not between", value1, value2, "area");
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

        public Criteria andSPsByyxjzyIsNull() {
            addCriterion("s_ps_byyxjzy is null");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyIsNotNull() {
            addCriterion("s_ps_byyxjzy is not null");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyEqualTo(String value) {
            addCriterion("s_ps_byyxjzy =", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyNotEqualTo(String value) {
            addCriterion("s_ps_byyxjzy <>", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyGreaterThan(String value) {
            addCriterion("s_ps_byyxjzy >", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_byyxjzy >=", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyLessThan(String value) {
            addCriterion("s_ps_byyxjzy <", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyLessThanOrEqualTo(String value) {
            addCriterion("s_ps_byyxjzy <=", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyLike(String value) {
            addCriterion("s_ps_byyxjzy like", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyNotLike(String value) {
            addCriterion("s_ps_byyxjzy not like", value, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyIn(List<String> values) {
            addCriterion("s_ps_byyxjzy in", values, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyNotIn(List<String> values) {
            addCriterion("s_ps_byyxjzy not in", values, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyBetween(String value1, String value2) {
            addCriterion("s_ps_byyxjzy between", value1, value2, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsByyxjzyNotBetween(String value1, String value2) {
            addCriterion("s_ps_byyxjzy not between", value1, value2, "sPsByyxjzy");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzIsNull() {
            addCriterion("s_ps_dwdz is null");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzIsNotNull() {
            addCriterion("s_ps_dwdz is not null");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzEqualTo(String value) {
            addCriterion("s_ps_dwdz =", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzNotEqualTo(String value) {
            addCriterion("s_ps_dwdz <>", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzGreaterThan(String value) {
            addCriterion("s_ps_dwdz >", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_dwdz >=", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzLessThan(String value) {
            addCriterion("s_ps_dwdz <", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzLessThanOrEqualTo(String value) {
            addCriterion("s_ps_dwdz <=", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzLike(String value) {
            addCriterion("s_ps_dwdz like", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzNotLike(String value) {
            addCriterion("s_ps_dwdz not like", value, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzIn(List<String> values) {
            addCriterion("s_ps_dwdz in", values, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzNotIn(List<String> values) {
            addCriterion("s_ps_dwdz not in", values, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzBetween(String value1, String value2) {
            addCriterion("s_ps_dwdz between", value1, value2, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsDwdzNotBetween(String value1, String value2) {
            addCriterion("s_ps_dwdz not between", value1, value2, "sPsDwdz");
            return (Criteria) this;
        }

        public Criteria andSPsZwIsNull() {
            addCriterion("s_ps_zw is null");
            return (Criteria) this;
        }

        public Criteria andSPsZwIsNotNull() {
            addCriterion("s_ps_zw is not null");
            return (Criteria) this;
        }

        public Criteria andSPsZwEqualTo(String value) {
            addCriterion("s_ps_zw =", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwNotEqualTo(String value) {
            addCriterion("s_ps_zw <>", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwGreaterThan(String value) {
            addCriterion("s_ps_zw >", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_zw >=", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwLessThan(String value) {
            addCriterion("s_ps_zw <", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwLessThanOrEqualTo(String value) {
            addCriterion("s_ps_zw <=", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwLike(String value) {
            addCriterion("s_ps_zw like", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwNotLike(String value) {
            addCriterion("s_ps_zw not like", value, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwIn(List<String> values) {
            addCriterion("s_ps_zw in", values, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwNotIn(List<String> values) {
            addCriterion("s_ps_zw not in", values, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwBetween(String value1, String value2) {
            addCriterion("s_ps_zw between", value1, value2, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZwNotBetween(String value1, String value2) {
            addCriterion("s_ps_zw not between", value1, value2, "sPsZw");
            return (Criteria) this;
        }

        public Criteria andSPsZjIsNull() {
            addCriterion("s_ps_zj is null");
            return (Criteria) this;
        }

        public Criteria andSPsZjIsNotNull() {
            addCriterion("s_ps_zj is not null");
            return (Criteria) this;
        }

        public Criteria andSPsZjEqualTo(String value) {
            addCriterion("s_ps_zj =", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjNotEqualTo(String value) {
            addCriterion("s_ps_zj <>", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjGreaterThan(String value) {
            addCriterion("s_ps_zj >", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_zj >=", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjLessThan(String value) {
            addCriterion("s_ps_zj <", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjLessThanOrEqualTo(String value) {
            addCriterion("s_ps_zj <=", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjLike(String value) {
            addCriterion("s_ps_zj like", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjNotLike(String value) {
            addCriterion("s_ps_zj not like", value, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjIn(List<String> values) {
            addCriterion("s_ps_zj in", values, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjNotIn(List<String> values) {
            addCriterion("s_ps_zj not in", values, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjBetween(String value1, String value2) {
            addCriterion("s_ps_zj between", value1, value2, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZjNotBetween(String value1, String value2) {
            addCriterion("s_ps_zj not between", value1, value2, "sPsZj");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwIsNull() {
            addCriterion("s_ps_zyjszw is null");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwIsNotNull() {
            addCriterion("s_ps_zyjszw is not null");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwEqualTo(String value) {
            addCriterion("s_ps_zyjszw =", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwNotEqualTo(String value) {
            addCriterion("s_ps_zyjszw <>", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwGreaterThan(String value) {
            addCriterion("s_ps_zyjszw >", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_zyjszw >=", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwLessThan(String value) {
            addCriterion("s_ps_zyjszw <", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwLessThanOrEqualTo(String value) {
            addCriterion("s_ps_zyjszw <=", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwLike(String value) {
            addCriterion("s_ps_zyjszw like", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwNotLike(String value) {
            addCriterion("s_ps_zyjszw not like", value, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwIn(List<String> values) {
            addCriterion("s_ps_zyjszw in", values, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwNotIn(List<String> values) {
            addCriterion("s_ps_zyjszw not in", values, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwBetween(String value1, String value2) {
            addCriterion("s_ps_zyjszw between", value1, value2, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andSPsZyjszwNotBetween(String value1, String value2) {
            addCriterion("s_ps_zyjszw not between", value1, value2, "sPsZyjszw");
            return (Criteria) this;
        }

        public Criteria andCPsLxIsNull() {
            addCriterion("c_ps_lx is null");
            return (Criteria) this;
        }

        public Criteria andCPsLxIsNotNull() {
            addCriterion("c_ps_lx is not null");
            return (Criteria) this;
        }

        public Criteria andCPsLxEqualTo(Integer value) {
            addCriterion("c_ps_lx =", value, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxNotEqualTo(Integer value) {
            addCriterion("c_ps_lx <>", value, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxGreaterThan(Integer value) {
            addCriterion("c_ps_lx >", value, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_ps_lx >=", value, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxLessThan(Integer value) {
            addCriterion("c_ps_lx <", value, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxLessThanOrEqualTo(Integer value) {
            addCriterion("c_ps_lx <=", value, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxIn(List<Integer> values) {
            addCriterion("c_ps_lx in", values, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxNotIn(List<Integer> values) {
            addCriterion("c_ps_lx not in", values, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_lx between", value1, value2, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsLxNotBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_lx not between", value1, value2, "cPsLx");
            return (Criteria) this;
        }

        public Criteria andCPsZylbIsNull() {
            addCriterion("c_ps_zylb is null");
            return (Criteria) this;
        }

        public Criteria andCPsZylbIsNotNull() {
            addCriterion("c_ps_zylb is not null");
            return (Criteria) this;
        }

        public Criteria andCPsZylbEqualTo(Integer value) {
            addCriterion("c_ps_zylb =", value, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbNotEqualTo(Integer value) {
            addCriterion("c_ps_zylb <>", value, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbGreaterThan(Integer value) {
            addCriterion("c_ps_zylb >", value, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_ps_zylb >=", value, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbLessThan(Integer value) {
            addCriterion("c_ps_zylb <", value, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbLessThanOrEqualTo(Integer value) {
            addCriterion("c_ps_zylb <=", value, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbIn(List<Integer> values) {
            addCriterion("c_ps_zylb in", values, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbNotIn(List<Integer> values) {
            addCriterion("c_ps_zylb not in", values, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_zylb between", value1, value2, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andCPsZylbNotBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_zylb not between", value1, value2, "cPsZylb");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwIsNull() {
            addCriterion("s_ps_rmdw is null");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwIsNotNull() {
            addCriterion("s_ps_rmdw is not null");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwEqualTo(String value) {
            addCriterion("s_ps_rmdw =", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwNotEqualTo(String value) {
            addCriterion("s_ps_rmdw <>", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwGreaterThan(String value) {
            addCriterion("s_ps_rmdw >", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_rmdw >=", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwLessThan(String value) {
            addCriterion("s_ps_rmdw <", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwLessThanOrEqualTo(String value) {
            addCriterion("s_ps_rmdw <=", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwLike(String value) {
            addCriterion("s_ps_rmdw like", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwNotLike(String value) {
            addCriterion("s_ps_rmdw not like", value, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwIn(List<String> values) {
            addCriterion("s_ps_rmdw in", values, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwNotIn(List<String> values) {
            addCriterion("s_ps_rmdw not in", values, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwBetween(String value1, String value2) {
            addCriterion("s_ps_rmdw between", value1, value2, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmdwNotBetween(String value1, String value2) {
            addCriterion("s_ps_rmdw not between", value1, value2, "sPsRmdw");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqIsNull() {
            addCriterion("s_ps_rmrq is null");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqIsNotNull() {
            addCriterion("s_ps_rmrq is not null");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_rmrq =", value, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_rmrq <>", value, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqGreaterThan(Date value) {
            addCriterionForJDBCDate("s_ps_rmrq >", value, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_rmrq >=", value, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqLessThan(Date value) {
            addCriterionForJDBCDate("s_ps_rmrq <", value, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_rmrq <=", value, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqIn(List<Date> values) {
            addCriterionForJDBCDate("s_ps_rmrq in", values, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("s_ps_rmrq not in", values, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("s_ps_rmrq between", value1, value2, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsRmrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("s_ps_rmrq not between", value1, value2, "sPsRmrq");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhIsNull() {
            addCriterion("s_ps_psybh is null");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhIsNotNull() {
            addCriterion("s_ps_psybh is not null");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhEqualTo(String value) {
            addCriterion("s_ps_psybh =", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhNotEqualTo(String value) {
            addCriterion("s_ps_psybh <>", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhGreaterThan(String value) {
            addCriterion("s_ps_psybh >", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_psybh >=", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhLessThan(String value) {
            addCriterion("s_ps_psybh <", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhLessThanOrEqualTo(String value) {
            addCriterion("s_ps_psybh <=", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhLike(String value) {
            addCriterion("s_ps_psybh like", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhNotLike(String value) {
            addCriterion("s_ps_psybh not like", value, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhIn(List<String> values) {
            addCriterion("s_ps_psybh in", values, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhNotIn(List<String> values) {
            addCriterion("s_ps_psybh not in", values, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhBetween(String value1, String value2) {
            addCriterion("s_ps_psybh between", value1, value2, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsPsybhNotBetween(String value1, String value2) {
            addCriterion("s_ps_psybh not between", value1, value2, "sPsPsybh");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqIsNull() {
            addCriterion("s_ps_mzrq is null");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqIsNotNull() {
            addCriterion("s_ps_mzrq is not null");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_mzrq =", value, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_mzrq <>", value, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqGreaterThan(Date value) {
            addCriterionForJDBCDate("s_ps_mzrq >", value, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_mzrq >=", value, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqLessThan(Date value) {
            addCriterionForJDBCDate("s_ps_mzrq <", value, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("s_ps_mzrq <=", value, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqIn(List<Date> values) {
            addCriterionForJDBCDate("s_ps_mzrq in", values, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("s_ps_mzrq not in", values, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("s_ps_mzrq between", value1, value2, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andSPsMzrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("s_ps_mzrq not between", value1, value2, "sPsMzrq");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyIsNull() {
            addCriterion("c_ps_mzyy is null");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyIsNotNull() {
            addCriterion("c_ps_mzyy is not null");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyEqualTo(Integer value) {
            addCriterion("c_ps_mzyy =", value, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyNotEqualTo(Integer value) {
            addCriterion("c_ps_mzyy <>", value, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyGreaterThan(Integer value) {
            addCriterion("c_ps_mzyy >", value, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_ps_mzyy >=", value, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyLessThan(Integer value) {
            addCriterion("c_ps_mzyy <", value, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyLessThanOrEqualTo(Integer value) {
            addCriterion("c_ps_mzyy <=", value, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyIn(List<Integer> values) {
            addCriterion("c_ps_mzyy in", values, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyNotIn(List<Integer> values) {
            addCriterion("c_ps_mzyy not in", values, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_mzyy between", value1, value2, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andCPsMzyyNotBetween(Integer value1, Integer value2) {
            addCriterion("c_ps_mzyy not between", value1, value2, "cPsMzyy");
            return (Criteria) this;
        }

        public Criteria andUkbmIsNull() {
            addCriterion("ukbm is null");
            return (Criteria) this;
        }

        public Criteria andUkbmIsNotNull() {
            addCriterion("ukbm is not null");
            return (Criteria) this;
        }

        public Criteria andUkbmEqualTo(String value) {
            addCriterion("ukbm =", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmNotEqualTo(String value) {
            addCriterion("ukbm <>", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmGreaterThan(String value) {
            addCriterion("ukbm >", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmGreaterThanOrEqualTo(String value) {
            addCriterion("ukbm >=", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmLessThan(String value) {
            addCriterion("ukbm <", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmLessThanOrEqualTo(String value) {
            addCriterion("ukbm <=", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmLike(String value) {
            addCriterion("ukbm like", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmNotLike(String value) {
            addCriterion("ukbm not like", value, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmIn(List<String> values) {
            addCriterion("ukbm in", values, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmNotIn(List<String> values) {
            addCriterion("ukbm not in", values, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmBetween(String value1, String value2) {
            addCriterion("ukbm between", value1, value2, "ukbm");
            return (Criteria) this;
        }

        public Criteria andUkbmNotBetween(String value1, String value2) {
            addCriterion("ukbm not between", value1, value2, "ukbm");
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

        public Criteria andSPsZyIsNull() {
            addCriterion("s_ps_zy is null");
            return (Criteria) this;
        }

        public Criteria andSPsZyIsNotNull() {
            addCriterion("s_ps_zy is not null");
            return (Criteria) this;
        }

        public Criteria andSPsZyEqualTo(String value) {
            addCriterion("s_ps_zy =", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyNotEqualTo(String value) {
            addCriterion("s_ps_zy <>", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyGreaterThan(String value) {
            addCriterion("s_ps_zy >", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyGreaterThanOrEqualTo(String value) {
            addCriterion("s_ps_zy >=", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyLessThan(String value) {
            addCriterion("s_ps_zy <", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyLessThanOrEqualTo(String value) {
            addCriterion("s_ps_zy <=", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyLike(String value) {
            addCriterion("s_ps_zy like", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyNotLike(String value) {
            addCriterion("s_ps_zy not like", value, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyIn(List<String> values) {
            addCriterion("s_ps_zy in", values, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyNotIn(List<String> values) {
            addCriterion("s_ps_zy not in", values, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyBetween(String value1, String value2) {
            addCriterion("s_ps_zy between", value1, value2, "sPsZy");
            return (Criteria) this;
        }

        public Criteria andSPsZyNotBetween(String value1, String value2) {
            addCriterion("s_ps_zy not between", value1, value2, "sPsZy");
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

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time not between", value1, value2, "endTime");
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
            addCriterion("is_info_complete <>", value, "isInfoComplete");
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

        public Criteria andOfficePhoneIsNull() {
            addCriterion("office_phone is null");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneIsNotNull() {
            addCriterion("office_phone is not null");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneEqualTo(String value) {
            addCriterion("office_phone =", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNotEqualTo(String value) {
            addCriterion("office_phone <>", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneGreaterThan(String value) {
            addCriterion("office_phone >", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("office_phone >=", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneLessThan(String value) {
            addCriterion("office_phone <", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneLessThanOrEqualTo(String value) {
            addCriterion("office_phone <=", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneLike(String value) {
            addCriterion("office_phone like", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNotLike(String value) {
            addCriterion("office_phone not like", value, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneIn(List<String> values) {
            addCriterion("office_phone in", values, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNotIn(List<String> values) {
            addCriterion("office_phone not in", values, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneBetween(String value1, String value2) {
            addCriterion("office_phone between", value1, value2, "officePhone");
            return (Criteria) this;
        }

        public Criteria andOfficePhoneNotBetween(String value1, String value2) {
            addCriterion("office_phone not between", value1, value2, "officePhone");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
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

        public Criteria andExtOfficePhoneIsNull() {
            addCriterion("ext_office_phone is null");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneIsNotNull() {
            addCriterion("ext_office_phone is not null");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneEqualTo(String value) {
            addCriterion("ext_office_phone =", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneNotEqualTo(String value) {
            addCriterion("ext_office_phone <>", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneGreaterThan(String value) {
            addCriterion("ext_office_phone >", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("ext_office_phone >=", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneLessThan(String value) {
            addCriterion("ext_office_phone <", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneLessThanOrEqualTo(String value) {
            addCriterion("ext_office_phone <=", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneLike(String value) {
            addCriterion("ext_office_phone like", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneNotLike(String value) {
            addCriterion("ext_office_phone not like", value, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneIn(List<String> values) {
            addCriterion("ext_office_phone in", values, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneNotIn(List<String> values) {
            addCriterion("ext_office_phone not in", values, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneBetween(String value1, String value2) {
            addCriterion("ext_office_phone between", value1, value2, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtOfficePhoneNotBetween(String value1, String value2) {
            addCriterion("ext_office_phone not between", value1, value2, "extOfficePhone");
            return (Criteria) this;
        }

        public Criteria andExtTaxIsNull() {
            addCriterion("ext_tax is null");
            return (Criteria) this;
        }

        public Criteria andExtTaxIsNotNull() {
            addCriterion("ext_tax is not null");
            return (Criteria) this;
        }

        public Criteria andExtTaxEqualTo(String value) {
            addCriterion("ext_tax =", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxNotEqualTo(String value) {
            addCriterion("ext_tax <>", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxGreaterThan(String value) {
            addCriterion("ext_tax >", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxGreaterThanOrEqualTo(String value) {
            addCriterion("ext_tax >=", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxLessThan(String value) {
            addCriterion("ext_tax <", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxLessThanOrEqualTo(String value) {
            addCriterion("ext_tax <=", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxLike(String value) {
            addCriterion("ext_tax like", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxNotLike(String value) {
            addCriterion("ext_tax not like", value, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxIn(List<String> values) {
            addCriterion("ext_tax in", values, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxNotIn(List<String> values) {
            addCriterion("ext_tax not in", values, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxBetween(String value1, String value2) {
            addCriterion("ext_tax between", value1, value2, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtTaxNotBetween(String value1, String value2) {
            addCriterion("ext_tax not between", value1, value2, "extTax");
            return (Criteria) this;
        }

        public Criteria andExtAddressIsNull() {
            addCriterion("ext_address is null");
            return (Criteria) this;
        }

        public Criteria andExtAddressIsNotNull() {
            addCriterion("ext_address is not null");
            return (Criteria) this;
        }

        public Criteria andExtAddressEqualTo(String value) {
            addCriterion("ext_address =", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressNotEqualTo(String value) {
            addCriterion("ext_address <>", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressGreaterThan(String value) {
            addCriterion("ext_address >", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ext_address >=", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressLessThan(String value) {
            addCriterion("ext_address <", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressLessThanOrEqualTo(String value) {
            addCriterion("ext_address <=", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressLike(String value) {
            addCriterion("ext_address like", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressNotLike(String value) {
            addCriterion("ext_address not like", value, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressIn(List<String> values) {
            addCriterion("ext_address in", values, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressNotIn(List<String> values) {
            addCriterion("ext_address not in", values, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressBetween(String value1, String value2) {
            addCriterion("ext_address between", value1, value2, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtAddressNotBetween(String value1, String value2) {
            addCriterion("ext_address not between", value1, value2, "extAddress");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeIsNull() {
            addCriterion("ext_zip_code is null");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeIsNotNull() {
            addCriterion("ext_zip_code is not null");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeEqualTo(String value) {
            addCriterion("ext_zip_code =", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeNotEqualTo(String value) {
            addCriterion("ext_zip_code <>", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeGreaterThan(String value) {
            addCriterion("ext_zip_code >", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ext_zip_code >=", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeLessThan(String value) {
            addCriterion("ext_zip_code <", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeLessThanOrEqualTo(String value) {
            addCriterion("ext_zip_code <=", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeLike(String value) {
            addCriterion("ext_zip_code like", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeNotLike(String value) {
            addCriterion("ext_zip_code not like", value, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeIn(List<String> values) {
            addCriterion("ext_zip_code in", values, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeNotIn(List<String> values) {
            addCriterion("ext_zip_code not in", values, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeBetween(String value1, String value2) {
            addCriterion("ext_zip_code between", value1, value2, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andExtZipCodeNotBetween(String value1, String value2) {
            addCriterion("ext_zip_code not between", value1, value2, "extZipCode");
            return (Criteria) this;
        }

        public Criteria andBzxxIsNull() {
            addCriterion("bzxx is null");
            return (Criteria) this;
        }

        public Criteria andBzxxIsNotNull() {
            addCriterion("bzxx is not null");
            return (Criteria) this;
        }

        public Criteria andBzxxEqualTo(String value) {
            addCriterion("bzxx =", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxNotEqualTo(String value) {
            addCriterion("bzxx <>", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxGreaterThan(String value) {
            addCriterion("bzxx >", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxGreaterThanOrEqualTo(String value) {
            addCriterion("bzxx >=", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxLessThan(String value) {
            addCriterion("bzxx <", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxLessThanOrEqualTo(String value) {
            addCriterion("bzxx <=", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxLike(String value) {
            addCriterion("bzxx like", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxNotLike(String value) {
            addCriterion("bzxx not like", value, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxIn(List<String> values) {
            addCriterion("bzxx in", values, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxNotIn(List<String> values) {
            addCriterion("bzxx not in", values, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxBetween(String value1, String value2) {
            addCriterion("bzxx between", value1, value2, "bzxx");
            return (Criteria) this;
        }

        public Criteria andBzxxNotBetween(String value1, String value2) {
            addCriterion("bzxx not between", value1, value2, "bzxx");
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