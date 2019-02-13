package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;

public interface ActivitySignupService {
    ActivityRequiredItemDetail getDetail(Integer userId,Integer actId);
}
