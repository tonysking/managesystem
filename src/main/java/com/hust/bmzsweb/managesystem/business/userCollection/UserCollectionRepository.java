package com.hust.bmzsweb.managesystem.business.userCollection;

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

    @Transactional
    void deleteUserCollectionEntityByCId(Integer cId);
    @Transactional
    void deleteUserCollectionEntityByUserIdAndActId(Integer userId,Integer actId);

//    @Modifying
//    @Transactional
//    @Query("delete from UserCollectionEntity uc where uc.userId=?1 and uc.actId=?2")
//    void delete(Integer userId, Integer actId);


}
