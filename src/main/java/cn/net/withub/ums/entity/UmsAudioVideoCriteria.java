package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

public class UmsAudioVideoCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public UmsAudioVideoCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
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

        public Criteria andOldIdIsNull() {
            addCriterion("old_id is null");
            return (Criteria) this;
        }

        public Criteria andOldIdIsNotNull() {
            addCriterion("old_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldIdEqualTo(Long value) {
            addCriterion("old_id =", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotEqualTo(Long value) {
            addCriterion("old_id <>", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThan(Long value) {
            addCriterion("old_id >", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThanOrEqualTo(Long value) {
            addCriterion("old_id >=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThan(Long value) {
            addCriterion("old_id <", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThanOrEqualTo(Long value) {
            addCriterion("old_id <=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdIn(List<Long> values) {
            addCriterion("old_id in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotIn(List<Long> values) {
            addCriterion("old_id not in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdBetween(Long value1, Long value2) {
            addCriterion("old_id between", value1, value2, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotBetween(Long value1, Long value2) {
            addCriterion("old_id not between", value1, value2, "oldId");
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

        public Criteria andCFileNameIsNull() {
            addCriterion("c_file_name is null");
            return (Criteria) this;
        }

        public Criteria andCFileNameIsNotNull() {
            addCriterion("c_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andCFileNameEqualTo(String value) {
            addCriterion("c_file_name =", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameNotEqualTo(String value) {
            addCriterion("c_file_name <>", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameGreaterThan(String value) {
            addCriterion("c_file_name >", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("c_file_name >=", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameLessThan(String value) {
            addCriterion("c_file_name <", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameLessThanOrEqualTo(String value) {
            addCriterion("c_file_name <=", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameLike(String value) {
            addCriterion("c_file_name like", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameNotLike(String value) {
            addCriterion("c_file_name not like", value, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameIn(List<String> values) {
            addCriterion("c_file_name in", values, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameNotIn(List<String> values) {
            addCriterion("c_file_name not in", values, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameBetween(String value1, String value2) {
            addCriterion("c_file_name between", value1, value2, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFileNameNotBetween(String value1, String value2) {
            addCriterion("c_file_name not between", value1, value2, "cFileName");
            return (Criteria) this;
        }

        public Criteria andCFilePathIsNull() {
            addCriterion("c_file_path is null");
            return (Criteria) this;
        }

        public Criteria andCFilePathIsNotNull() {
            addCriterion("c_file_path is not null");
            return (Criteria) this;
        }

        public Criteria andCFilePathEqualTo(String value) {
            addCriterion("c_file_path =", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathNotEqualTo(String value) {
            addCriterion("c_file_path <>", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathGreaterThan(String value) {
            addCriterion("c_file_path >", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("c_file_path >=", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathLessThan(String value) {
            addCriterion("c_file_path <", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathLessThanOrEqualTo(String value) {
            addCriterion("c_file_path <=", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathLike(String value) {
            addCriterion("c_file_path like", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathNotLike(String value) {
            addCriterion("c_file_path not like", value, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathIn(List<String> values) {
            addCriterion("c_file_path in", values, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathNotIn(List<String> values) {
            addCriterion("c_file_path not in", values, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathBetween(String value1, String value2) {
            addCriterion("c_file_path between", value1, value2, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCFilePathNotBetween(String value1, String value2) {
            addCriterion("c_file_path not between", value1, value2, "cFilePath");
            return (Criteria) this;
        }

        public Criteria andCDescriptionIsNull() {
            addCriterion("c_description is null");
            return (Criteria) this;
        }

        public Criteria andCDescriptionIsNotNull() {
            addCriterion("c_description is not null");
            return (Criteria) this;
        }

        public Criteria andCDescriptionEqualTo(String value) {
            addCriterion("c_description =", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionNotEqualTo(String value) {
            addCriterion("c_description <>", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionGreaterThan(String value) {
            addCriterion("c_description >", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("c_description >=", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionLessThan(String value) {
            addCriterion("c_description <", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionLessThanOrEqualTo(String value) {
            addCriterion("c_description <=", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionLike(String value) {
            addCriterion("c_description like", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionNotLike(String value) {
            addCriterion("c_description not like", value, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionIn(List<String> values) {
            addCriterion("c_description in", values, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionNotIn(List<String> values) {
            addCriterion("c_description not in", values, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionBetween(String value1, String value2) {
            addCriterion("c_description between", value1, value2, "cDescription");
            return (Criteria) this;
        }

        public Criteria andCDescriptionNotBetween(String value1, String value2) {
            addCriterion("c_description not between", value1, value2, "cDescription");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ums_audio_video
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ums_audio_video
     *
     * @mbggenerated
     */
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