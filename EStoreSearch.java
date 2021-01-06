package eStoreSearch;
import java.util.Scanner;
/*import jdk.internal.jshell.tool.resources.version;*/
import java.util.Set;
import java.util.*;
import java.util.Iterator;
import java.util.HashMap;
import javax.swing.JTextArea;

public class EStoreSearch{
    // similarities between books and electronics
    private String description;
    private String productid;
    private String [] temp;
    private String years;
    private String year1;
    private String year2;
    private int yr1;
    private int yr2;
    private String delimiter = "-";
    private ArrayList <Integer> interStore = new ArrayList <Integer > (); //will store the interesction of values fro HashMap
    private ArrayList <Product> filteredProducts = null;

/**
 * the search asks foe input and does error checking by booleans at he bottom of this class
 */
    EStoreSearch( ArrayList <Product> productList, String id, String des, String yr)throws Exception{ 
        int check2 =0,check3 =0, check4 =0;
        boolean gradeCheck = true;
        // taking in all the inputs here
       
            // taking in input and comparing against conditions
            description = des;
            description = description.toLowerCase();
            if(setdescription(description)== true){
                check4 = 1;
            }else{
                throw new Exception("\n Format for description is incorrect, Field cannot be left balnk.\n");
            }
            

        
           // taking in input and comparing against conditions
            productid = id;
            if(setproductid(productid)== true){
                check3 = 1;
            } else {
                throw new Exception("\nFormat for product ID is incorrect, cannot be left blank and has to be 6 digits long.\n");
            }

        

        do {
            // taking in input and comparing against conditions
            years = yr;
            if(setyears(years)== true && years.isEmpty()== false){
                // checking for 
                
                temp = years.split(delimiter);
                if(temp.length == 2 && years.length() > 6){
                    // in the format year1-year2
                    year1 = temp[0];
                    year2 = temp[1];
                    yr1 = Integer.parseInt(year1);
                    yr2 = Integer.parseInt(year2);
                    break;
                }else if(temp.length ==1 && years.length()== 4){
                    year1 = temp[0];
                    year2 = temp[0];
                    yr1 = Integer.parseInt(year1);
                    yr2 = 9999;
                    break;
                    // 2010 format
                }
            }else if (!yr.isEmpty() && (yr1 < 1000 || yr2 > 9999) ){
                    check2 =1;
                    throw new Exception("\nFormat for year is incorrect, year has to be four digits (cannot be left blank) and from 1000-9999.\n");
            }else{
                check2 =1;
            }
        } while (check2 != 1);
        if(yr1 > yr2){
            throw new Exception("\nFormat for year is incorrect, year1 cannot be greater year2.\n");
        }
        //System.out.printf("Year: %d, %d \n", yr1, yr2);
        // do match checking here for books and display
        int i =0;
        int call = 0;
        int count =0 ;
        int gate1 =0,gate2 =0, gate3=0;
        String [] s1  = description.split("[ ]+");
       // maps1(description,productList);
        // s1 is user
        // filteredElements = copyOf(productList)
        // Product Id = 100001 -> 0 or 1 elements => filterElements[products1]
        // Year = 2000 -> 0 to n elements
        // Search = "my search" -> 0 to n elemtns (intersection)
        

        // filters out the productlist by the product id
        if(!productid.isEmpty()){
            for ( i = 0; i < productList.size(); i++) {
                if (productList.get(i).getproductid().equalsIgnoreCase(productid)) { 
                    filteredProducts = new ArrayList<>();
                    filteredProducts.add(productList.get(i));
                    break;
                }
            }
            if (filteredProducts == null)
                filteredProducts = new ArrayList<>();
        } 
        else {
            filteredProducts = new ArrayList<>(productList);// shallow copy
        }

        // filtering out the by year
        //System.out.printf("Filtered by id: %s,\n", filteredProducts);
        if(!years.isEmpty()){
            
            ArrayList <Product> filteredYearProducts = new ArrayList<>();
            for ( i = 0; i < filteredProducts.size(); i++) {
                int bookYear = Integer.parseInt(filteredProducts.get(i).getyears());
                if(bookYear >= yr1 && bookYear <= yr2){
                    filteredYearProducts.add(filteredProducts.get(i));
                }
            }
            filteredProducts = filteredYearProducts;
        }
        //System.out.printf("Filtered : %s,\n", filteredProducts);
    }
    EStoreSearch( String search){

    }

        public void printList( ArrayList <Product> productList,JTextArea viewStudentText){
            if(!description.isEmpty()){
                HashMap <String, ArrayList<Integer>> keyWords = makeMap(filteredProducts); //create hashmap and will set interection Arraylist  -> called interStore (instance variable)
                for (int index : interStore) {
                     //object at the index will match the keyword of what you want
                    viewStudentText.append(productList.get(index).toString() ); 
                } 
            }else if (description.isEmpty()==true){
                for ( int i = 0; i < filteredProducts.size(); i++) {
                    viewStudentText.append(filteredProducts.get(i).toString());
                }
            }else{
                viewStudentText.append(" The product you are looking for is not here ");
            }
            if(productList.isEmpty() == true){
                viewStudentText.append(" \n \n The list is currently empty ");
            }
            viewStudentText.append( "\n" + " The search is complete." + "\n" );
        }
    /**
     * 
     * @param years checks the function has the correct conditions
     * @return it based on value of true or false
     */
    public boolean setyears(String years ){
        String eq = "[0-9,-]+";
        if(( years.matches(eq)) || years.isEmpty()==true){
            this.years = years;
            return true;
        }else{
            return false;
        }
    }
    public String getyears(){
        return years;
    }
    /**
     * 
     * @param productid checks the function has the correct conditions
     * @return it based on value of true or false
     */
    public boolean setproductid(String productid ){
        String eq = "[0-9,-]+";
        if(productid.length()== 6 && productid.matches(eq) || productid.isEmpty()==true){
            this.productid = productid;
            return true;
        }else{
            return false;
        }
        //return true;
    }
    public String getproductid(){
        return productid;
    }
    /**
     * 
     * @param description checks the function has the correct conditions
     * @return it based on value of true or false
     */
    public boolean setdescription(String description ){
        if(description.isEmpty()== true){
            return true;
        }else{
            this.description = description;
        }
        return true;
    }

    public String getdescription(){
        return description;
    }


    public HashMap makeMap (ArrayList <Product> productList) {
        HashMap <String, ArrayList <Integer>> keyWords = new HashMap <String, ArrayList <Integer>> (); //String -> keywords 
                                                         //ArrayList <Integer> stores index of products matches to keywords
        ArrayList <Integer> found = new ArrayList <Integer> ();  //stores found indices for each keyword in loop                                              
        String [] keyword  = getdescription().split("[ ]+"); //split keywords
        for (int i = 0; i < keyword.length; i++) {
            keyword[i] = keyword[i].toLowerCase();
            found = isFound(keyword[i], productList); // find indices of match 
            keyWords.put(keyword[i], found); 
        }
        
            intersection(keyWords);// everytime get the first keyword
       
        //System.out.println(keyWords); 
        return keyWords;
        
    }
    /**
     * 
     * 
     * @param keyWords the decription it needs to match with and example is below :
     */
    /**
     * ex. {java=[0, 1], is=[0, 1], nice=[1]}
     * first = [0,1]
     * second = [0,1]
     * intersect = [0,1]
     * first = intersect
     * 
     * first = [0,1]
     * second = [1] (nice)  
     * intersect = [1]
     * 
     * first = [1]
     * second = [0,1]
     * intersect = [1]
     */
    public void intersection (HashMap <String, ArrayList<Integer>> keyWords) {
        ArrayList <Integer> first = keyWords.get(getdescription().split("[ ]+")[0]); // sentence: java is nice: 1;java 
        Iterator keyWordsIterator = keyWords.entrySet().iterator(); 

        while (keyWordsIterator.hasNext()) {
            Map.Entry secondElement = (Map.Entry)keyWordsIterator.next(); // getting the elemnt the first time
            ArrayList <Integer> second = (ArrayList <Integer>) secondElement.getValue(); 
            //System.out.println("First element: value "  + Arrays.toString(first.toArray()));
            //System.out.println("Second element: key "  + secondElement.getKey() + " value " + Arrays.toString(second.toArray()));
            ArrayList <Integer> intersect = new ArrayList <Integer > (); // store the arraylist of intersections
            for (int i : first) {
                for (int j: second) {
                    if (i == j) {
                        intersect.add(i); 
                    }
                }
            }
            first = intersect; 
            interStore = intersect; 
           // System.out.println( "Interect = " + Arrays.toString(intersect.toArray()));
        }
          
    }

    public ArrayList isFound (String keywords, ArrayList <Product> productList) {
        ArrayList <Integer> found = new ArrayList <Integer> ();
        for (int i = 0; i < productList.size(); i++) {
             String[] sepWord = productList.get(i).getdescription().split("[ ]+"); 
             for (int j = 0; j < sepWord.length; j++) {
                 if (sepWord[j].equalsIgnoreCase(keywords)) {
                     found.add(i); // stores the integer arraylist if found (stores the matched index from productlist)
                 }
             }
             
        }
        return found; 
    }
     

}