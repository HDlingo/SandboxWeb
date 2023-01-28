package edu.example.demo.service.impl;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */

import edu.example.demo.Dao.ToyInstanceRepository;
import edu.example.demo.entity.ToyInstance;
import edu.example.demo.service.ToyInstanceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ToyInstanceServiceImpl implements ToyInstanceService {

    private final ToyInstanceRepository toyInstanceRepository;

    public ToyInstanceServiceImpl(ToyInstanceRepository toyInstanceRepository) {
        this.toyInstanceRepository = toyInstanceRepository;
    }

    @Override
    public ToyInstance findById(Integer id) {
        return toyInstanceRepository.findById(id).orElse(null);
    }

    @Override
    public List<ToyInstance> findAll() { return toyInstanceRepository.findAll(); }

    @Override
    public int save(ToyInstance toyInstance) {
        toyInstanceRepository.save(toyInstance);
        return toyInstance.getId();
    }

    @Override
    public int update(Integer id, ToyInstance toyInstance) {
        // 有id则更新，无id则新增
        toyInstance.setId(id);
        toyInstanceRepository.save(toyInstance);
        return id;
    }

    @Override
    public int delete(Integer id) {
        ToyInstance toyInstance = new ToyInstance();
        toyInstance.setId(id);
        toyInstanceRepository.delete(toyInstance);
        return id;
    }

}
