package com.allpass.projectAAA.Model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ACTIVITY_ID")
    private Long id;
    private String activityName;
    private String activityTime;
    private String activityContent;
    private Integer limitedParticipants;
    private Integer articleNumber;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="organizer_member_ID_FK")
    private Member activityOrganizer;
    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "activityReviewer_members",
            joinColumns = @JoinColumn(
                    name = "activity_id",referencedColumnName = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "member_id",referencedColumnName = "MEMBER_ID"))
    private Set<Member> activityParticipants_Reviewer=new HashSet<>();
    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "activityAuthor_members",
            joinColumns = @JoinColumn(
                    name = "activity_id",referencedColumnName = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "member_id",referencedColumnName = "MEMBER_ID"))
    private Set<Member> activityParticipants_Author=new HashSet<>();
    private String activityImg;

    public Activity(
//            String activityName
    ){
//        this.activityName=activityName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityTime(String activityTime) { this.activityTime = activityTime; }

    public String getActivityTime() { return activityTime; }

    public void setActivityOrganizer(Member activityFounder) {
        this.activityOrganizer = activityFounder;
    }

    public Member getActivityOrganizer() {
        return activityOrganizer;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityParticipants_Author(Set<Member> activityParticipants_Author) { this.activityParticipants_Author = activityParticipants_Author; }

    public Set<Member> getActivityParticipants_Author() { return activityParticipants_Author; }

    public void setActivityParticipants_Reviewer(Set<Member> activityParticipants_Reviewer) { this.activityParticipants_Reviewer = activityParticipants_Reviewer; }

    public Set<Member> getActivityParticipants_Reviewer() { return activityParticipants_Reviewer; }

    public void setLimitedParticipants(Integer limitedParticipants) {
        this.limitedParticipants = limitedParticipants;
    }

    public Integer getLimitedParticipants() {
        return limitedParticipants;
    }

    public void setArticleNumber(Integer articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Integer getArticleNumber() {
        return articleNumber;
    }


}