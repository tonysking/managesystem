package com.hust.bmzsweb.managesystem.business.activity.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityWithAllStatusModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceImplTest {

    @Autowired
    ActivityService activityService;

    @Test
    public void testWithAllStatus(){
        QueryActivityWithAllStatusModel queryActivityWithAllStatusModel = activityService.queryActWithAllStatus(2, 9);
        System.out.println("queryActivityWithAllStatusModel:"+queryActivityWithAllStatusModel);
    }

    @Test
    public void testSearch(){
        List<QueryActivityDetailModel> activity = activityService.queryActivityByTitleorderByHeat("",false);
        System.out.println(activity);

    }
}