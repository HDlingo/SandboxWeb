package edu.example.demo.Dao;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
import edu.example.demo.entity.StatisticView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface StatisticViewRepository extends JpaRepository<StatisticView,Integer>, Serializable {
    List<StatisticView> findByIp(String ip);

//    List<StatisticView> findByIpAndToyType(String ip, Integer toyType);
}