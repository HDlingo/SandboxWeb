package edu.example.demo.service;

import edu.example.demo.entity.ToyType;

import java.util.List;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
public interface ToyTypeService {
    public ToyType findById(Integer id);

    /**
     * 保存用户
     * @param toyType
     */
    public int save(ToyType toyType);

    /**
     * 删除用户
     * @param id
     */
    public int delete(Integer id);

    /**
     * 查询全部用户
     * @return
     */
    public List<ToyType> findAll();

    public int update(Integer id, ToyType toyType);

    public List<ToyType> findByNameLikePattern(String namePattern);
}
