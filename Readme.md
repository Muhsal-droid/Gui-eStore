## ##################### Documentation

## Using the program:
- Run the program by typing in gradle Run. (without the file)

- With the file it is gradle run --args=file.txt and file.txt is the filename. 

-The user will be prompted to a page with options: add ,  search   and  quit. (commands on the top left side of GUI)
-once there the user can search for the or add (books or electronics). (Jcombo box for electronics)
***Note: Years, product id and description are reuqired for both book and electronics.(when adding as exception will be thrown)
If either book or electronic is chosen, the user will be required to the information requested.
Once done with the information hit enter every time and you will be prompted to the options screen again.
From there on, the user can pick the option "search" as they arraylists are not empty.
*** Note: all fields for search can be left blank and then all the electronics and books will be displayed.

-- once everything is done, the product list is saved into the output file file.txt once the person uses the quit command;


## Testing the program for correctness:
For this , use  this command on the command line :
## (without the file):  gradle test
## (with the file) : gradle run --args=file.txt
If successful everything is working fine and else, there are some errors( but there are not).
For any tests not included in the testing file, the error checks have been implemented in the java file for better efficency.


## Program you are trying to solve :
The basis of what we are doing :
An “eStore” typically holds multiple kinds of products and allows the user to add and search for them .  
A product is better modeled by an object so that the user can distinguish different attributes and apply suitable methods for accessing and modifyingthese attributes.
For this project, the search has a limit to two kinds of products for simplicity: Book and Electronics.
the user adds products and can search for them. Basically like an eStore directory.

## Assumptions and limitations:
Assuming most users will put in the correct required input such as years having numbers in them and such.
If those requirementsments are not met, the user will be prompted to the question again for the reasonable and correct input.
Some cases, the error checks have to be different as some fields when the user inputs are not required thus displaying all products.
Limitations: cannot take in leading spaces for the program.
- Also assuming the file being tested is in the correct format when loading it in and outputting.

## Improvements for program:
I can improve my program by having more methods as then the code will be better in terms of readibility.
I can improve on my spacing.
Using more boolean methods as the functionality will  be better.


