package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.dao.HelperOrderPicDao;
import cn.exrick.xboot.your.entity.DictData;
import cn.exrick.xboot.your.entity.HelperOrderPic;
import cn.exrick.xboot.your.entity.req.HelpOrderPicSaveReq;
import cn.exrick.xboot.your.entity.resp.HelpOrderPicsResp;
import cn.exrick.xboot.your.mapper.DictDataMapper;
import cn.exrick.xboot.your.service.HelperOrderPicService;
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
 * 互助单主表接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class HelperOrderPicServiceImpl implements HelperOrderPicService {

    @Autowired
    private DictDataMapper dictDataMapper;
    @Autowired
    private HelperOrderPicDao helperOrderPicDao;

    @Override
    public HelperOrderPicDao getRepository() {
        return helperOrderPicDao;
    }

    @Override
    public Page<HelperOrderPic> findByCondition(HelperOrderPic helperOrderPic, SearchVo searchVo, Pageable pageable) {

        return helperOrderPicDao.findAll(new Specification<HelperOrderPic>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<HelperOrderPic> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                // TODO 请根据实际需求修改你的搜索条件
                Path<String> classifyField = root.get("classify");
                Path<String> helperOrderIdField = root.get("helperOrderId");
                Path<String> fileUrlField = root.get("fileUrl");
                Path<String> remarkField = root.get("remark");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                // 分类模糊搜素
                if(StrUtil.isNotBlank(helperOrderPic.getClassify())){
                    list.add(cb.like(classifyField,'%'+helperOrderPic.getClassify()+'%'));
                }
                // 互助单id模糊搜素
                if(StrUtil.isNotBlank(helperOrderPic.getHelperOrderId())){
                    list.add(cb.like(helperOrderIdField,'%'+helperOrderPic.getHelperOrderId()+'%'));
                }
                // 附件地址模糊搜素
                if(StrUtil.isNotBlank(helperOrderPic.getFileUrl())){
                    list.add(cb.like(fileUrlField,'%'+helperOrderPic.getFileUrl()+'%'));
                }
                // 照片备注模糊搜素
                if(StrUtil.isNotBlank(helperOrderPic.getRemark())){
                    list.add(cb.like(remarkField,'%'+helperOrderPic.getRemark()+'%'));
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
    public List<HelpOrderPicsResp> getByHelpOrderId(String helpOrderId) {
        ArrayList<HelpOrderPicsResp> helpOrderPicsResps = new ArrayList<>();
        List<DictData> helperOrderPicSorts = dictDataMapper.getByDictType("helperOrderPicSort");

        if (helperOrderPicSorts!=null){
            for (DictData helperOrderPicSort : helperOrderPicSorts) {
                HelpOrderPicsResp helpOrderPicsResp = new HelpOrderPicsResp();
                helpOrderPicsResp.setClassify(helperOrderPicSort.getValue());
                List<HelperOrderPic> pics = helperOrderPicDao.getByClassifyAndHelperOrderIdOrderByCreateTime(helperOrderPicSort.getValue(),
                        helpOrderId);
                ArrayList<String> picUrls = new ArrayList<>();
                if (pics!=null){
                    for (HelperOrderPic pic : pics) {
                        picUrls.add(pic.getFileUrl());
                    }
                }
                helpOrderPicsResp.setPics(picUrls);
                helpOrderPicsResps.add(helpOrderPicsResp);
            }
        }
        return helpOrderPicsResps;
    }

    @Override
    public void saveHelpOrderPics(HelpOrderPicSaveReq helpOrderPicSaveReq) {
        //1先删除掉以前的图片附件
        helperOrderPicDao.deleteByOrderId(helpOrderPicSaveReq.getHelpOrderId());
        //2再存储现在的图片附件
        List<HelpOrderPicsResp> helpOrderPicsResps = helpOrderPicSaveReq.getHelpOrderPicsResps();
        if (helpOrderPicsResps!=null){
            for (HelpOrderPicsResp helpOrderPicsResp : helpOrderPicsResps) {
                if (helpOrderPicsResp.getPics()!=null){
                    for (String pic : helpOrderPicsResp.getPics()) {
                        HelperOrderPic helperOrderPic = new HelperOrderPic();
                        helperOrderPic.setFileUrl(pic);
                        helperOrderPic.setClassify(helpOrderPicsResp.getClassify());
                        helperOrderPic.setHelperOrderId(helpOrderPicSaveReq.getHelpOrderId());
                        helperOrderPicDao.save(helperOrderPic);
                    }
                }
            }
        }
    }
}