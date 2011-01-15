package in.ac.dei.edrp.client.Shared;

import java.util.Date;

import com.gwtext.client.widgets.form.TextField;

public class Validator {
    //To check for a null string
    public boolean nullValidator(String input) {
        if (input.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean nullValidator2(Object input) {
        if (input==null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean emailcounter(String input) {
        char[] s = new char[input.length()];
        int c = 0;
        s = input.toCharArray();

        //Count the no. of occurrences of @ 
        for (int i = 0; i < input.length(); i++) {
            if (s[i] == '@') {
                c++;
            }
        }

        if (c == 1) {
            //If email id contains ".",no error	
            if (input.contains(".")) {
                return false;
            }
            //no "." implies invalid id
            else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean passchar(String input) {
        if (input.length() < 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkrepass(String input1, String input2) {
        if (input1.equals(input2)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isNotNum(String input) {
        try {
            int a;
            a = Integer.parseInt(input);

            //Negative nos. not allowed
            if (a < 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //To allow land-line and cell no. fields to be left blank
            if (input.equals("")) {
                return false;
            }
            //Field contains characters
            else {
                return true;
            }
        }
    }
    public int isNull(String input) {
        if (input.equals("")) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public int checkText(TextField firstnameTextBox) {
    
        int error1 = 0;

        char[] arr;

        arr = firstnameTextBox.getRawValue().toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (((arr[i] >= 'a') && (arr[i] <= 'z')) ||
                    ((arr[i] >= 'A') && (arr[i] <= 'Z'))) {
            
            } 
            else {
            	  int  error2 = 0;
            	  error2++;
                  error1=error1+error2;
                 try{ firstnameTextBox.markInvalid("");
                 }
                 catch(Exception e){
                	 
                 }
                
            }
         
        }

        return (error1);
    }
    
    public boolean datechecker(String input, String input1) {
        if (input.compareTo(input1) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean datechecker1(Date dateofbirth, Date currentDate) {
        if (dateofbirth.compareTo(currentDate) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}

