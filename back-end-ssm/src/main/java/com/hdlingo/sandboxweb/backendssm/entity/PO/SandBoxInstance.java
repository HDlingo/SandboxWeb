package com.hdlingo.sandboxweb.backendssm.entity.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : KyrinChen
 * @date : 2023/2/1
 */
@Data
@TableName("SandBoxInstance")
public class SandBoxInstance implements Serializable {
    @TableId(type = IdType.AUTO)
    int id;
    @TableField(value = "toyId")
    int toyId;
    @TableField(value = "phoneNumber")
    String phoneNumber;
    double x;
    double y;
    double rotation;
    double height;
    double width;
    String description;
}
