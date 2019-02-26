package com.hust.bmzsweb.managesystem.controller.web;

import com.hust.bmzsweb.managesystem.business.log.LogService;
import com.hust.bmzsweb.managesystem.business.log.ManagerLog;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import com.hust.bmzsweb.managesystem.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 *   用于日志
 */
@Api(value = "日志接口",tags = "日志接口")
@RestController
@RequestMapping("/admin")
public class LoggerController extends BaseController {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "显示日志信息主页")
    @GetMapping({"/log/","/log/index"})
    public ModelAndView index() {
        return new ModelAndView("admin/log/index");
    }

    @ApiOperation(value = "查询所有日志")
    @GetMapping("/log/list")
    public JSONResult queryActivities(@RequestParam(value="searchText",required=false) String searchText){
        Page<ManagerLog> allLogs = logService.findAllLogs(searchText,getPageRequest());

        return JSONResult.success().add("logPages", allLogs);
    }
}
