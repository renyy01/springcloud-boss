package com.camel.redis.entity;

import java.io.Serializable;
import java.util.List;

/**
 @author baily */
public class RedisUser implements Serializable {

    private long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String nickname;

    private String address;

    private String mobile;

    private String remark;

    private String email;

    private List<String> roles;

    public RedisUser() {
    }

    public RedisUser(Integer id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }

    public RedisUser(Integer id, String username, String nickname, String address, String mobile, String remark, String email) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.address = address;
        this.mobile = mobile;
        this.remark = remark;
        this.email = email;
    }

    public RedisUser(Integer id, String username, String nickname, String address, String mobile, String remark, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.address = address;
        this.mobile = mobile;
        this.remark = remark;
        this.email = email;
        this.roles = roles;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
