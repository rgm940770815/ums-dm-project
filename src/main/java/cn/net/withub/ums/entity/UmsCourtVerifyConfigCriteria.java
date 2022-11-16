package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsCourtVerifyConfigCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsCourtVerifyConfigCriteria() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andCScopeIsNull() {
            addCriterion("c_scope is null");
            return (Criteria) this;
        }

        public Criteria andCScopeIsNotNull() {
            addCriterion("c_scope is not null");
            return (Criteria) this;
        }

        public Criteria andCScopeEqualTo(String value) {
            addCriterion("c_scope =", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeNotEqualTo(String value) {
            addCriterion("c_scope <>", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeGreaterThan(String value) {
            addCriterion("c_scope >", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeGreaterThanOrEqualTo(String value) {
            addCriterion("c_scope >=", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeLessThan(String value) {
            addCriterion("c_scope <", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeLessThanOrEqualTo(String value) {
            addCriterion("c_scope <=", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeLike(String value) {
            addCriterion("c_scope like", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeNotLike(String value) {
            addCriterion("c_scope not like", value, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeIn(List<String> values) {
            addCriterion("c_scope in", values, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeNotIn(List<String> values) {
            addCriterion("c_scope not in", values, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeBetween(String value1, String value2) {
            addCriterion("c_scope between", value1, value2, "cScope");
            return (Criteria) this;
        }

        public Criteria andCScopeNotBetween(String value1, String value2) {
            addCriterion("c_scope not between", value1, value2, "cScope");
            return (Criteria) this;
        }

        public Criteria andCKeyIsNull() {
            addCriterion("c_key is null");
            return (Criteria) this;
        }

        public Criteria andCKeyIsNotNull() {
            addCriterion("c_key is not null");
            return (Criteria) this;
        }

        public Criteria andCKeyEqualTo(String value) {
            addCriterion("c_key =", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyNotEqualTo(String value) {
            addCriterion("c_key <>", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyGreaterThan(String value) {
            addCriterion("c_key >", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyGreaterThanOrEqualTo(String value) {
            addCriterion("c_key >=", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyLessThan(String value) {
            addCriterion("c_key <", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyLessThanOrEqualTo(String value) {
            addCriterion("c_key <=", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyLike(String value) {
            addCriterion("c_key like", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyNotLike(String value) {
            addCriterion("c_key not like", value, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyIn(List<String> values) {
            addCriterion("c_key in", values, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyNotIn(List<String> values) {
            addCriterion("c_key not in", values, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyBetween(String value1, String value2) {
            addCriterion("c_key between", value1, value2, "cKey");
            return (Criteria) this;
        }

        public Criteria andCKeyNotBetween(String value1, String value2) {
            addCriterion("c_key not between", value1, value2, "cKey");
            return (Criteria) this;
        }

        public Criteria andCValueIsNull() {
            addCriterion("c_value is null");
            return (Criteria) this;
        }

        public Criteria andCValueIsNotNull() {
            addCriterion("c_value is not null");
            return (Criteria) this;
        }

        public Criteria andCValueEqualTo(String value) {
            addCriterion("c_value =", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueNotEqualTo(String value) {
            addCriterion("c_value <>", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueGreaterThan(String value) {
            addCriterion("c_value >", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueGreaterThanOrEqualTo(String value) {
            addCriterion("c_value >=", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueLessThan(String value) {
            addCriterion("c_value <", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueLessThanOrEqualTo(String value) {
            addCriterion("c_value <=", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueLike(String value) {
            addCriterion("c_value like", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueNotLike(String value) {
            addCriterion("c_value not like", value, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueIn(List<String> values) {
            addCriterion("c_value in", values, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueNotIn(List<String> values) {
            addCriterion("c_value not in", values, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueBetween(String value1, String value2) {
            addCriterion("c_value between", value1, value2, "cValue");
            return (Criteria) this;
        }

        public Criteria andCValueNotBetween(String value1, String value2) {
            addCriterion("c_value not between", value1, value2, "cValue");
            return (Criteria) this;
        }

        public Criteria andCNameIsNull() {
            addCriterion("c_name is null");
            return (Criteria) this;
        }

        public Criteria andCNameIsNotNull() {
            addCriterion("c_name is not null");
            return (Criteria) this;
        }

        public Criteria andCNameEqualTo(String value) {
            addCriterion("c_name =", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotEqualTo(String value) {
            addCriterion("c_name <>", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameGreaterThan(String value) {
            addCriterion("c_name >", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameGreaterThanOrEqualTo(String value) {
            addCriterion("c_name >=", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLessThan(String value) {
            addCriterion("c_name <", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLessThanOrEqualTo(String value) {
            addCriterion("c_name <=", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameLike(String value) {
            addCriterion("c_name like", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotLike(String value) {
            addCriterion("c_name not like", value, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameIn(List<String> values) {
            addCriterion("c_name in", values, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotIn(List<String> values) {
            addCriterion("c_name not in", values, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameBetween(String value1, String value2) {
            addCriterion("c_name between", value1, value2, "cName");
            return (Criteria) this;
        }

        public Criteria andCNameNotBetween(String value1, String value2) {
            addCriterion("c_name not between", value1, value2, "cName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameIsNull() {
            addCriterion("update_user_name is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameIsNotNull() {
            addCriterion("update_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameEqualTo(String value) {
            addCriterion("update_user_name =", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameNotEqualTo(String value) {
            addCriterion("update_user_name <>", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameGreaterThan(String value) {
            addCriterion("update_user_name >", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("update_user_name >=", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameLessThan(String value) {
            addCriterion("update_user_name <", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameLessThanOrEqualTo(String value) {
            addCriterion("update_user_name <=", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameLike(String value) {
            addCriterion("update_user_name like", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameNotLike(String value) {
            addCriterion("update_user_name not like", value, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameIn(List<String> values) {
            addCriterion("update_user_name in", values, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameNotIn(List<String> values) {
            addCriterion("update_user_name not in", values, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameBetween(String value1, String value2) {
            addCriterion("update_user_name between", value1, value2, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNameNotBetween(String value1, String value2) {
            addCriterion("update_user_name not between", value1, value2, "updateUserName");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoIsNull() {
            addCriterion("update_user_no is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoIsNotNull() {
            addCriterion("update_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoEqualTo(String value) {
            addCriterion("update_user_no =", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoNotEqualTo(String value) {
            addCriterion("update_user_no <>", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoGreaterThan(String value) {
            addCriterion("update_user_no >", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("update_user_no >=", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoLessThan(String value) {
            addCriterion("update_user_no <", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoLessThanOrEqualTo(String value) {
            addCriterion("update_user_no <=", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoLike(String value) {
            addCriterion("update_user_no like", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoNotLike(String value) {
            addCriterion("update_user_no not like", value, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoIn(List<String> values) {
            addCriterion("update_user_no in", values, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoNotIn(List<String> values) {
            addCriterion("update_user_no not in", values, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoBetween(String value1, String value2) {
            addCriterion("update_user_no between", value1, value2, "updateUserNo");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNoNotBetween(String value1, String value2) {
            addCriterion("update_user_no not between", value1, value2, "updateUserNo");
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