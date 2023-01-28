package edu.example.demo.service.impl;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */

import edu.example.demo.Dao.ToyTypeRepository;
import edu.example.demo.entity.ToyType;
import edu.example.demo.service.ToyTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ToyTypeServiceImpl implements ToyTypeService {

    private final ToyTypeRepository toyTypeRepository;

    public ToyTypeServiceImpl(ToyTypeRepository toyTypeRepository) {
        this.toyTypeRepository = toyTypeRepository;
    }

    @Override
    public ToyType findById(Integer id) {
        return toyTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ToyType> findAll() { return toyTypeRepository.findAll(); }

    @Override
    public int save(ToyType toyType) {
        toyTypeRepository.save(toyType);
        return toyType.getId();
    }

    @Override
    public int update(Integer id, ToyType toyType) {
        // 有id则更新，无id则新增
        toyType.setId(id);
        toyTypeRepository.save(toyType);
        return id;
    }

    @Override
    public int delete(Integer id) {
        ToyType toyType = new ToyType();
        toyType.setId(id);
        toyTypeRepository.delete(toyType);
        return id;
    }

    @Override
    public List<ToyType> findByNameLikePattern(String namePattern) {
        return toyTypeRepository.findByNameLike(namePattern);
    }

}
