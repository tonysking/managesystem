package com.hust.bmzsweb.managesystem.business.log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface LogService {
    Page<ManagerLog> findAllLogs(String searchText,PageRequest pageRequest);
}
