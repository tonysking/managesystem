package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.ActivitySignupModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.RequiredItemDetailModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.SignUpWithRequiredItemDetailModel;

public interface ActivitySignupService {
    ActivityRequiredItemDetail getDetail(Integer userId, Integer actId);

    ActivityRequiredItemDetail saveActivitySignup(SignUpWithRequiredItemDetailModel signUpWithRequiredItemDetailModel) throws Exception;
    //Integer deleteActivitySignup(Integer signId)throws Exception;
    void banActivitySignup(Integer userSignId);
}
