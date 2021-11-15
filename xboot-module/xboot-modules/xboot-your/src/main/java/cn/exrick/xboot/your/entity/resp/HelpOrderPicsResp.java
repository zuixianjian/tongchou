package cn.exrick.xboot.your.entity.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HelpOrderPicsResp implements Serializable {
    private String classify;
    private List<String> pics;
}
