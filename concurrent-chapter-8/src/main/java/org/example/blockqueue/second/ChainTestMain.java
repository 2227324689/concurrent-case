package org.example.blockqueue.second;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ChainTestMain {

    public static void main(String[] args) {
        //构建责任链
        SaveProcessor saveProcessors=new SaveProcessor();
        saveProcessors.start();
        PrintProcessor printProcessor=new PrintProcessor(saveProcessors);
        printProcessor.start();
        ValidProcessor requestProcessor=new ValidProcessor(printProcessor);
        requestProcessor.start();
        Request request=new Request();
        request.setName("Mic");
        requestProcessor.processRequest(request);
    }
}
