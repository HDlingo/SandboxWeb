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
@Table(name = "ToyInstance")
public class ToyInstance {

    /**
     * 主键生成策略： 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private Integer toy_type;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

}
