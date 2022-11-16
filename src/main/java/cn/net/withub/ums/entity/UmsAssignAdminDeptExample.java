package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsAssignAdminDeptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsAssignAdminDeptExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserUuidIsNull() {
            addCriterion("user_uuid is null");
            return (Criteria) this;
        }

        public Criteria andUserUuidIsNotNull() {
            addCriterion("user_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUserUuidEqualTo(String value) {
            addCriterion("user_uuid =", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotEqualTo(String value) {
            addCriterion("user_uuid <>", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThan(String value) {
            addCriterion("user_uuid >", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidGreaterThanOrEqualTo(String value) {
            addCriterion("user_uuid >=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThan(String value) {
            addCriterion("user_uuid <", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLessThanOrEqualTo(String value) {
            addCriterion("user_uuid <=", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidLike(String value) {
            addCriterion("user_uuid like", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotLike(String value) {
            addCriterion("user_uuid not like", value, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidIn(List<String> values) {
            addCriterion("user_uuid in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotIn(List<String> values) {
            addCriterion("user_uuid not in", values, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidBetween(String value1, String value2) {
            addCriterion("user_uuid between", value1, value2, "userUuid");
            return (Criteria) this;
        }

        public Criteria andUserUuidNotBetween(String value1, String value2) {
            addCriterion("user_uuid not between", value1, value2, "userUuid");
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

        public Criteria andAdminCourtCodeIsNull() {
            addCriterion("admin_court_code is null");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeIsNotNull() {
            addCriterion("admin_court_code is not null");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeEqualTo(String value) {
            addCriterion("admin_court_code =", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeNotEqualTo(String value) {
            addCriterion("admin_court_code <>", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeGreaterThan(String value) {
            addCriterion("admin_court_code >", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("admin_court_code >=", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeLessThan(String value) {
            addCriterion("admin_court_code <", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeLessThanOrEqualTo(String value) {
            addCriterion("admin_court_code <=", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeLike(String value) {
            addCriterion("admin_court_code like", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeNotLike(String value) {
            addCriterion("admin_court_code not like", value, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeIn(List<String> values) {
            addCriterion("admin_court_code in", values, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeNotIn(List<String> values) {
            addCriterion("admin_court_code not in", values, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeBetween(String value1, String value2) {
            addCriterion("admin_court_code between", value1, value2, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtCodeNotBetween(String value1, String value2) {
            addCriterion("admin_court_code not between", value1, value2, "adminCourtCode");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameIsNull() {
            addCriterion("admin_court_name is null");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameIsNotNull() {
            addCriterion("admin_court_name is not null");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameEqualTo(String value) {
            addCriterion("admin_court_name =", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameNotEqualTo(String value) {
            addCriterion("admin_court_name <>", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameGreaterThan(String value) {
            addCriterion("admin_court_name >", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_court_name >=", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameLessThan(String value) {
            addCriterion("admin_court_name <", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameLessThanOrEqualTo(String value) {
            addCriterion("admin_court_name <=", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameLike(String value) {
            addCriterion("admin_court_name like", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameNotLike(String value) {
            addCriterion("admin_court_name not like", value, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameIn(List<String> values) {
            addCriterion("admin_court_name in", values, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameNotIn(List<String> values) {
            addCriterion("admin_court_name not in", values, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameBetween(String value1, String value2) {
            addCriterion("admin_court_name between", value1, value2, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminCourtNameNotBetween(String value1, String value2) {
            addCriterion("admin_court_name not between", value1, value2, "adminCourtName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdIsNull() {
            addCriterion("admin_dept_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdIsNotNull() {
            addCriterion("admin_dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdEqualTo(String value) {
            addCriterion("admin_dept_id =", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdNotEqualTo(String value) {
            addCriterion("admin_dept_id <>", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdGreaterThan(String value) {
            addCriterion("admin_dept_id >", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("admin_dept_id >=", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdLessThan(String value) {
            addCriterion("admin_dept_id <", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdLessThanOrEqualTo(String value) {
            addCriterion("admin_dept_id <=", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdLike(String value) {
            addCriterion("admin_dept_id like", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdNotLike(String value) {
            addCriterion("admin_dept_id not like", value, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdIn(List<String> values) {
            addCriterion("admin_dept_id in", values, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdNotIn(List<String> values) {
            addCriterion("admin_dept_id not in", values, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdBetween(String value1, String value2) {
            addCriterion("admin_dept_id between", value1, value2, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptIdNotBetween(String value1, String value2) {
            addCriterion("admin_dept_id not between", value1, value2, "adminDeptId");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameIsNull() {
            addCriterion("admin_dept_name is null");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameIsNotNull() {
            addCriterion("admin_dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameEqualTo(String value) {
            addCriterion("admin_dept_name =", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameNotEqualTo(String value) {
            addCriterion("admin_dept_name <>", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameGreaterThan(String value) {
            addCriterion("admin_dept_name >", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_dept_name >=", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameLessThan(String value) {
            addCriterion("admin_dept_name <", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameLessThanOrEqualTo(String value) {
            addCriterion("admin_dept_name <=", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameLike(String value) {
            addCriterion("admin_dept_name like", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameNotLike(String value) {
            addCriterion("admin_dept_name not like", value, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameIn(List<String> values) {
            addCriterion("admin_dept_name in", values, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameNotIn(List<String> values) {
            addCriterion("admin_dept_name not in", values, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameBetween(String value1, String value2) {
            addCriterion("admin_dept_name between", value1, value2, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andAdminDeptNameNotBetween(String value1, String value2) {
            addCriterion("admin_dept_name not between", value1, value2, "adminDeptName");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidIsNull() {
            addCriterion("create_user_uuid is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidIsNotNull() {
            addCriterion("create_user_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidEqualTo(String value) {
            addCriterion("create_user_uuid =", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidNotEqualTo(String value) {
            addCriterion("create_user_uuid <>", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidGreaterThan(String value) {
            addCriterion("create_user_uuid >", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_uuid >=", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidLessThan(String value) {
            addCriterion("create_user_uuid <", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidLessThanOrEqualTo(String value) {
            addCriterion("create_user_uuid <=", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidLike(String value) {
            addCriterion("create_user_uuid like", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidNotLike(String value) {
            addCriterion("create_user_uuid not like", value, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidIn(List<String> values) {
            addCriterion("create_user_uuid in", values, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidNotIn(List<String> values) {
            addCriterion("create_user_uuid not in", values, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidBetween(String value1, String value2) {
            addCriterion("create_user_uuid between", value1, value2, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserUuidNotBetween(String value1, String value2) {
            addCriterion("create_user_uuid not between", value1, value2, "createUserUuid");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIsNull() {
            addCriterion("create_user_name is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIsNotNull() {
            addCriterion("create_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameEqualTo(String value) {
            addCriterion("create_user_name =", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotEqualTo(String value) {
            addCriterion("create_user_name <>", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThan(String value) {
            addCriterion("create_user_name >", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_name >=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThan(String value) {
            addCriterion("create_user_name <", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLessThanOrEqualTo(String value) {
            addCriterion("create_user_name <=", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameLike(String value) {
            addCriterion("create_user_name like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotLike(String value) {
            addCriterion("create_user_name not like", value, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameIn(List<String> values) {
            addCriterion("create_user_name in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotIn(List<String> values) {
            addCriterion("create_user_name not in", values, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameBetween(String value1, String value2) {
            addCriterion("create_user_name between", value1, value2, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateUserNameNotBetween(String value1, String value2) {
            addCriterion("create_user_name not between", value1, value2, "createUserName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(String value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(String value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(String value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(String value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(String value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLike(String value) {
            addCriterion("update_user_id like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotLike(String value) {
            addCriterion("update_user_id not like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<String> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<String> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(String value1, String value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(String value1, String value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
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