package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ActivitySignupRepository extends JpaRepository<ActivitySignup,Integer>, JpaSpecificationExecutor<ActivitySignup> {

    List<ActivitySignup> findAllByUserIdEquals(Integer userId);

    ActivitySignup findByUserIdAndActIdEquals(Integer userId,Integer actId);

    ActivitySignup findByUserSignupId(Integer userSignId);

    // ActivitySignup findByUserSignupId(Integer userSignId);

//    @Transactional
//    @Modifying
//    @Query("update ActivitySignup a set a.userSignupStatus=1 where a.userSignupId=?1")
//    void banSignUp(Integer actId);

    @Transactional
    @Query(value = "UPDATE user_signup_info SET user_signup_status = 1 WHERE user_signup_id = ?1", nativeQuery = true)
    @Modifying
    void banSignUp(Integer stadiumId);


    //        @Transactional
//        @Modifying
//        @Query("delete from ActivityInfo a where a.actId=?1")
//        int deleteActivitySignupsByActId(Integer actId);
    void deleteActivitySignupsByActId(Integer actId);

    void deleteActivitySignupByActIdAndUserId(Integer actId, Integer userId);
}
