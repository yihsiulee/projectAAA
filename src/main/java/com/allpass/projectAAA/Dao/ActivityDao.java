package com.allpass.projectAAA.Dao;

import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDao extends JpaRepository<Activity,Long> {

    Activity findByActivityFounder(Member activityFounder);


}
