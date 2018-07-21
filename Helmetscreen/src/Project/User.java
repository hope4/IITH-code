/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

/**
 *
 * @author naveen
 */
class User {
    private int camera_id,alert_id,horizontal,vertical,height,width;
    private String timestamp;
    
    public User(int alert_id,String timestamp,int horizontal,int vertical,int height,int width){
    
    this.camera_id = camera_id;
    this.alert_id = alert_id;
    this.timestamp = timestamp;
    this.horizontal =horizontal;
    this.vertical = vertical;
    this.height = height;
    this.width =width;
    
    }
    
    public int getcamera_id(){
        return camera_id;
    }
    
    public int getalert_id(){
        return alert_id;
    }
    
    public String gettimestamp(){
        return timestamp;
    }
     
    public int gethorizontal(){
        return horizontal;
    }
       
    public int getvertical(){
        return vertical;
    }
        
    public int getheight(){
        return height;
    }
        
    public int getwidth(){
        return width;
    }
         
}