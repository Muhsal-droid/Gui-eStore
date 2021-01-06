package eStoreSearch;
import java.util.Scanner;
/*import jdk.internal.jshell.tool.resources.version;*/
import java.util.ArrayList;

public abstract class Product{
    private String years ;
    private String productid ;
    private String description ;
    private String price ;
    
    public void initializeSame(String id, String des,String pr,String yr) throws Exception
    {  
        int check2 =0,check3 =0,check4=0;
      
        
           
            years = yr;
            // add condition for number only 
            String eq = "[0-9]+";
            if (years.matches(eq) && years.length() ==4){
                int year1 = Integer.parseInt(years);
                    if(year1 > 1000 && year1 < 9999){
                        check2 =1;
                        setyears(years);
                    }else{
                        check2 =1;
                        //exception here
                    }
            }else if(setyears(years)== true){
                check2 = 1;
                setyears(years);
            }
            check2 =1;
       
            productid = id;
            // taking in input and comparing against conditions
            if(setproductid(productid)== true){
                check3 = 1;
            }
            check3 = 1;
      
            description = des;
            if(setdescription(description)== true){
                check4 = 1;
            }
            check4 = 1;
       // }while (check4!= 1);
        int check5 =0;
       // do {
       
        price = pr;
        if (setprice(price)==true){
            check5=1;
        }
        check5=1;
        //}while(check5 != 1);

    }  
    public Product()throws Exception{


    }
    
    public void checkDuplicate( ArrayList <Product> productList) throws Exception{
        int i = 0;
        for ( i = 0; i < productList.size(); i++) {
            for (int j = i + 1 ; j < productList.size() && j <productList.size() ; j++) { 
            if (productList.get(i).getproductid().equalsIgnoreCase(productList.get(j).getproductid()) ) { 
                productList.remove(j);
                throw new Exception( "\n" + "Duplicate product id found : add rejected " + "\n" );
                }
            }
        }
    }

    public boolean setyears(String years )throws Exception{
        String eq = "[0-9]+";
        int year1 = Integer.parseInt(years);
        if(years.length()== 4 && years.matches(eq) && year1 > 1000 && year1 < 9999){
            this.years = years;
        }else{
            throw new Exception("\nFormat for year is incorrect, year has to be four digits (cannot be left blank) and from 1000-9999.\n");
            //return false;
        }
        return true;
    }
    public String getyears(){
        return years;
    }
     /**
     * 
     * @param productid checks the function has the correct conditions of numerical values in this case
     * @return it based on value of true or false
     */
    public boolean setproductid(String productid )throws Exception{
        String eq = "[0-9]+";
        if(productid.length()==6 && productid.matches(eq)){
            this.productid = productid;
        }else{
            throw new Exception("\nFormat for product ID is incorrect, cannot be left blank and has to be 6 digits long.\n");
        }
        return true;
    }
    public String getproductid(){
        return productid;
    }
    /**
     * 
     * @param description checks the function has the correct conditions
     * @return it based on value of true or false
     */
    public boolean setdescription(String description )throws Exception{
        if(description.isEmpty()== true){
            throw new Exception("\n Format for description is incorrect, Field cannot be left balnk.\n");
        }else{
            this.description = description;
        }
        return true;
    }

    public String getdescription(){
        return description;
    }
    /**
     * 
     * @param price checks price to see if it is correct integer
     * @return it based on value of true or false
     */
    public boolean setprice(String price ){
        String eq = "[0-9,.]+";
        
        if( price.matches(eq)){
            this.price = price;
        }else{
            return false;
        }
        return true;
    }

    public String getprice(){
        return price;
    }

    @Override
    public String toString(){
        return  "| productID : " + productid + " \n" +  "| Description : " + description + " \n" + "| Price Entered : " + price + " \n" + "| Year : " + years + " \n";
    } 
    
    public String toString2(){
        return   "productID = " + "\"" + productid + "\"" + "\n" +  "Description = " + "\"" + description + "\"" + "\n" + "price = " + "\"" + price + "\"" + " \n" + "year = " + "\"" + years + "\"" + " \n";
    }
    

}
