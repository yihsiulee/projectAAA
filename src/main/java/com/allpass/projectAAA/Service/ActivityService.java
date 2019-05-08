package com.allpass.projectAAA.Service;

import com.allpass.projectAAA.Dao.ActivityDao;
import com.allpass.projectAAA.Model.Activity;
import com.allpass.projectAAA.Model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityService {
    @Resource
    private ActivityDao activityDao;

    public void save(Activity activity){
        activityDao.save(activity);
    }

    public List<Activity> getActivityInfoByActivityFounder(Member activityFounder){
        List<Activity> activity=activityDao.findByActivityFounder(activityFounder);
        return activity;

    }
}
