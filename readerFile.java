package eStoreSearch;
import java.util.Scanner;
//import javax.sound.sampled.SourceDataLine;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;

public class readerFile {
   private String productid ;
   private String description;
   private String price;
   private String year;
   private String author;
   private String publisher;
   private String maker;
    public  void fileInitializer (String input1,ArrayList <Product> productList ){ // input1 is file input 

        //System.out.println("\n" + " Enter the name for the input file " + "\n" );
        Scanner inputStream = null;
        int checkBook = 0, checkElec = 0;
        int addCheck = 0;
        try{
        
            inputStream = new Scanner (new FileInputStream(input1));
            File oneFile = new File (input1);
            if( oneFile.length()==0){
                System.out.println("\n" +" The File trying to be opened is empty" + "\n");
            }

            while(inputStream.hasNextLine())
            {
                try{
            String line = inputStream.nextLine();//.replace("\"","");
            String delimiter = "\"";
            String line2 = line.replace("\"","");
            String [] temp3= line.split(" = "); // for string that migh have quotations between themselves
            //String [] temp = line.split(delimiter);  //split by quotations
            String [] temp2 = line.split("[ ]+"); // split by spaces and no quotations
            temp2[2] = temp2[2].replace("\"","");
            temp3[1] = temp3[1].replace("\"","");
            if (temp3[1] == null)
            temp3[1] = "";
            //System.out.println(temp2[2]);
                    if (temp2[2].equalsIgnoreCase("book")){
                        checkBook = 1 ;
                        //System.out.println(temp2[2]);
                    } if  (temp2[2].equalsIgnoreCase("electronics")){
                        checkElec = 1;
                    }
                    //System.out.println("Hello" + checkBook);
                    
                    if (checkBook ==1){
                        
                        Book book1 = new Book("string");
                        

                    
                    if(temp2[0].equalsIgnoreCase("productID") && checkBook ==1){
                    productid = temp3[1];
                        //System.out.println(temp[1]);
                        //System.out.println( "book = " + book1.getproductid()); 
                    }else if(temp2[0].equalsIgnoreCase("description") && checkBook ==1){
                        description = temp3[1];
                    
                    //System.out.println(temp[1]);
                    }else if(temp2[0].equalsIgnoreCase("price") && checkBook ==1){
                        price = temp2[2];
                        
                        book1.setprice(temp3[1]);
                        //System.out.println(temp[1]);
                    }else if (temp2[0].equalsIgnoreCase("year") && checkBook ==1){
                        year = temp2[2];
                        //System.out.println(temp[1]);
                    }else if(temp2[0].equalsIgnoreCase("authors") && checkBook ==1){
                        author = temp3[1];
                        //System.out.println(temp[1]);
                }else if (temp2[0].equalsIgnoreCase("publisher") && checkBook ==1){
                   
                    publisher = temp3[1];
                    book1.setproductid(productid);
                    book1.setdescription(description);
                    book1.setprice(price);
                    book1.setyears(year);
                    book1.setauthor(author);
                        book1.setpublisher(publisher);
                        //System.out.println(temp[1]);
                        productList.add(book1);
                    // System.out.println( "Array = " + Arrays.toString(  productList.toArray())); 
                        addCheck = 1;
                        checkBook =0;
                        if (addCheck ==1){

                        addCheck = 0;
                        }
                    } 
                    //System.out.println("Hello" + checkBook + addCheck);

        
        }
        if (checkElec ==1){
        Electronics elec1 = new Electronics("string");
        if(temp2[0].equalsIgnoreCase("productID") && checkElec ==1){
            productid = temp3[1];
            //System.out.println(temp[1]);
            //System.out.println( "book = " + book1.getproductid()); 
        }else if(temp2[0].equalsIgnoreCase("description") && checkElec ==1){
            description = temp3[1];
            
            //System.out.println(temp[1]);
        }else if(temp2[0].equalsIgnoreCase("price") && checkElec ==1){
            price = temp2[2];
            //System.out.println(temp[1]);
        }else if (temp2[0].equalsIgnoreCase("year") && checkElec ==1){
            year = temp2[2];
            //System.out.println(temp[1]);
        }else if(temp2[0].equalsIgnoreCase("maker") && checkElec ==1){
            maker = temp3[1];
            elec1.setproductid(productid);
            elec1.setdescription(description);
            elec1.setprice(price);
            elec1.setyears(year);
            elec1.setmaker(maker);
            //System.out.println(temp[1]);
            productList.add(elec1);
            //System.out.println( "Array = " + Arrays.toString(  productList.toArray())); 
            addCheck = 1;
            checkElec =0;
        } 
        }
    }catch(Exception e1){
    }
    }
    //System.out.println( "Array = " + Arrays.toString(  productList.toArray())); 
    } catch(FileNotFoundException e){
        System.out.println("File : "+ input1 + " was not found"+ "\n or could not be opened " + "\n");
        System.exit(0);
    }
}

    public  void fileOutputter (ArrayList <Product> productList ){
        String input2 = "file.txt";
        PrintWriter outputStream = null;
        try {
                outputStream = new PrintWriter(new FileOutputStream(input2,true));
                for(int k=0;k< productList.size();k++)
                {
                    outputStream = new PrintWriter(new FileOutputStream(input2,true));
                    outputStream.println(productList.get(k).toString2());
                    outputStream.close();
                }
            
        } 
        catch (FileNotFoundException e) 
        {
        System.out.println("Error opening the file stuff.txt.");
        System.exit(0);
        }
    }
}