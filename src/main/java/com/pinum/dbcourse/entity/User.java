package com.pinum.dbcourse.entity;

import com.pinum.dbcourse.entity.jsonmodels.UserInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author lnurullina
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private Long braceletId;

    private UserInfo userInfo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBraceletId() {
        return braceletId;
    }

    public void setBraceletId(Long braceletId) {
        this.braceletId = braceletId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
