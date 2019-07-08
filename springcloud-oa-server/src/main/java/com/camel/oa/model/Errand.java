package com.camel.oa.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.camel.core.entity.BaseProcessPaginationEntity;
import lombok.Data;

import java.io.Serializable;

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
    private Integer id;
    /**
     * 人员
     */
    private Integer uid;
    /**
     * 编号
     */
    private Integer eno;
    /**
     * 目的地
     */
    private String target;
    /**
     * 差期
     */
    @TableField("dateRange")
    private String dateRange;
    /**
     * 状态
     */
    private Integer status;

    @Override
    public String toString() {
        return "Errand{" +
                ", id=" + id +
                ", uid=" + uid +
                ", id=" + eno +
                ", target=" + target +
                ", dateRange=" + dateRange +
                ", status=" + status +
                "}";
    }
}
