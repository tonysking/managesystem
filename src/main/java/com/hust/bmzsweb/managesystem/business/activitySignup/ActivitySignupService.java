package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.ActivitySignupModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.SignUpWithRequiredItemDetailModel;

public interface ActivitySignupService {
    ActivityRequiredItemDetail getDetail(Integer userId,Integer actId);
    Integer saveActivitySignup(SignUpWithRequiredItemDetailModel signUpWithRequiredItemDetailModel) throws Exception;

}
