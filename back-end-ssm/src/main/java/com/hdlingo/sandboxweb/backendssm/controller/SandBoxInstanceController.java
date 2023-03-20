package com.hdlingo.sandboxweb.backendssm.controller;

import com.hdlingo.sandboxweb.backendssm.entity.DTO.SandBoxInstance.SubmitResult;
import com.hdlingo.sandboxweb.backendssm.entity.PO.SandBoxInstance;
import com.hdlingo.sandboxweb.backendssm.entity.returning.ResultData;
import com.hdlingo.sandboxweb.backendssm.service.SandBoxInstanceService;
import com.hdlingo.sandboxweb.backendssm.service.ToyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author jqChen
 * @Date 2023/2/1
 */

@Tag(name="沙盒SandBoxInstance")
@RestController
@CrossOrigin
@RequestMapping("SandBoxInstance")
public class SandBoxInstanceController {
    @Resource
    SandBoxInstanceService sandBoxInstanceService;
    @Resource
    ToyService toyService;

    @Operation(summary = "根据 userId 获取所有SandBoxInstance")
    @GetMapping("user/{userId}")
    ResultData getSandBoxInstancesByUserId(@PathVariable("userId")String userId){
        List<SandBoxInstance> sandBoxInstanceList = sandBoxInstanceService.getByUserId(userId);
        if(sandBoxInstanceList.isEmpty()){
            return new ResultData().FAILED();
        }
        else{
            return new ResultData().sendObj(true,sandBoxInstanceList);
        }
    }

    @Operation(summary = "根据 toyId 获取所有SandBoxInstance")
    @GetMapping("toy/{toyId}")
    ResultData getSandBoxInstancesByUserId(@PathVariable("toyId")Integer toyId){
        List<SandBoxInstance> sandBoxInstanceList = sandBoxInstanceService.getByToyId(toyId);
        if(sandBoxInstanceList.isEmpty()){
            return new ResultData().FAILED();
        }
        else{
            return new ResultData().sendObj(true,sandBoxInstanceList);
        }
    }

    @Operation(summary = "添加 SandBoxInstance")
    @PostMapping
    ResultData addSandBoxInstance(@RequestBody SandBoxInstance sandBoxInstance){
        if(sandBoxInstance==null){
            return new ResultData<>().FAILED();
        }
        if(sandBoxInstanceService.save(sandBoxInstance)){
            return new ResultData<>().OK();
        }
        else{
            return new ResultData().FAILED();
        }
    }

    @Operation(summary = "添加 SandBoxInstances")
    @PostMapping("list")
    ResultData addSandBoxInstances(@RequestBody SubmitResult submitResult){
        if(submitResult==null){
            return new ResultData<>().FAILED();
        }

        for (SandBoxInstance s:submitResult.getSandBoxInstances()) {

            if(sandBoxInstanceService.save(s)){
            }
            else{
                return new ResultData().FAILED();
            }
        }
        return new ResultData<>().OK();

    }

    @Operation(summary = "根据 id 删除 SandBoxInstance")
    @DeleteMapping({"{id}"})
    ResultData deleteById(@PathVariable("id") int id){
        if(sandBoxInstanceService.removeById(id)){
            return new ResultData<>().OK();
        }
        else{
            return new ResultData<>().FAILED();
        }
    }

    @Operation(summary = "添加 SandBoxInstances")
    @PostMapping("test/list")
    ResultData t_addSandBoxInstances(@RequestBody SubmitResult submitResult){
        if(submitResult==null){
            return new ResultData<>().FAILED();
        }

        for (SandBoxInstance s:submitResult.getSandBoxInstances()) {
            System.out.println(s.getToyId());
            System.out.println(s.getPhoneNumber());
        }
        return new ResultData<>().OK();

    }
}
