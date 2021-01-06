package eStoreSearch;
import java.util.Scanner;
/*import jdk.internal.jshell.tool.resources.version;*/
import java.util.ArrayList;

public class Book extends Product{
    private String author ;
    private String publisher  ;
    /**
     * Book makes the book from the booklist needed by to add to the list
     */
    Book (String id, String des,String pr,String yr,String athr, String pub)throws Exception{
    super.initializeSame(id,  des, pr, yr);
    setauthor(athr);
    setpublisher(pub);
    setdescription(des);
    setyears(yr);
    setproductid(id);
    }
    Book(String test)throws Exception{
        super();
    }
    
    /**
     * 
     * @param author takes the author, sets and returns it
     */
    public void setauthor (String author){
        this.author = author ;
    }

    public String getauthor (){
        return author;
    }

    public void setpublisher(String publisher){
        this.publisher = publisher ;
    }
    public String getpublisher(){
        return publisher;
    }
    /* to see if evrything is being stored and is printing */
    public String toString(){
        return " ----------Book------------- \n" + super.toString() + "| Author : " + getauthor() + " \n" + "| Publisher : " + getpublisher() + " \n " + "--------------------------- \n";
    }
     public String toString2(){
         return "type = " + "\"" + "book" +"\"" + "\n"+ super.toString2() + "Authors = " +  "\"" + getauthor() + "\"" + " \n" + "Publisher = " +  "\"" + getpublisher() +  "\"" ;
     }
}