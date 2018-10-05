package com.pinum.dbcourse.entity;

/**
 * @author lnurullina
 */
public class User {
    private Long id;
    private String name;
    private String surname;
    private String address;

    public User() {
    }

    public User(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public User(Long id, String name, String surname, String address) {
        this(name, surname, address);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
