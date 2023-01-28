package edu.example.demo.Dao;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
import edu.example.demo.entity.ToyInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ToyInstanceRepository extends JpaRepository<ToyInstance,Integer>, Serializable {
}