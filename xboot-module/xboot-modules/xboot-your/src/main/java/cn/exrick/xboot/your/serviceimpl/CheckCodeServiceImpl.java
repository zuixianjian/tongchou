package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.CheckCodeDao;
import cn.exrick.xboot.your.entity.CheckCode;
import cn.exrick.xboot.your.mapper.CheckCodeMapper;
import cn.exrick.xboot.your.service.CheckCodeService;
import cn.exrick.xboot.your.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Field;

/**
 * 验车码接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class CheckCodeServiceImpl implements CheckCodeService {

    @Autowired
    private CheckCodeDao checkCodeDao;
    @Autowired
    private CheckCodeMapper checkCodeMapper;

    @Override
    public CheckCodeDao getRepository() {
        return checkCodeDao;
    }

    @Override
    public Page<CheckCode> findByCondition(CheckCode checkCode, SearchVo searchVo, Pageable pageable) {

        return checkCodeDao.findAll(new Specification<CheckCode>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<CheckCode> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 可添加你的其他搜索过滤条件 默认已有创建时间过滤
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public CheckCode getTodyCode() {
        CheckCode checkCode = checkCodeMapper.getTodayCode();
        if (checkCode==null){
            checkCode=new CheckCode();
            checkCode.setCode(CommonUtils.getRandomNickname(6));
            checkCode.setGenerateDate(LocalDateTime.now());
            save(checkCode);
        }
        return checkCode;
    }
}