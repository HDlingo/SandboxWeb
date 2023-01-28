package edu.example.demo.controller;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
import edu.example.demo.entity.ToyInstance;
import edu.example.demo.results.Result;
import edu.example.demo.service.ToyInstanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("toyInstance")
public class ToyInstanceController {

    /**
     * 这是构造方法注入，相当于 @autowired
     */
    private final ToyInstanceService toyInstanceService;

    public ToyInstanceController(ToyInstanceService toyInstanceService) {
        this.toyInstanceService = toyInstanceService;
    }

    @PostMapping("save")
    public Result save(@RequestBody ToyInstance toyInstance){
        toyInstanceService.save(toyInstance);
        return Result.success();
    }

    @PostMapping("update")
    public Result update(@RequestBody ToyInstance toyInstance){
        toyInstanceService.update(toyInstance.getId(), toyInstance);
        return Result.success();
    }

    @GetMapping("findToyInstanceById/{id}")
    public Result findToyInstanceById(@PathVariable Integer id){
        return Result.success(toyInstanceService.findById(id));
    }

    @GetMapping("findAll")
    public Result findAll(){
        return Result.success(toyInstanceService.findAll());
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        toyInstanceService.delete(id);
        return Result.success();
    }
}

