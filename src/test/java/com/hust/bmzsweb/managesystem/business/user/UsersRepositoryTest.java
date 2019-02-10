package com.hust.bmzsweb.managesystem.business.user;

import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @Test
    public void findAllByUserNickNameContaining() {
        Sort sort = new Sort(Sort.Direction.ASC, "userId");

        //index 从0开始
        Pageable pageable = PageRequest.of(0,5,sort);
        Page<User> page = usersRepository.findAllByUserNickNameContaining("", pageable);
        System.out.println("当前页面的集合:"+page.getContent());
    }
}