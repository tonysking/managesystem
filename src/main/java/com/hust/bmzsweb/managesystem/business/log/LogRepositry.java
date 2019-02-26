package com.hust.bmzsweb.managesystem.business.log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepositry extends JpaRepository<ManagerLog,Integer>,JpaSpecificationExecutor<ManagerLog> {
    Page<ManagerLog> findByLId(Integer lId, Pageable pageable);

}
