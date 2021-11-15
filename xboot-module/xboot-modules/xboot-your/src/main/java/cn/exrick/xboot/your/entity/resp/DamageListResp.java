package cn.exrick.xboot.your.entity.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class DamageListResp implements Serializable {
    private List<DamageListClassifyItem> damageListClassifyItems;

}
