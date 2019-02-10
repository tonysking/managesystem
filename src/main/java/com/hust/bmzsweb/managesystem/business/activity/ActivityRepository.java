package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActivityRepository extends JpaRepository<ActivityInfo,Integer>, JpaSpecificationExecutor<User> {
        Page<ActivityInfo> findAllByUserId(Integer userId, Pageable pageable);

}
