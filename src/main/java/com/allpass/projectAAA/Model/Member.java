package com.allpass.projectAAA.Model;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "idCardNumber"))
public class  Member  {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String idCardNumber;
    private String password;
    private String email;
    private Integer gender;
    private String birthday;
    private Integer education;
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
    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "activityParticipants_Reviewer")
//    @JoinTable(
//            name = "activity_member",
//            joinColumns = @JoinColumn(
//                    name = "member_id",referencedColumnName = "MEMBER_ID"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "activity_id",referencedColumnName = "ACTIVITY_ID"))
    private Set<Activity> activityParticipant_Reviewer=new HashSet<>();
    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "activityParticipants_Author")
    private Set<Activity> activityParticipant_Author=new HashSet<>();
    @OneToMany(mappedBy="activityOrganizer")
    private Set<Activity> activityOrganizer;
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private Set<ArticleReview> articleReviews;


    public Member(){

    }
//    public Member(
//            Long id,
//            String name,
//            String idCardNumber,
//            String password,
//            String email,
//            String phoneNumber,
//            Integer gender,
//            String birthday,
//            String special,
//            Integer educational,
//            Integer study,
//            Float tokenBalance
//    ) {
//        this.id=id;
//        this.name=name;
//        this.idCardNumber=idCardNumber;
//        this.password=password;
//        this.email=email;
//        this.phoneNumber=phoneNumber;
//        this.gender=gender;
//        this.birthday=birthday;
//        this.education=educational;
//        this.study=study;
//        this.tokenBalance=tokenBalance;
//        this.special=special;
//    }
//    public Member(
//            Long id,
//            String name,
//            String idCardNumber,
//            String password,
//            String email,
//            String phoneNumber,
//            Integer gender,
//            String birthday,
//            String special,
//            Integer educational,
//            Integer study,
//            Float tokenBalance,
//            Collection<Member_Role> roles
//    ) {
//        this.id=id;
//        this.name=name;
//        this.idCardNumber=idCardNumber;
//        this.password=password;
//        this.email=email;
//        this.phoneNumber=phoneNumber;
//        this.gender=gender;
//        this.birthday=birthday;
//        this.education=educational;
//        this.study=study;
//        this.tokenBalance=tokenBalance;
//        this.special=special;
//        this.roles=roles;
//    }





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

    public void setEducation(Integer educational) {
        this.education = educational;
    }

    public Integer getEducation() {
        return education;
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

    public void setActivityParticipant_Reviewer(Set<Activity> activityParticipant_Reviewer) { this.activityParticipant_Reviewer = activityParticipant_Reviewer; }

    public Set<Activity> getActivityParticipant_Reviewer() { return activityParticipant_Reviewer; }

    public void setActivityParticipant_Author(Set<Activity> activityParticipant_Author) { this.activityParticipant_Author = activityParticipant_Author; }

    public Set<Activity> getActivityParticipant_Author() { return activityParticipant_Author; }

    public void setActivityOrganizer(Set<Activity> activityOrganizer) { this.activityOrganizer = activityOrganizer; }

    public Set<Activity> getActivityOrganizer() { return activityOrganizer; }

    //    public void setActivityOrganizer(Set<Activity> activityOrganizer) { this.activityOrganizer = activityOrganizer; }
//
//    public Set<Activity> getActivityOrganizer() { return activityOrganizer; }
    //    public void setArticleReviews(Set<ArticleReview> articleReviews) { this.articleReviews = articleReviews; }
//
//    public Set<ArticleReview> getArticleReviews() { return articleReviews; }

    @Override
    public int hashCode() {
        // Objects 有 hash() 方法可以使用
        // 以下可以簡化為 return Objects.hash(name, number);
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.idCardNumber);
        return hash;
    }
}
