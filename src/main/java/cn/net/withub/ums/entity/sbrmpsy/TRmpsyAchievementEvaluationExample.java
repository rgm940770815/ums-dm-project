package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TRmpsyAchievementEvaluationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyAchievementEvaluationExample() {
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

        public Criteria andCaseIdIsNull() {
            addCriterion("case_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNotNull() {
            addCriterion("case_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseIdEqualTo(String value) {
            addCriterion("case_id =", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotEqualTo(String value) {
            addCriterion("case_id <>", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThan(String value) {
            addCriterion("case_id >", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("case_id >=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThan(String value) {
            addCriterion("case_id <", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThanOrEqualTo(String value) {
            addCriterion("case_id <=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLike(String value) {
            addCriterion("case_id like", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotLike(String value) {
            addCriterion("case_id not like", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIn(List<String> values) {
            addCriterion("case_id in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotIn(List<String> values) {
            addCriterion("case_id not in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdBetween(String value1, String value2) {
            addCriterion("case_id between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotBetween(String value1, String value2) {
            addCriterion("case_id not between", value1, value2, "caseId");
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

        public Criteria andJudgeIdIsNull() {
            addCriterion("judge_id is null");
            return (Criteria) this;
        }

        public Criteria andJudgeIdIsNotNull() {
            addCriterion("judge_id is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeIdEqualTo(String value) {
            addCriterion("judge_id =", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdNotEqualTo(String value) {
            addCriterion("judge_id <>", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdGreaterThan(String value) {
            addCriterion("judge_id >", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdGreaterThanOrEqualTo(String value) {
            addCriterion("judge_id >=", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdLessThan(String value) {
            addCriterion("judge_id <", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdLessThanOrEqualTo(String value) {
            addCriterion("judge_id <=", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdLike(String value) {
            addCriterion("judge_id like", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdNotLike(String value) {
            addCriterion("judge_id not like", value, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdIn(List<String> values) {
            addCriterion("judge_id in", values, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdNotIn(List<String> values) {
            addCriterion("judge_id not in", values, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdBetween(String value1, String value2) {
            addCriterion("judge_id between", value1, value2, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeIdNotBetween(String value1, String value2) {
            addCriterion("judge_id not between", value1, value2, "judgeId");
            return (Criteria) this;
        }

        public Criteria andJudgeNameIsNull() {
            addCriterion("judge_name is null");
            return (Criteria) this;
        }

        public Criteria andJudgeNameIsNotNull() {
            addCriterion("judge_name is not null");
            return (Criteria) this;
        }

        public Criteria andJudgeNameEqualTo(String value) {
            addCriterion("judge_name =", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameNotEqualTo(String value) {
            addCriterion("judge_name <>", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameGreaterThan(String value) {
            addCriterion("judge_name >", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameGreaterThanOrEqualTo(String value) {
            addCriterion("judge_name >=", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameLessThan(String value) {
            addCriterion("judge_name <", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameLessThanOrEqualTo(String value) {
            addCriterion("judge_name <=", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameLike(String value) {
            addCriterion("judge_name like", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameNotLike(String value) {
            addCriterion("judge_name not like", value, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameIn(List<String> values) {
            addCriterion("judge_name in", values, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameNotIn(List<String> values) {
            addCriterion("judge_name not in", values, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameBetween(String value1, String value2) {
            addCriterion("judge_name between", value1, value2, "judgeName");
            return (Criteria) this;
        }

        public Criteria andJudgeNameNotBetween(String value1, String value2) {
            addCriterion("judge_name not between", value1, value2, "judgeName");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterionForJDBCDate("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterionForJDBCDate("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterionForJDBCDate("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMoralScoreIsNull() {
            addCriterion("moral_score is null");
            return (Criteria) this;
        }

        public Criteria andMoralScoreIsNotNull() {
            addCriterion("moral_score is not null");
            return (Criteria) this;
        }

        public Criteria andMoralScoreEqualTo(String value) {
            addCriterion("moral_score =", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreNotEqualTo(String value) {
            addCriterion("moral_score <>", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreGreaterThan(String value) {
            addCriterion("moral_score >", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreGreaterThanOrEqualTo(String value) {
            addCriterion("moral_score >=", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreLessThan(String value) {
            addCriterion("moral_score <", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreLessThanOrEqualTo(String value) {
            addCriterion("moral_score <=", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreLike(String value) {
            addCriterion("moral_score like", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreNotLike(String value) {
            addCriterion("moral_score not like", value, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreIn(List<String> values) {
            addCriterion("moral_score in", values, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreNotIn(List<String> values) {
            addCriterion("moral_score not in", values, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreBetween(String value1, String value2) {
            addCriterion("moral_score between", value1, value2, "moralScore");
            return (Criteria) this;
        }

        public Criteria andMoralScoreNotBetween(String value1, String value2) {
            addCriterion("moral_score not between", value1, value2, "moralScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreIsNull() {
            addCriterion("business_score is null");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreIsNotNull() {
            addCriterion("business_score is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreEqualTo(String value) {
            addCriterion("business_score =", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreNotEqualTo(String value) {
            addCriterion("business_score <>", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreGreaterThan(String value) {
            addCriterion("business_score >", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreGreaterThanOrEqualTo(String value) {
            addCriterion("business_score >=", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreLessThan(String value) {
            addCriterion("business_score <", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreLessThanOrEqualTo(String value) {
            addCriterion("business_score <=", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreLike(String value) {
            addCriterion("business_score like", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreNotLike(String value) {
            addCriterion("business_score not like", value, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreIn(List<String> values) {
            addCriterion("business_score in", values, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreNotIn(List<String> values) {
            addCriterion("business_score not in", values, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreBetween(String value1, String value2) {
            addCriterion("business_score between", value1, value2, "businessScore");
            return (Criteria) this;
        }

        public Criteria andBusinessScoreNotBetween(String value1, String value2) {
            addCriterion("business_score not between", value1, value2, "businessScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreIsNull() {
            addCriterion("attitude_score is null");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreIsNotNull() {
            addCriterion("attitude_score is not null");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreEqualTo(String value) {
            addCriterion("attitude_score =", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreNotEqualTo(String value) {
            addCriterion("attitude_score <>", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreGreaterThan(String value) {
            addCriterion("attitude_score >", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreGreaterThanOrEqualTo(String value) {
            addCriterion("attitude_score >=", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreLessThan(String value) {
            addCriterion("attitude_score <", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreLessThanOrEqualTo(String value) {
            addCriterion("attitude_score <=", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreLike(String value) {
            addCriterion("attitude_score like", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreNotLike(String value) {
            addCriterion("attitude_score not like", value, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreIn(List<String> values) {
            addCriterion("attitude_score in", values, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreNotIn(List<String> values) {
            addCriterion("attitude_score not in", values, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreBetween(String value1, String value2) {
            addCriterion("attitude_score between", value1, value2, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andAttitudeScoreNotBetween(String value1, String value2) {
            addCriterion("attitude_score not between", value1, value2, "attitudeScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreIsNull() {
            addCriterion("trial_score is null");
            return (Criteria) this;
        }

        public Criteria andTrialScoreIsNotNull() {
            addCriterion("trial_score is not null");
            return (Criteria) this;
        }

        public Criteria andTrialScoreEqualTo(String value) {
            addCriterion("trial_score =", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreNotEqualTo(String value) {
            addCriterion("trial_score <>", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreGreaterThan(String value) {
            addCriterion("trial_score >", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreGreaterThanOrEqualTo(String value) {
            addCriterion("trial_score >=", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreLessThan(String value) {
            addCriterion("trial_score <", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreLessThanOrEqualTo(String value) {
            addCriterion("trial_score <=", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreLike(String value) {
            addCriterion("trial_score like", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreNotLike(String value) {
            addCriterion("trial_score not like", value, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreIn(List<String> values) {
            addCriterion("trial_score in", values, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreNotIn(List<String> values) {
            addCriterion("trial_score not in", values, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreBetween(String value1, String value2) {
            addCriterion("trial_score between", value1, value2, "trialScore");
            return (Criteria) this;
        }

        public Criteria andTrialScoreNotBetween(String value1, String value2) {
            addCriterion("trial_score not between", value1, value2, "trialScore");
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

        public Criteria andDisciplineEqualTo(String value) {
            addCriterion("discipline =", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineNotEqualTo(String value) {
            addCriterion("discipline <>", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineGreaterThan(String value) {
            addCriterion("discipline >", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineGreaterThanOrEqualTo(String value) {
            addCriterion("discipline >=", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineLessThan(String value) {
            addCriterion("discipline <", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineLessThanOrEqualTo(String value) {
            addCriterion("discipline <=", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineLike(String value) {
            addCriterion("discipline like", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineNotLike(String value) {
            addCriterion("discipline not like", value, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineIn(List<String> values) {
            addCriterion("discipline in", values, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineNotIn(List<String> values) {
            addCriterion("discipline not in", values, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineBetween(String value1, String value2) {
            addCriterion("discipline between", value1, value2, "discipline");
            return (Criteria) this;
        }

        public Criteria andDisciplineNotBetween(String value1, String value2) {
            addCriterion("discipline not between", value1, value2, "discipline");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("total_score is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("total_score is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(String value) {
            addCriterion("total_score =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(String value) {
            addCriterion("total_score <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(String value) {
            addCriterion("total_score >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(String value) {
            addCriterion("total_score >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(String value) {
            addCriterion("total_score <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(String value) {
            addCriterion("total_score <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLike(String value) {
            addCriterion("total_score like", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotLike(String value) {
            addCriterion("total_score not like", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<String> values) {
            addCriterion("total_score in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<String> values) {
            addCriterion("total_score not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(String value1, String value2) {
            addCriterion("total_score between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(String value1, String value2) {
            addCriterion("total_score not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("result like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("result not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdIsNull() {
            addCriterion("update_result_people_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdIsNotNull() {
            addCriterion("update_result_people_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdEqualTo(String value) {
            addCriterion("update_result_people_id =", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdNotEqualTo(String value) {
            addCriterion("update_result_people_id <>", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdGreaterThan(String value) {
            addCriterion("update_result_people_id >", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdGreaterThanOrEqualTo(String value) {
            addCriterion("update_result_people_id >=", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdLessThan(String value) {
            addCriterion("update_result_people_id <", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdLessThanOrEqualTo(String value) {
            addCriterion("update_result_people_id <=", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdLike(String value) {
            addCriterion("update_result_people_id like", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdNotLike(String value) {
            addCriterion("update_result_people_id not like", value, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdIn(List<String> values) {
            addCriterion("update_result_people_id in", values, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdNotIn(List<String> values) {
            addCriterion("update_result_people_id not in", values, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdBetween(String value1, String value2) {
            addCriterion("update_result_people_id between", value1, value2, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultPeopleIdNotBetween(String value1, String value2) {
            addCriterion("update_result_people_id not between", value1, value2, "updateResultPeopleId");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeIsNull() {
            addCriterion("update_result_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeIsNotNull() {
            addCriterion("update_result_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeEqualTo(Date value) {
            addCriterionForJDBCDate("update_result_time =", value, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_result_time <>", value, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("update_result_time >", value, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_result_time >=", value, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeLessThan(Date value) {
            addCriterionForJDBCDate("update_result_time <", value, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_result_time <=", value, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeIn(List<Date> values) {
            addCriterionForJDBCDate("update_result_time in", values, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_result_time not in", values, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_result_time between", value1, value2, "updateResultTime");
            return (Criteria) this;
        }

        public Criteria andUpdateResultTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_result_time not between", value1, value2, "updateResultTime");
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

        public Criteria andDataLevelEqualTo(Integer value) {
            addCriterion("data_level =", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotEqualTo(Integer value) {
            addCriterion("data_level <>", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelGreaterThan(Integer value) {
            addCriterion("data_level >", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_level >=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelLessThan(Integer value) {
            addCriterion("data_level <", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelLessThanOrEqualTo(Integer value) {
            addCriterion("data_level <=", value, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelIn(List<Integer> values) {
            addCriterion("data_level in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotIn(List<Integer> values) {
            addCriterion("data_level not in", values, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelBetween(Integer value1, Integer value2) {
            addCriterion("data_level between", value1, value2, "dataLevel");
            return (Criteria) this;
        }

        public Criteria andDataLevelNotBetween(Integer value1, Integer value2) {
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