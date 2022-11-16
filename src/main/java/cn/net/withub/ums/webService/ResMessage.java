package cn.net.withub.ums.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Administrator on 2016/4/11.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType( propOrder = {
        "result",
        "errorMsg"
})
@XmlRootElement(name = "ResMessage")
public class ResMessage {

    private boolean result;
    private String errorMsg;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
