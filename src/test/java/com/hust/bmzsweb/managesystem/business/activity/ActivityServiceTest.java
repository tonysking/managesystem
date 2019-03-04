package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    ActivityService activityService;

    @Test
    public void testSearchByContent(){
        List<QueryActivityDetailModel> activity = activityService.queryActivityByTitleorderByHeat("活动1",false);
        System.out.println("活动1:"+activity);
    }
}