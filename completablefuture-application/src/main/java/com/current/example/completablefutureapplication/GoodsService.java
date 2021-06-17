package com.current.example.completablefutureapplication;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class GoodsService {

    /**
     * 查询商品信息
     * @return
     */
    public List<Goods> queryGoods() {
        return Arrays.asList(
                new Goods(1,"电脑",new BigDecimal(5000)),
                new Goods(2,"手机",new BigDecimal(3000)),
                new Goods(3,"书",new BigDecimal(99)),
                new Goods(4,"杯子",new BigDecimal(18)));
    }
}
