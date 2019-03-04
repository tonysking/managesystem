package com.hust.bmzsweb.managesystem.common.utils;

import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {

    @Autowired
    ActivityRepository activityRepository;

    //每隔一小时执行一次 对没有被删除的所有活动进行热度衰减
    @Scheduled(fixedRate = 60*60*1000)
    public void HotReduceShedule() {
        List<ActivityInfo> all = activityRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
            ActivityInfo activityInfo = all.get(i);
            if(activityInfo.getIsDelete()!=null&&!activityInfo.getIsDelete())
            {
               int heat = activityInfo.getActHeat();
                heat = (int)((0.99)*heat);
                activityInfo.setActHeat(heat);
            }
        }
        activityRepository.saveAll(all);
    }


}
