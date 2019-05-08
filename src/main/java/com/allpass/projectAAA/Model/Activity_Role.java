package com.allpass.projectAAA.Model;

import javax.persistence.*;

@Entity
public class Activity_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ACTIVITY_ROLE_ID")
    private Long id;
    private String name;

    public Activity_Role(){}

    public Activity_Role(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
