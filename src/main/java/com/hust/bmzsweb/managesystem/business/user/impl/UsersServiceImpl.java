package com.hust.bmzsweb.managesystem.business.user.impl;

import com.hust.bmzsweb.managesystem.business.user.UsersRepository;
import com.hust.bmzsweb.managesystem.business.user.UsersService;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Page<User> findAllByLike(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
      //  System.out.println("seachText:"+searchText);
     //   System.out.println("pageRequest:"+pageRequest);
        Page<User> page = usersRepository.findAllByUserNickNameContaining(searchText, pageRequest);
      //  System.out.println("当前页面的集合:"+page.getContent());
        return  page;
    }

    @Override
    public void lockUserById(Integer userId) {
        usersRepository.lockUserById(userId);
    }

}
