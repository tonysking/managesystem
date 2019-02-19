package com.hust.bmzsweb.managesystem.business.activitySignup.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRequiredItemRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivityRequiredItemDetailRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.ActivitySignupModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.SignUpWithRequiredItemDetailModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@Slf4j
@Service
public class ActivitySignupServiceImpl implements ActivitySignupService {

    @Autowired
    ActivitySignupRepository activitySignupRepository;

    @Autowired
    ActivityRequiredItemDetailRepository activityRequiredItemDetailRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivityRequiredItemRepository activityRequiredItemRepository;

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

    //活动报名

    @Override
    public Integer saveActivitySignup(SignUpWithRequiredItemDetailModel signUpWithRequiredItemDetailModel) throws Exception {
         //查找对应活动
        ActivityInfo activityInfo = activityRepository.findByActId(signUpWithRequiredItemDetailModel.getActId());
        int a=activityInfo.getRequiredItemId();
        ActivityRequiredItem activityRequiredItem = activityRequiredItemRepository.findByRequiredItemIdEquals(a);
        if((activityRequiredItem.getAddress())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAddress()==null))
        {  throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getAge())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAge()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getCity())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getCity()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getClassNumber())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getClassNumber()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getDepartment())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getDepartment()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getGrade())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getGrade()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getName())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getName()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getSex())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getSex()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getSchool())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getSchool()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getJobNumber())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getJobNumber()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getProvince())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getProvince()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getStudentId())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getStudentId()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getQqNumber())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getQqNumber()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getWechatNumber())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getWechatNumber()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getWorkPlace())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getWorkPlace()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getFieldOne())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldOne()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getFieldTwo())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldTwo()==null))
        { throw new Exception("必填项不能为空"); }
        if((activityRequiredItem.getFieldThree())&&(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getFieldThree()==null))
        { throw new Exception("必填项不能为空"); }
        //填入报名详情表
        ActivityRequiredItemDetail activityRequiredItemDetail = new ActivityRequiredItemDetail();
        activityRequiredItemDetail.setAddress(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAddress());
        activityRequiredItemDetail.setAge(signUpWithRequiredItemDetailModel.getRequiredItemDetailModel().getAge());
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

        //填入报名表
        ActivitySignup activitySignup = new ActivitySignup();
        activitySignup.setActId(signUpWithRequiredItemDetailModel.getActId());
        activitySignup.setUserId(signUpWithRequiredItemDetailModel.getUserId());
        activitySignup.setCreateTime(new Date());
        activitySignup.setUpdateTime(new Date());
        activitySignup.setUserSignupStatus(1);
        //activitySignup.setRequiredItemDetailId(activityInfo.getRequiredItemId());
        activitySignup.setRequiredItemDetailId(activityRequiredItemDetail.getRequiredItemDetailId());
        activitySignupRepository.save(activitySignup);
         return activitySignup.getUserSignupId();
    }
}
