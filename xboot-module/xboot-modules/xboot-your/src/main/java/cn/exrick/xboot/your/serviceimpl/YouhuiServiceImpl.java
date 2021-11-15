package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.utils.SecurityUtil;
import cn.exrick.xboot.core.entity.Department;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.your.dao.YouhuiDao;
import cn.exrick.xboot.your.entity.ChannelDiscount;
import cn.exrick.xboot.your.entity.Youhui;
import cn.exrick.xboot.your.service.ChannelDiscountService;
import cn.exrick.xboot.your.service.IDepartmentService;
import cn.exrick.xboot.your.service.YouhuiService;
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

import java.util.List;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.lang.reflect.Field;

/**
 * 优惠政策配置接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class YouhuiServiceImpl implements YouhuiService {

    @Autowired
    private YouhuiDao youhuiDao;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private ChannelDiscountService channelDiscountService;
    @Autowired
    private IDepartmentService departmentService;

    @Override
    public YouhuiDao getRepository() {
        return youhuiDao;
    }

    @Override
    public Page<Youhui> findByCondition(Youhui youhui, SearchVo searchVo, Pageable pageable) {

        return youhuiDao.findAll(new Specification<Youhui>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Youhui> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {


                // TODO 请根据实际需求修改你的搜索条件
                Path<String> feeFloatReasonField = root.get("feeFloatReason");
                Path<java.math.BigDecimal> feeFloatRateField = root.get("feeFloatRate");
                Path<String> regionGroupField = root.get("regionGroup");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 优惠描述模糊搜素
                if(StrUtil.isNotBlank(youhui.getFeeFloatReason())){
                    list.add(cb.like(feeFloatReasonField,'%'+youhui.getFeeFloatReason()+'%'));
                }
                // 折扣率相等匹配
                if(youhui.getFeeFloatRate()!=null){
                    list.add(cb.equal(feeFloatRateField, youhui.getFeeFloatRate()));
                }
                // 折扣组模糊搜素
                if(StrUtil.isNotBlank(youhui.getRegionGroup())){
                    list.add(cb.like(regionGroupField,'%'+youhui.getRegionGroup()+'%'));
                }
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
    public Page<Youhui> getMineYouhui(Youhui youhui, SearchVo searchVo, Pageable pageable) {
        return youhuiDao.findAll(new Specification<Youhui>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Youhui> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //获取当前用户的费率组，如果没有费率组 按车牌号来
                // 获取当前登录用户
                User user = securityUtil.getCurrUser();
                //获取该用户所在部门的所有顶级部门(包括自己所在部门)
                String  rateRegionGroup = null;
                String  discountRegionGroup = null;
                if (user.getDepartmentId() != null) {
                    List<Department> departments = departmentService.getAllHeadDepartmentsByDepartmentId(user.getDepartmentId());
                    if (departments != null) {
                        for (Department department : departments) {
                            List<ChannelDiscount> channelDiscounts = channelDiscountService.findByDepartMentId(department.getId());
                            if (channelDiscounts != null && channelDiscounts.size() > 0) {
                                discountRegionGroup = channelDiscounts.get(0).getDiscountRegionGroup();
                                rateRegionGroup = channelDiscounts.get(0).getRateRegionGroup();
                                break;
                            }
                        }

                    }
                }
                // TODO 请根据实际需求修改你的搜索条件
                Path<String> feeFloatReasonField = root.get("feeFloatReason");
                Path<java.math.BigDecimal> feeFloatRateField = root.get("feeFloatRate");
                Path<String> regionGroupField = root.get("regionGroup");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 优惠描述模糊搜素
                if(StrUtil.isNotBlank(youhui.getFeeFloatReason())){
                    list.add(cb.like(feeFloatReasonField,'%'+youhui.getFeeFloatReason()+'%'));
                }
                // 折扣率相等匹配
                if(youhui.getFeeFloatRate()!=null){
                    list.add(cb.equal(feeFloatRateField, youhui.getFeeFloatRate()));
                }

                // 折扣组模糊搜素
                if(StrUtil.isNotBlank(discountRegionGroup)){
                    list.add(cb.like(regionGroupField,'%'+discountRegionGroup+'%'));
                }

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
}