package edu.example.demo.controller;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
import edu.example.demo.entity.ToyType;
import edu.example.demo.results.Result;
import edu.example.demo.service.ToyTypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("toyType")
public class ToyTypeController {

    /**
     * 这是构造方法注入，相当于 @autowired
     */
    private final ToyTypeService toyTypeService;

    public ToyTypeController(ToyTypeService toyTypeService) {
        this.toyTypeService = toyTypeService;
    }

    @PostMapping("save")
    public Result save(@RequestBody ToyType toyType){
        toyTypeService.save(toyType);
        return Result.success();
    }

    @PostMapping("update")
    public Result update(@RequestBody ToyType toyType){
        toyTypeService.update(toyType.getId(), toyType);
        return Result.success();
    }

    @GetMapping("findById/{id}")
    public Result findById(@PathVariable Integer id){
        return Result.success(toyTypeService.findById(id));
    }

    @GetMapping("findAll")
    public Result findAll(){
        return Result.success(toyTypeService.findAll());
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id){
        toyTypeService.delete(id);
        return Result.success();
    }

    @GetMapping("getByName")
    public Result getByName(String name){
        return Result.success(toyTypeService.findByNameLikePattern(name));
    }
}

