package com.camel.system.enums;

import com.camel.common.enums.BaseEnum;


public enum MacroStatus implements BaseEnum {
    /**/
    NORMAL("正常", 1), INVALID("无效", 0);
    /**
     备注
     */
    private String description;
    /**
     数据代码
     */
    private Integer code;
    /**
     数据列名
     */
    private String column;

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    public String getColumn() {
        return column;
    }

    MacroStatus(String description, Integer code) {
        this.description = description;
        this.code = code;
        this.column = "status";
    }

    @Override
    public Integer getValue() {
        return getCode();
    }

    @Override
    public String toString() {
        return "MacroStatus{" +
                "description='" + description + '\'' +
                ", code=" + code +
                ", column='" + column + '\'' +
                '}';
    }
}
