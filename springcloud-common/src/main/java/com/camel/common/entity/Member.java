package com.camel.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 〈会员实体〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
@Data
public class Member implements Serializable {
    private long serialVersionUID = 1L;

    private int id;
    private String memberName;
    private String password;
    private String mobile;
    private String email;
    private short sex;
    private Date birthday;
    private Date createTime;
    private Set<Role> roles;

    public Member(int id, String memberName) {
        this.id = id;
        this.memberName = memberName;
    }
}
