package com.hdlingo.sandboxweb.backendssm.entity.DTO.SandBoxInstance;

import com.hdlingo.sandboxweb.backendssm.entity.PO.SandBoxInstance;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jqChen
 * @Date 2023/2/4
 */
@Data
public class SubmitResult {
    String userId;
    List<NewSandBoxInstance> newSandBoxInstances;

    public List<SandBoxInstance> getSandBoxInstances(){
        List<SandBoxInstance> sandBoxInstances = new ArrayList<>();
        for (NewSandBoxInstance n:this.newSandBoxInstances
             ) {
            SandBoxInstance s=n.turnToSandBoxInstance();
            s.setUserId(this.userId);
            sandBoxInstances.add(s);
        }
        return sandBoxInstances;
    }
}

@Data
class NewSandBoxInstance implements Serializable {
    String name;
    String type;
    int id;// 玩具类型的id（toy.id
    Transform transform;


    public SandBoxInstance turnToSandBoxInstance(){
        SandBoxInstance s =new SandBoxInstance();
        s.setToyId(this.id);
        s.setX(this.transform.getX());
        s.setY(this.transform.getY());
        s.setRotation(this.transform.getRotation());
        return  s;
    }
}

@Data
class Transform{
    double height;
    double width;
    double x;
    double y;
    double rotation;
}