package com.allpass.projectAAA.Model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ACTIVITY_ID")
    private Long id;
    private String activityName;
    private String activityStart;
    private String activityEnd;
    private String activityContent;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="founder_member_ID_FK")
    private Member activityFounder;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "activity_member",
            joinColumns = @JoinColumn(
                    name = "member_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "activity_id"))
    private Set<Member> activityParticipants;
    private String activityImg;

    public Activity(){

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

    public void setActivityStart(String activityStart) {
        this.activityStart = activityStart;
    }

    public String getActivityStart() {
        return activityStart;
    }

    public void setActivityEnd(String activityEnd) {
        this.activityEnd = activityEnd;
    }

    public String getActivityEnd() {
        return activityEnd;
    }

    public void setActivityFounder(Member activityFounder) {
        this.activityFounder = activityFounder;
    }

    public Member getActivityFounder() {
        return activityFounder;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityParticipants(Set<Member> activityParticipants) {
        this.activityParticipants = activityParticipants;
    }

    public Collection<Member> getActivityParticipants() {
        return activityParticipants;
    }
}