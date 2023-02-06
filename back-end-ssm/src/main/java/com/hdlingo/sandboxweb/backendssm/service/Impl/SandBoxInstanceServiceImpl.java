package com.hdlingo.sandboxweb.backendssm.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdlingo.sandboxweb.backendssm.entity.PO.SandBoxInstance;
import com.hdlingo.sandboxweb.backendssm.map.SandBoxInstanceMapper;
import com.hdlingo.sandboxweb.backendssm.service.SandBoxInstanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author jqChen
 * @Date 2023/2/1
 */
@Service
public class SandBoxInstanceServiceImpl extends ServiceImpl<SandBoxInstanceMapper, SandBoxInstance> implements SandBoxInstanceService {
    @Resource
    SandBoxInstanceMapper sandBoxInstanceMapper;

    @Override
    public List<SandBoxInstance> getByUserId(String userId){
        //条件查询
        QueryWrapper<SandBoxInstance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId);
        //查找出maskTypeId对应的defectClassPlan列表
        return sandBoxInstanceMapper.selectList(queryWrapper);
    }

    @Override
    public List<SandBoxInstance> getByToyId(Integer toyId){
        //条件查询
        QueryWrapper<SandBoxInstance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("toyId",toyId);
        //查找出maskTypeId对应的defectClassPlan列表
        return sandBoxInstanceMapper.selectList(queryWrapper);
    }
}
