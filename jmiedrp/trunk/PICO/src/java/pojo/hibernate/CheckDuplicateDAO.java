/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.hibernate;
import java.util.List;
import java.io.*;

/**
 *
 * @author afreen
 */
public class CheckDuplicateDAO {

    public String checkDuplicate(String... atr) throws Exception {
        int k;
        FileReader fr = null;
        BufferedReader br = null;
        String s1 = "Start here ";
        String s2 = "";
        String Record = atr[0];
        if (Record != null) {
            fr = new FileReader(Record.toString());
            br = new BufferedReader(fr);
            int noOfRec = 0;
            while (s2 != null) {
                s2 = br.readLine();
                noOfRec++;
            }
            String rec[] = new String[--noOfRec];
            fr = new FileReader(Record.toString());
            br = new BufferedReader(fr);
            int id = 1;
            for (k = 0; k < rec.length; k++) {
                rec[k] = br.readLine();
                if (rec[k] != null) {
                    rec[k] = rec[k] + "," + id;
                    id++;
                }
            }
            String t = "";
            for (int i = 0; i < rec.length; i++) {
                for (int j = 0; j < (rec.length - i - 1); j++) {
                    // if(a[j-1] > a[j]){
                    if (((rec[j].substring(0, rec[j].lastIndexOf(','))).compareTo(rec[j + 1].substring(0, rec[j + 1].lastIndexOf(',')))) > 0) {
                        t = rec[j];
                        rec[j] = rec[j + 1];
                        rec[j + 1] = t;
                    }
                }
            }
            for (int i = 0; i < rec.length - 1; i++) {
                if (rec[i].substring(0, rec[i].lastIndexOf(',')).compareTo(rec[i + 1].substring(0, rec[i + 1].lastIndexOf(','))) == 0) {
                    // message="duplicate record found at line"+rec[i].substring((rec[i].lastIndexOf(',')));
                    return rec[i].substring((rec[i].lastIndexOf(',')));
                }
            }
        }
        return "saveSuccessfully";
    }
}
