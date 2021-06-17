package org.example.blockqueue.second;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Slf4j
public class SaveProcessor extends Thread implements IRequestProcessor {

    BlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while(true){
            try {
                Request request=requests.take();
                //此处只是打印信息，如果是实际情况，可以在这里填代码
                log.info("SaveProcessor:"+request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }
}
