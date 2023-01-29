package com.hdlingo.sandboxweb.backendssm.entity.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hdlingo.sandboxweb.backendssm.entity.DTO.toy.NewToyParam;
import lombok.Data;

/**
 * description : Toy Po
 *
 * @author : Pikachudy
 * @date : 2023/1/29 19:35
 */
@Data
@TableName("Toy")
public class Toy {
    @TableId(type = IdType.AUTO)
    int id;
    String name;
    public Toy(){};
    public Toy(NewToyParam newToy){
        this.name = newToy.getName();
    }
}
