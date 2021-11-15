package cn.exrick.xboot.your.entity.resp;

import cn.exrick.xboot.your.entity.HelperDamageDetail;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DamageListHelperDamageDetailItem extends HelperDamageDetail {
    private Boolean expand=false;
    private Integer node=2;
}
