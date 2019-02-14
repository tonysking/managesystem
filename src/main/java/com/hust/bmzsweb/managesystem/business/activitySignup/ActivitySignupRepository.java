package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivitySignupRepository extends JpaRepository<ActivitySignup,Integer>, JpaSpecificationExecutor<ActivitySignup> {

    List<ActivitySignup> findAllByUserIdEquals(Integer userId);

    ActivitySignup findByUserIdAndActIdEquals(Integer userId,Integer actId);
}