package com.camel.core.entity.process;

import lombok.Data;

/**
 *
 *                       .::::.
 *                     .::::::::.
 *                    :::::::::::
 *                 ..:::::::::::'      流程表单
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
 * @since 2019/7/7
 **/
@Data
public class ActivitiForm {
    private String comment;

    private String businessId;

    private boolean isPass;

    public ActivitiForm(String comment, String businessId, boolean isPass) {
        this.comment = comment;
        this.businessId = businessId;
        this.isPass = isPass;
    }

    public ActivitiForm() {
    }
}
