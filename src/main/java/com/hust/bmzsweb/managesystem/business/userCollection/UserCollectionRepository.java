package com.hust.bmzsweb.managesystem.business.userCollection;

import com.hust.bmzsweb.managesystem.business.userCollection.UserCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserCollectionRepository extends JpaRepository<UserCollectionEntity,Integer>, JpaSpecificationExecutor<UserCollectionEntity> {

    List<UserCollectionEntity> findAllByUserIdEquals(Integer userId);

    UserCollectionEntity findByUserIdAndActIdEquals(Integer userId, Integer actId);

    void deleteUserCollectionEntitiesByActId(Integer actId);

    void deleteUserCollectionEntityByUserIdAndActId(Integer userId,Integer actId);

//    @Transactional
//    @Modifying
//    @Query("delete from UserCollectionEntity where userId = ?1 and actId =?2")
//    void deleteByUserIdAndActId(Integer userId,Integer actId);

//    @Transactional
//    @Modifying
//    @Query("update ActivityInfo  a set a.actRunStatus=?2 where a.actId=?1")
//    void deleteUserCollectionEntitiesByActIdEqualsAndUserIdEquals(Integer actId,Integer actRunStatus)

}
