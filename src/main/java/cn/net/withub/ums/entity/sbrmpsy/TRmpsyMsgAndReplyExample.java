package cn.net.withub.ums.entity.sbrmpsy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TRmpsyMsgAndReplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TRmpsyMsgAndReplyExample() {
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

        public Criteria andRecordNumberIsNull() {
            addCriterion("record_number is null");
            return (Criteria) this;
        }

        public Criteria andRecordNumberIsNotNull() {
            addCriterion("record_number is not null");
            return (Criteria) this;
        }

        public Criteria andRecordNumberEqualTo(String value) {
            addCriterion("record_number =", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberNotEqualTo(String value) {
            addCriterion("record_number <>", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberGreaterThan(String value) {
            addCriterion("record_number >", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberGreaterThanOrEqualTo(String value) {
            addCriterion("record_number >=", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberLessThan(String value) {
            addCriterion("record_number <", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberLessThanOrEqualTo(String value) {
            addCriterion("record_number <=", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberLike(String value) {
            addCriterion("record_number like", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberNotLike(String value) {
            addCriterion("record_number not like", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberIn(List<String> values) {
            addCriterion("record_number in", values, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberNotIn(List<String> values) {
            addCriterion("record_number not in", values, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberBetween(String value1, String value2) {
            addCriterion("record_number between", value1, value2, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberNotBetween(String value1, String value2) {
            addCriterion("record_number not between", value1, value2, "recordNumber");
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

        public Criteria andSmsNumberIsNull() {
            addCriterion("sms_number is null");
            return (Criteria) this;
        }

        public Criteria andSmsNumberIsNotNull() {
            addCriterion("sms_number is not null");
            return (Criteria) this;
        }

        public Criteria andSmsNumberEqualTo(String value) {
            addCriterion("sms_number =", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberNotEqualTo(String value) {
            addCriterion("sms_number <>", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberGreaterThan(String value) {
            addCriterion("sms_number >", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberGreaterThanOrEqualTo(String value) {
            addCriterion("sms_number >=", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberLessThan(String value) {
            addCriterion("sms_number <", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberLessThanOrEqualTo(String value) {
            addCriterion("sms_number <=", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberLike(String value) {
            addCriterion("sms_number like", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberNotLike(String value) {
            addCriterion("sms_number not like", value, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberIn(List<String> values) {
            addCriterion("sms_number in", values, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberNotIn(List<String> values) {
            addCriterion("sms_number not in", values, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberBetween(String value1, String value2) {
            addCriterion("sms_number between", value1, value2, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsNumberNotBetween(String value1, String value2) {
            addCriterion("sms_number not between", value1, value2, "smsNumber");
            return (Criteria) this;
        }

        public Criteria andSmsContentIsNull() {
            addCriterion("sms_content is null");
            return (Criteria) this;
        }

        public Criteria andSmsContentIsNotNull() {
            addCriterion("sms_content is not null");
            return (Criteria) this;
        }

        public Criteria andSmsContentEqualTo(String value) {
            addCriterion("sms_content =", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotEqualTo(String value) {
            addCriterion("sms_content <>", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentGreaterThan(String value) {
            addCriterion("sms_content >", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentGreaterThanOrEqualTo(String value) {
            addCriterion("sms_content >=", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLessThan(String value) {
            addCriterion("sms_content <", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLessThanOrEqualTo(String value) {
            addCriterion("sms_content <=", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLike(String value) {
            addCriterion("sms_content like", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotLike(String value) {
            addCriterion("sms_content not like", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentIn(List<String> values) {
            addCriterion("sms_content in", values, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotIn(List<String> values) {
            addCriterion("sms_content not in", values, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentBetween(String value1, String value2) {
            addCriterion("sms_content between", value1, value2, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotBetween(String value1, String value2) {
            addCriterion("sms_content not between", value1, value2, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsTypeIsNull() {
            addCriterion("sms_type is null");
            return (Criteria) this;
        }

        public Criteria andSmsTypeIsNotNull() {
            addCriterion("sms_type is not null");
            return (Criteria) this;
        }

        public Criteria andSmsTypeEqualTo(String value) {
            addCriterion("sms_type =", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotEqualTo(String value) {
            addCriterion("sms_type <>", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeGreaterThan(String value) {
            addCriterion("sms_type >", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sms_type >=", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeLessThan(String value) {
            addCriterion("sms_type <", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeLessThanOrEqualTo(String value) {
            addCriterion("sms_type <=", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeLike(String value) {
            addCriterion("sms_type like", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotLike(String value) {
            addCriterion("sms_type not like", value, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeIn(List<String> values) {
            addCriterion("sms_type in", values, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotIn(List<String> values) {
            addCriterion("sms_type not in", values, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeBetween(String value1, String value2) {
            addCriterion("sms_type between", value1, value2, "smsType");
            return (Criteria) this;
        }

        public Criteria andSmsTypeNotBetween(String value1, String value2) {
            addCriterion("sms_type not between", value1, value2, "smsType");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyIsNull() {
            addCriterion("is_need_reply is null");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyIsNotNull() {
            addCriterion("is_need_reply is not null");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyEqualTo(String value) {
            addCriterion("is_need_reply =", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyNotEqualTo(String value) {
            addCriterion("is_need_reply <>", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyGreaterThan(String value) {
            addCriterion("is_need_reply >", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyGreaterThanOrEqualTo(String value) {
            addCriterion("is_need_reply >=", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyLessThan(String value) {
            addCriterion("is_need_reply <", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyLessThanOrEqualTo(String value) {
            addCriterion("is_need_reply <=", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyLike(String value) {
            addCriterion("is_need_reply like", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyNotLike(String value) {
            addCriterion("is_need_reply not like", value, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyIn(List<String> values) {
            addCriterion("is_need_reply in", values, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyNotIn(List<String> values) {
            addCriterion("is_need_reply not in", values, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyBetween(String value1, String value2) {
            addCriterion("is_need_reply between", value1, value2, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andIsNeedReplyNotBetween(String value1, String value2) {
            addCriterion("is_need_reply not between", value1, value2, "isNeedReply");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNull() {
            addCriterion("reply_content is null");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNotNull() {
            addCriterion("reply_content is not null");
            return (Criteria) this;
        }

        public Criteria andReplyContentEqualTo(String value) {
            addCriterion("reply_content =", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotEqualTo(String value) {
            addCriterion("reply_content <>", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThan(String value) {
            addCriterion("reply_content >", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThanOrEqualTo(String value) {
            addCriterion("reply_content >=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThan(String value) {
            addCriterion("reply_content <", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThanOrEqualTo(String value) {
            addCriterion("reply_content <=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLike(String value) {
            addCriterion("reply_content like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotLike(String value) {
            addCriterion("reply_content not like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentIn(List<String> values) {
            addCriterion("reply_content in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotIn(List<String> values) {
            addCriterion("reply_content not in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentBetween(String value1, String value2) {
            addCriterion("reply_content between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotBetween(String value1, String value2) {
            addCriterion("reply_content not between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("receive_time is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("receive_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("receive_time =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("receive_time <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("receive_time >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_time >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("receive_time <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("receive_time <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("receive_time in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("receive_time not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("receive_time between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("receive_time not between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andIsReplyIsNull() {
            addCriterion("is_reply is null");
            return (Criteria) this;
        }

        public Criteria andIsReplyIsNotNull() {
            addCriterion("is_reply is not null");
            return (Criteria) this;
        }

        public Criteria andIsReplyEqualTo(String value) {
            addCriterion("is_reply =", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyNotEqualTo(String value) {
            addCriterion("is_reply <>", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyGreaterThan(String value) {
            addCriterion("is_reply >", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyGreaterThanOrEqualTo(String value) {
            addCriterion("is_reply >=", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyLessThan(String value) {
            addCriterion("is_reply <", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyLessThanOrEqualTo(String value) {
            addCriterion("is_reply <=", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyLike(String value) {
            addCriterion("is_reply like", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyNotLike(String value) {
            addCriterion("is_reply not like", value, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyIn(List<String> values) {
            addCriterion("is_reply in", values, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyNotIn(List<String> values) {
            addCriterion("is_reply not in", values, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyBetween(String value1, String value2) {
            addCriterion("is_reply between", value1, value2, "isReply");
            return (Criteria) this;
        }

        public Criteria andIsReplyNotBetween(String value1, String value2) {
            addCriterion("is_reply not between", value1, value2, "isReply");
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

        public Criteria andCourtNumberIsNull() {
            addCriterion("court_number is null");
            return (Criteria) this;
        }

        public Criteria andCourtNumberIsNotNull() {
            addCriterion("court_number is not null");
            return (Criteria) this;
        }

        public Criteria andCourtNumberEqualTo(String value) {
            addCriterion("court_number =", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberNotEqualTo(String value) {
            addCriterion("court_number <>", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberGreaterThan(String value) {
            addCriterion("court_number >", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberGreaterThanOrEqualTo(String value) {
            addCriterion("court_number >=", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberLessThan(String value) {
            addCriterion("court_number <", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberLessThanOrEqualTo(String value) {
            addCriterion("court_number <=", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberLike(String value) {
            addCriterion("court_number like", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberNotLike(String value) {
            addCriterion("court_number not like", value, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberIn(List<String> values) {
            addCriterion("court_number in", values, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberNotIn(List<String> values) {
            addCriterion("court_number not in", values, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberBetween(String value1, String value2) {
            addCriterion("court_number between", value1, value2, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andCourtNumberNotBetween(String value1, String value2) {
            addCriterion("court_number not between", value1, value2, "courtNumber");
            return (Criteria) this;
        }

        public Criteria andIsSendedIsNull() {
            addCriterion("is_sended is null");
            return (Criteria) this;
        }

        public Criteria andIsSendedIsNotNull() {
            addCriterion("is_sended is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendedEqualTo(Integer value) {
            addCriterion("is_sended =", value, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedNotEqualTo(Integer value) {
            addCriterion("is_sended <>", value, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedGreaterThan(Integer value) {
            addCriterion("is_sended >", value, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_sended >=", value, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedLessThan(Integer value) {
            addCriterion("is_sended <", value, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedLessThanOrEqualTo(Integer value) {
            addCriterion("is_sended <=", value, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedIn(List<Integer> values) {
            addCriterion("is_sended in", values, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedNotIn(List<Integer> values) {
            addCriterion("is_sended not in", values, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedBetween(Integer value1, Integer value2) {
            addCriterion("is_sended between", value1, value2, "isSended");
            return (Criteria) this;
        }

        public Criteria andIsSendedNotBetween(Integer value1, Integer value2) {
            addCriterion("is_sended not between", value1, value2, "isSended");
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