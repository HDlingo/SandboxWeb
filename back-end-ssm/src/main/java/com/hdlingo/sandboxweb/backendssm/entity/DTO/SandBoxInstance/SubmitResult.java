package com.hdlingo.sandboxweb.backendssm.entity.DTO.SandBoxInstance;

import com.hdlingo.sandboxweb.backendssm.entity.PO.SandBoxInstance;
import lombok.Data;

import java.util.List;

/**
 * @Author jqChen
 * @Date 2023/2/4
 */
@Data
public class SubmitResult {
    String userId;
    List<SandBoxInstance> sandBoxInstances;

    public void syncUserId(){
        for (SandBoxInstance s:this.sandBoxInstances) {
            s.setUserId(this.userId);
        }
    }
}
