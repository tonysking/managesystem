package com.hust.bmzsweb.managesystem.business.user.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityCategoryRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivityBrowserHistoryRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivityRequiredItemDetailRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.user.UsersRepository;
import com.hust.bmzsweb.managesystem.business.user.UsersService;
import com.hust.bmzsweb.managesystem.business.user.WXUserRepository;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.business.user.entity.WXUser;
import com.hust.bmzsweb.managesystem.business.userBrowerHistory.UserBrowsingHistoryEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivityCategoryRepository activityCategoryRepository;
    @Autowired
    ActivitySignupRepository activitySignupRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ActivityBrowserHistoryRepository activityBrowserHistoryRepository;
    @Autowired
    ActivityRequiredItemDetailRepository requiredItemDetailRepository;





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
    public List<QueryActivityDetailModel> findUserCreateAct(Integer userID) {

        //查询用户发起的所有活动
        List<ActivityInfo> activityInfos = activityRepository.findAllByUserId(userID);
        List<QueryActivityDetailModel> activityListModels = new ArrayList<>();

        QueryActivityDetailModel activityModel = null;
        for (ActivityInfo actInfo:activityInfos) {
            activityModel = new QueryActivityDetailModel();
            activityModel.setActId(actInfo.getActId());
            activityModel.setActTitle(actInfo.getActTitle());
            activityModel.setRequiredItemId(actInfo.getRequiredItemId());
            activityModel.setActDetailInfo(actInfo.getActDetailInfo());
            //查询活动类型名
            ActivityCategory activityCategory = activityCategoryRepository.findActivityCategoryByCategoryType(actInfo.getCategoryType());
            activityModel.setCategory(activityCategory.getCategoryName());
            //获取活动状态名
            String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
            activityModel.setActStatus(actStatus);
            activityModel.setActRunStatus(actInfo.getActRunStatus());
            activityModel.setActSignupDeadline(actInfo.getActSignupDeadline());
            activityModel.setActStartTime(actInfo.getActStartTime());
            activityModel.setActHeat(actInfo.getActHeat());
            activityModel.setIsDelete(actInfo.getIsDelete());
            activityModel.setParticipantsNumber(actInfo.getParticipantsNumber());
            activityModel.setIsLimitNum(actInfo.getIsLimitNum());
            activityModel.setMaxNum(actInfo.getMaxNum());
            activityModel.setActPassword(actInfo.getActPassword());
            activityModel.setIsPrivate(actInfo.getIsPrivate());
            activityModel.setActRunStatus(actInfo.getActRunStatus());
            activityModel.setActAddress(actInfo.getActAddress());


            activityListModels.add(activityModel);

        }

        return activityListModels;



    }

    @Override
    public List<QueryActivityDetailModel> findUserSignupAct(Integer userID) {

        //查询用户参与的所有活动
        List<ActivitySignup> ActivitySignups = activitySignupRepository.findAllByUserIdEquals(userID);
        List<QueryActivityDetailModel> activityListModels = new ArrayList<>();
        QueryActivityDetailModel activityModel = null;
        for(ActivitySignup activitySignup: ActivitySignups){
            activityModel = new QueryActivityDetailModel();

            //通过参与活动信息查询活动信息
            ActivityInfo actInfo = activityRepository.findByActId(activitySignup.getActId());

            activityModel.setActId(actInfo.getActId());
            activityModel.setActTitle(actInfo.getActTitle());
            activityModel.setRequiredItemId(actInfo.getRequiredItemId());
            //查询活动类型名
            ActivityCategory activityCategory = activityCategoryRepository.findActivityCategoryByCategoryType(actInfo.getCategoryType());
            activityModel.setCategory(activityCategory.getCategoryName());

            activityModel.setActRunStatus(actInfo.getActRunStatus());

            //获取活动状态名
            String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
            activityModel.setActStatus(actStatus);


            activityModel.setActSignupDeadline(actInfo.getActSignupDeadline());
            activityModel.setActStartTime(actInfo.getActStartTime());
            activityModel.setActHeat(actInfo.getActHeat());
            activityModel.setIsDelete(actInfo.getIsDelete());
            activityModel.setParticipantsNumber(actInfo.getParticipantsNumber());
            activityModel.setIsLimitNum(actInfo.getIsLimitNum());
            activityModel.setMaxNum(actInfo.getMaxNum());
            activityModel.setIsPrivate(actInfo.getIsPrivate());
            activityModel.setActRunStatus(actInfo.getActRunStatus());
            activityModel.setActAddress(actInfo.getActAddress());

            activityListModels.add(activityModel);
        }
        return activityListModels;
    }


    @Override
    public boolean queryUserNickNameIsExist(String userNickName) {
        User userByUserNickName = usersRepository.findUserByUserNickName(userNickName);
        if (userByUserNickName!=null) return true;
        else return false;
    }

    @Override
    public void saveUserInfo(User user) {
        usersRepository.save(user);
    }

    @Override
    public User findUserByNickName(String userNickName) {
        User userByUserNickName = usersRepository.findUserByUserNickName(userNickName);
        return userByUserNickName;

    }

    //如果nickName不存在就存入user表
    @Override
    public Integer saveUser(String nickName,String openId) {

        User user = usersRepository.findByUserNickNameEquals(nickName);
        if(user!=null)
        {
            return user.getUserId();
        }
        User newUser = new User(nickName,openId);
        User save = usersRepository.save(newUser);
        return  save.getUserId();
    }

    @Override
    public User findUserByOpenId(Integer openId) {
        return usersRepository.findByUserOpenidEquals(openId);
    }

    @Override
    public List<UserBrowsingHistoryEntity> findUserBrowsingHistory(Integer userID) {
        List<UserBrowsingHistoryEntity> userBrowsingHistoryEntitiesByUserId = activityBrowserHistoryRepository.findUserBrowsingHistoryEntitiesByUserId(userID);
        return  userBrowsingHistoryEntitiesByUserId;

    }

    @Override
    @Transactional
    public Boolean deleteSignupUser(Integer actId, Integer userId) {

        
        //查询用户报名
        ActivitySignup userSignup = activitySignupRepository.findByUserIdAndActIdEquals(userId, actId);
        try {

            if (userSignup!=null){
                //删除报名详情
                requiredItemDetailRepository.deleteActivityRequiredItemDetailByRequiredItemDetailId(userSignup.getRequiredItemDetailId());
                //删除报名信息
                activitySignupRepository.deleteActivitySignupByActIdAndUserId(actId, userId);
                //查看是否删除
                ActivitySignup isUserDelete = activitySignupRepository.findByUserIdAndActIdEquals(actId, userId);
                if (isUserDelete == null){
                    log.info("删除成功！");
                    return true;
                }
            } else {
                log.info("用户"+userId+"未报名活动"+actId);
                return false;
            }
        } catch (Exception e) {
            log.info("删除失败...");
        }
        return false;
    }

}
