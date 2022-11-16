package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsExternalLabourContractCriteria {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to
     * the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public UmsExternalLabourContractCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
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
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table ums_extenal_labour_contract
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to
     * the database table ums_extenal_labour_contract
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

        public Criteria andContractStartIsNull() {
            addCriterion("contract_start is null");
            return (Criteria) this;
        }

        public Criteria andContractStartIsNotNull() {
            addCriterion("contract_start is not null");
            return (Criteria) this;
        }

        public Criteria andContractStartEqualTo(Date value) {
            addCriterionForJDBCDate("contract_start =", value, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartNotEqualTo(Date value) {
            addCriterionForJDBCDate("contract_start <>", value, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartGreaterThan(Date value) {
            addCriterionForJDBCDate("contract_start >", value, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("contract_start >=", value, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartLessThan(Date value) {
            addCriterionForJDBCDate("contract_start <", value, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("contract_start <=", value, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartIn(List<Date> values) {
            addCriterionForJDBCDate("contract_start in", values, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartNotIn(List<Date> values) {
            addCriterionForJDBCDate("contract_start not in", values, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("contract_start between", value1, value2, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractStartNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("contract_start not between", value1, value2, "contractStart");
            return (Criteria) this;
        }

        public Criteria andContractEndIsNull() {
            addCriterion("contract_end is null");
            return (Criteria) this;
        }

        public Criteria andContractEndIsNotNull() {
            addCriterion("contract_end is not null");
            return (Criteria) this;
        }

        public Criteria andContractEndEqualTo(Date value) {
            addCriterionForJDBCDate("contract_end =", value, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndNotEqualTo(Date value) {
            addCriterionForJDBCDate("contract_end <>", value, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndGreaterThan(Date value) {
            addCriterionForJDBCDate("contract_end >", value, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("contract_end >=", value, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndLessThan(Date value) {
            addCriterionForJDBCDate("contract_end <", value, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("contract_end <=", value, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndIn(List<Date> values) {
            addCriterionForJDBCDate("contract_end in", values, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndNotIn(List<Date> values) {
            addCriterionForJDBCDate("contract_end not in", values, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("contract_end between", value1, value2, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractEndNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("contract_end not between", value1, value2, "contractEnd");
            return (Criteria) this;
        }

        public Criteria andContractNatureIsNull() {
            addCriterion("contract_nature is null");
            return (Criteria) this;
        }

        public Criteria andContractNatureIsNotNull() {
            addCriterion("contract_nature is not null");
            return (Criteria) this;
        }

        public Criteria andContractNatureEqualTo(String value) {
            addCriterion("contract_nature =", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureNotEqualTo(String value) {
            addCriterion("contract_nature <>", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureGreaterThan(String value) {
            addCriterion("contract_nature >", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureGreaterThanOrEqualTo(String value) {
            addCriterion("contract_nature >=", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureLessThan(String value) {
            addCriterion("contract_nature <", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureLessThanOrEqualTo(String value) {
            addCriterion("contract_nature <=", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureLike(String value) {
            addCriterion("contract_nature like", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureNotLike(String value) {
            addCriterion("contract_nature not like", value, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureIn(List<String> values) {
            addCriterion("contract_nature in", values, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureNotIn(List<String> values) {
            addCriterion("contract_nature not in", values, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureBetween(String value1, String value2) {
            addCriterion("contract_nature between", value1, value2, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractNatureNotBetween(String value1, String value2) {
            addCriterion("contract_nature not between", value1, value2, "contractNature");
            return (Criteria) this;
        }

        public Criteria andContractCompanyIsNull() {
            addCriterion("contract_company is null");
            return (Criteria) this;
        }

        public Criteria andContractCompanyIsNotNull() {
            addCriterion("contract_company is not null");
            return (Criteria) this;
        }

        public Criteria andContractCompanyEqualTo(String value) {
            addCriterion("contract_company =", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyNotEqualTo(String value) {
            addCriterion("contract_company <>", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyGreaterThan(String value) {
            addCriterion("contract_company >", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("contract_company >=", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyLessThan(String value) {
            addCriterion("contract_company <", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyLessThanOrEqualTo(String value) {
            addCriterion("contract_company <=", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyLike(String value) {
            addCriterion("contract_company like", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyNotLike(String value) {
            addCriterion("contract_company not like", value, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyIn(List<String> values) {
            addCriterion("contract_company in", values, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyNotIn(List<String> values) {
            addCriterion("contract_company not in", values, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyBetween(String value1, String value2) {
            addCriterion("contract_company between", value1, value2, "contractCompany");
            return (Criteria) this;
        }

        public Criteria andContractCompanyNotBetween(String value1, String value2) {
            addCriterion("contract_company not between", value1, value2, "contractCompany");
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
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to
     * the database table ums_extenal_labour_contract
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to
     * the database table ums_extenal_labour_contract
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