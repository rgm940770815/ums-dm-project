package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TRmpsyAssessmentEvaluationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyAssessmentEvaluationExample() {
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

        public Criteria andRecordTableIdIsNull() {
            addCriterion("record_table_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdIsNotNull() {
            addCriterion("record_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdEqualTo(String value) {
            addCriterion("record_table_id =", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotEqualTo(String value) {
            addCriterion("record_table_id <>", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdGreaterThan(String value) {
            addCriterion("record_table_id >", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdGreaterThanOrEqualTo(String value) {
            addCriterion("record_table_id >=", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdLessThan(String value) {
            addCriterion("record_table_id <", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdLessThanOrEqualTo(String value) {
            addCriterion("record_table_id <=", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdLike(String value) {
            addCriterion("record_table_id like", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotLike(String value) {
            addCriterion("record_table_id not like", value, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdIn(List<String> values) {
            addCriterion("record_table_id in", values, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotIn(List<String> values) {
            addCriterion("record_table_id not in", values, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdBetween(String value1, String value2) {
            addCriterion("record_table_id between", value1, value2, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andRecordTableIdNotBetween(String value1, String value2) {
            addCriterion("record_table_id not between", value1, value2, "recordTableId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdIsNull() {
            addCriterion("assessor_id is null");
            return (Criteria) this;
        }

        public Criteria andAssessorIdIsNotNull() {
            addCriterion("assessor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssessorIdEqualTo(String value) {
            addCriterion("assessor_id =", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotEqualTo(String value) {
            addCriterion("assessor_id <>", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdGreaterThan(String value) {
            addCriterion("assessor_id >", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdGreaterThanOrEqualTo(String value) {
            addCriterion("assessor_id >=", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdLessThan(String value) {
            addCriterion("assessor_id <", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdLessThanOrEqualTo(String value) {
            addCriterion("assessor_id <=", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdLike(String value) {
            addCriterion("assessor_id like", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotLike(String value) {
            addCriterion("assessor_id not like", value, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdIn(List<String> values) {
            addCriterion("assessor_id in", values, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotIn(List<String> values) {
            addCriterion("assessor_id not in", values, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdBetween(String value1, String value2) {
            addCriterion("assessor_id between", value1, value2, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorIdNotBetween(String value1, String value2) {
            addCriterion("assessor_id not between", value1, value2, "assessorId");
            return (Criteria) this;
        }

        public Criteria andAssessorNameIsNull() {
            addCriterion("assessor_name is null");
            return (Criteria) this;
        }

        public Criteria andAssessorNameIsNotNull() {
            addCriterion("assessor_name is not null");
            return (Criteria) this;
        }

        public Criteria andAssessorNameEqualTo(String value) {
            addCriterion("assessor_name =", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameNotEqualTo(String value) {
            addCriterion("assessor_name <>", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameGreaterThan(String value) {
            addCriterion("assessor_name >", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameGreaterThanOrEqualTo(String value) {
            addCriterion("assessor_name >=", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameLessThan(String value) {
            addCriterion("assessor_name <", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameLessThanOrEqualTo(String value) {
            addCriterion("assessor_name <=", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameLike(String value) {
            addCriterion("assessor_name like", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameNotLike(String value) {
            addCriterion("assessor_name not like", value, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameIn(List<String> values) {
            addCriterion("assessor_name in", values, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameNotIn(List<String> values) {
            addCriterion("assessor_name not in", values, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameBetween(String value1, String value2) {
            addCriterion("assessor_name between", value1, value2, "assessorName");
            return (Criteria) this;
        }

        public Criteria andAssessorNameNotBetween(String value1, String value2) {
            addCriterion("assessor_name not between", value1, value2, "assessorName");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberIsNull() {
            addCriterion("professional_number is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberIsNotNull() {
            addCriterion("professional_number is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberEqualTo(Integer value) {
            addCriterion("professional_number =", value, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberNotEqualTo(Integer value) {
            addCriterion("professional_number <>", value, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberGreaterThan(Integer value) {
            addCriterion("professional_number >", value, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("professional_number >=", value, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberLessThan(Integer value) {
            addCriterion("professional_number <", value, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberLessThanOrEqualTo(Integer value) {
            addCriterion("professional_number <=", value, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberIn(List<Integer> values) {
            addCriterion("professional_number in", values, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberNotIn(List<Integer> values) {
            addCriterion("professional_number not in", values, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberBetween(Integer value1, Integer value2) {
            addCriterion("professional_number between", value1, value2, "professionalNumber");
            return (Criteria) this;
        }

        public Criteria andProfessionalNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("professional_number not between", value1, value2, "professionalNumber");
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

        public Criteria andTrialNumberIsNull() {
            addCriterion("trial_number is null");
            return (Criteria) this;
        }

        public Criteria andTrialNumberIsNotNull() {
            addCriterion("trial_number is not null");
            return (Criteria) this;
        }

        public Criteria andTrialNumberEqualTo(Integer value) {
            addCriterion("trial_number =", value, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberNotEqualTo(Integer value) {
            addCriterion("trial_number <>", value, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberGreaterThan(Integer value) {
            addCriterion("trial_number >", value, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("trial_number >=", value, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberLessThan(Integer value) {
            addCriterion("trial_number <", value, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberLessThanOrEqualTo(Integer value) {
            addCriterion("trial_number <=", value, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberIn(List<Integer> values) {
            addCriterion("trial_number in", values, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberNotIn(List<Integer> values) {
            addCriterion("trial_number not in", values, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberBetween(Integer value1, Integer value2) {
            addCriterion("trial_number between", value1, value2, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTrialNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("trial_number not between", value1, value2, "trialNumber");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andEthicsIsNull() {
            addCriterion("ethics is null");
            return (Criteria) this;
        }

        public Criteria andEthicsIsNotNull() {
            addCriterion("ethics is not null");
            return (Criteria) this;
        }

        public Criteria andEthicsEqualTo(Integer value) {
            addCriterion("ethics =", value, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsNotEqualTo(Integer value) {
            addCriterion("ethics <>", value, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsGreaterThan(Integer value) {
            addCriterion("ethics >", value, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsGreaterThanOrEqualTo(Integer value) {
            addCriterion("ethics >=", value, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsLessThan(Integer value) {
            addCriterion("ethics <", value, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsLessThanOrEqualTo(Integer value) {
            addCriterion("ethics <=", value, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsIn(List<Integer> values) {
            addCriterion("ethics in", values, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsNotIn(List<Integer> values) {
            addCriterion("ethics not in", values, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsBetween(Integer value1, Integer value2) {
            addCriterion("ethics between", value1, value2, "ethics");
            return (Criteria) this;
        }

        public Criteria andEthicsNotBetween(Integer value1, Integer value2) {
            addCriterion("ethics not between", value1, value2, "ethics");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelIsNull() {
            addCriterion("business_level is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelIsNotNull() {
            addCriterion("business_level is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelEqualTo(Integer value) {
            addCriterion("business_level =", value, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelNotEqualTo(Integer value) {
            addCriterion("business_level <>", value, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelGreaterThan(Integer value) {
            addCriterion("business_level >", value, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_level >=", value, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelLessThan(Integer value) {
            addCriterion("business_level <", value, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelLessThanOrEqualTo(Integer value) {
            addCriterion("business_level <=", value, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelIn(List<Integer> values) {
            addCriterion("business_level in", values, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelNotIn(List<Integer> values) {
            addCriterion("business_level not in", values, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelBetween(Integer value1, Integer value2) {
            addCriterion("business_level between", value1, value2, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andBusinessLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("business_level not between", value1, value2, "businessLevel");
            return (Criteria) this;
        }

        public Criteria andAttitudeIsNull() {
            addCriterion("attitude is null");
            return (Criteria) this;
        }

        public Criteria andAttitudeIsNotNull() {
            addCriterion("attitude is not null");
            return (Criteria) this;
        }

        public Criteria andAttitudeEqualTo(Integer value) {
            addCriterion("attitude =", value, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeNotEqualTo(Integer value) {
            addCriterion("attitude <>", value, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeGreaterThan(Integer value) {
            addCriterion("attitude >", value, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeGreaterThanOrEqualTo(Integer value) {
            addCriterion("attitude >=", value, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeLessThan(Integer value) {
            addCriterion("attitude <", value, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeLessThanOrEqualTo(Integer value) {
            addCriterion("attitude <=", value, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeIn(List<Integer> values) {
            addCriterion("attitude in", values, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeNotIn(List<Integer> values) {
            addCriterion("attitude not in", values, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeBetween(Integer value1, Integer value2) {
            addCriterion("attitude between", value1, value2, "attitude");
            return (Criteria) this;
        }

        public Criteria andAttitudeNotBetween(Integer value1, Integer value2) {
            addCriterion("attitude not between", value1, value2, "attitude");
            return (Criteria) this;
        }

        public Criteria andTrialResultsIsNull() {
            addCriterion("trial_results is null");
            return (Criteria) this;
        }

        public Criteria andTrialResultsIsNotNull() {
            addCriterion("trial_results is not null");
            return (Criteria) this;
        }

        public Criteria andTrialResultsEqualTo(Integer value) {
            addCriterion("trial_results =", value, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsNotEqualTo(Integer value) {
            addCriterion("trial_results <>", value, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsGreaterThan(Integer value) {
            addCriterion("trial_results >", value, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsGreaterThanOrEqualTo(Integer value) {
            addCriterion("trial_results >=", value, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsLessThan(Integer value) {
            addCriterion("trial_results <", value, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsLessThanOrEqualTo(Integer value) {
            addCriterion("trial_results <=", value, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsIn(List<Integer> values) {
            addCriterion("trial_results in", values, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsNotIn(List<Integer> values) {
            addCriterion("trial_results not in", values, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsBetween(Integer value1, Integer value2) {
            addCriterion("trial_results between", value1, value2, "trialResults");
            return (Criteria) this;
        }

        public Criteria andTrialResultsNotBetween(Integer value1, Integer value2) {
            addCriterion("trial_results not between", value1, value2, "trialResults");
            return (Criteria) this;
        }

        public Criteria andDisciplineIsNull() {
            addCriterion("discipline is null");
            return (Criteria) this;
        }

        public Criteria andDisciplineIsNotNull() {
            addCriterion("discipline is not null");
            return (Criteria) this;
        }

        public Criteria andDisciplineEqualTo(Integer value) {
            addCriterion("discipline =", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineNotEqualTo(Integer value) {
            addCriterion("discipline <>", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineGreaterThan(Integer value) {
            addCriterion("discipline >", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineGreaterThanOrEqualTo(Integer value) {
            addCriterion("discipline >=", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineLessThan(Integer value) {
            addCriterion("discipline <", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineLessThanOrEqualTo(Integer value) {
            addCriterion("discipline <=", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineIn(List<Integer> values) {
            addCriterion("discipline in", values, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineNotIn(List<Integer> values) {
            addCriterion("discipline not in", values, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineBetween(Integer value1, Integer value2) {
            addCriterion("discipline between", value1, value2, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineNotBetween(Integer value1, Integer value2) {
            addCriterion("discipline not between", value1, value2, "discipline");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
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