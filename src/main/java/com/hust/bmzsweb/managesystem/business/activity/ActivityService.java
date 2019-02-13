package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityLocationListModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ActivityService {

     Page<QueryActivityListModel> findAllActsByUserId(Integer userId, PageRequest pageRequest);


     Page<QueryActivityListModel> findAllActsByActTitleContaining(String searchText,  PageRequest pageRequest);

     void banActivity(Integer actId);

     void allowActivity(Integer actId);

     void updateActRunstatus(Integer actId,Integer actRunStatus);

     QueryActivityDetailModel queryAct(Integer actId);

     Page<QueryActivityListModel> findUserSignupAct(Integer userId, PageRequest pageRequest);

     Page<QueryActivityLocationListModel> queryLocation(Integer type,String searchText,PageRequest pageRequest);
}
