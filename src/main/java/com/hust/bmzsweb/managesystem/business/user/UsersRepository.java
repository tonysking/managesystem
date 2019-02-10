package com.hust.bmzsweb.managesystem.business.user;

import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    Page<User> findAllByUserNickNameContaining(String searchText,  Pageable pageable);

    @Transactional
    @Modifying
    @Query("update User u set u.userStatus = 1 where u.userId =?1")
    void lockUserById(Integer userId);
}
