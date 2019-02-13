package com.hust.bmzsweb.managesystem.business.user;

import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UsersService {

     Page<User> findAllByLike(String searchText, PageRequest pageRequest);

     void lockUserById(Integer userId);

     void unLockUserById(Integer userId);
}
