package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.ActivitySignupModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.AlterSignUpRequestModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.SignUpWithRequiredItemDetailModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.RequiredItemDetailModel;

public interface ActivitySignupService {
    ActivityRequiredItemDetail getDetail(Integer userId, Integer actId);

    ActivityRequiredItemDetail saveActivitySignup(SignUpWithRequiredItemDetailModel signUpWithRequiredItemDetailModel);
    //Integer deleteActivitySignup(Integer signId)throws Exception;
    void banActivitySignup(Integer userSignId);

    void alterSignUp(AlterSignUpRequestModel alterSignUpRequestModel);
}
