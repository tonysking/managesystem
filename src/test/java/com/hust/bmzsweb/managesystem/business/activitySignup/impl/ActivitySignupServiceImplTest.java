package com.hust.bmzsweb.managesystem.business.activitySignup.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.SignUpWithRequiredItemDetailModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitySignupServiceImplTest {

    @Autowired
    ActivityRepository activityRepository;

    @Test
    public void saveActivitySignup() {
        ActivityInfo activityInfo = activityRepository.findByActId(72);
        activityInfo.setParticipantsNumber(30);
    //    activityRepository.save(activityInfo);
    }
}