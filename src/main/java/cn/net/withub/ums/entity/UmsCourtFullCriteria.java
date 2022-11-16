package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsCourtFullCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsCourtFullCriteria() {
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

        public Criteria andCourtStdNameIsNull() {
            addCriterion("court_std_name is null");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameIsNotNull() {
            addCriterion("court_std_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameEqualTo(String value) {
            addCriterion("court_std_name =", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameNotEqualTo(String value) {
            addCriterion("court_std_name <>", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameGreaterThan(String value) {
            addCriterion("court_std_name >", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameGreaterThanOrEqualTo(String value) {
            addCriterion("court_std_name >=", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameLessThan(String value) {
            addCriterion("court_std_name <", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameLessThanOrEqualTo(String value) {
            addCriterion("court_std_name <=", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameLike(String value) {
            addCriterion("court_std_name like", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameNotLike(String value) {
            addCriterion("court_std_name not like", value, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameIn(List<String> values) {
            addCriterion("court_std_name in", values, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameNotIn(List<String> values) {
            addCriterion("court_std_name not in", values, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameBetween(String value1, String value2) {
            addCriterion("court_std_name between", value1, value2, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtStdNameNotBetween(String value1, String value2) {
            addCriterion("court_std_name not between", value1, value2, "courtStdName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameIsNull() {
            addCriterion("court_short_name is null");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameIsNotNull() {
            addCriterion("court_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameEqualTo(String value) {
            addCriterion("court_short_name =", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotEqualTo(String value) {
            addCriterion("court_short_name <>", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameGreaterThan(String value) {
            addCriterion("court_short_name >", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("court_short_name >=", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameLessThan(String value) {
            addCriterion("court_short_name <", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameLessThanOrEqualTo(String value) {
            addCriterion("court_short_name <=", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameLike(String value) {
            addCriterion("court_short_name like", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotLike(String value) {
            addCriterion("court_short_name not like", value, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameIn(List<String> values) {
            addCriterion("court_short_name in", values, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotIn(List<String> values) {
            addCriterion("court_short_name not in", values, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameBetween(String value1, String value2) {
            addCriterion("court_short_name between", value1, value2, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andCourtShortNameNotBetween(String value1, String value2) {
            addCriterion("court_short_name not between", value1, value2, "courtShortName");
            return (Criteria) this;
        }

        public Criteria andAreaMarkIsNull() {
            addCriterion("area_mark is null");
            return (Criteria) this;
        }

        public Criteria andAreaMarkIsNotNull() {
            addCriterion("area_mark is not null");
            return (Criteria) this;
        }

        public Criteria andAreaMarkEqualTo(Integer value) {
            addCriterion("area_mark =", value, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkNotEqualTo(Integer value) {
            addCriterion("area_mark <>", value, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkGreaterThan(Integer value) {
            addCriterion("area_mark >", value, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_mark >=", value, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkLessThan(Integer value) {
            addCriterion("area_mark <", value, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkLessThanOrEqualTo(Integer value) {
            addCriterion("area_mark <=", value, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkIn(List<Integer> values) {
            addCriterion("area_mark in", values, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkNotIn(List<Integer> values) {
            addCriterion("area_mark not in", values, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkBetween(Integer value1, Integer value2) {
            addCriterion("area_mark between", value1, value2, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaMarkNotBetween(Integer value1, Integer value2) {
            addCriterion("area_mark not between", value1, value2, "areaMark");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaIsNull() {
            addCriterion("basic_area is null");
            return (Criteria) this;
        }

        public Criteria andBasicAreaIsNotNull() {
            addCriterion("basic_area is not null");
            return (Criteria) this;
        }

        public Criteria andBasicAreaEqualTo(Integer value) {
            addCriterion("basic_area =", value, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNotEqualTo(Integer value) {
            addCriterion("basic_area <>", value, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaGreaterThan(Integer value) {
            addCriterion("basic_area >", value, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("basic_area >=", value, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaLessThan(Integer value) {
            addCriterion("basic_area <", value, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaLessThanOrEqualTo(Integer value) {
            addCriterion("basic_area <=", value, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaIn(List<Integer> values) {
            addCriterion("basic_area in", values, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNotIn(List<Integer> values) {
            addCriterion("basic_area not in", values, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaBetween(Integer value1, Integer value2) {
            addCriterion("basic_area between", value1, value2, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("basic_area not between", value1, value2, "basicArea");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameIsNull() {
            addCriterion("basic_area_name is null");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameIsNotNull() {
            addCriterion("basic_area_name is not null");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameEqualTo(String value) {
            addCriterion("basic_area_name =", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameNotEqualTo(String value) {
            addCriterion("basic_area_name <>", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameGreaterThan(String value) {
            addCriterion("basic_area_name >", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("basic_area_name >=", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameLessThan(String value) {
            addCriterion("basic_area_name <", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameLessThanOrEqualTo(String value) {
            addCriterion("basic_area_name <=", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameLike(String value) {
            addCriterion("basic_area_name like", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameNotLike(String value) {
            addCriterion("basic_area_name not like", value, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameIn(List<String> values) {
            addCriterion("basic_area_name in", values, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameNotIn(List<String> values) {
            addCriterion("basic_area_name not in", values, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameBetween(String value1, String value2) {
            addCriterion("basic_area_name between", value1, value2, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andBasicAreaNameNotBetween(String value1, String value2) {
            addCriterion("basic_area_name not between", value1, value2, "basicAreaName");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationIsNull() {
            addCriterion("area_preparation is null");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationIsNotNull() {
            addCriterion("area_preparation is not null");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationEqualTo(Integer value) {
            addCriterion("area_preparation =", value, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationNotEqualTo(Integer value) {
            addCriterion("area_preparation <>", value, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationGreaterThan(Integer value) {
            addCriterion("area_preparation >", value, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_preparation >=", value, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationLessThan(Integer value) {
            addCriterion("area_preparation <", value, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationLessThanOrEqualTo(Integer value) {
            addCriterion("area_preparation <=", value, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationIn(List<Integer> values) {
            addCriterion("area_preparation in", values, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationNotIn(List<Integer> values) {
            addCriterion("area_preparation not in", values, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationBetween(Integer value1, Integer value2) {
            addCriterion("area_preparation between", value1, value2, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andAreaPreparationNotBetween(Integer value1, Integer value2) {
            addCriterion("area_preparation not between", value1, value2, "areaPreparation");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoIsNull() {
            addCriterion("court_area_no is null");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoIsNotNull() {
            addCriterion("court_area_no is not null");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoEqualTo(Integer value) {
            addCriterion("court_area_no =", value, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoNotEqualTo(Integer value) {
            addCriterion("court_area_no <>", value, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoGreaterThan(Integer value) {
            addCriterion("court_area_no >", value, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_area_no >=", value, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoLessThan(Integer value) {
            addCriterion("court_area_no <", value, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoLessThanOrEqualTo(Integer value) {
            addCriterion("court_area_no <=", value, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoIn(List<Integer> values) {
            addCriterion("court_area_no in", values, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoNotIn(List<Integer> values) {
            addCriterion("court_area_no not in", values, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoBetween(Integer value1, Integer value2) {
            addCriterion("court_area_no between", value1, value2, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNoNotBetween(Integer value1, Integer value2) {
            addCriterion("court_area_no not between", value1, value2, "courtAreaNo");
            return (Criteria) this;
        }

        public Criteria andCourtGradationIsNull() {
            addCriterion("court_gradation is null");
            return (Criteria) this;
        }

        public Criteria andCourtGradationIsNotNull() {
            addCriterion("court_gradation is not null");
            return (Criteria) this;
        }

        public Criteria andCourtGradationEqualTo(Integer value) {
            addCriterion("court_gradation =", value, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationNotEqualTo(Integer value) {
            addCriterion("court_gradation <>", value, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationGreaterThan(Integer value) {
            addCriterion("court_gradation >", value, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_gradation >=", value, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationLessThan(Integer value) {
            addCriterion("court_gradation <", value, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationLessThanOrEqualTo(Integer value) {
            addCriterion("court_gradation <=", value, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationIn(List<Integer> values) {
            addCriterion("court_gradation in", values, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationNotIn(List<Integer> values) {
            addCriterion("court_gradation not in", values, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationBetween(Integer value1, Integer value2) {
            addCriterion("court_gradation between", value1, value2, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtGradationNotBetween(Integer value1, Integer value2) {
            addCriterion("court_gradation not between", value1, value2, "courtGradation");
            return (Criteria) this;
        }

        public Criteria andCourtLevelIsNull() {
            addCriterion("court_level is null");
            return (Criteria) this;
        }

        public Criteria andCourtLevelIsNotNull() {
            addCriterion("court_level is not null");
            return (Criteria) this;
        }

        public Criteria andCourtLevelEqualTo(Integer value) {
            addCriterion("court_level =", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelNotEqualTo(Integer value) {
            addCriterion("court_level <>", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelGreaterThan(Integer value) {
            addCriterion("court_level >", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_level >=", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelLessThan(Integer value) {
            addCriterion("court_level <", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelLessThanOrEqualTo(Integer value) {
            addCriterion("court_level <=", value, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelIn(List<Integer> values) {
            addCriterion("court_level in", values, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelNotIn(List<Integer> values) {
            addCriterion("court_level not in", values, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelBetween(Integer value1, Integer value2) {
            addCriterion("court_level between", value1, value2, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andCourtLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("court_level not between", value1, value2, "courtLevel");
            return (Criteria) this;
        }

        public Criteria andLastPushIsNull() {
            addCriterion("last_push is null");
            return (Criteria) this;
        }

        public Criteria andLastPushIsNotNull() {
            addCriterion("last_push is not null");
            return (Criteria) this;
        }

        public Criteria andLastPushEqualTo(Date value) {
            addCriterion("last_push =", value, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushNotEqualTo(Date value) {
            addCriterion("last_push <>", value, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushGreaterThan(Date value) {
            addCriterion("last_push >", value, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushGreaterThanOrEqualTo(Date value) {
            addCriterion("last_push >=", value, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushLessThan(Date value) {
            addCriterion("last_push <", value, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushLessThanOrEqualTo(Date value) {
            addCriterion("last_push <=", value, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushIn(List<Date> values) {
            addCriterion("last_push in", values, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushNotIn(List<Date> values) {
            addCriterion("last_push not in", values, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushBetween(Date value1, Date value2) {
            addCriterion("last_push between", value1, value2, "lastPush");
            return (Criteria) this;
        }

        public Criteria andLastPushNotBetween(Date value1, Date value2) {
            addCriterion("last_push not between", value1, value2, "lastPush");
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

        public Criteria andCourtAreaIsNull() {
            addCriterion("court_area is null");
            return (Criteria) this;
        }

        public Criteria andCourtAreaIsNotNull() {
            addCriterion("court_area is not null");
            return (Criteria) this;
        }

        public Criteria andCourtAreaEqualTo(Integer value) {
            addCriterion("court_area =", value, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNotEqualTo(Integer value) {
            addCriterion("court_area <>", value, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaGreaterThan(Integer value) {
            addCriterion("court_area >", value, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_area >=", value, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaLessThan(Integer value) {
            addCriterion("court_area <", value, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaLessThanOrEqualTo(Integer value) {
            addCriterion("court_area <=", value, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaIn(List<Integer> values) {
            addCriterion("court_area in", values, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNotIn(List<Integer> values) {
            addCriterion("court_area not in", values, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaBetween(Integer value1, Integer value2) {
            addCriterion("court_area between", value1, value2, "courtArea");
            return (Criteria) this;
        }

        public Criteria andCourtAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("court_area not between", value1, value2, "courtArea");
            return (Criteria) this;
        }

        public Criteria andAreaTypeIsNull() {
            addCriterion("area_type is null");
            return (Criteria) this;
        }

        public Criteria andAreaTypeIsNotNull() {
            addCriterion("area_type is not null");
            return (Criteria) this;
        }

        public Criteria andAreaTypeEqualTo(Integer value) {
            addCriterion("area_type =", value, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeNotEqualTo(Integer value) {
            addCriterion("area_type <>", value, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeGreaterThan(Integer value) {
            addCriterion("area_type >", value, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_type >=", value, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeLessThan(Integer value) {
            addCriterion("area_type <", value, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeLessThanOrEqualTo(Integer value) {
            addCriterion("area_type <=", value, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeIn(List<Integer> values) {
            addCriterion("area_type in", values, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeNotIn(List<Integer> values) {
            addCriterion("area_type not in", values, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeBetween(Integer value1, Integer value2) {
            addCriterion("area_type between", value1, value2, "areaType");
            return (Criteria) this;
        }

        public Criteria andAreaTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("area_type not between", value1, value2, "areaType");
            return (Criteria) this;
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

        public Criteria andInstitutionCodeIsNull() {
            addCriterion("INSTITUTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeIsNotNull() {
            addCriterion("INSTITUTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeEqualTo(String value) {
            addCriterion("INSTITUTION_CODE =", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotEqualTo(String value) {
            addCriterion("INSTITUTION_CODE <>", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeGreaterThan(String value) {
            addCriterion("INSTITUTION_CODE >", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("INSTITUTION_CODE >=", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeLessThan(String value) {
            addCriterion("INSTITUTION_CODE <", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeLessThanOrEqualTo(String value) {
            addCriterion("INSTITUTION_CODE <=", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeLike(String value) {
            addCriterion("INSTITUTION_CODE like", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotLike(String value) {
            addCriterion("INSTITUTION_CODE not like", value, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeIn(List<String> values) {
            addCriterion("INSTITUTION_CODE in", values, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotIn(List<String> values) {
            addCriterion("INSTITUTION_CODE not in", values, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeBetween(String value1, String value2) {
            addCriterion("INSTITUTION_CODE between", value1, value2, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andInstitutionCodeNotBetween(String value1, String value2) {
            addCriterion("INSTITUTION_CODE not between", value1, value2, "institutionCode");
            return (Criteria) this;
        }

        public Criteria andCourtTypeIsNull() {
            addCriterion("COURT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCourtTypeIsNotNull() {
            addCriterion("COURT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCourtTypeEqualTo(String value) {
            addCriterion("COURT_TYPE =", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeNotEqualTo(String value) {
            addCriterion("COURT_TYPE <>", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeGreaterThan(String value) {
            addCriterion("COURT_TYPE >", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COURT_TYPE >=", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeLessThan(String value) {
            addCriterion("COURT_TYPE <", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeLessThanOrEqualTo(String value) {
            addCriterion("COURT_TYPE <=", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeLike(String value) {
            addCriterion("COURT_TYPE like", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeNotLike(String value) {
            addCriterion("COURT_TYPE not like", value, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeIn(List<String> values) {
            addCriterion("COURT_TYPE in", values, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeNotIn(List<String> values) {
            addCriterion("COURT_TYPE not in", values, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeBetween(String value1, String value2) {
            addCriterion("COURT_TYPE between", value1, value2, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtTypeNotBetween(String value1, String value2) {
            addCriterion("COURT_TYPE not between", value1, value2, "courtType");
            return (Criteria) this;
        }

        public Criteria andCourtGradeIsNull() {
            addCriterion("COURT_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andCourtGradeIsNotNull() {
            addCriterion("COURT_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andCourtGradeEqualTo(String value) {
            addCriterion("COURT_GRADE =", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeNotEqualTo(String value) {
            addCriterion("COURT_GRADE <>", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeGreaterThan(String value) {
            addCriterion("COURT_GRADE >", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeGreaterThanOrEqualTo(String value) {
            addCriterion("COURT_GRADE >=", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeLessThan(String value) {
            addCriterion("COURT_GRADE <", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeLessThanOrEqualTo(String value) {
            addCriterion("COURT_GRADE <=", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeLike(String value) {
            addCriterion("COURT_GRADE like", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeNotLike(String value) {
            addCriterion("COURT_GRADE not like", value, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeIn(List<String> values) {
            addCriterion("COURT_GRADE in", values, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeNotIn(List<String> values) {
            addCriterion("COURT_GRADE not in", values, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeBetween(String value1, String value2) {
            addCriterion("COURT_GRADE between", value1, value2, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andCourtGradeNotBetween(String value1, String value2) {
            addCriterion("COURT_GRADE not between", value1, value2, "courtGrade");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNull() {
            addCriterion("header is null");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNotNull() {
            addCriterion("header is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderEqualTo(String value) {
            addCriterion("header =", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotEqualTo(String value) {
            addCriterion("header <>", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThan(String value) {
            addCriterion("header >", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThanOrEqualTo(String value) {
            addCriterion("header >=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThan(String value) {
            addCriterion("header <", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThanOrEqualTo(String value) {
            addCriterion("header <=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLike(String value) {
            addCriterion("header like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotLike(String value) {
            addCriterion("header not like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderIn(List<String> values) {
            addCriterion("header in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotIn(List<String> values) {
            addCriterion("header not in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderBetween(String value1, String value2) {
            addCriterion("header between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotBetween(String value1, String value2) {
            addCriterion("header not between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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