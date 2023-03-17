package com.hdlingo.sandboxweb.backendssm.entity.DTO.SandBoxInstance;

import com.hdlingo.sandboxweb.backendssm.entity.PO.SandBoxInstance;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author jqChen
 * @Date 2023/2/4
 */
@Data
public class SubmitResult {
    String userId;
    List<SandBoxInstance> sandBoxInstances;
//    List<NewSandBoxInstance> VO_SandBoxInstances;

    public void syncUserId(){
        for (SandBoxInstance s:this.sandBoxInstances) {
            s.setUserId(this.userId);
        }
    }
}

//@Data
//class NewSandBoxInstance implements Serializable {
//    String toyName;
//    String userId;
//    double x;
//    double y;
//    double rotation;
//}
