package com.hust.bmzsweb.managesystem.business.activitySignup.impl;

import com.hust.bmzsweb.managesystem.business.activitySignup.ActivityRequiredItemDetailRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitySignupServiceImpl implements ActivitySignupService {

    @Autowired
    ActivitySignupRepository activitySignupRepository;

    @Autowired
    ActivityRequiredItemDetailRepository activityRequiredItemDetailRepository;

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
}
