package com.hust.bmzsweb.managesystem.business.activity.impl;

import com.hust.bmzsweb.managesystem.business.activity.ActivityCategoryRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityRequiredItemRepository;
import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import com.hust.bmzsweb.managesystem.business.activity.model.*;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivityBrowserHistoryRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupRepository;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;

import com.hust.bmzsweb.managesystem.business.user.UsersRepository;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.business.user.model.UserPositionModel;
import com.hust.bmzsweb.managesystem.business.userBrowerHistory.UserBrowsingHistoryEntity;
import com.hust.bmzsweb.managesystem.business.userCollection.UserCollectionEntity;
import com.hust.bmzsweb.managesystem.business.userCollection.UserCollectionModel;
import com.hust.bmzsweb.managesystem.business.userCollection.UserCollectionRepository;
import com.hust.bmzsweb.managesystem.business.userBrowerHistory.UserBrowsingHistoryEntity;
import com.hust.bmzsweb.managesystem.business.userCollection.UserCollectionModel;
import com.hust.bmzsweb.managesystem.common.enums.ResultEnum;
import com.hust.bmzsweb.managesystem.common.exception.ActivityException;
import com.hust.bmzsweb.managesystem.common.utils.GSUtil;
import com.hust.bmzsweb.managesystem.common.utils.SensitivewordFilter;
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
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    UserCollectionRepository userCollectionRepository;

    @Autowired
    SensitivewordFilter sensitivewordFilter;


    @Autowired
    UsersRepository usersRepository;

    //查询用户创建的所有活动 分页结果
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

    //删除对应ID的活动
    @Transactional
    @Override
    public void deleteAct(Integer actId){
        try{
            userCollectionRepository.deleteUserCollectionEntitiesByActId(actId);
            activityBrowserHistoryRepository.deleteUserBrowsingHistoryEntityByActId(actId);
            activitySignupRepository.deleteActivitySignupsByActId(actId);
            activityRepository.deleteById(actId);}
        catch(Exception e){
            System.out.println("删除活动失败");
        }
    }

    //删除活动收藏
    @Override
    @Transactional
    public void deleteUserCollection(Integer userCollectionId){
        userCollectionRepository.deleteUserCollectionEntityByCId(userCollectionId);
    }

    //模糊查询活动通过活动标题 分页结果
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

    //禁止活动
    @Override
    public void banActivity(Integer actId) {
        activityRepository.banAcitivity(actId);
    }

    //允许活动
    @Override
    public void allowActivity(Integer actId) {
        activityRepository.allowAcitivity(actId);
    }

    //更改活动运行状态
    @Override
    public void updateActRunstatus(Integer actId, Integer actRunStatus) {
        activityRepository.updateActRunStatus(actId,actRunStatus );
    }



    //通过id查询活动信息
    @Override
    public QueryActivityDetailModel queryAct(Integer actId) {

        ActivityInfo actInfo = activityRepository.findByActId(actId);
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
        QueryActivityDetailModel act = new QueryActivityDetailModel(actInfo.getActId(),actInfo.getUserId(),actInfo.getRequiredItemId(),actInfo.getActTitle(),
                categoryMap.get(actInfo.getCategoryType()),actStatus,actInfo.getActDetailInfo(),actInfo.getActAddress(),actInfo.getActSignupDeadline(),
                actInfo.getActStartTime(),actInfo.getActHeat(),actInfo.getIsDelete(),actInfo.getParticipantsNumber(),actInfo.getActRunStatus(),actInfo.getIsLimitNum(),
                actInfo.getMaxNum(),actInfo.getIsPrivate(),actInfo.getActPassword());

        return act;
    }

    //查询用户报名的所有活动
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

    //通过城市或者名称 模糊查询活动位置信息
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


    //小程序 发起活动
    @Override
    @Transactional
    public Integer saveActivityInfo(ActivityWithRequiredItemModel activityInfo)  {

        User user = usersRepository.findUserByUserId(activityInfo.getUserId());


        if(user==null)
        {
            throw new ActivityException("用户未登录，无法发起活动");
        }

        if(user.getUserStatus()!=null&&user.getUserStatus()!=0)
        {
            throw new ActivityException("用户被锁定，无法发起活动");
        }


        activityInfo.setParticipantsNumber(1);
        activityInfo.setCategoryType(activityInfo.getCategoryType()+1);
        activityInfo.setActReminder(false);
        activityInfo.setIsDelete(false);
        //判断是否有敏感字 如果有就审核不通过
        if(hasSensitiveWord(activityInfo.getActTitle())||hasSensitiveWord(activityInfo.getActDetailInfo()))
        {
            activityInfo.setActStatus(2);
        }else{
            activityInfo.setActStatus(1);
        }

        activityInfo.setActRunStatus(0);
        ActivityRequiredItem req = activityRequiredItemRepository.save(activityInfo.getActivityRequiredItem());
        activityInfo.setRequiredItemId(req.getRequiredItemId());
        ActivityInfo act = activityRepository.save(activityInfo.createActWithActHeatActLikeZero());
        return act.getActId();
    }

    //小程序 收藏活动
    @Override
    @Transactional
    public Integer saveUserCollection(UserCollectionModel userCollectionModel) {
        //UserCollectionEntity uc =new UserCollectionEntity(userCollectionModel.createCollection());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        userCollectionModel.setCollectTime(new Date());
        try{
            UserCollectionEntity uu = userCollectionRepository.save(userCollectionModel.createCollection());return uu.getCId();}
        catch(Exception e){
            System.out.println("收藏活动失败");
        }
        return 400;
    }

    //小程序 通过活动标题搜索活动
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
            QueryActivityDetailModel act = new QueryActivityDetailModel(actInfo.getActId(),actInfo.getUserId(),actInfo.getRequiredItemId(), actInfo.getActTitle(), categoryMap.get(actInfo.getCategoryType()), actStatus,actInfo.getActDetailInfo(), actInfo.getActAddress(),actInfo.getActSignupDeadline(),actInfo.getActStartTime(),actInfo.getActHeat(),actInfo.getIsDelete() ,actInfo.getParticipantsNumber(), actInfo.getActRunStatus(),actInfo.getIsLimitNum(),actInfo.getMaxNum(),actInfo.getIsPrivate(),actInfo.getActPassword());
            activityModels.add(act);
        }
         return activityModels;
    }

    //小程序 更改活动状态
    @Override
    public Integer updateActivityInfo(ActivityWithRequiredItemModel activityInfo){
        ActivityInfo act = activityInfo.createActWithActHeatActLikeZero();
        Integer actId = act.getActId();

        act.setActReminder(false);

        if(hasSensitiveWord(activityInfo.getActTitle())||hasSensitiveWord(activityInfo.getActDetailInfo()))
        {
            act.setActStatus(2);
        }else{
            act.setActStatus(1);
        }
        act.setActRunStatus(0);
        act.setCategoryType(activityInfo.getCategoryType()+1);

        //保存不需要修改的之前的信息
        ActivityInfo beforeInfo = activityRepository.findByActId(actId);
        act.setIsDelete(beforeInfo.getIsDelete());
        act.setParticipantsNumber(beforeInfo.getParticipantsNumber());
        act.setRequiredItemId(beforeInfo.getRequiredItemId());
        activityRequiredItemRepository.save(activityInfo.getActivityRequiredItem());
        ActivityInfo save = activityRepository.save(act);
        return save.getActId();
    }

    //小程序 保存活动浏览历史
    @Override
    public void saveBrowserHistory(Integer userId, Integer actId) {
        activityBrowserHistoryRepository.save(new UserBrowsingHistoryEntity( actId,userId ));
    }

    //小程序 判断是否是发起者
    @Override
    public boolean isIniator(Integer actId, Integer userId) {
        ActivityInfo result = activityRepository.findByActIdAndUserIdEquals(actId, userId);
        if(result!=null)
        {
            return true;
        }
        return false;
    }

    //通过id查询活动信息 带有reuquiredItemId
    @Override
    public QueryActivityDetailModel queryActWithRequiredItemId(Integer actId) {
        ActivityInfo actInfo = activityRepository.findByActId(actId);
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
        QueryActivityDetailModel act = new QueryActivityDetailModel(actInfo.getActId(), actInfo.getRequiredItemId(),actInfo.getActTitle(), categoryMap.get(actInfo.getCategoryType()), actStatus,actInfo.getActDetailInfo(), actInfo.getActAddress(),actInfo.getActSignupDeadline(),actInfo.getActStartTime(), actInfo.getParticipantsNumber(), actInfo.getActRunStatus());
        return act;
    }

    @Override
    public ActivityRequiredItem findRequiredItem(Integer requiredItemId) {
        return activityRequiredItemRepository.findByRequiredItemIdEquals(requiredItemId);
    }

    //小程序 查询所有活动种类
    @Override
    public List<ActivityCategory> getAllActivityCategories(){
        return activityCategoryRepository.findAllByCategoryNameNotNull();
    }

    //小程序 查询活动信息附带活动所有的状态
    @Override
    public QueryActivityWithAllStatusModel queryActWithAllStatus(Integer actId,Integer userId) {
        ActivityInfo actInfo = activityRepository.findByActId(actId);
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        String actStatus = actInfo.getActStatus()==0?"审核中":(actInfo.getActStatus()==1?"审核通过":"审核未通过");
        QueryActivityDetailModel act = new QueryActivityDetailModel(actInfo.getActId(),actInfo.getUserId(), actInfo.getRequiredItemId(),actInfo.getActTitle(), categoryMap.get(actInfo.getCategoryType()), actStatus,actInfo.getActDetailInfo(), actInfo.getActAddress(),actInfo.getActSignupDeadline(),actInfo.getActStartTime(),actInfo.getActHeat(),actInfo.getIsDelete(),
                actInfo.getParticipantsNumber(), actInfo.getActRunStatus(),actInfo.getIsLimitNum(),actInfo.getMaxNum(),actInfo.getIsPrivate(),actInfo.getActPassword());
        Boolean isSponsor = isIniator(actId, userId);
        Date date = new Date();
        int i = date.compareTo(actInfo.getActStartTime());
        int j = date.compareTo(actInfo.getActSignupDeadline());
        Boolean isActivityStart = i>0;
        Boolean isTakePartEnd = j>0;
        Boolean isAuthorized = actInfo.getActStatus()==1;
        
        QueryActivityWithAllStatusModel actWithStatus =
                new QueryActivityWithAllStatusModel(act,isActivityStart,isTakePartEnd,isSponsor,isAuthorized,actInfo.getCategoryType()-1,actInfo.getLatitude(),actInfo.getLongitude());
        return actWithStatus;
    }

    @Override
    public Boolean hasSensitiveWord(String word) {

        Set<String> sensitiveWord = sensitivewordFilter.getSensitiveWord(word, 1);
        if(!sensitiveWord.isEmpty())
        {
            return true;
        }
        return false;
    }

    //小程序 查询用户周围的活动位置信息
    @Override
    public List<QueryActivityLocationListModel> findActLocationsFromUserPosition(UserPositionModel userPositionModel,Double distance) {
        List<QueryActivityLocationListModel> locationListModels = new ArrayList<>();

        if (distance == null){
            distance = 3244.79;
        }
        System.out.println("距离："+distance);
        //获取用户经纬度
        Double longitude = userPositionModel.getLongitude();
        Double latitude = userPositionModel.getLatitude();
        //按热度降序查找所有活动
        Sort sort = new Sort(Sort.Direction.DESC,"actHeat");
        List<ActivityInfo> all = activityRepository.findAll(sort);
        //将所有活动类型放入map
        List<ActivityCategory> categories = activityCategoryRepository.findAllByCategoryNameNotNull();
        Map<Integer,String> categoryMap = new HashMap<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryMap.put(categories.get(i).getCategoryType(), categories.get(i).getCategoryName());
        }
        //查询距离用户位置distance米的活动
        QueryActivityLocationListModel actLocationModel = null;
        for (ActivityInfo act: all) {
            if (GSUtil.getmeter(longitude,latitude,act.getLongitude(),act.getLatitude())<=distance) {
                actLocationModel = new QueryActivityLocationListModel();
                actLocationModel.setActId(act.getActId());
                actLocationModel.setActTitle(act.getActTitle());
                actLocationModel.setCategory(categoryMap.get(act.getCategoryType()));
                actLocationModel.setActAddress(act.getActAddress());
                actLocationModel.setLongitude(act.getLongitude());
                actLocationModel.setLatitude(act.getLatitude());
                actLocationModel.setParticipantsNumber(act.getParticipantsNumber());
                actLocationModel.setActHeat(act.getActHeat());
                locationListModels.add(actLocationModel);
            }
        }
        return locationListModels;
    }

}
