package cn.net.withub.ums.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsJurorInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UmsJurorInfoCriteria() {
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

        public Criteria andUkNoIsNull() {
            addCriterion("uk_no is null");
            return (Criteria) this;
        }

        public Criteria andUkNoIsNotNull() {
            addCriterion("uk_no is not null");
            return (Criteria) this;
        }

        public Criteria andUkNoEqualTo(String value) {
            addCriterion("uk_no =", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotEqualTo(String value) {
            addCriterion("uk_no <>", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoGreaterThan(String value) {
            addCriterion("uk_no >", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoGreaterThanOrEqualTo(String value) {
            addCriterion("uk_no >=", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoLessThan(String value) {
            addCriterion("uk_no <", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoLessThanOrEqualTo(String value) {
            addCriterion("uk_no <=", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoLike(String value) {
            addCriterion("uk_no like", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotLike(String value) {
            addCriterion("uk_no not like", value, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoIn(List<String> values) {
            addCriterion("uk_no in", values, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotIn(List<String> values) {
            addCriterion("uk_no not in", values, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoBetween(String value1, String value2) {
            addCriterion("uk_no between", value1, value2, "ukNo");
            return (Criteria) this;
        }

        public Criteria andUkNoNotBetween(String value1, String value2) {
            addCriterion("uk_no not between", value1, value2, "ukNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoIsNull() {
            addCriterion("work_no is null");
            return (Criteria) this;
        }

        public Criteria andWorkNoIsNotNull() {
            addCriterion("work_no is not null");
            return (Criteria) this;
        }

        public Criteria andWorkNoEqualTo(String value) {
            addCriterion("work_no =", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotEqualTo(String value) {
            addCriterion("work_no <>", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoGreaterThan(String value) {
            addCriterion("work_no >", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoGreaterThanOrEqualTo(String value) {
            addCriterion("work_no >=", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLessThan(String value) {
            addCriterion("work_no <", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLessThanOrEqualTo(String value) {
            addCriterion("work_no <=", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLike(String value) {
            addCriterion("work_no like", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotLike(String value) {
            addCriterion("work_no not like", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoIn(List<String> values) {
            addCriterion("work_no in", values, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotIn(List<String> values) {
            addCriterion("work_no not in", values, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoBetween(String value1, String value2) {
            addCriterion("work_no between", value1, value2, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotBetween(String value1, String value2) {
            addCriterion("work_no not between", value1, value2, "workNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoIsNull() {
            addCriterion("fanka_no is null");
            return (Criteria) this;
        }

        public Criteria andFankaNoIsNotNull() {
            addCriterion("fanka_no is not null");
            return (Criteria) this;
        }

        public Criteria andFankaNoEqualTo(String value) {
            addCriterion("fanka_no =", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotEqualTo(String value) {
            addCriterion("fanka_no <>", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoGreaterThan(String value) {
            addCriterion("fanka_no >", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoGreaterThanOrEqualTo(String value) {
            addCriterion("fanka_no >=", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoLessThan(String value) {
            addCriterion("fanka_no <", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoLessThanOrEqualTo(String value) {
            addCriterion("fanka_no <=", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoLike(String value) {
            addCriterion("fanka_no like", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotLike(String value) {
            addCriterion("fanka_no not like", value, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoIn(List<String> values) {
            addCriterion("fanka_no in", values, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotIn(List<String> values) {
            addCriterion("fanka_no not in", values, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoBetween(String value1, String value2) {
            addCriterion("fanka_no between", value1, value2, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andFankaNoNotBetween(String value1, String value2) {
            addCriterion("fanka_no not between", value1, value2, "fankaNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoIsNull() {
            addCriterion("official_no is null");
            return (Criteria) this;
        }

        public Criteria andOfficialNoIsNotNull() {
            addCriterion("official_no is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialNoEqualTo(String value) {
            addCriterion("official_no =", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotEqualTo(String value) {
            addCriterion("official_no <>", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoGreaterThan(String value) {
            addCriterion("official_no >", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoGreaterThanOrEqualTo(String value) {
            addCriterion("official_no >=", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoLessThan(String value) {
            addCriterion("official_no <", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoLessThanOrEqualTo(String value) {
            addCriterion("official_no <=", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoLike(String value) {
            addCriterion("official_no like", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotLike(String value) {
            addCriterion("official_no not like", value, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoIn(List<String> values) {
            addCriterion("official_no in", values, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotIn(List<String> values) {
            addCriterion("official_no not in", values, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoBetween(String value1, String value2) {
            addCriterion("official_no between", value1, value2, "officialNo");
            return (Criteria) this;
        }

        public Criteria andOfficialNoNotBetween(String value1, String value2) {
            addCriterion("official_no not between", value1, value2, "officialNo");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureIsNull() {
            addCriterion("company_nature is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureIsNotNull() {
            addCriterion("company_nature is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureEqualTo(String value) {
            addCriterion("company_nature =", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureNotEqualTo(String value) {
            addCriterion("company_nature <>", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureGreaterThan(String value) {
            addCriterion("company_nature >", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureGreaterThanOrEqualTo(String value) {
            addCriterion("company_nature >=", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureLessThan(String value) {
            addCriterion("company_nature <", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureLessThanOrEqualTo(String value) {
            addCriterion("company_nature <=", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureLike(String value) {
            addCriterion("company_nature like", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureNotLike(String value) {
            addCriterion("company_nature not like", value, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureIn(List<String> values) {
            addCriterion("company_nature in", values, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureNotIn(List<String> values) {
            addCriterion("company_nature not in", values, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureBetween(String value1, String value2) {
            addCriterion("company_nature between", value1, value2, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyNatureNotBetween(String value1, String value2) {
            addCriterion("company_nature not between", value1, value2, "companyNature");
            return (Criteria) this;
        }

        public Criteria andCompanyJobIsNull() {
            addCriterion("company_job is null");
            return (Criteria) this;
        }

        public Criteria andCompanyJobIsNotNull() {
            addCriterion("company_job is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyJobEqualTo(String value) {
            addCriterion("company_job =", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobNotEqualTo(String value) {
            addCriterion("company_job <>", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobGreaterThan(String value) {
            addCriterion("company_job >", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobGreaterThanOrEqualTo(String value) {
            addCriterion("company_job >=", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobLessThan(String value) {
            addCriterion("company_job <", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobLessThanOrEqualTo(String value) {
            addCriterion("company_job <=", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobLike(String value) {
            addCriterion("company_job like", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobNotLike(String value) {
            addCriterion("company_job not like", value, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobIn(List<String> values) {
            addCriterion("company_job in", values, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobNotIn(List<String> values) {
            addCriterion("company_job not in", values, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobBetween(String value1, String value2) {
            addCriterion("company_job between", value1, value2, "companyJob");
            return (Criteria) this;
        }

        public Criteria andCompanyJobNotBetween(String value1, String value2) {
            addCriterion("company_job not between", value1, value2, "companyJob");
            return (Criteria) this;
        }

        public Criteria andJurorUnitIsNull() {
            addCriterion("juror_unit is null");
            return (Criteria) this;
        }

        public Criteria andJurorUnitIsNotNull() {
            addCriterion("juror_unit is not null");
            return (Criteria) this;
        }

        public Criteria andJurorUnitEqualTo(String value) {
            addCriterion("juror_unit =", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitNotEqualTo(String value) {
            addCriterion("juror_unit <>", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitGreaterThan(String value) {
            addCriterion("juror_unit >", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitGreaterThanOrEqualTo(String value) {
            addCriterion("juror_unit >=", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitLessThan(String value) {
            addCriterion("juror_unit <", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitLessThanOrEqualTo(String value) {
            addCriterion("juror_unit <=", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitLike(String value) {
            addCriterion("juror_unit like", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitNotLike(String value) {
            addCriterion("juror_unit not like", value, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitIn(List<String> values) {
            addCriterion("juror_unit in", values, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitNotIn(List<String> values) {
            addCriterion("juror_unit not in", values, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitBetween(String value1, String value2) {
            addCriterion("juror_unit between", value1, value2, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorUnitNotBetween(String value1, String value2) {
            addCriterion("juror_unit not between", value1, value2, "jurorUnit");
            return (Criteria) this;
        }

        public Criteria andJurorDateIsNull() {
            addCriterion("juror_date is null");
            return (Criteria) this;
        }

        public Criteria andJurorDateIsNotNull() {
            addCriterion("juror_date is not null");
            return (Criteria) this;
        }

        public Criteria andJurorDateEqualTo(Date value) {
            addCriterionForJDBCDate("juror_date =", value, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("juror_date <>", value, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateGreaterThan(Date value) {
            addCriterionForJDBCDate("juror_date >", value, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("juror_date >=", value, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateLessThan(Date value) {
            addCriterionForJDBCDate("juror_date <", value, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("juror_date <=", value, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateIn(List<Date> values) {
            addCriterionForJDBCDate("juror_date in", values, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("juror_date not in", values, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("juror_date between", value1, value2, "jurorDate");
            return (Criteria) this;
        }

        public Criteria andJurorDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("juror_date not between", value1, value2, "jurorDate");
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

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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

        public Criteria andJurorWorkIsNull() {
            addCriterion("juror_work is null");
            return (Criteria) this;
        }

        public Criteria andJurorWorkIsNotNull() {
            addCriterion("juror_work is not null");
            return (Criteria) this;
        }

        public Criteria andJurorWorkEqualTo(String value) {
            addCriterion("juror_work =", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkNotEqualTo(String value) {
            addCriterion("juror_work <>", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkGreaterThan(String value) {
            addCriterion("juror_work >", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkGreaterThanOrEqualTo(String value) {
            addCriterion("juror_work >=", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkLessThan(String value) {
            addCriterion("juror_work <", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkLessThanOrEqualTo(String value) {
            addCriterion("juror_work <=", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkLike(String value) {
            addCriterion("juror_work like", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkNotLike(String value) {
            addCriterion("juror_work not like", value, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkIn(List<String> values) {
            addCriterion("juror_work in", values, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkNotIn(List<String> values) {
            addCriterion("juror_work not in", values, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkBetween(String value1, String value2) {
            addCriterion("juror_work between", value1, value2, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andJurorWorkNotBetween(String value1, String value2) {
            addCriterion("juror_work not between", value1, value2, "jurorWork");
            return (Criteria) this;
        }

        public Criteria andRegionalIsNull() {
            addCriterion("regional is null");
            return (Criteria) this;
        }

        public Criteria andRegionalIsNotNull() {
            addCriterion("regional is not null");
            return (Criteria) this;
        }

        public Criteria andRegionalEqualTo(String value) {
            addCriterion("regional =", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalNotEqualTo(String value) {
            addCriterion("regional <>", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalGreaterThan(String value) {
            addCriterion("regional >", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalGreaterThanOrEqualTo(String value) {
            addCriterion("regional >=", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalLessThan(String value) {
            addCriterion("regional <", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalLessThanOrEqualTo(String value) {
            addCriterion("regional <=", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalLike(String value) {
            addCriterion("regional like", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalNotLike(String value) {
            addCriterion("regional not like", value, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalIn(List<String> values) {
            addCriterion("regional in", values, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalNotIn(List<String> values) {
            addCriterion("regional not in", values, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalBetween(String value1, String value2) {
            addCriterion("regional between", value1, value2, "regional");
            return (Criteria) this;
        }

        public Criteria andRegionalNotBetween(String value1, String value2) {
            addCriterion("regional not between", value1, value2, "regional");
            return (Criteria) this;
        }

        public Criteria andWorkArea1IsNull() {
            addCriterion("work_area_1 is null");
            return (Criteria) this;
        }

        public Criteria andWorkArea1IsNotNull() {
            addCriterion("work_area_1 is not null");
            return (Criteria) this;
        }

        public Criteria andWorkArea1EqualTo(String value) {
            addCriterion("work_area_1 =", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1NotEqualTo(String value) {
            addCriterion("work_area_1 <>", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1GreaterThan(String value) {
            addCriterion("work_area_1 >", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1GreaterThanOrEqualTo(String value) {
            addCriterion("work_area_1 >=", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1LessThan(String value) {
            addCriterion("work_area_1 <", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1LessThanOrEqualTo(String value) {
            addCriterion("work_area_1 <=", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1Like(String value) {
            addCriterion("work_area_1 like", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1NotLike(String value) {
            addCriterion("work_area_1 not like", value, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1In(List<String> values) {
            addCriterion("work_area_1 in", values, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1NotIn(List<String> values) {
            addCriterion("work_area_1 not in", values, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1Between(String value1, String value2) {
            addCriterion("work_area_1 between", value1, value2, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea1NotBetween(String value1, String value2) {
            addCriterion("work_area_1 not between", value1, value2, "workArea1");
            return (Criteria) this;
        }

        public Criteria andWorkArea2IsNull() {
            addCriterion("work_area_2 is null");
            return (Criteria) this;
        }

        public Criteria andWorkArea2IsNotNull() {
            addCriterion("work_area_2 is not null");
            return (Criteria) this;
        }

        public Criteria andWorkArea2EqualTo(String value) {
            addCriterion("work_area_2 =", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2NotEqualTo(String value) {
            addCriterion("work_area_2 <>", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2GreaterThan(String value) {
            addCriterion("work_area_2 >", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2GreaterThanOrEqualTo(String value) {
            addCriterion("work_area_2 >=", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2LessThan(String value) {
            addCriterion("work_area_2 <", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2LessThanOrEqualTo(String value) {
            addCriterion("work_area_2 <=", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2Like(String value) {
            addCriterion("work_area_2 like", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2NotLike(String value) {
            addCriterion("work_area_2 not like", value, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2In(List<String> values) {
            addCriterion("work_area_2 in", values, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2NotIn(List<String> values) {
            addCriterion("work_area_2 not in", values, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2Between(String value1, String value2) {
            addCriterion("work_area_2 between", value1, value2, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea2NotBetween(String value1, String value2) {
            addCriterion("work_area_2 not between", value1, value2, "workArea2");
            return (Criteria) this;
        }

        public Criteria andWorkArea3IsNull() {
            addCriterion("work_area_3 is null");
            return (Criteria) this;
        }

        public Criteria andWorkArea3IsNotNull() {
            addCriterion("work_area_3 is not null");
            return (Criteria) this;
        }

        public Criteria andWorkArea3EqualTo(String value) {
            addCriterion("work_area_3 =", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3NotEqualTo(String value) {
            addCriterion("work_area_3 <>", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3GreaterThan(String value) {
            addCriterion("work_area_3 >", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3GreaterThanOrEqualTo(String value) {
            addCriterion("work_area_3 >=", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3LessThan(String value) {
            addCriterion("work_area_3 <", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3LessThanOrEqualTo(String value) {
            addCriterion("work_area_3 <=", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3Like(String value) {
            addCriterion("work_area_3 like", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3NotLike(String value) {
            addCriterion("work_area_3 not like", value, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3In(List<String> values) {
            addCriterion("work_area_3 in", values, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3NotIn(List<String> values) {
            addCriterion("work_area_3 not in", values, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3Between(String value1, String value2) {
            addCriterion("work_area_3 between", value1, value2, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea3NotBetween(String value1, String value2) {
            addCriterion("work_area_3 not between", value1, value2, "workArea3");
            return (Criteria) this;
        }

        public Criteria andWorkArea4IsNull() {
            addCriterion("work_area_4 is null");
            return (Criteria) this;
        }

        public Criteria andWorkArea4IsNotNull() {
            addCriterion("work_area_4 is not null");
            return (Criteria) this;
        }

        public Criteria andWorkArea4EqualTo(String value) {
            addCriterion("work_area_4 =", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4NotEqualTo(String value) {
            addCriterion("work_area_4 <>", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4GreaterThan(String value) {
            addCriterion("work_area_4 >", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4GreaterThanOrEqualTo(String value) {
            addCriterion("work_area_4 >=", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4LessThan(String value) {
            addCriterion("work_area_4 <", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4LessThanOrEqualTo(String value) {
            addCriterion("work_area_4 <=", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4Like(String value) {
            addCriterion("work_area_4 like", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4NotLike(String value) {
            addCriterion("work_area_4 not like", value, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4In(List<String> values) {
            addCriterion("work_area_4 in", values, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4NotIn(List<String> values) {
            addCriterion("work_area_4 not in", values, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4Between(String value1, String value2) {
            addCriterion("work_area_4 between", value1, value2, "workArea4");
            return (Criteria) this;
        }

        public Criteria andWorkArea4NotBetween(String value1, String value2) {
            addCriterion("work_area_4 not between", value1, value2, "workArea4");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNull() {
            addCriterion("is_new is null");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNotNull() {
            addCriterion("is_new is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewEqualTo(String value) {
            addCriterion("is_new =", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotEqualTo(String value) {
            addCriterion("is_new <>", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThan(String value) {
            addCriterion("is_new >", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThanOrEqualTo(String value) {
            addCriterion("is_new >=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThan(String value) {
            addCriterion("is_new <", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThanOrEqualTo(String value) {
            addCriterion("is_new <=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLike(String value) {
            addCriterion("is_new like", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotLike(String value) {
            addCriterion("is_new not like", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewIn(List<String> values) {
            addCriterion("is_new in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotIn(List<String> values) {
            addCriterion("is_new not in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewBetween(String value1, String value2) {
            addCriterion("is_new between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotBetween(String value1, String value2) {
            addCriterion("is_new not between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesIsNull() {
            addCriterion("number_of_times is null");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesIsNotNull() {
            addCriterion("number_of_times is not null");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesEqualTo(Integer value) {
            addCriterion("number_of_times =", value, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesNotEqualTo(Integer value) {
            addCriterion("number_of_times <>", value, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesGreaterThan(Integer value) {
            addCriterion("number_of_times >", value, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("number_of_times >=", value, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesLessThan(Integer value) {
            addCriterion("number_of_times <", value, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesLessThanOrEqualTo(Integer value) {
            addCriterion("number_of_times <=", value, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesIn(List<Integer> values) {
            addCriterion("number_of_times in", values, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesNotIn(List<Integer> values) {
            addCriterion("number_of_times not in", values, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesBetween(Integer value1, Integer value2) {
            addCriterion("number_of_times between", value1, value2, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andNumberOfTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("number_of_times not between", value1, value2, "numberOfTimes");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseIsNull() {
            addCriterion("type_of_case is null");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseIsNotNull() {
            addCriterion("type_of_case is not null");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseEqualTo(String value) {
            addCriterion("type_of_case =", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseNotEqualTo(String value) {
            addCriterion("type_of_case <>", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseGreaterThan(String value) {
            addCriterion("type_of_case >", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseGreaterThanOrEqualTo(String value) {
            addCriterion("type_of_case >=", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseLessThan(String value) {
            addCriterion("type_of_case <", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseLessThanOrEqualTo(String value) {
            addCriterion("type_of_case <=", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseLike(String value) {
            addCriterion("type_of_case like", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseNotLike(String value) {
            addCriterion("type_of_case not like", value, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseIn(List<String> values) {
            addCriterion("type_of_case in", values, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseNotIn(List<String> values) {
            addCriterion("type_of_case not in", values, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseBetween(String value1, String value2) {
            addCriterion("type_of_case between", value1, value2, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andTypeOfCaseNotBetween(String value1, String value2) {
            addCriterion("type_of_case not between", value1, value2, "typeOfCase");
            return (Criteria) this;
        }

        public Criteria andYearAddCountIsNull() {
            addCriterion("year_add_count is null");
            return (Criteria) this;
        }

        public Criteria andYearAddCountIsNotNull() {
            addCriterion("year_add_count is not null");
            return (Criteria) this;
        }

        public Criteria andYearAddCountEqualTo(Integer value) {
            addCriterion("year_add_count =", value, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountNotEqualTo(Integer value) {
            addCriterion("year_add_count <>", value, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountGreaterThan(Integer value) {
            addCriterion("year_add_count >", value, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("year_add_count >=", value, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountLessThan(Integer value) {
            addCriterion("year_add_count <", value, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountLessThanOrEqualTo(Integer value) {
            addCriterion("year_add_count <=", value, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountIn(List<Integer> values) {
            addCriterion("year_add_count in", values, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountNotIn(List<Integer> values) {
            addCriterion("year_add_count not in", values, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountBetween(Integer value1, Integer value2) {
            addCriterion("year_add_count between", value1, value2, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearAddCountNotBetween(Integer value1, Integer value2) {
            addCriterion("year_add_count not between", value1, value2, "yearAddCount");
            return (Criteria) this;
        }

        public Criteria andYearCostIsNull() {
            addCriterion("year_cost is null");
            return (Criteria) this;
        }

        public Criteria andYearCostIsNotNull() {
            addCriterion("year_cost is not null");
            return (Criteria) this;
        }

        public Criteria andYearCostEqualTo(Integer value) {
            addCriterion("year_cost =", value, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostNotEqualTo(Integer value) {
            addCriterion("year_cost <>", value, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostGreaterThan(Integer value) {
            addCriterion("year_cost >", value, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("year_cost >=", value, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostLessThan(Integer value) {
            addCriterion("year_cost <", value, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostLessThanOrEqualTo(Integer value) {
            addCriterion("year_cost <=", value, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostIn(List<Integer> values) {
            addCriterion("year_cost in", values, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostNotIn(List<Integer> values) {
            addCriterion("year_cost not in", values, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostBetween(Integer value1, Integer value2) {
            addCriterion("year_cost between", value1, value2, "yearCost");
            return (Criteria) this;
        }

        public Criteria andYearCostNotBetween(Integer value1, Integer value2) {
            addCriterion("year_cost not between", value1, value2, "yearCost");
            return (Criteria) this;
        }

        public Criteria andMemberStateIsNull() {
            addCriterion("member_state is null");
            return (Criteria) this;
        }

        public Criteria andMemberStateIsNotNull() {
            addCriterion("member_state is not null");
            return (Criteria) this;
        }

        public Criteria andMemberStateEqualTo(String value) {
            addCriterion("member_state =", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateNotEqualTo(String value) {
            addCriterion("member_state <>", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateGreaterThan(String value) {
            addCriterion("member_state >", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateGreaterThanOrEqualTo(String value) {
            addCriterion("member_state >=", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateLessThan(String value) {
            addCriterion("member_state <", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateLessThanOrEqualTo(String value) {
            addCriterion("member_state <=", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateLike(String value) {
            addCriterion("member_state like", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateNotLike(String value) {
            addCriterion("member_state not like", value, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateIn(List<String> values) {
            addCriterion("member_state in", values, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateNotIn(List<String> values) {
            addCriterion("member_state not in", values, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateBetween(String value1, String value2) {
            addCriterion("member_state between", value1, value2, "memberState");
            return (Criteria) this;
        }

        public Criteria andMemberStateNotBetween(String value1, String value2) {
            addCriterion("member_state not between", value1, value2, "memberState");
            return (Criteria) this;
        }

        public Criteria andExitModeIsNull() {
            addCriterion("exit_mode is null");
            return (Criteria) this;
        }

        public Criteria andExitModeIsNotNull() {
            addCriterion("exit_mode is not null");
            return (Criteria) this;
        }

        public Criteria andExitModeEqualTo(String value) {
            addCriterion("exit_mode =", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeNotEqualTo(String value) {
            addCriterion("exit_mode <>", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeGreaterThan(String value) {
            addCriterion("exit_mode >", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeGreaterThanOrEqualTo(String value) {
            addCriterion("exit_mode >=", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeLessThan(String value) {
            addCriterion("exit_mode <", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeLessThanOrEqualTo(String value) {
            addCriterion("exit_mode <=", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeLike(String value) {
            addCriterion("exit_mode like", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeNotLike(String value) {
            addCriterion("exit_mode not like", value, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeIn(List<String> values) {
            addCriterion("exit_mode in", values, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeNotIn(List<String> values) {
            addCriterion("exit_mode not in", values, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeBetween(String value1, String value2) {
            addCriterion("exit_mode between", value1, value2, "exitMode");
            return (Criteria) this;
        }

        public Criteria andExitModeNotBetween(String value1, String value2) {
            addCriterion("exit_mode not between", value1, value2, "exitMode");
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