package cn.net.withub.ums.action.config.verify;

public class PageUtil {


    private Integer page;
    private Integer limit;
    private Integer start;

    //page从1开始
    public PageUtil(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
        this.start = (page - 1) * limit;
    }

    public PageUtil(){
        super();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }


}
