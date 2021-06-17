package com.current.example.completablefutureapplication;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Getter
@Data
public class Goods {
    private Integer id;
    //商品名称
    private String name;
    //价格
    private BigDecimal price;
    //库存
    private Integer repo;
    //购买人数
    private Integer buyerNum;
    //评价
    private List<String> comment;

    public Goods(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
