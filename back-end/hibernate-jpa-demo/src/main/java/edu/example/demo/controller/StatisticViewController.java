package edu.example.demo.controller;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */

import edu.example.demo.Dao.StatisticViewRepository;
import edu.example.demo.results.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("StatisticView")
public class StatisticViewController {

    /**
     * 这是构造方法注入，相当于 @autowired
     */
    private final StatisticViewRepository statisticViewRepository;

    public StatisticViewController(StatisticViewRepository statisticViewRepository) {
        this.statisticViewRepository = statisticViewRepository;
    }

    @GetMapping("findByIp")
    public Result findByIp(@RequestParam(name = "ip") String ip){
        return Result.success(statisticViewRepository.findByIp(ip));
    }

//    @GetMapping("findByIpAndToy")
//    public Result findByIpAndToy(@RequestParam(name = "ip") String ip, @RequestParam(name = "toyType") Integer toyType){
//        return Result.success(statisticViewRepository.findByIpAndToyType(ip, toyType));
//    }

    @GetMapping("findAll")
    public Result findAll(){
        return Result.success(statisticViewRepository.findAll());
    }

}

