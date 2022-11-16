package cn.net.withub.ums.entity;

import cn.net.withub.ums.action.config.verify.PageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsSubitemAuditExample extends PageUtil {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsSubitemAuditExample() {
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

        public Criteria andShlxEqualTo(String value) {
            addCriterion("shlx =", value, "shlx");
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

        public Criteria andContentTypeIsNull() {
            addCriterion("content_type is null");
            return (Criteria) this;
        }

        public Criteria andContentTypeIsNotNull() {
            addCriterion("content_type is not null");
            return (Criteria) this;
        }

        public Criteria andContentTypeEqualTo(String value) {
            addCriterion("content_type =", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotEqualTo(String value) {
            addCriterion("content_type <>", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeGreaterThan(String value) {
            addCriterion("content_type >", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("content_type >=", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLessThan(String value) {
            addCriterion("content_type <", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLessThanOrEqualTo(String value) {
            addCriterion("content_type <=", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeLike(String value) {
            addCriterion("content_type like", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotLike(String value) {
            addCriterion("content_type not like", value, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeIn(List<String> values) {
            addCriterion("content_type in", values, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotIn(List<String> values) {
            addCriterion("content_type not in", values, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeBetween(String value1, String value2) {
            addCriterion("content_type between", value1, value2, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeNotBetween(String value1, String value2) {
            addCriterion("content_type not between", value1, value2, "contentType");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextIsNull() {
            addCriterion("content_type_text is null");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextIsNotNull() {
            addCriterion("content_type_text is not null");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextEqualTo(String value) {
            addCriterion("content_type_text =", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextNotEqualTo(String value) {
            addCriterion("content_type_text <>", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextGreaterThan(String value) {
            addCriterion("content_type_text >", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextGreaterThanOrEqualTo(String value) {
            addCriterion("content_type_text >=", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextLessThan(String value) {
            addCriterion("content_type_text <", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextLessThanOrEqualTo(String value) {
            addCriterion("content_type_text <=", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextLike(String value) {
            addCriterion("content_type_text like", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextNotLike(String value) {
            addCriterion("content_type_text not like", value, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextIn(List<String> values) {
            addCriterion("content_type_text in", values, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextNotIn(List<String> values) {
            addCriterion("content_type_text not in", values, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextBetween(String value1, String value2) {
            addCriterion("content_type_text between", value1, value2, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andContentTypeTextNotBetween(String value1, String value2) {
            addCriterion("content_type_text not between", value1, value2, "contentTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIsNull() {
            addCriterion("examine_type is null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIsNotNull() {
            addCriterion("examine_type is not null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeEqualTo(String value) {
            addCriterion("examine_type =", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotEqualTo(String value) {
            addCriterion("examine_type <>", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeGreaterThan(String value) {
            addCriterion("examine_type >", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeGreaterThanOrEqualTo(String value) {
            addCriterion("examine_type >=", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeLessThan(String value) {
            addCriterion("examine_type <", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeLessThanOrEqualTo(String value) {
            addCriterion("examine_type <=", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeLike(String value) {
            addCriterion("examine_type like", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotLike(String value) {
            addCriterion("examine_type not like", value, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeIn(List<String> values) {
            addCriterion("examine_type in", values, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotIn(List<String> values) {
            addCriterion("examine_type not in", values, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeBetween(String value1, String value2) {
            addCriterion("examine_type between", value1, value2, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeNotBetween(String value1, String value2) {
            addCriterion("examine_type not between", value1, value2, "examineType");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextIsNull() {
            addCriterion("examine_type_text is null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextIsNotNull() {
            addCriterion("examine_type_text is not null");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextEqualTo(String value) {
            addCriterion("examine_type_text =", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextNotEqualTo(String value) {
            addCriterion("examine_type_text <>", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextGreaterThan(String value) {
            addCriterion("examine_type_text >", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextGreaterThanOrEqualTo(String value) {
            addCriterion("examine_type_text >=", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextLessThan(String value) {
            addCriterion("examine_type_text <", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextLessThanOrEqualTo(String value) {
            addCriterion("examine_type_text <=", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextLike(String value) {
            addCriterion("examine_type_text like", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextNotLike(String value) {
            addCriterion("examine_type_text not like", value, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextIn(List<String> values) {
            addCriterion("examine_type_text in", values, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextNotIn(List<String> values) {
            addCriterion("examine_type_text not in", values, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextBetween(String value1, String value2) {
            addCriterion("examine_type_text between", value1, value2, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andExamineTypeTextNotBetween(String value1, String value2) {
            addCriterion("examine_type_text not between", value1, value2, "examineTypeText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdIsNull() {
            addCriterion("operated_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdIsNotNull() {
            addCriterion("operated_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdEqualTo(String value) {
            addCriterion("operated_user_id =", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdNotEqualTo(String value) {
            addCriterion("operated_user_id <>", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdGreaterThan(String value) {
            addCriterion("operated_user_id >", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("operated_user_id >=", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdLessThan(String value) {
            addCriterion("operated_user_id <", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdLessThanOrEqualTo(String value) {
            addCriterion("operated_user_id <=", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdLike(String value) {
            addCriterion("operated_user_id like", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdNotLike(String value) {
            addCriterion("operated_user_id not like", value, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdIn(List<String> values) {
            addCriterion("operated_user_id in", values, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdNotIn(List<String> values) {
            addCriterion("operated_user_id not in", values, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdBetween(String value1, String value2) {
            addCriterion("operated_user_id between", value1, value2, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserIdNotBetween(String value1, String value2) {
            addCriterion("operated_user_id not between", value1, value2, "operatedUserId");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameIsNull() {
            addCriterion("operated_user_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameIsNotNull() {
            addCriterion("operated_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameEqualTo(String value) {
            addCriterion("operated_user_name =", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameNotEqualTo(String value) {
            addCriterion("operated_user_name <>", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameGreaterThan(String value) {
            addCriterion("operated_user_name >", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("operated_user_name >=", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameLessThan(String value) {
            addCriterion("operated_user_name <", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameLessThanOrEqualTo(String value) {
            addCriterion("operated_user_name <=", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameLike(String value) {
            addCriterion("operated_user_name like", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameNotLike(String value) {
            addCriterion("operated_user_name not like", value, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameIn(List<String> values) {
            addCriterion("operated_user_name in", values, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameNotIn(List<String> values) {
            addCriterion("operated_user_name not in", values, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameBetween(String value1, String value2) {
            addCriterion("operated_user_name between", value1, value2, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserNameNotBetween(String value1, String value2) {
            addCriterion("operated_user_name not between", value1, value2, "operatedUserName");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoIsNull() {
            addCriterion("operated_user_court_no is null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoIsNotNull() {
            addCriterion("operated_user_court_no is not null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoEqualTo(Integer value) {
            addCriterion("operated_user_court_no =", value, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoNotEqualTo(Integer value) {
            addCriterion("operated_user_court_no <>", value, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoGreaterThan(Integer value) {
            addCriterion("operated_user_court_no >", value, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("operated_user_court_no >=", value, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoLessThan(Integer value) {
            addCriterion("operated_user_court_no <", value, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoLessThanOrEqualTo(Integer value) {
            addCriterion("operated_user_court_no <=", value, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoIn(List<Integer> values) {
            addCriterion("operated_user_court_no in", values, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoNotIn(List<Integer> values) {
            addCriterion("operated_user_court_no not in", values, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoBetween(Integer value1, Integer value2) {
            addCriterion("operated_user_court_no between", value1, value2, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtNoNotBetween(Integer value1, Integer value2) {
            addCriterion("operated_user_court_no not between", value1, value2, "operatedUserCourtNo");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextIsNull() {
            addCriterion("operated_user_court_text is null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextIsNotNull() {
            addCriterion("operated_user_court_text is not null");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextEqualTo(String value) {
            addCriterion("operated_user_court_text =", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextNotEqualTo(String value) {
            addCriterion("operated_user_court_text <>", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextGreaterThan(String value) {
            addCriterion("operated_user_court_text >", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextGreaterThanOrEqualTo(String value) {
            addCriterion("operated_user_court_text >=", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextLessThan(String value) {
            addCriterion("operated_user_court_text <", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextLessThanOrEqualTo(String value) {
            addCriterion("operated_user_court_text <=", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextLike(String value) {
            addCriterion("operated_user_court_text like", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextNotLike(String value) {
            addCriterion("operated_user_court_text not like", value, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextIn(List<String> values) {
            addCriterion("operated_user_court_text in", values, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextNotIn(List<String> values) {
            addCriterion("operated_user_court_text not in", values, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextBetween(String value1, String value2) {
            addCriterion("operated_user_court_text between", value1, value2, "operatedUserCourtText");
            return (Criteria) this;
        }

        public Criteria andOperatedUserCourtTextNotBetween(String value1, String value2) {
            addCriterion("operated_user_court_text not between", value1, value2, "operatedUserCourtText");
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

        public Criteria andCreatorIdIsNull() {
            addCriterion("creator_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(String value) {
            addCriterion("creator_id =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(String value) {
            addCriterion("creator_id <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(String value) {
            addCriterion("creator_id >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("creator_id >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(String value) {
            addCriterion("creator_id <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("creator_id <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLike(String value) {
            addCriterion("creator_id like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotLike(String value) {
            addCriterion("creator_id not like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<String> values) {
            addCriterion("creator_id in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<String> values) {
            addCriterion("creator_id not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(String value1, String value2) {
            addCriterion("creator_id between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(String value1, String value2) {
            addCriterion("creator_id not between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIsNull() {
            addCriterion("creator_name is null");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIsNotNull() {
            addCriterion("creator_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorNameEqualTo(String value) {
            addCriterion("creator_name =", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotEqualTo(String value) {
            addCriterion("creator_name <>", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameGreaterThan(String value) {
            addCriterion("creator_name >", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("creator_name >=", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLessThan(String value) {
            addCriterion("creator_name <", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLessThanOrEqualTo(String value) {
            addCriterion("creator_name <=", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLike(String value) {
            addCriterion("creator_name like", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotLike(String value) {
            addCriterion("creator_name not like", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIn(List<String> values) {
            addCriterion("creator_name in", values, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotIn(List<String> values) {
            addCriterion("creator_name not in", values, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameBetween(String value1, String value2) {
            addCriterion("creator_name between", value1, value2, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotBetween(String value1, String value2) {
            addCriterion("creator_name not between", value1, value2, "creatorName");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNull() {
            addCriterion("auditor_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNotNull() {
            addCriterion("auditor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdEqualTo(String value) {
            addCriterion("auditor_id =", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotEqualTo(String value) {
            addCriterion("auditor_id <>", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThan(String value) {
            addCriterion("auditor_id >", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThanOrEqualTo(String value) {
            addCriterion("auditor_id >=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThan(String value) {
            addCriterion("auditor_id <", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThanOrEqualTo(String value) {
            addCriterion("auditor_id <=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLike(String value) {
            addCriterion("auditor_id like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotLike(String value) {
            addCriterion("auditor_id not like", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIn(List<String> values) {
            addCriterion("auditor_id in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotIn(List<String> values) {
            addCriterion("auditor_id not in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdBetween(String value1, String value2) {
            addCriterion("auditor_id between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotBetween(String value1, String value2) {
            addCriterion("auditor_id not between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIsNull() {
            addCriterion("auditor_name is null");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIsNotNull() {
            addCriterion("auditor_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorNameEqualTo(String value) {
            addCriterion("auditor_name =", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotEqualTo(String value) {
            addCriterion("auditor_name <>", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameGreaterThan(String value) {
            addCriterion("auditor_name >", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameGreaterThanOrEqualTo(String value) {
            addCriterion("auditor_name >=", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLessThan(String value) {
            addCriterion("auditor_name <", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLessThanOrEqualTo(String value) {
            addCriterion("auditor_name <=", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLike(String value) {
            addCriterion("auditor_name like", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotLike(String value) {
            addCriterion("auditor_name not like", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIn(List<String> values) {
            addCriterion("auditor_name in", values, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotIn(List<String> values) {
            addCriterion("auditor_name not in", values, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameBetween(String value1, String value2) {
            addCriterion("auditor_name between", value1, value2, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotBetween(String value1, String value2) {
            addCriterion("auditor_name not between", value1, value2, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Integer value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Integer value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Integer value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Integer value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Integer value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Integer> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Integer> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Integer value1, Integer value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsIsNull() {
            addCriterion("audit_opinions is null");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsIsNotNull() {
            addCriterion("audit_opinions is not null");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsEqualTo(String value) {
            addCriterion("audit_opinions =", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsNotEqualTo(String value) {
            addCriterion("audit_opinions <>", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsGreaterThan(String value) {
            addCriterion("audit_opinions >", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsGreaterThanOrEqualTo(String value) {
            addCriterion("audit_opinions >=", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsLessThan(String value) {
            addCriterion("audit_opinions <", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsLessThanOrEqualTo(String value) {
            addCriterion("audit_opinions <=", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsLike(String value) {
            addCriterion("audit_opinions like", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsNotLike(String value) {
            addCriterion("audit_opinions not like", value, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsIn(List<String> values) {
            addCriterion("audit_opinions in", values, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsNotIn(List<String> values) {
            addCriterion("audit_opinions not in", values, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsBetween(String value1, String value2) {
            addCriterion("audit_opinions between", value1, value2, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andAuditOpinionsNotBetween(String value1, String value2) {
            addCriterion("audit_opinions not between", value1, value2, "auditOpinions");
            return (Criteria) this;
        }

        public Criteria andImportStatusIsNull() {
            addCriterion("import_status is null");
            return (Criteria) this;
        }

        public Criteria andImportStatusIsNotNull() {
            addCriterion("import_status is not null");
            return (Criteria) this;
        }

        public Criteria andImportStatusEqualTo(Integer value) {
            addCriterion("import_status =", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusNotEqualTo(Integer value) {
            addCriterion("import_status <>", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusGreaterThan(Integer value) {
            addCriterion("import_status >", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("import_status >=", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusLessThan(Integer value) {
            addCriterion("import_status <", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusLessThanOrEqualTo(Integer value) {
            addCriterion("import_status <=", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusIn(List<Integer> values) {
            addCriterion("import_status in", values, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusNotIn(List<Integer> values) {
            addCriterion("import_status not in", values, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusBetween(Integer value1, Integer value2) {
            addCriterion("import_status between", value1, value2, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("import_status not between", value1, value2, "importStatus");
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