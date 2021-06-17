package org.example;

import java.util.ArrayList;
import java.util.List;

public class ApplyLock {

    private List<Object> list=new ArrayList<>();
    public synchronized boolean applyLock(Resource res1,Resource res2){
        if(list.contains(res1)||list.contains(res2)){
            return false;
        }else{
            list.add(res1);
            list.add(res2);
            return true;
        }
    }
    public synchronized void free(Resource res1,Resource res2){
        list.remove(res1);
        list.remove(res2);
    }
}
