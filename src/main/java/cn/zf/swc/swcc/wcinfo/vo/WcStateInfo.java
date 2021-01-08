package cn.zf.swc.swcc.wcinfo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WcStateInfo implements Serializable {

    private WcInfoVo wcInfoVo;
    private Long sumSetDataNumber;
    private Long sumSetNumber;
    private Long sumManSetNumber;
    private Long sumWomanSetNumber;
}
