package cn.zf.swc.swcc.wcinfo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WcStatisticsInfo implements Serializable {

    public WcStatisticsInfo(String date,Integer number){
        this.date = date;
        this.number = number;
    }


    private String date;

    private Integer number;
}
