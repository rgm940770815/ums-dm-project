package cn.net.withub.ums.entity.sbrmpsy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TRmpsyTrainingClassInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyTrainingClassInfoExample() {
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

        public Criteria andTrainingClassNameIsNull() {
            addCriterion("training_class_name is null");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameIsNotNull() {
            addCriterion("training_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameEqualTo(String value) {
            addCriterion("training_class_name =", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameNotEqualTo(String value) {
            addCriterion("training_class_name <>", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameGreaterThan(String value) {
            addCriterion("training_class_name >", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("training_class_name >=", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameLessThan(String value) {
            addCriterion("training_class_name <", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameLessThanOrEqualTo(String value) {
            addCriterion("training_class_name <=", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameLike(String value) {
            addCriterion("training_class_name like", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameNotLike(String value) {
            addCriterion("training_class_name not like", value, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameIn(List<String> values) {
            addCriterion("training_class_name in", values, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameNotIn(List<String> values) {
            addCriterion("training_class_name not in", values, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameBetween(String value1, String value2) {
            addCriterion("training_class_name between", value1, value2, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameNotBetween(String value1, String value2) {
            addCriterion("training_class_name not between", value1, value2, "trainingClassName");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementIsNull() {
            addCriterion("training_class_name_supplement is null");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementIsNotNull() {
            addCriterion("training_class_name_supplement is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementEqualTo(String value) {
            addCriterion("training_class_name_supplement =", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementNotEqualTo(String value) {
            addCriterion("training_class_name_supplement <>", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementGreaterThan(String value) {
            addCriterion("training_class_name_supplement >", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementGreaterThanOrEqualTo(String value) {
            addCriterion("training_class_name_supplement >=", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementLessThan(String value) {
            addCriterion("training_class_name_supplement <", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementLessThanOrEqualTo(String value) {
            addCriterion("training_class_name_supplement <=", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementLike(String value) {
            addCriterion("training_class_name_supplement like", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementNotLike(String value) {
            addCriterion("training_class_name_supplement not like", value, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementIn(List<String> values) {
            addCriterion("training_class_name_supplement in", values, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementNotIn(List<String> values) {
            addCriterion("training_class_name_supplement not in", values, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementBetween(String value1, String value2) {
            addCriterion("training_class_name_supplement between", value1, value2, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingClassNameSupplementNotBetween(String value1, String value2) {
            addCriterion("training_class_name_supplement not between", value1, value2, "trainingClassNameSupplement");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIsNull() {
            addCriterion("training_year is null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIsNotNull() {
            addCriterion("training_year is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearEqualTo(String value) {
            addCriterion("training_year =", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotEqualTo(String value) {
            addCriterion("training_year <>", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThan(String value) {
            addCriterion("training_year >", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThanOrEqualTo(String value) {
            addCriterion("training_year >=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThan(String value) {
            addCriterion("training_year <", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThanOrEqualTo(String value) {
            addCriterion("training_year <=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLike(String value) {
            addCriterion("training_year like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotLike(String value) {
            addCriterion("training_year not like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIn(List<String> values) {
            addCriterion("training_year in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotIn(List<String> values) {
            addCriterion("training_year not in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearBetween(String value1, String value2) {
            addCriterion("training_year between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotBetween(String value1, String value2) {
            addCriterion("training_year not between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeIsNull() {
            addCriterion("training_theme is null");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeIsNotNull() {
            addCriterion("training_theme is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeEqualTo(String value) {
            addCriterion("training_theme =", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeNotEqualTo(String value) {
            addCriterion("training_theme <>", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeGreaterThan(String value) {
            addCriterion("training_theme >", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeGreaterThanOrEqualTo(String value) {
            addCriterion("training_theme >=", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeLessThan(String value) {
            addCriterion("training_theme <", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeLessThanOrEqualTo(String value) {
            addCriterion("training_theme <=", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeLike(String value) {
            addCriterion("training_theme like", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeNotLike(String value) {
            addCriterion("training_theme not like", value, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeIn(List<String> values) {
            addCriterion("training_theme in", values, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeNotIn(List<String> values) {
            addCriterion("training_theme not in", values, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeBetween(String value1, String value2) {
            addCriterion("training_theme between", value1, value2, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingThemeNotBetween(String value1, String value2) {
            addCriterion("training_theme not between", value1, value2, "trainingTheme");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIsNull() {
            addCriterion("training_type is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIsNotNull() {
            addCriterion("training_type is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeEqualTo(String value) {
            addCriterion("training_type =", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNotEqualTo(String value) {
            addCriterion("training_type <>", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeGreaterThan(String value) {
            addCriterion("training_type >", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("training_type >=", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeLessThan(String value) {
            addCriterion("training_type <", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeLessThanOrEqualTo(String value) {
            addCriterion("training_type <=", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeLike(String value) {
            addCriterion("training_type like", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNotLike(String value) {
            addCriterion("training_type not like", value, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIn(List<String> values) {
            addCriterion("training_type in", values, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNotIn(List<String> values) {
            addCriterion("training_type not in", values, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeBetween(String value1, String value2) {
            addCriterion("training_type between", value1, value2, "trainingType");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNotBetween(String value1, String value2) {
            addCriterion("training_type not between", value1, value2, "trainingType");
            return (Criteria) this;
        }

        public Criteria andLiaisonIsNull() {
            addCriterion("liaison is null");
            return (Criteria) this;
        }

        public Criteria andLiaisonIsNotNull() {
            addCriterion("liaison is not null");
            return (Criteria) this;
        }

        public Criteria andLiaisonEqualTo(String value) {
            addCriterion("liaison =", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonNotEqualTo(String value) {
            addCriterion("liaison <>", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonGreaterThan(String value) {
            addCriterion("liaison >", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonGreaterThanOrEqualTo(String value) {
            addCriterion("liaison >=", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonLessThan(String value) {
            addCriterion("liaison <", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonLessThanOrEqualTo(String value) {
            addCriterion("liaison <=", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonLike(String value) {
            addCriterion("liaison like", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonNotLike(String value) {
            addCriterion("liaison not like", value, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonIn(List<String> values) {
            addCriterion("liaison in", values, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonNotIn(List<String> values) {
            addCriterion("liaison not in", values, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonBetween(String value1, String value2) {
            addCriterion("liaison between", value1, value2, "liaison");
            return (Criteria) this;
        }

        public Criteria andLiaisonNotBetween(String value1, String value2) {
            addCriterion("liaison not between", value1, value2, "liaison");
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

        public Criteria andOrganizerIsNull() {
            addCriterion("organizer is null");
            return (Criteria) this;
        }

        public Criteria andOrganizerIsNotNull() {
            addCriterion("organizer is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizerEqualTo(String value) {
            addCriterion("organizer =", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerNotEqualTo(String value) {
            addCriterion("organizer <>", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerGreaterThan(String value) {
            addCriterion("organizer >", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerGreaterThanOrEqualTo(String value) {
            addCriterion("organizer >=", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerLessThan(String value) {
            addCriterion("organizer <", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerLessThanOrEqualTo(String value) {
            addCriterion("organizer <=", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerLike(String value) {
            addCriterion("organizer like", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerNotLike(String value) {
            addCriterion("organizer not like", value, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerIn(List<String> values) {
            addCriterion("organizer in", values, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerNotIn(List<String> values) {
            addCriterion("organizer not in", values, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerBetween(String value1, String value2) {
            addCriterion("organizer between", value1, value2, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerNotBetween(String value1, String value2) {
            addCriterion("organizer not between", value1, value2, "organizer");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryIsNull() {
            addCriterion("organizer_category is null");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryIsNotNull() {
            addCriterion("organizer_category is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryEqualTo(String value) {
            addCriterion("organizer_category =", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryNotEqualTo(String value) {
            addCriterion("organizer_category <>", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryGreaterThan(String value) {
            addCriterion("organizer_category >", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("organizer_category >=", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryLessThan(String value) {
            addCriterion("organizer_category <", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryLessThanOrEqualTo(String value) {
            addCriterion("organizer_category <=", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryLike(String value) {
            addCriterion("organizer_category like", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryNotLike(String value) {
            addCriterion("organizer_category not like", value, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryIn(List<String> values) {
            addCriterion("organizer_category in", values, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryNotIn(List<String> values) {
            addCriterion("organizer_category not in", values, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryBetween(String value1, String value2) {
            addCriterion("organizer_category between", value1, value2, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerCategoryNotBetween(String value1, String value2) {
            addCriterion("organizer_category not between", value1, value2, "organizerCategory");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementIsNull() {
            addCriterion("organizer_supplement is null");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementIsNotNull() {
            addCriterion("organizer_supplement is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementEqualTo(String value) {
            addCriterion("organizer_supplement =", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementNotEqualTo(String value) {
            addCriterion("organizer_supplement <>", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementGreaterThan(String value) {
            addCriterion("organizer_supplement >", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementGreaterThanOrEqualTo(String value) {
            addCriterion("organizer_supplement >=", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementLessThan(String value) {
            addCriterion("organizer_supplement <", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementLessThanOrEqualTo(String value) {
            addCriterion("organizer_supplement <=", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementLike(String value) {
            addCriterion("organizer_supplement like", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementNotLike(String value) {
            addCriterion("organizer_supplement not like", value, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementIn(List<String> values) {
            addCriterion("organizer_supplement in", values, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementNotIn(List<String> values) {
            addCriterion("organizer_supplement not in", values, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementBetween(String value1, String value2) {
            addCriterion("organizer_supplement between", value1, value2, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andOrganizerSupplementNotBetween(String value1, String value2) {
            addCriterion("organizer_supplement not between", value1, value2, "organizerSupplement");
            return (Criteria) this;
        }

        public Criteria andHostedByIsNull() {
            addCriterion("hosted_by is null");
            return (Criteria) this;
        }

        public Criteria andHostedByIsNotNull() {
            addCriterion("hosted_by is not null");
            return (Criteria) this;
        }

        public Criteria andHostedByEqualTo(String value) {
            addCriterion("hosted_by =", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByNotEqualTo(String value) {
            addCriterion("hosted_by <>", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByGreaterThan(String value) {
            addCriterion("hosted_by >", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByGreaterThanOrEqualTo(String value) {
            addCriterion("hosted_by >=", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByLessThan(String value) {
            addCriterion("hosted_by <", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByLessThanOrEqualTo(String value) {
            addCriterion("hosted_by <=", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByLike(String value) {
            addCriterion("hosted_by like", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByNotLike(String value) {
            addCriterion("hosted_by not like", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByIn(List<String> values) {
            addCriterion("hosted_by in", values, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByNotIn(List<String> values) {
            addCriterion("hosted_by not in", values, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByBetween(String value1, String value2) {
            addCriterion("hosted_by between", value1, value2, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByNotBetween(String value1, String value2) {
            addCriterion("hosted_by not between", value1, value2, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryIsNull() {
            addCriterion("hosted_by_category is null");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryIsNotNull() {
            addCriterion("hosted_by_category is not null");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryEqualTo(String value) {
            addCriterion("hosted_by_category =", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryNotEqualTo(String value) {
            addCriterion("hosted_by_category <>", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryGreaterThan(String value) {
            addCriterion("hosted_by_category >", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("hosted_by_category >=", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryLessThan(String value) {
            addCriterion("hosted_by_category <", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryLessThanOrEqualTo(String value) {
            addCriterion("hosted_by_category <=", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryLike(String value) {
            addCriterion("hosted_by_category like", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryNotLike(String value) {
            addCriterion("hosted_by_category not like", value, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryIn(List<String> values) {
            addCriterion("hosted_by_category in", values, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryNotIn(List<String> values) {
            addCriterion("hosted_by_category not in", values, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryBetween(String value1, String value2) {
            addCriterion("hosted_by_category between", value1, value2, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedByCategoryNotBetween(String value1, String value2) {
            addCriterion("hosted_by_category not between", value1, value2, "hostedByCategory");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementIsNull() {
            addCriterion("hosted_by_supplement is null");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementIsNotNull() {
            addCriterion("hosted_by_supplement is not null");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementEqualTo(String value) {
            addCriterion("hosted_by_supplement =", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementNotEqualTo(String value) {
            addCriterion("hosted_by_supplement <>", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementGreaterThan(String value) {
            addCriterion("hosted_by_supplement >", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementGreaterThanOrEqualTo(String value) {
            addCriterion("hosted_by_supplement >=", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementLessThan(String value) {
            addCriterion("hosted_by_supplement <", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementLessThanOrEqualTo(String value) {
            addCriterion("hosted_by_supplement <=", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementLike(String value) {
            addCriterion("hosted_by_supplement like", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementNotLike(String value) {
            addCriterion("hosted_by_supplement not like", value, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementIn(List<String> values) {
            addCriterion("hosted_by_supplement in", values, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementNotIn(List<String> values) {
            addCriterion("hosted_by_supplement not in", values, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementBetween(String value1, String value2) {
            addCriterion("hosted_by_supplement between", value1, value2, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andHostedBySupplementNotBetween(String value1, String value2) {
            addCriterion("hosted_by_supplement not between", value1, value2, "hostedBySupplement");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentIsNull() {
            addCriterion("undertaking_department is null");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentIsNotNull() {
            addCriterion("undertaking_department is not null");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentEqualTo(String value) {
            addCriterion("undertaking_department =", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentNotEqualTo(String value) {
            addCriterion("undertaking_department <>", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentGreaterThan(String value) {
            addCriterion("undertaking_department >", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("undertaking_department >=", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentLessThan(String value) {
            addCriterion("undertaking_department <", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentLessThanOrEqualTo(String value) {
            addCriterion("undertaking_department <=", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentLike(String value) {
            addCriterion("undertaking_department like", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentNotLike(String value) {
            addCriterion("undertaking_department not like", value, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentIn(List<String> values) {
            addCriterion("undertaking_department in", values, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentNotIn(List<String> values) {
            addCriterion("undertaking_department not in", values, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentBetween(String value1, String value2) {
            addCriterion("undertaking_department between", value1, value2, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andUndertakingDepartmentNotBetween(String value1, String value2) {
            addCriterion("undertaking_department not between", value1, value2, "undertakingDepartment");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodIsNull() {
            addCriterion("assessment_method is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodIsNotNull() {
            addCriterion("assessment_method is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodEqualTo(String value) {
            addCriterion("assessment_method =", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodNotEqualTo(String value) {
            addCriterion("assessment_method <>", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodGreaterThan(String value) {
            addCriterion("assessment_method >", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("assessment_method >=", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodLessThan(String value) {
            addCriterion("assessment_method <", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodLessThanOrEqualTo(String value) {
            addCriterion("assessment_method <=", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodLike(String value) {
            addCriterion("assessment_method like", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodNotLike(String value) {
            addCriterion("assessment_method not like", value, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodIn(List<String> values) {
            addCriterion("assessment_method in", values, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodNotIn(List<String> values) {
            addCriterion("assessment_method not in", values, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodBetween(String value1, String value2) {
            addCriterion("assessment_method between", value1, value2, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andAssessmentMethodNotBetween(String value1, String value2) {
            addCriterion("assessment_method not between", value1, value2, "assessmentMethod");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceIsNull() {
            addCriterion("training_place is null");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceIsNotNull() {
            addCriterion("training_place is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceEqualTo(String value) {
            addCriterion("training_place =", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceNotEqualTo(String value) {
            addCriterion("training_place <>", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceGreaterThan(String value) {
            addCriterion("training_place >", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("training_place >=", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceLessThan(String value) {
            addCriterion("training_place <", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceLessThanOrEqualTo(String value) {
            addCriterion("training_place <=", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceLike(String value) {
            addCriterion("training_place like", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceNotLike(String value) {
            addCriterion("training_place not like", value, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceIn(List<String> values) {
            addCriterion("training_place in", values, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceNotIn(List<String> values) {
            addCriterion("training_place not in", values, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceBetween(String value1, String value2) {
            addCriterion("training_place between", value1, value2, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andTrainingPlaceNotBetween(String value1, String value2) {
            addCriterion("training_place not between", value1, value2, "trainingPlace");
            return (Criteria) this;
        }

        public Criteria andHostIsNull() {
            addCriterion("host is null");
            return (Criteria) this;
        }

        public Criteria andHostIsNotNull() {
            addCriterion("host is not null");
            return (Criteria) this;
        }

        public Criteria andHostEqualTo(String value) {
            addCriterion("host =", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotEqualTo(String value) {
            addCriterion("host <>", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThan(String value) {
            addCriterion("host >", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThanOrEqualTo(String value) {
            addCriterion("host >=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThan(String value) {
            addCriterion("host <", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThanOrEqualTo(String value) {
            addCriterion("host <=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLike(String value) {
            addCriterion("host like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotLike(String value) {
            addCriterion("host not like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostIn(List<String> values) {
            addCriterion("host in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotIn(List<String> values) {
            addCriterion("host not in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostBetween(String value1, String value2) {
            addCriterion("host between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotBetween(String value1, String value2) {
            addCriterion("host not between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursIsNull() {
            addCriterion("training_hours is null");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursIsNotNull() {
            addCriterion("training_hours is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursEqualTo(String value) {
            addCriterion("training_hours =", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursNotEqualTo(String value) {
            addCriterion("training_hours <>", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursGreaterThan(String value) {
            addCriterion("training_hours >", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursGreaterThanOrEqualTo(String value) {
            addCriterion("training_hours >=", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursLessThan(String value) {
            addCriterion("training_hours <", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursLessThanOrEqualTo(String value) {
            addCriterion("training_hours <=", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursLike(String value) {
            addCriterion("training_hours like", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursNotLike(String value) {
            addCriterion("training_hours not like", value, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursIn(List<String> values) {
            addCriterion("training_hours in", values, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursNotIn(List<String> values) {
            addCriterion("training_hours not in", values, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursBetween(String value1, String value2) {
            addCriterion("training_hours between", value1, value2, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingHoursNotBetween(String value1, String value2) {
            addCriterion("training_hours not between", value1, value2, "trainingHours");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysIsNull() {
            addCriterion("training_days is null");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysIsNotNull() {
            addCriterion("training_days is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysEqualTo(String value) {
            addCriterion("training_days =", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysNotEqualTo(String value) {
            addCriterion("training_days <>", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysGreaterThan(String value) {
            addCriterion("training_days >", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysGreaterThanOrEqualTo(String value) {
            addCriterion("training_days >=", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysLessThan(String value) {
            addCriterion("training_days <", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysLessThanOrEqualTo(String value) {
            addCriterion("training_days <=", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysLike(String value) {
            addCriterion("training_days like", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysNotLike(String value) {
            addCriterion("training_days not like", value, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysIn(List<String> values) {
            addCriterion("training_days in", values, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysNotIn(List<String> values) {
            addCriterion("training_days not in", values, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysBetween(String value1, String value2) {
            addCriterion("training_days between", value1, value2, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingDaysNotBetween(String value1, String value2) {
            addCriterion("training_days not between", value1, value2, "trainingDays");
            return (Criteria) this;
        }

        public Criteria andTrainingContentIsNull() {
            addCriterion("training_content is null");
            return (Criteria) this;
        }

        public Criteria andTrainingContentIsNotNull() {
            addCriterion("training_content is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingContentEqualTo(String value) {
            addCriterion("training_content =", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentNotEqualTo(String value) {
            addCriterion("training_content <>", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentGreaterThan(String value) {
            addCriterion("training_content >", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentGreaterThanOrEqualTo(String value) {
            addCriterion("training_content >=", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentLessThan(String value) {
            addCriterion("training_content <", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentLessThanOrEqualTo(String value) {
            addCriterion("training_content <=", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentLike(String value) {
            addCriterion("training_content like", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentNotLike(String value) {
            addCriterion("training_content not like", value, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentIn(List<String> values) {
            addCriterion("training_content in", values, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentNotIn(List<String> values) {
            addCriterion("training_content not in", values, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentBetween(String value1, String value2) {
            addCriterion("training_content between", value1, value2, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andTrainingContentNotBetween(String value1, String value2) {
            addCriterion("training_content not between", value1, value2, "trainingContent");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingIsNull() {
            addCriterion("is_registration_and_warehousing is null");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingIsNotNull() {
            addCriterion("is_registration_and_warehousing is not null");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingEqualTo(String value) {
            addCriterion("is_registration_and_warehousing =", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingNotEqualTo(String value) {
            addCriterion("is_registration_and_warehousing <>", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingGreaterThan(String value) {
            addCriterion("is_registration_and_warehousing >", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingGreaterThanOrEqualTo(String value) {
            addCriterion("is_registration_and_warehousing >=", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingLessThan(String value) {
            addCriterion("is_registration_and_warehousing <", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingLessThanOrEqualTo(String value) {
            addCriterion("is_registration_and_warehousing <=", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingLike(String value) {
            addCriterion("is_registration_and_warehousing like", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingNotLike(String value) {
            addCriterion("is_registration_and_warehousing not like", value, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingIn(List<String> values) {
            addCriterion("is_registration_and_warehousing in", values, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingNotIn(List<String> values) {
            addCriterion("is_registration_and_warehousing not in", values, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingBetween(String value1, String value2) {
            addCriterion("is_registration_and_warehousing between", value1, value2, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andIsRegistrationAndWarehousingNotBetween(String value1, String value2) {
            addCriterion("is_registration_and_warehousing not between", value1, value2, "isRegistrationAndWarehousing");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeIsNull() {
            addCriterion("registration_and_warehousing_time is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeIsNotNull() {
            addCriterion("registration_and_warehousing_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeEqualTo(Date value) {
            addCriterionForJDBCDate("registration_and_warehousing_time =", value, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("registration_and_warehousing_time <>", value, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("registration_and_warehousing_time >", value, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registration_and_warehousing_time >=", value, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeLessThan(Date value) {
            addCriterionForJDBCDate("registration_and_warehousing_time <", value, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registration_and_warehousing_time <=", value, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeIn(List<Date> values) {
            addCriterionForJDBCDate("registration_and_warehousing_time in", values, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("registration_and_warehousing_time not in", values, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registration_and_warehousing_time between", value1, value2, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationAndWarehousingTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registration_and_warehousing_time not between", value1, value2, "registrationAndWarehousingTime");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementIsNull() {
            addCriterion("training_content_supplement is null");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementIsNotNull() {
            addCriterion("training_content_supplement is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementEqualTo(String value) {
            addCriterion("training_content_supplement =", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementNotEqualTo(String value) {
            addCriterion("training_content_supplement <>", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementGreaterThan(String value) {
            addCriterion("training_content_supplement >", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementGreaterThanOrEqualTo(String value) {
            addCriterion("training_content_supplement >=", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementLessThan(String value) {
            addCriterion("training_content_supplement <", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementLessThanOrEqualTo(String value) {
            addCriterion("training_content_supplement <=", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementLike(String value) {
            addCriterion("training_content_supplement like", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementNotLike(String value) {
            addCriterion("training_content_supplement not like", value, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementIn(List<String> values) {
            addCriterion("training_content_supplement in", values, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementNotIn(List<String> values) {
            addCriterion("training_content_supplement not in", values, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementBetween(String value1, String value2) {
            addCriterion("training_content_supplement between", value1, value2, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andTrainingContentSupplementNotBetween(String value1, String value2) {
            addCriterion("training_content_supplement not between", value1, value2, "trainingContentSupplement");
            return (Criteria) this;
        }

        public Criteria andLeavingStateIsNull() {
            addCriterion("leaving_state is null");
            return (Criteria) this;
        }

        public Criteria andLeavingStateIsNotNull() {
            addCriterion("leaving_state is not null");
            return (Criteria) this;
        }

        public Criteria andLeavingStateEqualTo(String value) {
            addCriterion("leaving_state =", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateNotEqualTo(String value) {
            addCriterion("leaving_state <>", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateGreaterThan(String value) {
            addCriterion("leaving_state >", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateGreaterThanOrEqualTo(String value) {
            addCriterion("leaving_state >=", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateLessThan(String value) {
            addCriterion("leaving_state <", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateLessThanOrEqualTo(String value) {
            addCriterion("leaving_state <=", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateLike(String value) {
            addCriterion("leaving_state like", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateNotLike(String value) {
            addCriterion("leaving_state not like", value, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateIn(List<String> values) {
            addCriterion("leaving_state in", values, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateNotIn(List<String> values) {
            addCriterion("leaving_state not in", values, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateBetween(String value1, String value2) {
            addCriterion("leaving_state between", value1, value2, "leavingState");
            return (Criteria) this;
        }

        public Criteria andLeavingStateNotBetween(String value1, String value2) {
            addCriterion("leaving_state not between", value1, value2, "leavingState");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
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
            addCriterionForJDBCDate("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIsNull() {
            addCriterion("delete_user is null");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIsNotNull() {
            addCriterion("delete_user is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteUserEqualTo(String value) {
            addCriterion("delete_user =", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserNotEqualTo(String value) {
            addCriterion("delete_user <>", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserGreaterThan(String value) {
            addCriterion("delete_user >", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserGreaterThanOrEqualTo(String value) {
            addCriterion("delete_user >=", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserLessThan(String value) {
            addCriterion("delete_user <", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserLessThanOrEqualTo(String value) {
            addCriterion("delete_user <=", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserLike(String value) {
            addCriterion("delete_user like", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserNotLike(String value) {
            addCriterion("delete_user not like", value, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIn(List<String> values) {
            addCriterion("delete_user in", values, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserNotIn(List<String> values) {
            addCriterion("delete_user not in", values, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserBetween(String value1, String value2) {
            addCriterion("delete_user between", value1, value2, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteUserNotBetween(String value1, String value2) {
            addCriterion("delete_user not between", value1, value2, "deleteUser");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(String value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(String value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(String value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(String value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(String value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(String value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLike(String value) {
            addCriterion("delete_flag like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotLike(String value) {
            addCriterion("delete_flag not like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<String> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<String> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(String value1, String value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(String value1, String value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDataLevelIsNull() {
            addCriterion("data_level is null");
            return (Criteria) this;
        }

        public Criteria andDataLevelIsNotNull() {
            addCriterion("data_level is not null");
            return (Criteria) this;
        }

        public Criteria andDataLevelEqualTo(BigDecimal value) {
            addCriterion("data_level =", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotEqualTo(BigDecimal value) {
            addCriterion("data_level <>", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelGreaterThan(BigDecimal value) {
            addCriterion("data_level >", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("data_level >=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelLessThan(BigDecimal value) {
            addCriterion("data_level <", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelLessThanOrEqualTo(BigDecimal value) {
            addCriterion("data_level <=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelIn(List<BigDecimal> values) {
            addCriterion("data_level in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotIn(List<BigDecimal> values) {
            addCriterion("data_level not in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("data_level between", value1, value2, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("data_level not between", value1, value2, "dataLevel");
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