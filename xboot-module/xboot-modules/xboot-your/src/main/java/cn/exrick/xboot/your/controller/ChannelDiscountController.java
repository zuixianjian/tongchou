package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.base.XbootBaseController;
import cn.exrick.xboot.core.common.constant.CommonConstant;
import cn.exrick.xboot.core.common.exception.XbootException;
import cn.exrick.xboot.core.common.redis.RedisTemplateHelper;
import cn.exrick.xboot.core.common.utils.HibernateProxyTypeAdapter;
import cn.exrick.xboot.core.common.utils.PageUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.utils.SecurityUtil;
import cn.exrick.xboot.core.common.vo.PageVo;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.common.vo.SearchVo;
import cn.exrick.xboot.core.entity.Department;
import cn.exrick.xboot.core.entity.User;
import cn.exrick.xboot.core.service.DepartmentHeaderService;
import cn.exrick.xboot.core.service.DepartmentService;
import cn.exrick.xboot.your.entity.ChannelDiscount;
import cn.exrick.xboot.your.entity.resp.DepartMentChannelDiscountResp;
import cn.exrick.xboot.your.service.ChannelDiscountService;
import cn.exrick.xboot.your.utils.security.SecretAnnotation;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Jingqi Chen
 */
@Slf4j
@RestController
@Api(description = "渠道折扣配置管理接口")
@RequestMapping("/xboot/channelDiscount")
@Transactional
public class ChannelDiscountController extends XbootBaseController<ChannelDiscount, String> {

    @Autowired
    private ChannelDiscountService channelDiscountService;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentHeaderService departmentHeaderService;
    @Override
    public ChannelDiscountService getService() {
        return channelDiscountService;
    }

    @RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<Page<ChannelDiscount>> getByCondition(ChannelDiscount channelDiscount,
                                                            SearchVo searchVo,
                                                            PageVo pageVo){

        Page<ChannelDiscount> page = channelDiscountService.findByCondition(channelDiscount, searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<ChannelDiscount>>().setData(page);
    }

    @RequestMapping(value = "/getByParentId/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过parentId获取")
    @SecretAnnotation(decode=false,encode=true)
    public Result<List<DepartMentChannelDiscountResp>> getByParentId(@PathVariable String parentId,
                                                  @ApiParam("是否开始数据权限过滤") @RequestParam(required = false, defaultValue = "true") Boolean openDataFilter){

        List<Department> list = new ArrayList<>();
        User u = securityUtil.getCurrUser();
        String key = "department::"+parentId+":"+u.getId()+"_"+openDataFilter;
        String v = redisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(v)){
            list = new Gson().fromJson(v, new TypeToken<List<Department>>(){}.getType());
        }else {
            list = departmentService.findByParentIdOrderBySortOrder(parentId, openDataFilter);
            list = setInfo(list);
            redisTemplate.opsForValue().set(key,
                    new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create().toJson(list), 15L, TimeUnit.DAYS);
        }
        ArrayList<DepartMentChannelDiscountResp> departMentChannelDiscountResps = new ArrayList<>();
        if (list!=null){
            list.forEach(item->{
                List<ChannelDiscount> channelDiscounts = channelDiscountService.findByDepartMentId(item.getId());
                DepartMentChannelDiscountResp departMentChannelDiscountResp = new DepartMentChannelDiscountResp();
                BeanUtils.copyProperties(item,departMentChannelDiscountResp);
                if (channelDiscounts!=null&&channelDiscounts.size()>0){
                    departMentChannelDiscountResp.setDiscountRate(channelDiscounts.get(0).getDiscountRate());
                    departMentChannelDiscountResp.setChannelDiscountId(channelDiscounts.get(0).getId());
                    departMentChannelDiscountResp.setDiscountRegionGroup(channelDiscounts.get(0).getDiscountRegionGroup());
                    departMentChannelDiscountResp.setRateRegionGroup(channelDiscounts.get(0).getRateRegionGroup());
                }else {
                    departMentChannelDiscountResp.setDiscountRate(new BigDecimal(1));
                }
                departMentChannelDiscountResps.add(departMentChannelDiscountResp);
            });
        }

        return new ResultUtil<List<DepartMentChannelDiscountResp>>().setData(departMentChannelDiscountResps);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "部门名模糊搜索")
    @SecretAnnotation(decode=false,encode=true)
    public Result<List<DepartMentChannelDiscountResp>> searchByTitle(@RequestParam String title,
                                                  @ApiParam("是否开始数据权限过滤") @RequestParam(required = false, defaultValue = "true") Boolean openDataFilter){

        List<Department> list = departmentService.findByTitleLikeOrderBySortOrder("%"+title+"%", openDataFilter);
        list = setInfo(list);
        ArrayList<DepartMentChannelDiscountResp> departMentChannelDiscountResps = new ArrayList<>();
        if (list!=null){
            list.forEach(item->{
                List<ChannelDiscount> channelDiscounts = channelDiscountService.findByDepartMentId(item.getId());
                DepartMentChannelDiscountResp departMentChannelDiscountResp = new DepartMentChannelDiscountResp();
                BeanUtils.copyProperties(item,departMentChannelDiscountResp);
                if (channelDiscounts!=null&&channelDiscounts.size()>0){
                    departMentChannelDiscountResp.setDiscountRate(channelDiscounts.get(0).getDiscountRate());
                    departMentChannelDiscountResp.setChannelDiscountId(channelDiscounts.get(0).getId());
                    departMentChannelDiscountResp.setDiscountRegionGroup(channelDiscounts.get(0).getDiscountRegionGroup());
                    departMentChannelDiscountResp.setRateRegionGroup(channelDiscounts.get(0).getRateRegionGroup());
                }else {
                    departMentChannelDiscountResp.setDiscountRate(new BigDecimal(1));
                }
                departMentChannelDiscountResps.add(departMentChannelDiscountResp);
            });
        }
        return new ResultUtil<List<DepartMentChannelDiscountResp>>().setData(departMentChannelDiscountResps);
    }

    public List<Department> setInfo(List<Department> list){

        // lambda表达式
        list.forEach(item -> {
            if(!CommonConstant.PARENT_ID.equals(item.getParentId())){
                Department parent = departmentService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            }else{
                item.setParentTitle("一级部门");
            }
            // 设置负责人
            item.setMainHeader(departmentHeaderService.findHeaderByDepartmentId(item.getId(), CommonConstant.HEADER_TYPE_MAIN));
            item.setViceHeader(departmentHeaderService.findHeaderByDepartmentId(item.getId(), CommonConstant.HEADER_TYPE_VICE));
        });
        return list;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public Result<ChannelDiscount> save(ChannelDiscount entity){
        ChannelDiscount channelDiscount=entity;
        List<ChannelDiscount> channelDiscounts = channelDiscountService.findByDepartMentId(entity.getDepartmentId());
        if (channelDiscounts!=null&&channelDiscounts.size()>0){
            channelDiscount=channelDiscounts.get(0);
            channelDiscount.setDiscountRate(entity.getDiscountRate());
            channelDiscount.setDiscountRegionGroup(entity.getDiscountRegionGroup());
            channelDiscount.setRateRegionGroup(entity.getRateRegionGroup());
        }

        ChannelDiscount e = getService().save(entity);
        return new ResultUtil<ChannelDiscount>().setData(e);
    }



}
