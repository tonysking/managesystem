package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityInfo,Integer>, JpaSpecificationExecutor<ActivityInfo> {
        Page<ActivityInfo> findAllByUserId(Integer userId, Pageable pageable);

        Page<ActivityInfo> findAllByActTitleContaining(String searchText,  Pageable pageable);

        @Transactional
        @Modifying
        @Query("update ActivityInfo a  set a.actRunStatus=1 where a.actId=?1")
        void banAcitivity(Integer actId);

        @Transactional
        @Modifying
        @Query("update ActivityInfo  a set a.actRunStatus=0 where a.actId=?1")
        void allowAcitivity(Integer actId);


        @Transactional
        @Modifying
        @Query("update ActivityInfo  a set a.actRunStatus=?2 where a.actId=?1")
        void updateActRunStatus(Integer actId,Integer actRunStatus);



//        @Transactional
//        @Modifying
//        @Query("delete a from ActivityInfo a where a.actId=?1")
//        void deleteActivityInfoBy(Integer actId);


        ActivityInfo findByActId(Integer actId);

        Page<ActivityInfo> findAllByActIdIn(List<Integer>actId,  Pageable pageable);

        //小程序
        List<ActivityInfo> findAllByUserId(Integer userID);

        ActivityInfo findByActIdAndUserIdEquals(Integer actId,Integer userId);




}
