package com.example.food_project.entity;

import javax.persistence.*;

@Entity(name = "user_detail")
public class UserDetailEntity {

    @Id
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "address")
    private String addresss;

    @Column(name = "moblie_phone")
    private String mobilePhone;

    @Column(name = "street")
    private String street;

    @OneToOne()
    @JoinColumn(name = "id_user")
    private UserEntity user;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
