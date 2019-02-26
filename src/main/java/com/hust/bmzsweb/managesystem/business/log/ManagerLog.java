package com.hust.bmzsweb.managesystem.business.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ManagerLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lId;
    private String lName;
    private String lIp;
    private String lDetail;
    private String lApiname;
    private String lPara;
    @Column(name = "l_time_cosume")
    private Date lTimeConsume;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//结果多处一个字段
    private Date lCreateTime;
    private Date lUpdateTime;

    public ManagerLog() {
    }
}
