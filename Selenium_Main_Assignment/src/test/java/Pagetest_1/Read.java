package Pagetest_1;
import java.io.*;
public class Read {
        public  String NewCustomerDetails(int index){
            String return_value="";
            try {
                String line = null;

                // Created Bufferd Reader to Read Value From Excel
                BufferedReader br = new BufferedReader(new FileReader("C:\\selenium_main_assesment\\NewCustomerDetails.csv"));
                line = br.readLine();                     // Read Single Line From Excel
                String[] input = line.split(",");   // Assign that line to string array with comma seperated words
                return_value = input[index];              // The return value is the string at the index which passed in argument
            } catch (Exception e) {
                e.printStackTrace();
            }
            return return_value;   // return required value
        }
    }

