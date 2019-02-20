package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activity.ActivityCategoryRepository;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBrowerHistoryRepositoryTest {

    @Autowired
    ActivitySignupRepository activitySignupRepository;

    @Test
    public void testFindByUserId(){
        List<ActivitySignup> activitySignup = activitySignupRepository.findAllByUserIdEquals(1);
        System.out.println(activitySignup);
    }

}