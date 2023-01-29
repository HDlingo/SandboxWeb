package com.hdlingo.sandboxweb.backendssm.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdlingo.sandboxweb.backendssm.entity.DTO.toy.NewToyParam;
import com.hdlingo.sandboxweb.backendssm.entity.PO.Toy;
import com.hdlingo.sandboxweb.backendssm.map.ToyMapper;
import com.hdlingo.sandboxweb.backendssm.service.ToyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * description : Toy Service 接口实现
 *
 * @author : Pikachudy
 * @date : 2023/1/29 19:42
 */
@Service
public class ToyServiceImpl implements ToyService {
    @Resource
    ToyMapper toyMapper;
    @Override
    public List<Toy> getAllToys() {
        return toyMapper.selectList(null);
    }

    @Override
    public Toy getToyById(int id) {
        return toyMapper.selectById(id);
    }
    @Override
    public Toy getToyByName(String toyName){
        QueryWrapper<Toy> wrapper = new QueryWrapper<Toy>()
                .eq("name",toyName);
        return toyMapper.selectOne(wrapper);
    }
    @Override
    public boolean addToy(NewToyParam toyParam) {
        // 是否存在
        if(this.getToyByName(toyParam.getName())==null){
            Toy newToy = new Toy(toyParam);
            toyMapper.insert(newToy);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        if(toyMapper.deleteById(id)==0){
            return false;
        }
        else{
            return true;
        }
    }
}
