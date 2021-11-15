package cn.exrick.xboot.your.entity.resp;

import cn.exrick.xboot.your.entity.HelperDamage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class DamangeListHelperDamageItem extends HelperDamage {

    private List<DamageListHelperDamageDetailItem> children;
    private Boolean expand=true;
    private Integer node=1;
}
