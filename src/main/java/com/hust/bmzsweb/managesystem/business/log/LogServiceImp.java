package com.hust.bmzsweb.managesystem.business.log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImp implements LogService{

    @Autowired
    private LogRepositry logRepositry;

    @Override
    public Page<ManagerLog> findAllLogs(String searchText,PageRequest pageRequest) {


        if (StringUtils.isBlank(searchText)){
            searchText = "";
        }
        String finalSearchText = searchText;

        Specification<ManagerLog> managerLogSpecification = new Specification<ManagerLog>() {
            @Override
            public Predicate toPredicate(Root<ManagerLog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(cb.like(root.get("lName"), "%"+finalSearchText+"%"));
                predicateList.add(cb.like(root.get("lIp"), "%"+finalSearchText+"%"));
                predicateList.add(cb.like(root.get("lDetail"), "%"+finalSearchText+"%"));
                Predicate[] preArray = new Predicate[predicateList.size()];
                return cb.or(predicateList.toArray(preArray));
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"lId");

//        Sort sort1 = pageRequest.getSort();System.out.println("sort:"+sort1);
        PageRequest of = PageRequest.of(0, pageRequest.getPageSize(), sort);

        Page<ManagerLog> all = logRepositry.findAll(managerLogSpecification, of);
        return all;
    }
}
