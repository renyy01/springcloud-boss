package com.camel.core.entity.process;

import lombok.Data;

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
