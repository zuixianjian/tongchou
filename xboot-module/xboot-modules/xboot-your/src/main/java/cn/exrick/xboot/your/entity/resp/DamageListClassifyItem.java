package cn.exrick.xboot.your.entity.resp;

import cn.exrick.xboot.your.entity.HelperDamageClassify;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Data
@Getter
@Setter
public class DamageListClassifyItem extends HelperDamageClassify {

    private List<DamangeListHelperDamageItem> children;

    private Boolean expand=true;

    private Integer node=0;
}
