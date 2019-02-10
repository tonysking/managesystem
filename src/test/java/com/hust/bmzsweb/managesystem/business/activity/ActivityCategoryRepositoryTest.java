package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityCategoryRepositoryTest {

    @Autowired
    ActivityCategoryRepository activityCategoryRepository;

    @Test
    public void testFindAll(){
        List<ActivityCategory> allByCategoryNameNotNull = activityCategoryRepository.findAllByCategoryNameNotNull();
        System.out.println(allByCategoryNameNotNull);
    }
}