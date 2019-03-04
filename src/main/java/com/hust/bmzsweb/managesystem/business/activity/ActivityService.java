package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import com.hust.bmzsweb.managesystem.business.activity.model.*;
import com.hust.bmzsweb.managesystem.business.user.model.UserPositionModel;
import com.hust.bmzsweb.managesystem.business.userCollection.UserCollectionModel;
import com.hust.bmzsweb.managesystem.common.exception.ActivityException;
import com.hust.bmzsweb.managesystem.common.exception.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityService {

     Page<QueryActivityListModel> findAllActsByUserId(Integer userId, PageRequest pageRequest);


     Page<QueryActivityListModel> findAllActsByActTitleContaining(String searchText,  PageRequest pageRequest);

     void banActivity(Integer actId);

     void allowActivity(Integer actId);

     void updateActRunstatus(Integer actId,Integer actRunStatus);

     void deleteAct(Integer actId);

     QueryActivityDetailModel queryAct(Integer actId);

     Page<QueryActivityListModel> findUserSignupAct(Integer userId, PageRequest pageRequest);

     Page<QueryActivityLocationListModel> queryLocation(Integer type,String searchText,PageRequest pageRequest);

     Integer saveActivityInfo(ActivityWithRequiredItemModel activityInfo);

     Integer saveUserCollection(UserCollectionModel userCollectionModel);

     void deleteUserCollection(Integer userCollectionId);

     List<QueryActivityDetailModel> queryActivityByTitleorderByHeat(String searchText,Boolean isSearched);

     Integer updateActivityInfo(ActivityWithRequiredItemModel activityInfo);

     void saveBrowserHistory(Integer userId,Integer actId);

     boolean isIniator(Integer actId,Integer userId);

     QueryActivityDetailModel queryActWithRequiredItemId(Integer actId);

     ActivityRequiredItem findRequiredItem(Integer actRequiredItemId);

     QueryActivityWithAllStatusModel queryActWithAllStatus(Integer actId,Integer userId);

     List<ActivityCategory> getAllActivityCategories();

     Boolean hasSensitiveWord(String word);

     List<QueryActivityLocationListModel> findActLocationsFromUserPosition(UserPositionModel userPositionModel,Double distance);
}
