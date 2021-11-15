package cn.exrick.xboot.your.service;

import cn.exrick.xboot.core.base.XbootBaseService;
import cn.exrick.xboot.your.entity.CheckCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import cn.exrick.xboot.core.common.vo.SearchVo;

import java.util.List;

/**
 * 验车码接口
 * @author Jingqi Chen
 */
public interface CheckCodeService extends XbootBaseService<CheckCode, String> {

    /**
    * 多条件分页获取
    * @param checkCode
    * @param searchVo
    * @param pageable
    * @return
    */
    Page<CheckCode> findByCondition(CheckCode checkCode, SearchVo searchVo, Pageable pageable);

    CheckCode getTodyCode();
}