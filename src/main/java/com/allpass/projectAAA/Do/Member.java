package com.allpass.projectAAA.Do;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    private String id;
    private String name;
    private String idCardNumber;
    private String password;
    private String email;
    private Integer gender;
    private String birthday;
    private Integer educational;
    private Integer study;
    private String special;
    private Float tokenBalance;
    private String phoneNumber;

//    public Member(
//            String id,
//            String name,
//            String idCardNumber,
//            String password,
//            String email,
//            Integer gender,
//            String birthday,
//            Integer educational,
//            Integer study,
//            Float tokenBalance) {
//        this.id=id;
//        this.name=name;
//        this.idCardNumber=idCardNumber;
//        this.password=password;
//        this.email=email;
//        this.gender=gender;
//        this.birthday=birthday;
//        this.educational=educational;
//        this.study=study;
//        this.tokenBalance=tokenBalance;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGender() {
        return gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void setSpecial(String special) {
        this.special = special;
    }
    public String getSpecial() {
        return special;
    }

    public void setEducational(Integer educational) {
        this.educational = educational;
    }

    public Integer getEducational() {
        return educational;
    }

    public void setStudy(Integer study) {
        this.study = study;
    }

    public Integer getStudy() {
        return study;
    }

    public void setTokenBalance(Float tokenBalance) {
        this.tokenBalance = tokenBalance;
    }

    public Float getTokenBalance() {
        return tokenBalance;
    }
}
