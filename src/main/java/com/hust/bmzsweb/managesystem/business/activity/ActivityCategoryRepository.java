package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActivityCategoryRepository extends JpaRepository<ActivityCategory,Integer>, JpaSpecificationExecutor<User> {
    List<ActivityCategory> findAllByCategoryNameNotNull();

    //小程序
    ActivityCategory findActivityCategoryByCategoryType(Integer categoryType);
}
