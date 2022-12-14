package cn.net.withub.ums.entity;

public class UmsMenu {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.page_name
     *
     * @mbggenerated
     */
    private String pageName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.href
     *
     * @mbggenerated
     */
    private String href;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.menu_name
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ums_menu.sort_no
     *
     * @mbggenerated
     */
    private Integer sortNo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.id
     *
     * @return the value of ums_menu.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.id
     *
     * @param id the value for ums_menu.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.page_name
     *
     * @return the value of ums_menu.page_name
     *
     * @mbggenerated
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.page_name
     *
     * @param pageName the value for ums_menu.page_name
     *
     * @mbggenerated
     */
    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.href
     *
     * @return the value of ums_menu.href
     *
     * @mbggenerated
     */
    public String getHref() {
        return href;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.href
     *
     * @param href the value for ums_menu.href
     *
     * @mbggenerated
     */
    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.menu_name
     *
     * @return the value of ums_menu.menu_name
     *
     * @mbggenerated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.menu_name
     *
     * @param menuName the value for ums_menu.menu_name
     *
     * @mbggenerated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ums_menu.sort_no
     *
     * @return the value of ums_menu.sort_no
     *
     * @mbggenerated
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ums_menu.sort_no
     *
     * @param sortNo the value for ums_menu.sort_no
     *
     * @mbggenerated
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_menu
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UmsMenu other = (UmsMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPageName() == null ? other.getPageName() == null : this.getPageName().equals(other.getPageName()))
            && (this.getHref() == null ? other.getHref() == null : this.getHref().equals(other.getHref()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_menu
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPageName() == null) ? 0 : getPageName().hashCode());
        result = prime * result + ((getHref() == null) ? 0 : getHref().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
        return result;
    }
}