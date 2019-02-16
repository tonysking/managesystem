package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.userBrowerHistory.UserBrowserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityBrowserHistoryRepository extends JpaRepository<UserBrowserHistoryEntity,Integer>, JpaSpecificationExecutor<UserBrowserHistoryEntity> {
}
