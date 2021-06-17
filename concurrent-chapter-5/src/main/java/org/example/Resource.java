package org.example;

public class Resource {
    private String name;
    private int count;
    public Resource(String name) {
        this.name = name;
    }
    public void statisticsResource(){
        synchronized (this){
            System.out.println("statistics resource");
            count++;
        }
    }
    public void saveResource(Resource resource){
        Resource lock=this.hashCode()> resource.hashCode()?this:resource;
        synchronized (lock) {
            System.out.println("save resource");
            resource.statisticsResource();
        }
    }
}
