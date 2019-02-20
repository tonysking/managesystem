package com.hust.bmzsweb.managesystem.business.userCollection;

import com.hust.bmzsweb.managesystem.business.user.UsersRepository;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleTest {
    @Autowired
    UserCollectionRepository userCollectionRepository;
    @Autowired
    UsersRepository usersRepository;
    //测试jpa接口方法
    @Test
    public void test1(){

        userCollectionRepository.deleteUserCollectionEntityByCId(1);


    }


}
