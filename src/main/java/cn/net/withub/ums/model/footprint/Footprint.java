/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.net.withub.ums.model.footprint;

import java.util.Date;
import org.apache.struts2.json.annotations.JSON;

/**
 *
 * @author Diluka
 */
public class Footprint {

    Date timePoint;
    Object content;
    Object entity;
    FootprintType type;

    public Date getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(Date timePoint) {
        this.timePoint = timePoint;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @JSON(serialize = false)
    public FootprintType getType() {
        return type;
    }

    public void setType(FootprintType type) {
        this.type = type;
    }

    @JSON(name = "type")
    public String getTypeName() {
        return this.type.toString();
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}
