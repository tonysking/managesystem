/*package com.hust.bmzsweb.managesystem.business.userBrowerHistory;

import com.hust.bmzsweb.managesystem.business.userBrowerHistory.UserBrowsingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserBrowserHistoryRepository extends JpaRepository<UserBrowsingHistoryEntity,Integer>, JpaSpecificationExecutor<UserBrowsingHistoryEntity> {
        @Transactional
        @Modifying
        @Query("delete from UserBrowsingHistoryEntity a where a.actId=?1")
        void deleteUserBrowsingHistoryEntitiesByActId(Integer actId);
}
*/