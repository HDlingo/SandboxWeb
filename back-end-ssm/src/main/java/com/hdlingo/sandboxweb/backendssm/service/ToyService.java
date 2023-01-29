package com.hdlingo.sandboxweb.backendssm.service;

import com.hdlingo.sandboxweb.backendssm.entity.DTO.toy.NewToyParam;
import com.hdlingo.sandboxweb.backendssm.entity.PO.Toy;


import java.util.List;

/**
 * description : Toy Service 接口
 *
 * @author : Pikachudy
 * @date : 2023/1/29 19:41
 */
public interface ToyService {
    /**
     * 返回所有Toy
     *
     * @return {@link List}<{@link Toy}>
     */
    List<Toy> getAllToys();

    /**
     * 通过id获取Toy
     *
     * @param id id
     * @return {@link Toy}
     */
    Toy getToyById(int id);

    /**
     * 通过name获取Toy
     *
     * @param toyName 玩具名字
     * @return {@link Toy}
     */
    Toy getToyByName(String toyName);

    /**
     * 添加Toy
     *
     * @param toyParam DTO
     * @return boolean
     */
    boolean addToy(NewToyParam toyParam);
    boolean deleteById(int id);
}
