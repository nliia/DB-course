package com.pinum.dbcourse.entity.jsonmodels;

import java.io.Serializable;

/**
 * @author lnurullina
 */
public class UserInfo implements Serializable {
    private String surname;
    private String name;
    private String birthday;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
