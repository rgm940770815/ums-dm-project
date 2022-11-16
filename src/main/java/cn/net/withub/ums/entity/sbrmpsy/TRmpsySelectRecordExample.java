package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TRmpsySelectRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsySelectRecordExample() {
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

        public Criteria andCaseNumberIsNull() {
            addCriterion("case_number is null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIsNotNull() {
            addCriterion("case_number is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNumberEqualTo(String value) {
            addCriterion("case_number =", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotEqualTo(String value) {
            addCriterion("case_number <>", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThan(String value) {
            addCriterion("case_number >", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("case_number >=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThan(String value) {
            addCriterion("case_number <", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLessThanOrEqualTo(String value) {
            addCriterion("case_number <=", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberLike(String value) {
            addCriterion("case_number like", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotLike(String value) {
            addCriterion("case_number not like", value, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberIn(List<String> values) {
            addCriterion("case_number in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotIn(List<String> values) {
            addCriterion("case_number not in", values, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberBetween(String value1, String value2) {
            addCriterion("case_number between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNumberNotBetween(String value1, String value2) {
            addCriterion("case_number not between", value1, value2, "caseNumber");
            return (Criteria) this;
        }

        public Criteria andCaseNameIsNull() {
            addCriterion("case_name is null");
            return (Criteria) this;
        }

        public Criteria andCaseNameIsNotNull() {
            addCriterion("case_name is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNameEqualTo(String value) {
            addCriterion("case_name =", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotEqualTo(String value) {
            addCriterion("case_name <>", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameGreaterThan(String value) {
            addCriterion("case_name >", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("case_name >=", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameLessThan(String value) {
            addCriterion("case_name <", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameLessThanOrEqualTo(String value) {
            addCriterion("case_name <=", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameLike(String value) {
            addCriterion("case_name like", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotLike(String value) {
            addCriterion("case_name not like", value, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameIn(List<String> values) {
            addCriterion("case_name in", values, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotIn(List<String> values) {
            addCriterion("case_name not in", values, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameBetween(String value1, String value2) {
            addCriterion("case_name between", value1, value2, "caseName");
            return (Criteria) this;
        }

        public Criteria andCaseNameNotBetween(String value1, String value2) {
            addCriterion("case_name not between", value1, value2, "caseName");
            return (Criteria) this;
        }

        public Criteria andCourtNameIsNull() {
            addCriterion("court_name is null");
            return (Criteria) this;
        }

        public Criteria andCourtNameIsNotNull() {
            addCriterion("court_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourtNameEqualTo(String value) {
            addCriterion("court_name =", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameNotEqualTo(String value) {
            addCriterion("court_name <>", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameGreaterThan(String value) {
            addCriterion("court_name >", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameGreaterThanOrEqualTo(String value) {
            addCriterion("court_name >=", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameLessThan(String value) {
            addCriterion("court_name <", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameLessThanOrEqualTo(String value) {
            addCriterion("court_name <=", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameLike(String value) {
            addCriterion("court_name like", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameNotLike(String value) {
            addCriterion("court_name not like", value, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameIn(List<String> values) {
            addCriterion("court_name in", values, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameNotIn(List<String> values) {
            addCriterion("court_name not in", values, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameBetween(String value1, String value2) {
            addCriterion("court_name between", value1, value2, "courtName");
            return (Criteria) this;
        }

        public Criteria andCourtNameNotBetween(String value1, String value2) {
            addCriterion("court_name not between", value1, value2, "courtName");
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

        public Criteria andContractorIdIsNull() {
            addCriterion("contractor_id is null");
            return (Criteria) this;
        }

        public Criteria andContractorIdIsNotNull() {
            addCriterion("contractor_id is not null");
            return (Criteria) this;
        }

        public Criteria andContractorIdEqualTo(String value) {
            addCriterion("contractor_id =", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdNotEqualTo(String value) {
            addCriterion("contractor_id <>", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdGreaterThan(String value) {
            addCriterion("contractor_id >", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdGreaterThanOrEqualTo(String value) {
            addCriterion("contractor_id >=", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdLessThan(String value) {
            addCriterion("contractor_id <", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdLessThanOrEqualTo(String value) {
            addCriterion("contractor_id <=", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdLike(String value) {
            addCriterion("contractor_id like", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdNotLike(String value) {
            addCriterion("contractor_id not like", value, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdIn(List<String> values) {
            addCriterion("contractor_id in", values, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdNotIn(List<String> values) {
            addCriterion("contractor_id not in", values, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdBetween(String value1, String value2) {
            addCriterion("contractor_id between", value1, value2, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorIdNotBetween(String value1, String value2) {
            addCriterion("contractor_id not between", value1, value2, "contractorId");
            return (Criteria) this;
        }

        public Criteria andContractorNameIsNull() {
            addCriterion("contractor_name is null");
            return (Criteria) this;
        }

        public Criteria andContractorNameIsNotNull() {
            addCriterion("contractor_name is not null");
            return (Criteria) this;
        }

        public Criteria andContractorNameEqualTo(String value) {
            addCriterion("contractor_name =", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameNotEqualTo(String value) {
            addCriterion("contractor_name <>", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameGreaterThan(String value) {
            addCriterion("contractor_name >", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameGreaterThanOrEqualTo(String value) {
            addCriterion("contractor_name >=", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameLessThan(String value) {
            addCriterion("contractor_name <", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameLessThanOrEqualTo(String value) {
            addCriterion("contractor_name <=", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameLike(String value) {
            addCriterion("contractor_name like", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameNotLike(String value) {
            addCriterion("contractor_name not like", value, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameIn(List<String> values) {
            addCriterion("contractor_name in", values, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameNotIn(List<String> values) {
            addCriterion("contractor_name not in", values, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameBetween(String value1, String value2) {
            addCriterion("contractor_name between", value1, value2, "contractorName");
            return (Criteria) this;
        }

        public Criteria andContractorNameNotBetween(String value1, String value2) {
            addCriterion("contractor_name not between", value1, value2, "contractorName");
            return (Criteria) this;
        }

        public Criteria andTrialTimeIsNull() {
            addCriterion("trial_time is null");
            return (Criteria) this;
        }

        public Criteria andTrialTimeIsNotNull() {
            addCriterion("trial_time is not null");
            return (Criteria) this;
        }

        public Criteria andTrialTimeEqualTo(Date value) {
            addCriterion("trial_time =", value, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeNotEqualTo(Date value) {
            addCriterion("trial_time <>", value, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeGreaterThan(Date value) {
            addCriterion("trial_time >", value, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trial_time >=", value, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeLessThan(Date value) {
            addCriterion("trial_time <", value, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeLessThanOrEqualTo(Date value) {
            addCriterion("trial_time <=", value, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeIn(List<Date> values) {
            addCriterion("trial_time in", values, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeNotIn(List<Date> values) {
            addCriterion("trial_time not in", values, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeBetween(Date value1, Date value2) {
            addCriterion("trial_time between", value1, value2, "trialTime");
            return (Criteria) this;
        }

        public Criteria andTrialTimeNotBetween(Date value1, Date value2) {
            addCriterion("trial_time not between", value1, value2, "trialTime");
            return (Criteria) this;
        }

        public Criteria andSelectModeIsNull() {
            addCriterion("select_mode is null");
            return (Criteria) this;
        }

        public Criteria andSelectModeIsNotNull() {
            addCriterion("select_mode is not null");
            return (Criteria) this;
        }

        public Criteria andSelectModeEqualTo(Integer value) {
            addCriterion("select_mode =", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeNotEqualTo(Integer value) {
            addCriterion("select_mode <>", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeGreaterThan(Integer value) {
            addCriterion("select_mode >", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("select_mode >=", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeLessThan(Integer value) {
            addCriterion("select_mode <", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeLessThanOrEqualTo(Integer value) {
            addCriterion("select_mode <=", value, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeIn(List<Integer> values) {
            addCriterion("select_mode in", values, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeNotIn(List<Integer> values) {
            addCriterion("select_mode not in", values, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeBetween(Integer value1, Integer value2) {
            addCriterion("select_mode between", value1, value2, "selectMode");
            return (Criteria) this;
        }

        public Criteria andSelectModeNotBetween(Integer value1, Integer value2) {
            addCriterion("select_mode not between", value1, value2, "selectMode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameIsNull() {
            addCriterion("trial_place_name is null");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameIsNotNull() {
            addCriterion("trial_place_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameEqualTo(String value) {
            addCriterion("trial_place_name =", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameNotEqualTo(String value) {
            addCriterion("trial_place_name <>", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameGreaterThan(String value) {
            addCriterion("trial_place_name >", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("trial_place_name >=", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameLessThan(String value) {
            addCriterion("trial_place_name <", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameLessThanOrEqualTo(String value) {
            addCriterion("trial_place_name <=", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameLike(String value) {
            addCriterion("trial_place_name like", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameNotLike(String value) {
            addCriterion("trial_place_name not like", value, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameIn(List<String> values) {
            addCriterion("trial_place_name in", values, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameNotIn(List<String> values) {
            addCriterion("trial_place_name not in", values, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameBetween(String value1, String value2) {
            addCriterion("trial_place_name between", value1, value2, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceNameNotBetween(String value1, String value2) {
            addCriterion("trial_place_name not between", value1, value2, "trialPlaceName");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeIsNull() {
            addCriterion("trial_place_code is null");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeIsNotNull() {
            addCriterion("trial_place_code is not null");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeEqualTo(String value) {
            addCriterion("trial_place_code =", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeNotEqualTo(String value) {
            addCriterion("trial_place_code <>", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeGreaterThan(String value) {
            addCriterion("trial_place_code >", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("trial_place_code >=", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeLessThan(String value) {
            addCriterion("trial_place_code <", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeLessThanOrEqualTo(String value) {
            addCriterion("trial_place_code <=", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeLike(String value) {
            addCriterion("trial_place_code like", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeNotLike(String value) {
            addCriterion("trial_place_code not like", value, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeIn(List<String> values) {
            addCriterion("trial_place_code in", values, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeNotIn(List<String> values) {
            addCriterion("trial_place_code not in", values, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeBetween(String value1, String value2) {
            addCriterion("trial_place_code between", value1, value2, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialPlaceCodeNotBetween(String value1, String value2) {
            addCriterion("trial_place_code not between", value1, value2, "trialPlaceCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameIsNull() {
            addCriterion("trial_court_name is null");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameIsNotNull() {
            addCriterion("trial_court_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameEqualTo(String value) {
            addCriterion("trial_court_name =", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameNotEqualTo(String value) {
            addCriterion("trial_court_name <>", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameGreaterThan(String value) {
            addCriterion("trial_court_name >", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameGreaterThanOrEqualTo(String value) {
            addCriterion("trial_court_name >=", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameLessThan(String value) {
            addCriterion("trial_court_name <", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameLessThanOrEqualTo(String value) {
            addCriterion("trial_court_name <=", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameLike(String value) {
            addCriterion("trial_court_name like", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameNotLike(String value) {
            addCriterion("trial_court_name not like", value, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameIn(List<String> values) {
            addCriterion("trial_court_name in", values, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameNotIn(List<String> values) {
            addCriterion("trial_court_name not in", values, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameBetween(String value1, String value2) {
            addCriterion("trial_court_name between", value1, value2, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtNameNotBetween(String value1, String value2) {
            addCriterion("trial_court_name not between", value1, value2, "trialCourtName");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeIsNull() {
            addCriterion("trial_court_code is null");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeIsNotNull() {
            addCriterion("trial_court_code is not null");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeEqualTo(String value) {
            addCriterion("trial_court_code =", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeNotEqualTo(String value) {
            addCriterion("trial_court_code <>", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeGreaterThan(String value) {
            addCriterion("trial_court_code >", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("trial_court_code >=", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeLessThan(String value) {
            addCriterion("trial_court_code <", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeLessThanOrEqualTo(String value) {
            addCriterion("trial_court_code <=", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeLike(String value) {
            addCriterion("trial_court_code like", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeNotLike(String value) {
            addCriterion("trial_court_code not like", value, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeIn(List<String> values) {
            addCriterion("trial_court_code in", values, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeNotIn(List<String> values) {
            addCriterion("trial_court_code not in", values, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeBetween(String value1, String value2) {
            addCriterion("trial_court_code between", value1, value2, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andTrialCourtCodeNotBetween(String value1, String value2) {
            addCriterion("trial_court_code not between", value1, value2, "trialCourtCode");
            return (Criteria) this;
        }

        public Criteria andSelectNumberIsNull() {
            addCriterion("select_number is null");
            return (Criteria) this;
        }

        public Criteria andSelectNumberIsNotNull() {
            addCriterion("select_number is not null");
            return (Criteria) this;
        }

        public Criteria andSelectNumberEqualTo(Integer value) {
            addCriterion("select_number =", value, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberNotEqualTo(Integer value) {
            addCriterion("select_number <>", value, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberGreaterThan(Integer value) {
            addCriterion("select_number >", value, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("select_number >=", value, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberLessThan(Integer value) {
            addCriterion("select_number <", value, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberLessThanOrEqualTo(Integer value) {
            addCriterion("select_number <=", value, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberIn(List<Integer> values) {
            addCriterion("select_number in", values, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberNotIn(List<Integer> values) {
            addCriterion("select_number not in", values, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberBetween(Integer value1, Integer value2) {
            addCriterion("select_number between", value1, value2, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andSelectNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("select_number not between", value1, value2, "selectNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberIsNull() {
            addCriterion("alternative_number is null");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberIsNotNull() {
            addCriterion("alternative_number is not null");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberEqualTo(Integer value) {
            addCriterion("alternative_number =", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberNotEqualTo(Integer value) {
            addCriterion("alternative_number <>", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberGreaterThan(Integer value) {
            addCriterion("alternative_number >", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("alternative_number >=", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberLessThan(Integer value) {
            addCriterion("alternative_number <", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberLessThanOrEqualTo(Integer value) {
            addCriterion("alternative_number <=", value, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberIn(List<Integer> values) {
            addCriterion("alternative_number in", values, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberNotIn(List<Integer> values) {
            addCriterion("alternative_number not in", values, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberBetween(Integer value1, Integer value2) {
            addCriterion("alternative_number between", value1, value2, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("alternative_number not between", value1, value2, "alternativeNumber");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNull() {
            addCriterion("specialty is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNotNull() {
            addCriterion("specialty is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyEqualTo(String value) {
            addCriterion("specialty =", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotEqualTo(String value) {
            addCriterion("specialty <>", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThan(String value) {
            addCriterion("specialty >", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThanOrEqualTo(String value) {
            addCriterion("specialty >=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThan(String value) {
            addCriterion("specialty <", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThanOrEqualTo(String value) {
            addCriterion("specialty <=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLike(String value) {
            addCriterion("specialty like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotLike(String value) {
            addCriterion("specialty not like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIn(List<String> values) {
            addCriterion("specialty in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotIn(List<String> values) {
            addCriterion("specialty not in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyBetween(String value1, String value2) {
            addCriterion("specialty between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotBetween(String value1, String value2) {
            addCriterion("specialty not between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andNationalIsNull() {
            addCriterion("national is null");
            return (Criteria) this;
        }

        public Criteria andNationalIsNotNull() {
            addCriterion("national is not null");
            return (Criteria) this;
        }

        public Criteria andNationalEqualTo(String value) {
            addCriterion("national =", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotEqualTo(String value) {
            addCriterion("national <>", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThan(String value) {
            addCriterion("national >", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalGreaterThanOrEqualTo(String value) {
            addCriterion("national >=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThan(String value) {
            addCriterion("national <", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLessThanOrEqualTo(String value) {
            addCriterion("national <=", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalLike(String value) {
            addCriterion("national like", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotLike(String value) {
            addCriterion("national not like", value, "national");
            return (Criteria) this;
        }

        public Criteria andNationalIn(List<String> values) {
            addCriterion("national in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotIn(List<String> values) {
            addCriterion("national not in", values, "national");
            return (Criteria) this;
        }

        public Criteria andNationalBetween(String value1, String value2) {
            addCriterion("national between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andNationalNotBetween(String value1, String value2) {
            addCriterion("national not between", value1, value2, "national");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIsNull() {
            addCriterion("case_type is null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIsNotNull() {
            addCriterion("case_type is not null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeEqualTo(String value) {
            addCriterion("case_type =", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotEqualTo(String value) {
            addCriterion("case_type <>", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeGreaterThan(String value) {
            addCriterion("case_type >", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("case_type >=", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLessThan(String value) {
            addCriterion("case_type <", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLessThanOrEqualTo(String value) {
            addCriterion("case_type <=", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeLike(String value) {
            addCriterion("case_type like", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotLike(String value) {
            addCriterion("case_type not like", value, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeIn(List<String> values) {
            addCriterion("case_type in", values, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotIn(List<String> values) {
            addCriterion("case_type not in", values, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeBetween(String value1, String value2) {
            addCriterion("case_type between", value1, value2, "caseType");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNotBetween(String value1, String value2) {
            addCriterion("case_type not between", value1, value2, "caseType");
            return (Criteria) this;
        }

        public Criteria andIsNearestIsNull() {
            addCriterion("is_nearest is null");
            return (Criteria) this;
        }

        public Criteria andIsNearestIsNotNull() {
            addCriterion("is_nearest is not null");
            return (Criteria) this;
        }

        public Criteria andIsNearestEqualTo(Integer value) {
            addCriterion("is_nearest =", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestNotEqualTo(Integer value) {
            addCriterion("is_nearest <>", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestGreaterThan(Integer value) {
            addCriterion("is_nearest >", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_nearest >=", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestLessThan(Integer value) {
            addCriterion("is_nearest <", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestLessThanOrEqualTo(Integer value) {
            addCriterion("is_nearest <=", value, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestIn(List<Integer> values) {
            addCriterion("is_nearest in", values, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestNotIn(List<Integer> values) {
            addCriterion("is_nearest not in", values, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestBetween(Integer value1, Integer value2) {
            addCriterion("is_nearest between", value1, value2, "isNearest");
            return (Criteria) this;
        }

        public Criteria andIsNearestNotBetween(Integer value1, Integer value2) {
            addCriterion("is_nearest not between", value1, value2, "isNearest");
            return (Criteria) this;
        }

        public Criteria andSelectTimeIsNull() {
            addCriterion("select_time is null");
            return (Criteria) this;
        }

        public Criteria andSelectTimeIsNotNull() {
            addCriterion("select_time is not null");
            return (Criteria) this;
        }

        public Criteria andSelectTimeEqualTo(Date value) {
            addCriterion("select_time =", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeNotEqualTo(Date value) {
            addCriterion("select_time <>", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeGreaterThan(Date value) {
            addCriterion("select_time >", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("select_time >=", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeLessThan(Date value) {
            addCriterion("select_time <", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeLessThanOrEqualTo(Date value) {
            addCriterion("select_time <=", value, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeIn(List<Date> values) {
            addCriterion("select_time in", values, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeNotIn(List<Date> values) {
            addCriterion("select_time not in", values, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeBetween(Date value1, Date value2) {
            addCriterion("select_time between", value1, value2, "selectTime");
            return (Criteria) this;
        }

        public Criteria andSelectTimeNotBetween(Date value1, Date value2) {
            addCriterion("select_time not between", value1, value2, "selectTime");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameIsNull() {
            addCriterion("case_type_name is null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameIsNotNull() {
            addCriterion("case_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameEqualTo(String value) {
            addCriterion("case_type_name =", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameNotEqualTo(String value) {
            addCriterion("case_type_name <>", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameGreaterThan(String value) {
            addCriterion("case_type_name >", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("case_type_name >=", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameLessThan(String value) {
            addCriterion("case_type_name <", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameLessThanOrEqualTo(String value) {
            addCriterion("case_type_name <=", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameLike(String value) {
            addCriterion("case_type_name like", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameNotLike(String value) {
            addCriterion("case_type_name not like", value, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameIn(List<String> values) {
            addCriterion("case_type_name in", values, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameNotIn(List<String> values) {
            addCriterion("case_type_name not in", values, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameBetween(String value1, String value2) {
            addCriterion("case_type_name between", value1, value2, "caseTypeName");
            return (Criteria) this;
        }

        public Criteria andCaseTypeNameNotBetween(String value1, String value2) {
            addCriterion("case_type_name not between", value1, value2, "caseTypeName");
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