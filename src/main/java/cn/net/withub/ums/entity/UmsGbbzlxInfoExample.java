package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

public class UmsGbbzlxInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsGbbzlxInfoExample() {
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

        public Criteria andChangeuuidIsNull() {
            addCriterion("changeUUID is null");
            return (Criteria) this;
        }

        public Criteria andChangeuuidIsNotNull() {
            addCriterion("changeUUID is not null");
            return (Criteria) this;
        }

        public Criteria andChangeuuidEqualTo(String value) {
            addCriterion("changeUUID =", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidNotEqualTo(String value) {
            addCriterion("changeUUID <>", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidGreaterThan(String value) {
            addCriterion("changeUUID >", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidGreaterThanOrEqualTo(String value) {
            addCriterion("changeUUID >=", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidLessThan(String value) {
            addCriterion("changeUUID <", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidLessThanOrEqualTo(String value) {
            addCriterion("changeUUID <=", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidLike(String value) {
            addCriterion("changeUUID like", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidNotLike(String value) {
            addCriterion("changeUUID not like", value, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidIn(List<String> values) {
            addCriterion("changeUUID in", values, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidNotIn(List<String> values) {
            addCriterion("changeUUID not in", values, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidBetween(String value1, String value2) {
            addCriterion("changeUUID between", value1, value2, "changeuuid");
            return (Criteria) this;
        }

        public Criteria andChangeuuidNotBetween(String value1, String value2) {
            addCriterion("changeUUID not between", value1, value2, "changeuuid");
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andCourtTextIsNull() {
            addCriterion("court_text is null");
            return (Criteria) this;
        }

        public Criteria andCourtTextIsNotNull() {
            addCriterion("court_text is not null");
            return (Criteria) this;
        }

        public Criteria andCourtTextEqualTo(String value) {
            addCriterion("court_text =", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextNotEqualTo(String value) {
            addCriterion("court_text <>", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextGreaterThan(String value) {
            addCriterion("court_text >", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextGreaterThanOrEqualTo(String value) {
            addCriterion("court_text >=", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextLessThan(String value) {
            addCriterion("court_text <", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextLessThanOrEqualTo(String value) {
            addCriterion("court_text <=", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextLike(String value) {
            addCriterion("court_text like", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextNotLike(String value) {
            addCriterion("court_text not like", value, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextIn(List<String> values) {
            addCriterion("court_text in", values, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextNotIn(List<String> values) {
            addCriterion("court_text not in", values, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextBetween(String value1, String value2) {
            addCriterion("court_text between", value1, value2, "courtText");
            return (Criteria) this;
        }

        public Criteria andCourtTextNotBetween(String value1, String value2) {
            addCriterion("court_text not between", value1, value2, "courtText");
            return (Criteria) this;
        }

        public Criteria andDepNoIsNull() {
            addCriterion("dep_no is null");
            return (Criteria) this;
        }

        public Criteria andDepNoIsNotNull() {
            addCriterion("dep_no is not null");
            return (Criteria) this;
        }

        public Criteria andDepNoEqualTo(Integer value) {
            addCriterion("dep_no =", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoNotEqualTo(Integer value) {
            addCriterion("dep_no <>", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoGreaterThan(Integer value) {
            addCriterion("dep_no >", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("dep_no >=", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoLessThan(Integer value) {
            addCriterion("dep_no <", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoLessThanOrEqualTo(Integer value) {
            addCriterion("dep_no <=", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoIn(List<Integer> values) {
            addCriterion("dep_no in", values, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoNotIn(List<Integer> values) {
            addCriterion("dep_no not in", values, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoBetween(Integer value1, Integer value2) {
            addCriterion("dep_no between", value1, value2, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoNotBetween(Integer value1, Integer value2) {
            addCriterion("dep_no not between", value1, value2, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepTextIsNull() {
            addCriterion("dep_text is null");
            return (Criteria) this;
        }

        public Criteria andDepTextIsNotNull() {
            addCriterion("dep_text is not null");
            return (Criteria) this;
        }

        public Criteria andDepTextEqualTo(String value) {
            addCriterion("dep_text =", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextNotEqualTo(String value) {
            addCriterion("dep_text <>", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextGreaterThan(String value) {
            addCriterion("dep_text >", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextGreaterThanOrEqualTo(String value) {
            addCriterion("dep_text >=", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextLessThan(String value) {
            addCriterion("dep_text <", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextLessThanOrEqualTo(String value) {
            addCriterion("dep_text <=", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextLike(String value) {
            addCriterion("dep_text like", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextNotLike(String value) {
            addCriterion("dep_text not like", value, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextIn(List<String> values) {
            addCriterion("dep_text in", values, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextNotIn(List<String> values) {
            addCriterion("dep_text not in", values, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextBetween(String value1, String value2) {
            addCriterion("dep_text between", value1, value2, "depText");
            return (Criteria) this;
        }

        public Criteria andDepTextNotBetween(String value1, String value2) {
            addCriterion("dep_text not between", value1, value2, "depText");
            return (Criteria) this;
        }

        public Criteria andSqContentIsNull() {
            addCriterion("sq_content is null");
            return (Criteria) this;
        }

        public Criteria andSqContentIsNotNull() {
            addCriterion("sq_content is not null");
            return (Criteria) this;
        }

        public Criteria andSqContentEqualTo(String value) {
            addCriterion("sq_content =", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentNotEqualTo(String value) {
            addCriterion("sq_content <>", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentGreaterThan(String value) {
            addCriterion("sq_content >", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentGreaterThanOrEqualTo(String value) {
            addCriterion("sq_content >=", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentLessThan(String value) {
            addCriterion("sq_content <", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentLessThanOrEqualTo(String value) {
            addCriterion("sq_content <=", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentLike(String value) {
            addCriterion("sq_content like", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentNotLike(String value) {
            addCriterion("sq_content not like", value, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentIn(List<String> values) {
            addCriterion("sq_content in", values, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentNotIn(List<String> values) {
            addCriterion("sq_content not in", values, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentBetween(String value1, String value2) {
            addCriterion("sq_content between", value1, value2, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqContentNotBetween(String value1, String value2) {
            addCriterion("sq_content not between", value1, value2, "sqContent");
            return (Criteria) this;
        }

        public Criteria andSqTimeIsNull() {
            addCriterion("sq_time is null");
            return (Criteria) this;
        }

        public Criteria andSqTimeIsNotNull() {
            addCriterion("sq_time is not null");
            return (Criteria) this;
        }

        public Criteria andSqTimeEqualTo(String value) {
            addCriterion("sq_time =", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeNotEqualTo(String value) {
            addCriterion("sq_time <>", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeGreaterThan(String value) {
            addCriterion("sq_time >", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeGreaterThanOrEqualTo(String value) {
            addCriterion("sq_time >=", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeLessThan(String value) {
            addCriterion("sq_time <", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeLessThanOrEqualTo(String value) {
            addCriterion("sq_time <=", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeLike(String value) {
            addCriterion("sq_time like", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeNotLike(String value) {
            addCriterion("sq_time not like", value, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeIn(List<String> values) {
            addCriterion("sq_time in", values, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeNotIn(List<String> values) {
            addCriterion("sq_time not in", values, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeBetween(String value1, String value2) {
            addCriterion("sq_time between", value1, value2, "sqTime");
            return (Criteria) this;
        }

        public Criteria andSqTimeNotBetween(String value1, String value2) {
            addCriterion("sq_time not between", value1, value2, "sqTime");
            return (Criteria) this;
        }

        public Criteria andClrUserIdIsNull() {
            addCriterion("clr_user_id is null");
            return (Criteria) this;
        }

        public Criteria andClrUserIdIsNotNull() {
            addCriterion("clr_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andClrUserIdEqualTo(String value) {
            addCriterion("clr_user_id =", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdNotEqualTo(String value) {
            addCriterion("clr_user_id <>", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdGreaterThan(String value) {
            addCriterion("clr_user_id >", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("clr_user_id >=", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdLessThan(String value) {
            addCriterion("clr_user_id <", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdLessThanOrEqualTo(String value) {
            addCriterion("clr_user_id <=", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdLike(String value) {
            addCriterion("clr_user_id like", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdNotLike(String value) {
            addCriterion("clr_user_id not like", value, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdIn(List<String> values) {
            addCriterion("clr_user_id in", values, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdNotIn(List<String> values) {
            addCriterion("clr_user_id not in", values, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdBetween(String value1, String value2) {
            addCriterion("clr_user_id between", value1, value2, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserIdNotBetween(String value1, String value2) {
            addCriterion("clr_user_id not between", value1, value2, "clrUserId");
            return (Criteria) this;
        }

        public Criteria andClrUserNameIsNull() {
            addCriterion("clr_user_name is null");
            return (Criteria) this;
        }

        public Criteria andClrUserNameIsNotNull() {
            addCriterion("clr_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andClrUserNameEqualTo(String value) {
            addCriterion("clr_user_name =", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameNotEqualTo(String value) {
            addCriterion("clr_user_name <>", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameGreaterThan(String value) {
            addCriterion("clr_user_name >", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("clr_user_name >=", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameLessThan(String value) {
            addCriterion("clr_user_name <", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameLessThanOrEqualTo(String value) {
            addCriterion("clr_user_name <=", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameLike(String value) {
            addCriterion("clr_user_name like", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameNotLike(String value) {
            addCriterion("clr_user_name not like", value, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameIn(List<String> values) {
            addCriterion("clr_user_name in", values, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameNotIn(List<String> values) {
            addCriterion("clr_user_name not in", values, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameBetween(String value1, String value2) {
            addCriterion("clr_user_name between", value1, value2, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrUserNameNotBetween(String value1, String value2) {
            addCriterion("clr_user_name not between", value1, value2, "clrUserName");
            return (Criteria) this;
        }

        public Criteria andClrTimeIsNull() {
            addCriterion("clr_time is null");
            return (Criteria) this;
        }

        public Criteria andClrTimeIsNotNull() {
            addCriterion("clr_time is not null");
            return (Criteria) this;
        }

        public Criteria andClrTimeEqualTo(String value) {
            addCriterion("clr_time =", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeNotEqualTo(String value) {
            addCriterion("clr_time <>", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeGreaterThan(String value) {
            addCriterion("clr_time >", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeGreaterThanOrEqualTo(String value) {
            addCriterion("clr_time >=", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeLessThan(String value) {
            addCriterion("clr_time <", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeLessThanOrEqualTo(String value) {
            addCriterion("clr_time <=", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeLike(String value) {
            addCriterion("clr_time like", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeNotLike(String value) {
            addCriterion("clr_time not like", value, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeIn(List<String> values) {
            addCriterion("clr_time in", values, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeNotIn(List<String> values) {
            addCriterion("clr_time not in", values, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeBetween(String value1, String value2) {
            addCriterion("clr_time between", value1, value2, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrTimeNotBetween(String value1, String value2) {
            addCriterion("clr_time not between", value1, value2, "clrTime");
            return (Criteria) this;
        }

        public Criteria andClrReasonIsNull() {
            addCriterion("clr_reason is null");
            return (Criteria) this;
        }

        public Criteria andClrReasonIsNotNull() {
            addCriterion("clr_reason is not null");
            return (Criteria) this;
        }

        public Criteria andClrReasonEqualTo(String value) {
            addCriterion("clr_reason =", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonNotEqualTo(String value) {
            addCriterion("clr_reason <>", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonGreaterThan(String value) {
            addCriterion("clr_reason >", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonGreaterThanOrEqualTo(String value) {
            addCriterion("clr_reason >=", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonLessThan(String value) {
            addCriterion("clr_reason <", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonLessThanOrEqualTo(String value) {
            addCriterion("clr_reason <=", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonLike(String value) {
            addCriterion("clr_reason like", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonNotLike(String value) {
            addCriterion("clr_reason not like", value, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonIn(List<String> values) {
            addCriterion("clr_reason in", values, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonNotIn(List<String> values) {
            addCriterion("clr_reason not in", values, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonBetween(String value1, String value2) {
            addCriterion("clr_reason between", value1, value2, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrReasonNotBetween(String value1, String value2) {
            addCriterion("clr_reason not between", value1, value2, "clrReason");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoIsNull() {
            addCriterion("clr_court_no is null");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoIsNotNull() {
            addCriterion("clr_court_no is not null");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoEqualTo(Integer value) {
            addCriterion("clr_court_no =", value, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoNotEqualTo(Integer value) {
            addCriterion("clr_court_no <>", value, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoGreaterThan(Integer value) {
            addCriterion("clr_court_no >", value, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("clr_court_no >=", value, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoLessThan(Integer value) {
            addCriterion("clr_court_no <", value, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoLessThanOrEqualTo(Integer value) {
            addCriterion("clr_court_no <=", value, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoIn(List<Integer> values) {
            addCriterion("clr_court_no in", values, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoNotIn(List<Integer> values) {
            addCriterion("clr_court_no not in", values, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoBetween(Integer value1, Integer value2) {
            addCriterion("clr_court_no between", value1, value2, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtNoNotBetween(Integer value1, Integer value2) {
            addCriterion("clr_court_no not between", value1, value2, "clrCourtNo");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextIsNull() {
            addCriterion("clr_court_text is null");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextIsNotNull() {
            addCriterion("clr_court_text is not null");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextEqualTo(String value) {
            addCriterion("clr_court_text =", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextNotEqualTo(String value) {
            addCriterion("clr_court_text <>", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextGreaterThan(String value) {
            addCriterion("clr_court_text >", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextGreaterThanOrEqualTo(String value) {
            addCriterion("clr_court_text >=", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextLessThan(String value) {
            addCriterion("clr_court_text <", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextLessThanOrEqualTo(String value) {
            addCriterion("clr_court_text <=", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextLike(String value) {
            addCriterion("clr_court_text like", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextNotLike(String value) {
            addCriterion("clr_court_text not like", value, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextIn(List<String> values) {
            addCriterion("clr_court_text in", values, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextNotIn(List<String> values) {
            addCriterion("clr_court_text not in", values, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextBetween(String value1, String value2) {
            addCriterion("clr_court_text between", value1, value2, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andClrCourtTextNotBetween(String value1, String value2) {
            addCriterion("clr_court_text not between", value1, value2, "clrCourtText");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
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