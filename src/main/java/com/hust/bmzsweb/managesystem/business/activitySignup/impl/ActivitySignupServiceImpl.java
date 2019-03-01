package com.hust.bmzsweb.managesystem.business.activitySignup.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRequiredItemRepository;
import com.hust.bmzsweb.managesystem.business.activity.impl.ActivityServiceImpl;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivityRequiredItemDetailRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.*;
import com.hust.bmzsweb.managesystem.business.user.*;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.common.enums.HeatEnum;
import com.hust.bmzsweb.managesystem.common.exception.ActivitySignupException;
import com.hust.bmzsweb.managesystem.common.exception.UserException;
import com.hust.bmzsweb.managesystem.common.utils.HeatCalculateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ActivitySignupServiceImpl implements ActivitySignupService {

    @Autowired
    ActivitySignupRepository activitySignupRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivityRequiredItemDetailRepository activityRequiredItemDetailRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivityRequiredItemRepository activityRequiredItemRepository;

    @Autowired
    ActivityService activityServiceImpl;
    @Autowired
    UsersService usersService;



    @Override
    public ActivityRequiredItemDetail getDetail(Integer userId, Integer actId) {
        ActivitySignup actSignup = activitySignupRepository.findByUserIdAndActIdEquals(userId, actId);
        if(actSignup==null)
        {
            return null;
        }
        ActivityRequiredItemDetail detail = activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(actSignup.getRequiredItemDetailId());
        return detail;
    }

//    @Override
//    public Integer deleteActivitySignup(Integer signId)throws Exception {
//        ActivitySignup activitySignup = activitySignupRepository.findByUserSignupId(signId);
//        if (activitySignup == null){log.info("该报名不存在");
//            throw new Exception("该报名不存在");}
//        if (activitySignup.getUserSignupStatus()==1){log.info("该报名已失效");
//            throw new Exception("该报名已失效");}
//        activitySignup.setUserSignupStatus(1);
//        activitySignupRepository.save(activitySignup);
//         return activitySignup.getUserSignupId();
//     }

    //取消报名（置报名状态为1）
    @Transactional
    @Override
    public void banActivitySignup(Integer userSignId) {
        activitySignupRepository.banSignUp(userSignId);
    }

    //取消报名（删除报名信息）
    @Override
    @Transactional
    public void banActivitySignup(Integer userId, Integer actId) {
//        ActivitySignup actSignup = activitySignupRepository.findByUserIdAndActIdEquals(userId, actId);
//        if (actSignup!=null){
//
//            actSignup.setUserSignupStatus(1);
//        }
        Boolean isDelete = usersService.deleteSignupUser(actId, userId);
        if (isDelete){

            ActivityInfo activityInfo = activityRepository.findByActId(actId);
            //取消报名 活动热度减少
            activityInfo.setActHeat(HeatCalculateUtils.reduceHeat(activityInfo.getActHeat(), HeatEnum.SignUp));
            activityRepository.save(activityInfo);


            //若不是发起者，报名的活动人数减1
            if (!activityServiceImpl.isIniator(actId,userId)){
                ActivityInfo actInfo = activityInfo;
                actInfo.setParticipantsNumber(actInfo.getParticipantsNumber()-1);
                activityRepository.save(actInfo);
            }
        }

    }


    //活动报名
    @Override
    public ActivityRequiredItemDetail saveActivitySignup(SignUpWithRequiredItemDetailModel signUpWithRequiredItemDetailModel) {

        //查找是否已存在该报名信息
        ActivitySignup as = activitySignupRepository.findByUserIdAndActIdEquals(signUpWithRequiredItemDetailModel.getUserId(),signUpWithRequiredItemDetailModel.getActId());
        if(as!=null){ log.info("已报名该活动");
            throw new ActivitySignupException("已报名该活动");}
        //查找对应活动，判断报名是否有效
        ActivityInfo activityInfo = activityRepository.findByActId(signUpWithRequiredItemDetailModel.getActId());
//          try {
        if (activityInfo == null) {
            log.info("活动不存在");
            throw new ActivitySignupException("活动不存在");
        }


        if(isSignupEnd(activityInfo.getActSignupDeadline()))
        {
            log.info("报名已经截止");
            throw new ActivitySignupException("报名已经截止");
        }

        if (activityInfo.getMaxNum()!=null){
            if ((activityInfo.getParticipantsNumber() >= activityInfo.getMaxNum()) && activityInfo.getIsLimitNum() == true) {
                log.info("活动报名人数已满");
                throw new ActivitySignupException("活动报名人数已满");
            }
        }
        if (activityInfo.getActStatus() == 3) {
            log.info("活动已禁止");
            throw new ActivitySignupException("活动已禁止");
        }//0正常 1表示被禁止(失效)
        if (activityInfo.getActStatus() == 0) {
            log.info("活动还在审核中");
            throw new ActivitySignupException("活动还在审核中");
        }
        if (activityInfo.getActStatus() == 2) {
            log.info("活动未通过审核");
            throw new ActivitySignupException("活动未通过审核");
        }
        if (activityInfo.getIsDelete()) {
            log.info("活动已被删除");
            throw new ActivitySignupException("活动已被删除");
        }
        User user = usersRepository.findUserByUserId(signUpWithRequiredItemDetailModel.getUserId());
        if (user == null) {
            log.info("用户不存在");
            throw new ActivitySignupException("该用户不存在");
        }

        if(user.getUserStatus()==1)
        {
            log.info("该用户被锁定");
            throw new UserException("该用户被锁定");
        }

        //判断必填项是否为空
        int a = activityInfo.getRequiredItemId();
        ActivityRequiredItem activityRequiredItem = activityRequiredItemRepository.findByRequiredItemIdEquals(a);
        if ((activityRequiredItem.getAddress()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAddress() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getAge()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAge() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if((activityRequiredItem.getPhone()) && signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getPhone() == null) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getCity()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getCity() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getClassNumber()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getClassNumber() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getDepartment()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getDepartment() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getGrade()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getGrade() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getName()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getName() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getSex()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getSex() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getSchool()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getSchool() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getJobNumber()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getJobNumber() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getProvince()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getProvince() == null)) {
            log.info("必填项不能为空");
            throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getStudentId()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getStudentId() == null)) {
            log.info("必填项不能为空"); throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getQqNumber()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getQqNumber() == null)) {
            log.info("必填项不能为空"); throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getWechatNumber()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getWechatNumber() == null)) {
            log.info("必填项不能为空");  throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getWorkPlace()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getWorkPlace() == null)) {
            log.info("必填项不能为空"); throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getFieldOne()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldOne() == null)) {
            log.info("必填项不能为空");  throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getFieldTwo()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldTwo() == null)) {
            log.info("必填项不能为空"); throw new ActivitySignupException("必填项不能为空");
        }
        if ((activityRequiredItem.getFieldThree()) && (signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldThree() == null)) {
            log.info("必填项不能为空");  throw new ActivitySignupException("必填项不能为空");
        }

        //更改 不是发起者则参与人数加1
        if(!activityServiceImpl.isIniator(signUpWithRequiredItemDetailModel.getActId(), signUpWithRequiredItemDetailModel.getUserId()))
        {
            //参与人数+1
            activityInfo.setParticipantsNumber(activityInfo.getParticipantsNumber() + 1);
            activityRepository.save(activityInfo);
        }

        //填入报名详情表
        ActivityRequiredItemDetail activityRequiredItemDetail = new ActivityRequiredItemDetail();
        activityRequiredItemDetail.setAddress(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAddress());
        activityRequiredItemDetail.setAge(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAge());
        activityRequiredItemDetail.setPhone(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getPhone());
        activityRequiredItemDetail.setPosition(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getPosition());
        activityRequiredItemDetail.setEmail(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getEmail());
        activityRequiredItemDetail.setCity(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getCity());
        activityRequiredItemDetail.setClassNumber(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getClassNumber());
        activityRequiredItemDetail.setDepartment(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getDepartment());
        activityRequiredItemDetail.setGrade(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getGrade());
        activityRequiredItemDetail.setName(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getName());
        activityRequiredItemDetail.setSex(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getSex());
        activityRequiredItemDetail.setSchool(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getSchool());
        activityRequiredItemDetail.setJobNumber(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getJobNumber());
        activityRequiredItemDetail.setProvince(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getProvince());
        activityRequiredItemDetail.setStudentId(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getStudentId());
        activityRequiredItemDetail.setQqNumber(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getQqNumber());
        activityRequiredItemDetail.setWechatNumber(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getWechatNumber());
        activityRequiredItemDetail.setWorkPlace(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getWorkPlace());
        activityRequiredItemDetail.setFieldOne(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldOne());
        activityRequiredItemDetail.setFieldTwo(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldTwo());
        activityRequiredItemDetail.setFieldThree(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldThree());
        activityRequiredItemDetailRepository.save(activityRequiredItemDetail);

        //报名活动 活动热度增加
        activityInfo.setActHeat(HeatCalculateUtils.addHeat(activityInfo.getActHeat(), HeatEnum.SignUp));
        activityRepository.save(activityInfo);


        //填入报名表
        ActivitySignup activitySignup = new ActivitySignup();
        activitySignup.setActId(signUpWithRequiredItemDetailModel.getActId());
        activitySignup.setUserId(signUpWithRequiredItemDetailModel.getUserId());
        activitySignup.setCreateTime(new Date());
        activitySignup.setUpdateTime(new Date());
        activitySignup.setUserSignupStatus(0);//0表示正常报名，1表示取消报名
        //activitySignup.setRequiredItemDetailId(activityInfo.getRequiredItemId());
        activitySignup.setRequiredItemDetailId(activityRequiredItemDetail.getRequiredItemDetailId());
        activitySignupRepository.save(activitySignup);
        return activityRequiredItemDetail;

//      return activityRequiredItemDetail;
    }

    //修改报名信息
    @Transactional
    @Override
    public void alterSignUp(AlterSignUpRequestModel alterSignUpRequestModel){

        ActivitySignup activitySignup = activitySignupRepository.findByUserIdAndActIdEquals(alterSignUpRequestModel.getUserId(), alterSignUpRequestModel.getActId());
//        ActivitySignup activitySignup = activitySignupRepository.findByUserSignupId(alterSignUpRequestModel.getUserSignId());
        if (activitySignup == null){log.info("该报名不存在");
            throw new ActivitySignupException("该报名不存在");}
        if (activitySignup.getUserSignupStatus()==1){log.info("该报名已失效");
            throw new ActivitySignupException("该报名已失效");}

        //填入报名详情表
//        ActivityRequiredItemDetail activityRequiredItemDetail = new ActivityRequiredItemDetail();
        ActivityRequiredItemDetail activityRequiredItemDetail = activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(activitySignup.getRequiredItemDetailId());
        activityRequiredItemDetail.setAddress(alterSignUpRequestModel.getRequiredItemDetailModel().getAddress());
        activityRequiredItemDetail.setPhone(alterSignUpRequestModel.getRequiredItemDetailModel().getPhone());
        activityRequiredItemDetail.setEmail(alterSignUpRequestModel.getRequiredItemDetailModel().getEmail());
        activityRequiredItemDetail.setPosition(alterSignUpRequestModel.getRequiredItemDetailModel().getPosition());
        activityRequiredItemDetail.setAge(alterSignUpRequestModel.getRequiredItemDetailModel().getAge());
        activityRequiredItemDetail.setCity(alterSignUpRequestModel.getRequiredItemDetailModel().getCity());
        activityRequiredItemDetail.setClassNumber(alterSignUpRequestModel.getRequiredItemDetailModel().getClassNumber());
        activityRequiredItemDetail.setDepartment(alterSignUpRequestModel.getRequiredItemDetailModel().getDepartment());
        activityRequiredItemDetail.setGrade(alterSignUpRequestModel.getRequiredItemDetailModel().getGrade());
        activityRequiredItemDetail.setName(alterSignUpRequestModel.getRequiredItemDetailModel().getName());
        activityRequiredItemDetail.setSex(alterSignUpRequestModel.getRequiredItemDetailModel().getSex());
        activityRequiredItemDetail.setSchool(alterSignUpRequestModel.getRequiredItemDetailModel().getSchool());
        activityRequiredItemDetail.setJobNumber(alterSignUpRequestModel.getRequiredItemDetailModel().getJobNumber());
        activityRequiredItemDetail.setProvince(alterSignUpRequestModel.getRequiredItemDetailModel().getProvince());
        activityRequiredItemDetail.setStudentId(alterSignUpRequestModel.getRequiredItemDetailModel().getStudentId());
        activityRequiredItemDetail.setQqNumber(alterSignUpRequestModel.getRequiredItemDetailModel().getQqNumber());
        activityRequiredItemDetail.setWechatNumber(alterSignUpRequestModel.getRequiredItemDetailModel().getWechatNumber());
        activityRequiredItemDetail.setWorkPlace(alterSignUpRequestModel.getRequiredItemDetailModel().getWorkPlace());
        activityRequiredItemDetail.setFieldOne(alterSignUpRequestModel.getRequiredItemDetailModel().getFieldOne());
        activityRequiredItemDetail.setFieldTwo(alterSignUpRequestModel.getRequiredItemDetailModel().getFieldTwo());
        activityRequiredItemDetail.setFieldThree(alterSignUpRequestModel.getRequiredItemDetailModel().getFieldThree());
        activityRequiredItemDetailRepository.save(activityRequiredItemDetail);

        activitySignupRepository.save(activitySignup);
    }


    //判断报名是否截止
    @Override
    public  boolean isSignupEnd(Date date) {

        Date now = new Date();
        if(date.compareTo(now)<=0)
        {
            return true;
        }
        return false;
    }

    @Override
    public List<QuerySignUpDetailModel> querySingupDetail(Integer actId){
       List<ActivitySignup> a = activitySignupRepository.findActivitySignupsByActId(actId);
        List<QuerySignUpDetailModel> c = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            QuerySignUpDetailModel t = new QuerySignUpDetailModel();
            t.setUserId(a.get(i).getUserId());
            t.setName(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getName());
            t.setSex(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getSex());
            t.setAge(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getAge());
            t.setAddress(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getAddress());
            t.setPhone(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getPhone());
            t.setWechatNumber(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getWechatNumber());
            t.setQqNumber(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getQqNumber());
            t.setEmail(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getEmail());
            t.setSchool(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getSchool());
            t.setGrade(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getGrade());
            t.setClassNumber(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getClassNumber());
            t.setStudentId(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getStudentId());
            t.setWorkPlace(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getWorkPlace());
            t.setDepartment(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getDepartment());
            t.setPosition(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getPosition());
            t.setJobNumber(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getJobNumber());
            t.setProvince(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getProvince());
            t.setCity(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getCity());
            t.setFieldOne(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getFieldOne());
            t.setFieldTwo(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getFieldTwo());
            t.setFieldThree(activityRequiredItemDetailRepository.findByRequiredItemDetailIdEquals(a.get(i).getRequiredItemDetailId()).getFieldThree());
            c.add(t);
        }
        return c;
    }

}
