package com.allpass.projectAAA.Model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idCardNumber"))
public class  Member  {
    @Id
    @Column(name = "MEMBER_ID")
    private Long id;
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "members_roles",
            joinColumns = @JoinColumn(
                    name = "member_id", referencedColumnName = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "MEMBER_ROLE_ID"))
//    @OneToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name="MEMBER_ROLE_ID", referencedColumnName="ROLE_ID")
    private Collection<Member_Role> roles;
    @ManyToMany(mappedBy = "activityParticipants")
    private Set<Activity> activity;

    public Member(){

    }
    public Member(
            Long id,
            String name,
            String idCardNumber,
            String password,
            String email,
            String phoneNumber,
            Integer gender,
            String birthday,
            String special,
            Integer educational,
            Integer study,
            Float tokenBalance
    ) {
        this.id=id;
        this.name=name;
        this.idCardNumber=idCardNumber;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
        this.birthday=birthday;
        this.educational=educational;
        this.study=study;
        this.tokenBalance=tokenBalance;
        this.special=special;
    }
    public Member(
            Long id,
            String name,
            String idCardNumber,
            String password,
            String email,
            String phoneNumber,
            Integer gender,
            String birthday,
            String special,
            Integer educational,
            Integer study,
            Float tokenBalance,
            Collection<Member_Role> roles
    ) {
        this.id=id;
        this.name=name;
        this.idCardNumber=idCardNumber;
        this.password=password;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
        this.birthday=birthday;
        this.educational=educational;
        this.study=study;
        this.tokenBalance=tokenBalance;
        this.special=special;
        this.roles=roles;
    }





    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() { return id; }

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

    public String getPassword(){ return password; }

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

    public Collection<Member_Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Member_Role> roles) {
        this.roles = roles;
    }

}
