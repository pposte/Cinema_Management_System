/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.util;

import java.util.Random;

/**
 *
 * @author prateek
 */
public class IdGenerator {
    static final String range = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
    static Random rnd = new Random();
    
    public static String randomID(){
        StringBuilder sb = new StringBuilder(15);
        for(int i = 0; i < 15; i++)
            sb.append(range.charAt(rnd.nextInt(range.length())));
        return sb.toString();
    }
    
}
