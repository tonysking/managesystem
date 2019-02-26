package com.hust.bmzsweb.managesystem.controller.applet;

import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityLocationListModel;
import com.hust.bmzsweb.managesystem.business.user.model.UserPositionModel;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 在地图上标明活动位置
 */
@RestController
@Api(value = "小程序活动位置接口",tags = "小程序活动位置接口")
@RequestMapping("/applet/activityLocation")
public class ActivityLocationController {

    @Autowired
    ActivityService activityService;

    @ApiOperation(value = "查询用户周围的活动位置信息")
    @PostMapping("/getLocations")
    public JSONResult getActLocationsFromUserPosition(@RequestBody UserPositionModel userPositionModel,
                                                      @RequestParam(value = "distance",required = false) Double distance){

        List<QueryActivityLocationListModel> actLocationsList = activityService.findActLocationsFromUserPosition(userPositionModel, distance);
        return JSONResult.success().add("addressNear", actLocationsList);
    }

}
