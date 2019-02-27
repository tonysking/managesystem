package com.hust.bmzsweb.managesystem.business.activitySignup;

import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivitySignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface ActivityRequiredItemDetailRepository extends JpaRepository<ActivityRequiredItemDetail,Integer>, JpaSpecificationExecutor<ActivityRequiredItemDetail> {

    ActivityRequiredItemDetail findByRequiredItemDetailIdEquals(Integer requiredItemDetailId);

    void deleteActivityRequiredItemDetailByRequiredItemDetailId(Integer requiredItemDetailId);

    List<ActivityRequiredItemDetail> findActivityRequiredItemDetailsByRequiredItemDetailId(Integer requiredItemDetailId);

}
