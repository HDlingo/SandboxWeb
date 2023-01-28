package edu.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author jqChen
 * @Date 2022/11/24
 * @Github https://github.com/KyrinChen
 */
@Data
@Entity
@Table(name = "StatisticView")
public class StatisticView {
    /**
     * 主键生成策略： 自增
     */
    @Id

    private String ip;

    private Integer toy_type;

    private Integer num;
}
