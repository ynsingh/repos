/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class RandomTokenString extends ActionSupport {

    private static final String dCase = "abcdefghijklmnopqrstuvwxyz";
    private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String sChar = "!@#$%^&*";
    private static final String intChar = "0123456789";
    private static Random r = new Random();
    private static String TokenString = "";

    public static String generateTokenString(int StrLength) {
        while (TokenString.length() != StrLength) {
            int rPick = r.nextInt(4);
            if (rPick == 0) {
                int spot = r.nextInt(25);
                TokenString += dCase.charAt(spot);
            } else if (rPick == 1) {
                int spot = r.nextInt(25);
                TokenString += uCase.charAt(spot);
//            } else if (rPick == 2) {
//                int spot = r.nextInt(6);
//                TokenString += sChar.charAt(spot);
            } else if (rPick == 3) {
                int spot = r.nextInt(9);
                TokenString += intChar.charAt(spot);
            }
        }
        return TokenString;
    }
}
