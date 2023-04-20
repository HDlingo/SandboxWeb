package com.hdlingo.sandboxweb.backendssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hdlingo.sandboxweb.backendssm.entity.DTO.SandBoxInstance.SubmitResult;
import com.hdlingo.sandboxweb.backendssm.entity.PO.SandBoxInstance;

import java.util.List;

/**
 * @Author jqChen
 * @Date 2023/2/1
 */
public interface SandBoxInstanceService extends IService<SandBoxInstance> {

    List<SandBoxInstance> getByPhoneNumber(String phoneNumber);

    List<SandBoxInstance> getByToyId(Integer toyId);
}
