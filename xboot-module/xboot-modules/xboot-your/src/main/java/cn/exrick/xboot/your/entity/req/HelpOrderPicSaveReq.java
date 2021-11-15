package cn.exrick.xboot.your.entity.req;

import cn.exrick.xboot.your.entity.resp.HelpOrderPicsResp;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HelpOrderPicSaveReq implements Serializable {
    private List<HelpOrderPicsResp> helpOrderPicsResps;
    private String helpOrderId;
}
