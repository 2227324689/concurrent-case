package org.example.blockqueue.second;

import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class PrintProcessor extends Thread implements IRequestProcessor{

    BlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    //下一个处理节点
    protected IRequestProcessor nextProcessor;

    public PrintProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while(true){
            try {
                //从阻塞队列中获取任务，相当于消费者角色
                Request request=requests.take();
                //此处只是打印信息，如果是实际情况，可以在这里填代码
                log.info("PrintProcessor:"+request);
                //传递给下一个业务处理器
                if(null!=nextProcessor) {
                    nextProcessor.processRequest(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void processRequest(Request request) {
        //把请求添加到阻塞队列后结束，相当于生产任务的过程
        requests.add(request);
    }
}
