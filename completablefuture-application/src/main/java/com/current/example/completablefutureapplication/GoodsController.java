package com.current.example.completablefutureapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    CommentService commentService;
    @Autowired
    RepoService repoService;

    @GetMapping("/goods")
    public List<Goods> goods() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Goods>> goodsFuture=CompletableFuture
                .supplyAsync(()->goodsService.queryGoods());
        CompletableFuture cf=goodsFuture.thenApplyAsync(goods->{
            //查询商品购买人数
            goods.stream().map(good1->CompletableFuture.supplyAsync(()->{
                good1.setRepo(repoService.getRepoByGoodsId(good1.getId()));
                return good1;
            }).thenCompose(good2->CompletableFuture.supplyAsync(()->{
                good2.setComment(commentService.getCommentsByGoodsId(good2.getId()));
                return good2;
            }))).toArray(size->new CompletableFuture[size]);
            return goods;
        });
        return (List<Goods>) cf.handleAsync((goods, th)->th!=null?"系统繁忙":goods).get();
    }
}
