package cn.zf.swc.swcc.setinfo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SetStatus {
    private Long wcId;
    private String macCode;
    private String updateTime;
    private List<Integer> statusList;
    private String messageId;
}
