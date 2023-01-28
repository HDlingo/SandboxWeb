package edu.example.demo.service;

import edu.example.demo.entity.ToyInstance;

import java.util.List;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
public interface ToyInstanceService {
    public ToyInstance findById(Integer id);

    /**
     * 保存用户
     * @param toyInstance
     */
    public int save(ToyInstance toyInstance);

    /**
     * 删除用户
     * @param id
     */
    public int delete(Integer id);

    /**
     * 查询全部用户
     * @return
     */
    public List<ToyInstance> findAll();

    public int update(Integer id, ToyInstance Instance);
}
