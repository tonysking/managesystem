package com.hust.bmzsweb.managesystem.business.user;

import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.business.user.entity.WXUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WXUserRepository extends JpaRepository<WXUser,Integer>, JpaSpecificationExecutor<WXUser> {

    WXUser findByNickNameEquals(String nickName);
}
