package edu.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author jqChen
 * @Date 2022/11/24
 * @Github https://github.com/KyrinChen
 */
@Data
@Entity
@Table(name = "ToyType")
public class ToyType {
    /**
     * 主键生成策略： 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
