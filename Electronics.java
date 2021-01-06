package eStoreSearch;
import java.util.Scanner;
/*import jdk.internal.jshell.tool.resources.version;*/
import java.util.*;

public class Electronics extends Product{
    private String maker ;
/**
 *  Electronics makes the dat fro the electronics needed by to add to the list
 */
    Electronics (String id, String des,String pr,String yr,String maker) throws Exception{
        super.initializeSame(id,  des, pr, yr);
        setmaker(maker);
        setdescription(des);
        setyears(yr);
        setproductid(id);
        
    }

    Electronics(String test)throws Exception{

    }
    public void setmaker(String maker){
        this.maker = maker;
    }
    public String getmaker(){
        return maker;
    }
    @Override
    public String toString(){
        return (" ----------Electronic------------- \n" + super.toString() +"| Maker : " + maker + "\n ---------------------------------\n");
    }
    public String toString2(){
        return "type = " + "\"" + "electronics" +"\"" + "\n" + super.toString2()  + "Maker = " +  "\"" + maker + "\"" ;
    }

}