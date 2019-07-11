package com.camel.oa.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.camel.core.entity.BaseProcessPaginationEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 *                       .::::.
 *                     .::::::::.
 *                    :::::::::::
 *                 ..:::::::::::'      出差管理计划
 *              '::::::::::::'
 *                .::::::::::
 *           '::::::::::::::..
 *                ..::::::::::::.
 *              ``::::::::::::::::
 *               ::::``:::::::::'        .:::.
 *              ::::'   ':::::'       .::::::::.
 *            .::::'      ::::     .:::::::'::::.
 *           .:::'       :::::  .:::::::::' ':::::.
 *          .::'        :::::.:::::::::'      ':::::.
 *         .::'         ::::::::::::::'         ``::::.
 *     ...:::           ::::::::::::'              ``::.
 *    ```` ':.          ':::::::::'                  ::::..
 *                       '.:::::'                    ':'````..
 * @author baily
 * @since 2019/7/8
 **/
@Data
public class Errand extends BaseProcessPaginationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 编号
     */
    private String eno;
    /**
     * 人员
     */
    private Integer uid;
    /**
     * 姓名
     */
    private String username;
    /**
     * 目的地
     */
    private String target;
    /**
     * 差期开始
     */
    private Timestamp dateRangeStart;
    /**
     * 差期结束
     */
    private Timestamp dateRangeEnd;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 部门
     */
    private Integer orgId;
    /**
     * 部门
     */
    private String orgName;
    /**
     * 申请时间
     */
    private Date applyDate;
    /**
     * 事由类型
     */
    private Integer planType;
    /**
     * 目的和计划
     */
    private String plan;
    /**
     * 部门负责人
     */
    private Integer director;
    /**
     * 部门负责人意见
     */
    private String directorComment;
    /**
     * 分管领导
     */
    private Integer leader;
    /**
     * 分管领导意见
     */
    private String leaderComment;
    /**
     * 运营管理
     */
    private Integer operator;
    /**
     * 运营管理确认意见
     */
    private String operatorComment;
    /**
     * 人力资源
     */
    private Integer resources;
    /**
     * 人力资源意见
     */
    private String resourcesComment;
    /**
     * 总经理
     */
    private Integer manager;
    /**
     * 总经理意见
     */
    private String managerComment;

    public Errand() {
    }

    public Errand(Integer id) {
        this.id = id;
    }

    public Errand(Integer id, Integer status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Errand{" +
                ", id=" + id +
                ", eno=" + eno +
                ", uid=" + uid +
                ", target=" + target +
                ", dateRangeStart=" + dateRangeStart +
                ", dateRangeEnd=" + dateRangeEnd +
                ", status=" + status +
                ", orgId=" + orgId +
                ", applyDate=" + applyDate +
                ", planType=" + planType +
                ", plan=" + plan +
                ", director=" + director +
                ", directorComment=" + directorComment +
                ", leader=" + leader +
                ", leaderComment=" + leaderComment +
                ", operator=" + operator +
                ", operatorComment=" + operatorComment +
                ", resources=" + resources +
                ", resourcesComment=" + resourcesComment +
                ", manager=" + manager +
                ", managerComment=" + managerComment +
                "}";
    }
}
