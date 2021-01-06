package eStoreSearch;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.util.*;
import javax.swing.JScrollPane;
import java.awt.CardLayout;


public class Gui extends JFrame {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    private JPanel addPanel;
    private JPanel search;
    private JPanel quit;
    private JPanel startWin;
    private JPanel lyout;
    private int flag = 0;
    private ArrayList <Product> productList = new ArrayList<Product>();
    JTextField id;
    JTextField des;
    JTextField id2;
    JTextField des2;
    JTextField pr;
    JTextField yr;
    JTextField yr2;
    JTextField athr;
    JTextField pub;
    JTextField maker;
    JTextField searchID;
    JTextField searchDes;
    JTextField searchYr;
    JTextField searchYr2;
    private CardLayout cardLayout;
    private JPanel sectionPanel;
    private JTextArea viewStudentText;
    JTextArea viewStudentText1 ;
    // declaration for options
    public static void main(String[] args)
    {
        Gui newGui = new Gui();
        newGui.setVisible(true);

        if (args.length < 1 ){
        } else {
          String inputFile = args[0];
      readerFile one = new readerFile (); 
      one.fileInitializer(inputFile,newGui.productList);
        }
       
    }
    /////////////////////////////////////////////////////////////////
    private class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            viewStudentText1.setText("");
            //viewStudentText1.setText("");
            Component myComp [] = addPanel.getComponents(); //get things added to panel (components)
            JPanel one = (JPanel) myComp[0];//.getComponents(); //get things added to one panel that was located in addPanel at index 0
            
            Component obj[] = one.getComponents(); // get components of one
            
            // Select combobox item
            JPanel comboBox = (JPanel) obj[1];
            JComboBox productMenu = (JComboBox) comboBox.getComponents()[1];
            
            // get the component at index 1 from the respective panels
            // that component will always be JtextField
            String productID = id.getText();
            String description = des.getText();
            String price = pr.getText();
            String year = yr.getText();
            //System.out.println("testing here  " + year);
            
            // 0 -> book, 1 -> electronics
            if (productMenu.getSelectedIndex() == 0) {
                //author 
                String author = athr.getText();
                //publisher
                String publisher = pub.getText();
                try{
                Book book = new Book(productID, description, price, year, author, publisher);
                productList.add(book);
                book.checkDuplicate(productList);
                }catch(Exception e1){
                    viewStudentText1.setText(e1.getLocalizedMessage());
                }
                // if duplicate has been detected, delete it
               
            }
            else {
                 // maker 
                String maker1 = maker.getText();
                try{
                Electronics elec = new Electronics(productID, description, price, year, maker1);
                productList.add(elec);
                // if duplicate has been detected, delete it
                elec.checkDuplicate(productList);
                }catch(Exception e1){
                    viewStudentText1.setText(e1.getLocalizedMessage());
                }
                
            }
        }
    } //End for add listener 
/////////////////////////////////////////////////////////////////
private class searchButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String productID = searchID.getText();
            String description = searchDes.getText();
            String year1 = searchYr.getText();
            String year2 = searchYr2.getText();
            if (year1.length()==4 && year2.length()==4){
                year1 = year1 +"-"+year2;
            }else if (year1.isEmpty()== true && year2.isEmpty()== false){
                year1 = "1000-"+year2;
            }
            //System.out.printf("Year: %s, %s \n", year1, year2);
            try{
            EStoreSearch system = new EStoreSearch(productList,productID,description,year1);
            viewStudentText.setText("");
            system.printList(productList, viewStudentText);
            //searchList.add(system);
            system.makeMap(productList);
            }catch(Exception e1){
                viewStudentText.setText(e1.getLocalizedMessage());
            }
            
        }
    } //End for search listener 
////////////////////////////////////////////////////////////////////
    private class addListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            startWin.setVisible(false);
            cardLayout.show(sectionPanel, "addPanel");
            bookType();// book is the default to be displayed
            
        }
    } //End for add listener
////////////////////////////////////////////////////////////////////
private class searchListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            startWin.setVisible(false);
            cardLayout.show(sectionPanel, "search");
            
        }
    } //End for search listener
////////////////////////////////////////////////////////////////////
private class quitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            readerFile one = new readerFile (); 
            one.fileOutputter(productList );
           System.exit(0);
            
        }
    } //End for search listener 
////////////////////////////////////////////////////////////////////
private class optionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           // for the displayong of book and electronics
        JComboBox thing1 = (JComboBox) e.getSource();
        String options = (String)thing1.getSelectedItem();
        if (options.equalsIgnoreCase("Electronic")){
            elecType(1);// for electronics
        }else{
            //elecType(1);
           bookType(); // for books
        }
            
        }
    } //End for search listener
    //////////////////////////////////////////////////////////
    private class resetButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // resetting everything to blanks
            id.setText("");
            des.setText("");
            pr.setText("");
            yr.setText("");
            athr.setText("");
            pub.setText("");
            maker.setText("");
            
        }
    } //End ()
////////////////////////////////////////////////////////////////////
private class resetButton2Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // resetting everything to blanks
            searchID.setText("");
            searchDes.setText("");
            searchYr.setText("");
            searchYr2.setText("");
            
        }
    } //End 
////////////////////////////////////////////////////////////////////
    public void bookType () {
        Component myComp [] = addPanel.getComponents(); //get things added to panel (components)
        JPanel one = (JPanel) myComp[0];//.getComponents(); //get things added to one panel that was located in addPanel at index 0
        
        Component obj[] = one.getComponents(); // get components of one
        
        // Select combobox item
        JPanel comboBox = (JPanel) obj[1];
        JComboBox productMenu = (JComboBox) comboBox.getComponents()[1];
        productMenu.setSelectedIndex(0);
        
        JPanel author = (JPanel) obj[6];  //author panel
        author.setVisible(true);
        JPanel publisher = (JPanel) obj[7];  //publisher panel
        publisher.setVisible(true); 
        JPanel maker = (JPanel) obj[8]; // maker panel
        maker.setVisible(false);

    }
    public void elecType (int num) {
        
        Component myComp [] = addPanel.getComponents(); //get things added to panel (components)
        JPanel one = (JPanel) myComp[0];//.getComponents(); //get things added to one panel that was located in addPanel at index 0
        
        Component obj[] = one.getComponents(); // get components of one
        JPanel author = (JPanel) obj[6];  //author panel
        JPanel publisher = (JPanel) obj[7];  //publisher panel
        JPanel maker = (JPanel) obj[8]; // maker panel
        if (num ==1){
            author.setVisible(false); 
            publisher.setVisible(false); 
            maker.setVisible(true);
         }else if (num ==2){
             author.setVisible(true); 
             publisher.setVisible(true); 
        //     maker.setVisible(false);
         }
    }
    public Gui( ){
        super("EStoreSearch");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // for button listing add search and quit
        startWin = new JPanel(new FlowLayout (FlowLayout.LEFT));
        startWin.setVisible(true);
        JPanel infoJPanel = new JPanel ();
         
        infoJPanel.setLayout(new BoxLayout(infoJPanel, BoxLayout.Y_AXIS)); 
        //first message
         JLabel info = new JLabel( "Welcome to eStoreSearch");
         info.setVerticalAlignment(JLabel.TOP);
        //second message
        
         JLabel info2 = new JLabel("Choose a command from the “Commands” menu above for adding a product, searching products, or quitting the program");
         info2.setVerticalAlignment(JLabel.CENTER);
         // add it to the starting window 
        infoJPanel.add(info); 

        infoJPanel.add(info2); 

        startWin.add(infoJPanel); 
       // startWin.add(info2); 

        add(startWin, BorderLayout.PAGE_START); 
         // fix the spacin for the info1 and info2
         // menu bar options start
        mainMenu();

        // method for adding

        sectionPanel = new JPanel();
        cardLayout = new CardLayout();
        sectionPanel.setLayout(cardLayout);

        addPanel = new JPanel(new BorderLayout());
        add();
        addPanel.setVisible(false);
        sectionPanel.add(new JPanel(), "empty Panel");
        sectionPanel.add(addPanel,"addPanel");

        search = new JPanel (new BorderLayout()); 
        search();
        search.setVisible(false);
        sectionPanel.add(search,"search");
        
        add(sectionPanel, BorderLayout.CENTER);
    }
    /**
     * this is for adding the main menu
     */
    public void mainMenu(){

        JMenu options = new JMenu("Commands");

        JMenuItem add = new JMenuItem("add");
        add.addActionListener(new addListener( ));
        options.add(add);

        JMenuItem search = new JMenuItem("search");
        search.addActionListener(new searchListener( ));
        options.add(search);

        JMenuItem quit = new JMenuItem("quit");
        quit.addActionListener(new quitListener( ));
        options.add(quit);

        JMenuBar bar = new JMenuBar( );
        bar.add(options);
        setJMenuBar(bar);
        // menubar declaration above
    }
/////////////////////////////////////////////////////////////////////////////////////
/**
 * this is for adding 
 */
    public void add(){
        //System.out.println("This should be before the message book type");
        JPanel one = new JPanel(); //holds all textfields (everything to the left)
        BoxLayout setter = new BoxLayout(one,BoxLayout.Y_AXIS);
        one.setLayout(setter);//(one,BoxLayout.PAGE_AXIS));

        JPanel infoPanel = new JPanel (new FlowLayout(FlowLayout.LEFT)); //add
        JLabel mssg = new JLabel("Adding a product");
        infoPanel.add(mssg);

        JPanel two = new JPanel (new FlowLayout(FlowLayout.LEFT));
        JLabel object = new JLabel("Type:");
        String [] objectType = {"Book","Electronic"};
        JComboBox  thing = new JComboBox(objectType);
        thing.addActionListener(new optionListener( ));
        
        two.add(object);
        two.add(thing);
        // adding product id
        JPanel three = new JPanel ();
        JLabel productID = new JLabel ("ProductID :");
        id = new JTextField(25);
        three.add(productID);
        three.add(id);
        // adding description
        JPanel four = new JPanel ();        
        JLabel description = new JLabel ("Description :");
         des = new JTextField(25);
        four.add(description);
        four.add(des);
        // adding price
        JPanel five = new JPanel ();        
        JLabel price = new JLabel ("Price :");
        pr = new JTextField(25);
        five.add(price);
        five.add(pr);
        // adding  year
        JPanel six = new JPanel ();        
        JLabel year = new JLabel ("Year :");
        yr = new JTextField(25);
        six.add(year);
        six.add(yr);
        // adding authors
        JPanel seven = new JPanel ();        
        JLabel author = new JLabel ("Authors :");
        athr = new JTextField(25);
        seven.add(author);
        seven.add(athr);
        // adding publisher
        JPanel eight = new JPanel ();        
        JLabel publisher = new JLabel ("Publisher :");
        pub = new JTextField(25);
        eight.add(publisher);
        eight.add(pub);
        // adding maker
        JPanel nine = new JPanel ();        
        JLabel make = new JLabel ("Maker :");
        maker = new JTextField(25);
        nine.add(make);
        nine.add(maker);
    
        JPanel buttonsRight = new JPanel();
        buttonsRight.setLayout(new BoxLayout(buttonsRight, BoxLayout.Y_AXIS));
        //buttonsRight.setBorder(BorderFactory.createLineBorder(Color.black));
        //buttonsRight.setPreferredSize(new DimensionUIResource(300, 500));
    

        JPanel adderb = new JPanel();
        adderb.setLayout(new GridLayout(11, 1));
        adderb.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        buttonsRight.add(adderb);
        // adding spaces
        adderb.add(new JLabel());
        adderb.add(new JLabel());
        adderb.add(new JLabel());

        JButton adder = new JButton("Add");
        adder.setPreferredSize(new DimensionUIResource(80, 50));
        adder.addActionListener(new AddButtonListener());
        adder.setAlignmentY(AbstractButton.CENTER_ALIGNMENT);
        adder.setAlignmentX(AbstractButton.CENTER_ALIGNMENT);
        adderb.add(adder);
        // adding spaces
        adderb.add(new JLabel());
        adderb.add(new JLabel());
        adderb.add(new JLabel());

        JButton reset = new JButton("Reset");
        reset.setSize(new DimensionUIResource(80, 50));
        reset.addActionListener(new resetButtonListener());
        reset.setAlignmentY(AbstractButton.CENTER_ALIGNMENT);
        reset.setAlignmentX(AbstractButton.CENTER_ALIGNMENT);
        adderb.add(reset);

        adderb.add(new JLabel());
        adderb.add(new JLabel());
        adderb.add(new JLabel());

        JPanel one12 = new JPanel(); // holds all textfields (everything to the left)
        BoxLayout setter12 = new BoxLayout(one12, BoxLayout.PAGE_AXIS);
        one12.setLayout(setter12);

        one.add(infoPanel); 
        one.add(two);
        one.add(three); // id
        one.add(four);// descrip
        one.add(five);// price
        one.add(six);  //year
        one.add(seven); //author
        one.add(eight); //publisher
        one.add(nine); // maker
        //one.add(adderb);
  
      JPanel one1 = new JPanel(); // holds all textfields (everything to the left)
        BoxLayout setter1 = new BoxLayout(one1, BoxLayout.PAGE_AXIS);
        one1.setLayout(setter1);// (one,BoxLayout.PAGE_AXIS));

        JPanel messagePanel = new JPanel();
         viewStudentText1 = new JTextArea(9, 30);
        viewStudentText1.setEditable(false);

        JScrollPane scrollContainer = new JScrollPane(viewStudentText1);
        // messagePanel.add(scrollContainer);

        one1.add(scrollContainer);
        
        addPanel.add(one,BorderLayout.WEST);
        addPanel.add(buttonsRight,BorderLayout.EAST);
        addPanel.add(one1, BorderLayout.SOUTH);
         //addPanel.add(one12, BorderLayout.LINE_END);
        thing.setSelectedIndex(0);

    }
    /////////////////////////////////////////////////////////////////////////////
    /**adding search */
    public void search(){
        //System.out.println("This should be before the message book type");
        JPanel one = new JPanel(); //holds all textfields (everything to the left)
        BoxLayout setter = new BoxLayout(one,BoxLayout.Y_AXIS);
        one.setLayout(setter);//(one,BoxLayout.PAGE_AXIS));

        JPanel infoPanel = new JPanel (new FlowLayout(FlowLayout.LEFT)); //add
        JLabel mssg = new JLabel("Searching products");
        infoPanel.add(mssg);

        JPanel three = new JPanel ();
        JLabel productID = new JLabel ("ProductID :");
        searchID = new JTextField(25);
        three.add(productID);
        three.add(searchID);

        JPanel four = new JPanel ();        
        JLabel description = new JLabel ("Description Keywords:");
        searchDes = new JTextField(25);
        four.add(description);
        four.add(searchDes);

        JPanel six = new JPanel ();        
        JLabel year = new JLabel ("Start Year :");
        searchYr = new JTextField(25);
        six.add(year);
        six.add(searchYr);

        JPanel seven = new JPanel ();        
        JLabel year2 = new JLabel ("End Year :");
        searchYr2 = new JTextField(25);
        seven.add(year2);
        seven.add(searchYr2);

        JPanel buttonsRight = new JPanel();
        buttonsRight.setLayout(new BoxLayout(buttonsRight, BoxLayout.Y_AXIS));
        JPanel adderb = new JPanel();
        adderb.setLayout(new GridLayout(11, 1));
        adderb.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        buttonsRight.add(adderb);
        
        adderb.add(new JLabel());
        adderb.add(new JLabel());
        adderb.add(new JLabel());

        JButton adder = new JButton("Search");
        adder.setPreferredSize(new DimensionUIResource(80, 50));
        adder.addActionListener(new searchButtonListener());
        adder.setAlignmentY(AbstractButton.CENTER_ALIGNMENT);
        adder.setAlignmentX(AbstractButton.CENTER_ALIGNMENT);
        adderb.add(adder);

        adderb.add(new JLabel());
        adderb.add(new JLabel());
        adderb.add(new JLabel());

        JButton reset = new JButton("Reset");
        reset.setSize(new DimensionUIResource(80, 50));
        reset.addActionListener(new resetButton2Listener());
        reset.setAlignmentY(AbstractButton.CENTER_ALIGNMENT);
        reset.setAlignmentX(AbstractButton.CENTER_ALIGNMENT);
        adderb.add(reset);
        // adding white space
        adderb.add(new JLabel());
        adderb.add(new JLabel());
        adderb.add(new JLabel());

        JPanel one1 = new JPanel(); // holds all textfields (everything to the left)
        BoxLayout setter1 = new BoxLayout(one1, BoxLayout.PAGE_AXIS);
        one1.setLayout(setter1);// (one,BoxLayout.PAGE_AXIS));

        JPanel messagePanel = new JPanel();
         viewStudentText = new JTextArea(8, 30);
        viewStudentText.setEditable(false);

        JScrollPane scrollContainer = new JScrollPane(viewStudentText);
        // messagePanel.add(scrollContainer);

        one1.add(scrollContainer);
        
         search.add(one1, BorderLayout.SOUTH);

        one.add(infoPanel);
        one.add(three);
        one.add(four);
        one.add(six);
        one.add(seven);
        search.add(buttonsRight,BorderLayout.EAST);
        search.add(one,BorderLayout.LINE_START);

    }

}