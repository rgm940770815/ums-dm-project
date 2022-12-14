package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.List;

public class UmsUploadCodeContrastExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public UmsUploadCodeContrastExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
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
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
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

        public Criteria andFieldTypeIsNull() {
            addCriterion("field_type is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNotNull() {
            addCriterion("field_type is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeEqualTo(String value) {
            addCriterion("field_type =", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotEqualTo(String value) {
            addCriterion("field_type <>", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThan(String value) {
            addCriterion("field_type >", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThanOrEqualTo(String value) {
            addCriterion("field_type >=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThan(String value) {
            addCriterion("field_type <", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThanOrEqualTo(String value) {
            addCriterion("field_type <=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLike(String value) {
            addCriterion("field_type like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotLike(String value) {
            addCriterion("field_type not like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIn(List<String> values) {
            addCriterion("field_type in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotIn(List<String> values) {
            addCriterion("field_type not in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeBetween(String value1, String value2) {
            addCriterion("field_type between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotBetween(String value1, String value2) {
            addCriterion("field_type not between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andSourceFieldIsNull() {
            addCriterion("source_field is null");
            return (Criteria) this;
        }

        public Criteria andSourceFieldIsNotNull() {
            addCriterion("source_field is not null");
            return (Criteria) this;
        }

        public Criteria andSourceFieldEqualTo(String value) {
            addCriterion("source_field =", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNotEqualTo(String value) {
            addCriterion("source_field <>", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldGreaterThan(String value) {
            addCriterion("source_field >", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldGreaterThanOrEqualTo(String value) {
            addCriterion("source_field >=", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldLessThan(String value) {
            addCriterion("source_field <", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldLessThanOrEqualTo(String value) {
            addCriterion("source_field <=", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldLike(String value) {
            addCriterion("source_field like", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNotLike(String value) {
            addCriterion("source_field not like", value, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldIn(List<String> values) {
            addCriterion("source_field in", values, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNotIn(List<String> values) {
            addCriterion("source_field not in", values, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldBetween(String value1, String value2) {
            addCriterion("source_field between", value1, value2, "sourceField");
            return (Criteria) this;
        }

        public Criteria andSourceFieldNotBetween(String value1, String value2) {
            addCriterion("source_field not between", value1, value2, "sourceField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldIsNull() {
            addCriterion("target_field is null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldIsNotNull() {
            addCriterion("target_field is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldEqualTo(String value) {
            addCriterion("target_field =", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotEqualTo(String value) {
            addCriterion("target_field <>", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldGreaterThan(String value) {
            addCriterion("target_field >", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldGreaterThanOrEqualTo(String value) {
            addCriterion("target_field >=", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldLessThan(String value) {
            addCriterion("target_field <", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldLessThanOrEqualTo(String value) {
            addCriterion("target_field <=", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldLike(String value) {
            addCriterion("target_field like", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotLike(String value) {
            addCriterion("target_field not like", value, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldIn(List<String> values) {
            addCriterion("target_field in", values, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotIn(List<String> values) {
            addCriterion("target_field not in", values, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldBetween(String value1, String value2) {
            addCriterion("target_field between", value1, value2, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldNotBetween(String value1, String value2) {
            addCriterion("target_field not between", value1, value2, "targetField");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeIsNull() {
            addCriterion("target_field_describe is null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeIsNotNull() {
            addCriterion("target_field_describe is not null");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeEqualTo(String value) {
            addCriterion("target_field_describe =", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeNotEqualTo(String value) {
            addCriterion("target_field_describe <>", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeGreaterThan(String value) {
            addCriterion("target_field_describe >", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("target_field_describe >=", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeLessThan(String value) {
            addCriterion("target_field_describe <", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeLessThanOrEqualTo(String value) {
            addCriterion("target_field_describe <=", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeLike(String value) {
            addCriterion("target_field_describe like", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeNotLike(String value) {
            addCriterion("target_field_describe not like", value, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeIn(List<String> values) {
            addCriterion("target_field_describe in", values, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeNotIn(List<String> values) {
            addCriterion("target_field_describe not in", values, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeBetween(String value1, String value2) {
            addCriterion("target_field_describe between", value1, value2, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andTargetFieldDescribeNotBetween(String value1, String value2) {
            addCriterion("target_field_describe not between", value1, value2, "targetFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeIsNull() {
            addCriterion("source_field_describe is null");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeIsNotNull() {
            addCriterion("source_field_describe is not null");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeEqualTo(String value) {
            addCriterion("source_field_describe =", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeNotEqualTo(String value) {
            addCriterion("source_field_describe <>", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeGreaterThan(String value) {
            addCriterion("source_field_describe >", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("source_field_describe >=", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeLessThan(String value) {
            addCriterion("source_field_describe <", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeLessThanOrEqualTo(String value) {
            addCriterion("source_field_describe <=", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeLike(String value) {
            addCriterion("source_field_describe like", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeNotLike(String value) {
            addCriterion("source_field_describe not like", value, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeIn(List<String> values) {
            addCriterion("source_field_describe in", values, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeNotIn(List<String> values) {
            addCriterion("source_field_describe not in", values, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeBetween(String value1, String value2) {
            addCriterion("source_field_describe between", value1, value2, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceFieldDescribeNotBetween(String value1, String value2) {
            addCriterion("source_field_describe not between", value1, value2, "sourceFieldDescribe");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdIsNull() {
            addCriterion("source_type_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdIsNotNull() {
            addCriterion("source_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdEqualTo(Integer value) {
            addCriterion("source_type_id =", value, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdNotEqualTo(Integer value) {
            addCriterion("source_type_id <>", value, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdGreaterThan(Integer value) {
            addCriterion("source_type_id >", value, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("source_type_id >=", value, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdLessThan(Integer value) {
            addCriterion("source_type_id <", value, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("source_type_id <=", value, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdIn(List<Integer> values) {
            addCriterion("source_type_id in", values, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdNotIn(List<Integer> values) {
            addCriterion("source_type_id not in", values, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("source_type_id between", value1, value2, "sourceTypeId");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("source_type_id not between", value1, value2, "sourceTypeId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated do_not_delete_during_merge Tue Jan 26 17:26:34 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ums_upload_code_contrast
     *
     * @mbggenerated Tue Jan 26 17:26:34 CST 2016
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
