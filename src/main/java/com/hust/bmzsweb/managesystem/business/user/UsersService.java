package com.hust.bmzsweb.managesystem.business.user;

import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.business.user.entity.WXUser;
import com.hust.bmzsweb.managesystem.business.userBrowerHistory.UserBrowsingHistoryEntity;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UsersService {

     Page<User> findAllByLike(String searchText, PageRequest pageRequest);

     void lockUserById(Integer userId);

     void unLockUserById(Integer userId);

     //小程序
     User findUserById(Integer userID);
     void updateUserInfo(User user);
     List<QueryActivityListModel> findUserCreateAct(Integer userID);
     List<QueryActivityListModel> findUserSignupAct(Integer userID);

     boolean queryUserNickNameIsExist(String userNickName);
     void saveUserInfo(User user);

     User findUserByNickName(String userNickName);

     Integer saveUser(String nickName,String openId);

     User findUserByOpenId(Integer openId);
     List<UserBrowsingHistoryEntity> findUserBrowsingHistory(Integer userID);

     //发起者删除报名成员
     Boolean deleteSignupUser(Integer actId, Integer userId);
}
