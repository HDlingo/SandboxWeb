package com.hdlingo.sandboxweb.backendssm.controller;

import com.hdlingo.sandboxweb.backendssm.entity.DTO.toy.NewToyParam;
import com.hdlingo.sandboxweb.backendssm.entity.PO.Toy;
import com.hdlingo.sandboxweb.backendssm.entity.returning.ResultData;
import com.hdlingo.sandboxweb.backendssm.service.ToyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * description : Toy Web Controller
 *
 * @author : Pikachudy
 * @date : 2023/1/29 19:37
 */
@Tag(name="沙盒Toy")
@RestController
@CrossOrigin
@RequestMapping("toy")
public class ToyController {
    @Resource
    ToyService toyService;
    @Operation(summary = "获取全部Toy")
    @GetMapping
    ResultData getAllToys(){
        List<Toy> toyList = toyService.getAllToys();
        if(toyList.isEmpty()){
            return new ResultData().FAILED();
        }
        else{
            return new ResultData().sendObj(true,toyList);
        }
    }

    @Operation(summary = "根据 id 获取单个Toy")
    @GetMapping("{id}")
    ResultData getById(@PathVariable("id")int toyId){
        Toy toy = toyService.getToyById(toyId);
        if(toy==null){
            return new ResultData().FAILED();
        }
        else{
            return new ResultData<>().sendObj(true,toy);
        }
    }

    @Operation(summary = "根据 name 获取单个Toy")
    @GetMapping("name/{toyName}")
    ResultData getByName(@PathVariable("toyName")String toyName){
        Toy toy = toyService.getToyByName(toyName);
        if(toy==null){
            return new ResultData<>().FAILED();
        }
        else{
            return new ResultData<>().sendObj(true,toy);
        }
    }

    @Operation(summary = "添加 Toy")
    @PostMapping
    ResultData addToy(@RequestBody NewToyParam toyParam){
        if(toyParam==null){
            return new ResultData<>().FAILED();
        }
        if(toyService.addToy(toyParam)){
            return new ResultData<>().OK();
        }
        else{
            return new ResultData().FAILED();
        }
    }

    @Operation(summary = "根据 id 删除 Toy")
    @DeleteMapping({"{id}"})
    ResultData deleteById(@PathVariable("id") int id){
        if(toyService.deleteById(id)){
            return new ResultData<>().OK();
        }
        else{
            return new ResultData<>().FAILED();
        }
    }

    @Operation(summary = "添加 Toy")
    @PostMapping("testPost")
    ResultData testPostToy(@RequestBody NewToyParam toyParam){
        if(toyParam==null){
            return new ResultData<>().FAILED();
        }
        else{
            System.out.println("name:"+toyParam.getName());
            return new ResultData<>().OK();
        }
    }
}
