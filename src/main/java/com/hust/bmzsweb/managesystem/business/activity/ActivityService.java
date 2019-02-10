package com.hust.bmzsweb.managesystem.business.activity;

import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ActivityService {

    public Page<QueryActivityListModel> findAllActsByUserId(Integer userId, PageRequest pageRequest);

}
