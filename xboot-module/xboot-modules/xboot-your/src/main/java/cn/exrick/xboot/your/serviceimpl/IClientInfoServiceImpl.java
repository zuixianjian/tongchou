package cn.exrick.xboot.your.serviceimpl;

import cn.exrick.xboot.your.mapper.ClientInfoMapper;
import cn.exrick.xboot.your.entity.ClientInfo;
import cn.exrick.xboot.your.service.IClientInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户信息维护接口实现
 * @author Jingqi Chen
 */
@Slf4j
@Service
@Transactional
public class IClientInfoServiceImpl extends ServiceImpl<ClientInfoMapper, ClientInfo> implements IClientInfoService {

    @Autowired
    private ClientInfoMapper clientInfoMapper;
}