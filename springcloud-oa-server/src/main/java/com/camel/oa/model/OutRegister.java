package com.camel.oa.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.camel.core.entity.BaseProcessPaginationEntity;

import java.io.Serializable;
import java.util.Date;

public class OutRegister extends BaseProcessPaginationEntity implements Serializable {

    private static final long serialVersionUID = -2139891131145778201L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 部门id
     */
    private Integer orgId;
    /**
     * 外出开始时间
     */
    private Date outStart;
    /**
     * 外出时长
     */
    private Integer outInterval;
    /**
     * 回公司时间
     */
    private Date outEnd;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 部门名称
     */
    private String orgName;
    /**
     * 外出地点
     */
    private String outPlace;
    /**
     * 外出事由
     */
    private String outReason;
    /**
     * 外出状态
     */
    private String status;
    /**
     * 部门负责人
     */
    private String orgDirector;
    /**
     * 是否有效标识
     */
    private String validFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getOutStart() {
        return outStart;
    }

    public void setOutStart(Date outStart) {
        this.outStart = outStart;
    }

    public Integer getOutInterval() {
        return outInterval;
    }

    public void setOutInterval(Integer outInterval) {
        this.outInterval = outInterval;
    }

    public Date getOutEnd() {
        return outEnd;
    }

    public void setOutEnd(Date outEnd) {
        this.outEnd = outEnd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOutPlace() {
        return outPlace;
    }

    public void setOutPlace(String outPlace) {
        this.outPlace = outPlace == null ? null : outPlace.trim();
    }

    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason == null ? null : outReason.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOrgDirector() {
        return orgDirector;
    }

    public void setOrgDirector(String orgDirector) {
        this.orgDirector = orgDirector == null ? null : orgDirector.trim();
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    @Override
    public String toString() {
        return "OutRegister{" +
                "id=" + id +
                ", uid=" + uid +
                ", orgId=" + orgId +
                ", outStart=" + outStart +
                ", outInterval=" + outInterval +
                ", outEnd=" + outEnd +
                ", userName='" + userName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", outPlace='" + outPlace + '\'' +
                ", outReason='" + outReason + '\'' +
                ", status='" + status + '\'' +
                ", orgDirector='" + orgDirector + '\'' +
                ", validFlag='" + validFlag + '\'' +
                '}';
    }
}