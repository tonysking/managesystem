package com.hust.bmzsweb.managesystem.business.activity.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityCategoryRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Autowired
    ActivityRepository acticityRepository;
    
    @Autowired
    ActivityCategoryRepository activityCategoryRepository;

    public Page<QueryActivityListModel> findAllActsByUserId(Integer userId, PageRequest pageRequest){
        Page<ActivityInfo> page = acticityRepository.findAllByUserId(userId, pageRequest);
        List<ActivityInfo> content = page.getContent();
        List<QueryActivityListModel> activityModels = new ArrayList<>();
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
      //  System.out.println("categories"+categories);
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
       // System.out.println("categoryMap"+categoryMap);
        for (int i = 0; i < content.size(); i++) {
            ActivityInfo activity = content.get(i);
            String actStatus = activity.getActStatus()==0?"审核中":(activity.getActStatus()==1?"审核通过":"审核未通过");
            QueryActivityListModel userCreateActivityModel = new QueryActivityListModel(activity.getActId(), activity.getActTitle(), categoryMap.get(activity.getCategoryType()), actStatus, activity.getCreateTime(), activity.getUpdateTime());
            activityModels.add(userCreateActivityModel);
        }
        Page<QueryActivityListModel> newPage = new PageImpl<QueryActivityListModel>(activityModels,page.getPageable(),page.getTotalElements());
      return newPage;
    }
}
