package com.hust.bmzsweb.managesystem.business.activity.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityCategoryRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRequiredItemRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import com.hust.bmzsweb.managesystem.business.activity.model.ActivityWithRequiredItemModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityLocationListModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivityBrowserHistoryRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import com.hust.bmzsweb.managesystem.business.userBrowerHistory.UserBrowserHistoryEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Autowired
    ActivityRepository activityRepository;
    
    @Autowired
    ActivityCategoryRepository activityCategoryRepository;
    
    @Autowired
    ActivitySignupRepository activitySignupRepository;

    @Autowired
    ActivityRequiredItemRepository activityRequiredItemRepository;

    @Autowired
    ActivityBrowserHistoryRepository activityBrowserHistoryRepository;

    public Page<QueryActivityListModel> findAllActsByUserId(Integer userId, PageRequest pageRequest){
        Page<ActivityInfo> page = activityRepository.findAllByUserId(userId, pageRequest);
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
            QueryActivityListModel userCreateActivityModel = new QueryActivityListModel(activity.getActId(), activity.getActTitle(), categoryMap.get(activity.getCategoryType()), actStatus, activity.getCreateTime(), activity.getUpdateTime(),activity.getActRunStatus());
            activityModels.add(userCreateActivityModel);
        }
        Page<QueryActivityListModel> newPage = new PageImpl<QueryActivityListModel>(activityModels,page.getPageable(),page.getTotalElements());
      return newPage;
    }


    @Override
    public Page<QueryActivityListModel> findAllActsByActTitleContaining(String searchText, PageRequest pageRequest) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        Page<ActivityInfo> page = activityRepository.findAllByActTitleContaining(searchText, pageRequest);
        List<ActivityInfo> content = page.getContent();
        List<QueryActivityListModel> activityModels = new ArrayList<>();
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        for (int i = 0; i < content.size(); i++) {
            ActivityInfo activity = content.get(i);
            String actStatus = activity.getActStatus()==0?"审核中":(activity.getActStatus()==1?"审核通过":"审核未通过");
            QueryActivityListModel userCreateActivityModel = new QueryActivityListModel(activity.getActId(), activity.getActTitle(), categoryMap.get(activity.getCategoryType()), actStatus, activity.getCreateTime(), activity.getUpdateTime(),activity.getActRunStatus());
            activityModels.add(userCreateActivityModel);

        }
        Page<QueryActivityListModel> newPage = new PageImpl<QueryActivityListModel>(activityModels,page.getPageable(),page.getTotalElements());
        return newPage;
    }

    @Override
    public void banActivity(Integer actId) {
        activityRepository.banAcitivity(actId);
    }

    @Override
    public void allowActivity(Integer actId) {
        activityRepository.allowAcitivity(actId);
    }

    @Override
    public void updateActRunstatus(Integer actId, Integer actRunStatus) {
        activityRepository.updateActRunStatus(actId,actRunStatus );
    }

    @Override
    public QueryActivityDetailModel queryAct(Integer actId) {

        ActivityInfo actInfo = activityRepository.findByActId(actId);
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
        QueryActivityDetailModel act = new QueryActivityDetailModel(actInfo.getActId(), actInfo.getActTitle(), categoryMap.get(actInfo.getCategoryType()), actStatus,actInfo.getActDetailInfo(), actInfo.getActAddress(),actInfo.getActSignupDeadline(),actInfo.getActStartTime(), actInfo.getParticipantsNumber(), actInfo.getActRunStatus());
        return act;
    }

    @Override
    public Page<QueryActivityListModel> findUserSignupAct(Integer userId, PageRequest pageRequest) {
        System.out.println("userId:"+userId);
        List<ActivitySignup> userSignupActs =   activitySignupRepository.findAllByUserIdEquals(userId);
        List<Integer> actIds = new ArrayList<>();
        for (int i = 0; i < userSignupActs.size(); i++) {
            actIds.add(userSignupActs.get(i).getActId());
        }
        System.out.println("actIds"+actIds);
        Page<ActivityInfo> page = activityRepository.findAllByActIdIn(actIds,pageRequest);
        System.out.println(page.getContent());

        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        List<ActivityInfo> content = page.getContent();
        List<QueryActivityListModel> activityModels = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        for (int i = 0; i < content.size(); i++) {
            ActivityInfo activity = content.get(i);
            String actStatus = activity.getActStatus()==0?"审核中":(activity.getActStatus()==1?"审核通过":"审核未通过");
            QueryActivityListModel userCreateActivityModel = new QueryActivityListModel(activity.getActId(), activity.getActTitle(), categoryMap.get(activity.getCategoryType()), actStatus, activity.getCreateTime(), activity.getUpdateTime(),activity.getActRunStatus());
            activityModels.add(userCreateActivityModel);

        }
        Page<QueryActivityListModel> newPage = new PageImpl<QueryActivityListModel>(activityModels,page.getPageable(),page.getTotalElements());
        return newPage;
    }

    @Override
    public Page<QueryActivityLocationListModel> queryLocation(Integer type,String searchText, PageRequest pageRequest) {

        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        if(type==null){
           type = 2;
        }

        Specification<ActivityInfo> specification1;
        final String text = searchText;
        //1表示搜索城市
        if(type==1) {

            specification1 = new Specification<ActivityInfo>() {
                @Override
                public Predicate toPredicate(Root<ActivityInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                    List<Predicate> predicates = new ArrayList<Predicate>();
                    // root(employee(age))
                    Predicate predicate1 =  cb.equal(root.get("actAddress"), text);
                    return cb.and(predicate1);
                }
            };

        }
        //否则搜索名称
        else{
           specification1 = new Specification<ActivityInfo>() {
                @Override
                public Predicate toPredicate(Root<ActivityInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                    List<Predicate> predicates = new ArrayList<Predicate>();
                    // root(employee(age))
                    Predicate predicate1 =  cb.like(root.get("actTitle"), "%"+text+"%");
                    return cb.and(predicate1);
                }
            };
        }
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Page<ActivityInfo> page = activityRepository.findAll(specification1, pageRequest);


        Map<Integer,String> categoryMap = new HashMap<>();
        List<ActivityInfo> content = page.getContent();
        List<QueryActivityLocationListModel> activityModels = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        for (int i = 0; i < content.size(); i++) {
            ActivityInfo activity = content.get(i);
            QueryActivityLocationListModel locationActivityModel = new QueryActivityLocationListModel(activity.getActId(),activity.getActTitle(),categoryMap.get(activity.getCategoryType()),activity.getActAddress(),activity.getLongitude(),activity.getLatitude(),activity.getParticipantsNumber(),activity.getActHeat());
            activityModels.add(locationActivityModel);
        }

        Page<QueryActivityLocationListModel> newPage = new PageImpl<QueryActivityLocationListModel>(activityModels,page.getPageable(),page.getTotalElements());
        return newPage;
    }

    @Override
    @Transactional
    public Integer saveActivityInfo(ActivityWithRequiredItemModel activityInfo) {
        activityInfo.setCategoryType(1);
        activityInfo.setParticipantsNumber(1);
        activityInfo.setUserId(1);
        activityInfo.setActHeat(0);
        activityInfo.setActLike(0);
        activityInfo.setActReminder(false);
        activityInfo.setActStatus(1);
        activityInfo.setActRunStatus(0);
        ActivityRequiredItem req = activityRequiredItemRepository.save(activityInfo.getActivityRequiredItem());
        activityInfo.setRequiredItemId(req.getRequiredItemId());
        System.out.println("requiredItemId:"+req.getRequiredItemId());
        ActivityInfo act = activityRepository.save(activityInfo.createAct());
        return act.getActId();
    }

    @Override
    public List<QueryActivityDetailModel> queryActivityByTitleorderByHeat(String searchText) {
        if(StringUtils.isBlank(searchText)){
            searchText = "";
        }
        Sort sort = new Sort(Sort.Direction.DESC,"actHeat");
        Specification<ActivityInfo> specification;
        final String  text = searchText;
        specification = new Specification<ActivityInfo>() {
            @Override
            public Predicate toPredicate(Root<ActivityInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();
                // root(employee(age))
                Predicate predicate1 =  cb.like(root.get("actTitle"), "%"+text+"%");
                return cb.and(predicate1);
            }
        };
        List<ActivityInfo> activities = activityRepository.findAll(specification, sort);
        List<QueryActivityDetailModel> activityModels = new ArrayList<>();
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        for (int i = 0; i < activities.size(); i++) {
            ActivityInfo actInfo = activities.get(i);
            String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
            QueryActivityDetailModel act = new QueryActivityDetailModel(actInfo.getActId(), actInfo.getActTitle(), categoryMap.get(actInfo.getCategoryType()), actStatus,actInfo.getActDetailInfo(), actInfo.getActAddress(),actInfo.getActSignupDeadline(),actInfo.getActStartTime(), actInfo.getParticipantsNumber(), actInfo.getActRunStatus());
            activityModels.add(act);
        }
         return activityModels;
    }

    @Override
    public Integer updateActivityInfo(ActivityWithRequiredItemModel activityInfo) {
        ActivityInfo act = activityInfo.createAct();
        activityRequiredItemRepository.save(activityInfo.getActivityRequiredItem());
        activityRepository.save(act);
        return act.getActId();
    }

    @Override
    public void saveBrowserHistory(Integer userId, Integer actId) {
        activityBrowserHistoryRepository.save(new UserBrowserHistoryEntity( actId,userId ));
    }
}
