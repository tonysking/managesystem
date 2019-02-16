package com.hust.bmzsweb.managesystem.business.user.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityCategoryRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.user.UsersRepository;
import com.hust.bmzsweb.managesystem.business.user.UsersService;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivityCategoryRepository activityCategoryRepository;
    @Autowired
    ActivitySignupRepository activitySignupRepository;
    @Autowired
    ActivityRepository activityRepository;



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

    @Override
    public void unLockUserById(Integer userId) {
        usersRepository.unLockUserById(userId);
    }

    //小程序
    @Override
    public User findUserById(Integer userID) {
        return usersRepository.findUserByUserId(userID);
    }

    @Override
    public void updateUserInfo(User user) {
        usersRepository.save(user);
    }

    @Override
    public List<QueryActivityListModel> findUserCreateAct(Integer userID) {

        //查询用户发起的所有活动
        List<ActivityInfo> activityInfos = activityRepository.findAllByUserId(userID);
        List<QueryActivityListModel> activityListModels = new ArrayList<>();

        QueryActivityListModel activityModel = null;
        for (ActivityInfo actInfo:activityInfos) {
            activityModel = new QueryActivityListModel();
            activityModel.setActId(actInfo.getActId());
            activityModel.setActTitle(actInfo.getActTitle());
            //查询活动类型名
            ActivityCategory activityCategory = activityCategoryRepository.findActivityCategoryByCategoryType(actInfo.getCategoryType());
            activityModel.setCategory(activityCategory.getCategoryName());

            activityModel.setActRunStatus(actInfo.getActRunStatus());

            //获取活动状态名
            String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
            activityModel.setActStatus(actStatus);

            activityModel.setCreateTime(actInfo.getCreateTime());
            activityModel.setUpdateTime(actInfo.getUpdateTime());

            activityListModels.add(activityModel);

        }

        return activityListModels;



    }

    @Override
    public List<QueryActivityListModel> findUserSignupAct(Integer userID) {

        //查询用户参与的所有活动
        List<ActivitySignup> ActivitySignups = activitySignupRepository.findAllByUserIdEquals(userID);
        List<QueryActivityListModel> activityListModels = new ArrayList<>();
        QueryActivityListModel activityModel = null;
        for(ActivitySignup activitySignup: ActivitySignups){
            activityModel = new QueryActivityListModel();

            //通过参与活动信息查询活动信息
            ActivityInfo actInfo = activityRepository.findByActId(activitySignup.getActId());

            activityModel.setActId(actInfo.getActId());
            activityModel.setActTitle(actInfo.getActTitle());
            //查询活动类型名
            ActivityCategory activityCategory = activityCategoryRepository.findActivityCategoryByCategoryType(actInfo.getCategoryType());
            activityModel.setCategory(activityCategory.getCategoryName());

            activityModel.setActRunStatus(actInfo.getActRunStatus());

            //获取活动状态名
            String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
            activityModel.setActStatus(actStatus);

            activityModel.setCreateTime(actInfo.getCreateTime());
            activityModel.setUpdateTime(actInfo.getUpdateTime());

            activityListModels.add(activityModel);
        }
        return activityListModels;
    }


}
