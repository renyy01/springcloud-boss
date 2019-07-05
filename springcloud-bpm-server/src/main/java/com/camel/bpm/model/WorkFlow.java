package com.camel.bpm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.camel.bpm.exceptions.String2XmlException;
import com.camel.core.entity.BasePaginationEntity;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2019-05-16
 */
@Data
public class WorkFlow extends BasePaginationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否发布
     */
    private Integer isPublic;
    private String currentState;
    private String nextState;
    @TableField("flowType")
    private Integer flowType;
    private Integer creator;
    private Date createdAt;
    private Integer isDel;
    /**
     * 流程定義
     */
    private String flow;
    private String key;

    public static void readKey(WorkFlow workFlow) {
        if(!StringUtils.isBlank(workFlow.getFlow())){
            try {
                Document document = DocumentHelper.parseText(workFlow.getFlow());
                Element root = document.getRootElement();
                Element data = root.element("process");
                String id = data.attributeValue("id");
                workFlow.setKey(id);
            }catch (Exception e){
                throw new String2XmlException();
            }
        }
    }

    @Override
    public String toString() {
        return "WorkFlow{" +
        ", id=" + id +
        ", name=" + name +
        ", status=" + status +
        ", isPublic=" + isPublic +
        ", currentState=" + currentState +
        ", nextState=" + nextState +
        ", flowType=" + flowType +
        ", creator=" + creator +
        ", createdAt=" + createdAt +
        ", isDel=" + isDel +
        ", flow=" + flow +
        ", key=" + key +
        "}";
    }
}
