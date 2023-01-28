package edu.example.demo.Dao;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
import edu.example.demo.entity.ToyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface ToyTypeRepository extends JpaRepository<ToyType,Integer>, Serializable {
    List<ToyType> findByNameLike(String namePattern);
}