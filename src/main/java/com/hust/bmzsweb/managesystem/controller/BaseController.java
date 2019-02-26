package com.hust.bmzsweb.managesystem.controller;


import com.hust.bmzsweb.managesystem.common.DateEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class BaseController {
	@Autowired
	protected HttpServletRequest request;
	

	@InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
    }
	

    /**
     * 获取分页请求
     * @return
     */
    protected PageRequest getPageRequest(){
    	int page = 1;
    	int size = 10;
    	Sort sort = null;

    	try {
    		String sortName = request.getParameter("sortName");
    		String sortOrder = request.getParameter("sortOrder");
/*			System.out.println("sortName:"+sortName);
			System.out.println("sortOrder:"+sortOrder);*/
    		if(StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)){
    			if(sortOrder.equalsIgnoreCase("desc")){
    				sort = new Sort(Direction.DESC, sortName);
				}else{
    				sort = new Sort(Direction.ASC, sortName);
    			}
    		}
    		if (request.getParameter("pageNumber")!=null)
    		page = Integer.parseInt(request.getParameter("pageNumber")) - 1;
    		if (request.getParameter("pageSize")!=null)
    		size = Integer.parseInt(request.getParameter("pageSize"));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	/*	System.out.println("sort:"+sort);
		System.out.println("page:"+page);
		System.out.println("size:"+size);*/
    	PageRequest pageRequest = PageRequest.of(page, size, sort);
    	return pageRequest;
    }
    
    /**
     * 获取分页请求
     * @param sort 排序条件
     * @return
     */
    protected PageRequest getPageRequest(Sort sort){
    	int page = 0;
    	int size = 10;
    	try {
    		String sortName = request.getParameter("sortName");
    		String sortOrder = request.getParameter("sortOrder");
    		if(StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)){
    			if(sortOrder.equalsIgnoreCase("desc")){
    				sort.and(new Sort(Direction.DESC, sortName));
    			}else{
    				sort.and(new Sort(Direction.ASC, sortName));
    			}
			}
    		page = Integer.parseInt(request.getParameter("pageNumber")) - 1;
    		size = Integer.parseInt(request.getParameter("pageSize"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	PageRequest pageRequest = PageRequest.of(page, size, sort);
    	return pageRequest;
    }

}
