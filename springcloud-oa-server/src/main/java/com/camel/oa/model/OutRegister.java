package com.camel.oa.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.camel.core.entity.BaseProcessPaginationEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
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
    private Integer deptId;
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
    private String deptName;
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
    private String deptDirector;
    /**
     * 是否有效标识
     */
    private String validFlag;

    @Override
    public String toString() {
        return "OutRegister{" +
                "id=" + id +
                ", uid=" + uid +
                ", deptId=" + deptId +
                ", outStart=" + outStart +
                ", outInterval=" + outInterval +
                ", outEnd=" + outEnd +
                ", userName='" + userName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", outPlace='" + outPlace + '\'' +
                ", outReason='" + outReason + '\'' +
                ", status='" + status + '\'' +
                ", deptDirector='" + deptDirector + '\'' +
                ", validFlag='" + validFlag + '\'' +
                '}';
    }
}