package com.learning.mipsim;

import java.util.List;

/**
 * Created by Ian on 4/11/2014.
 */
public class Multiplexor {
    public static String  stringMutiplexor(String a, String b, String control) {
        if (control.equals("0")) {
            return a;
        } else {
            return b;
        }
    }
    public static List<String> listMultiplexor(List<String> a, List<String> b , String control) {
        if (control.equals("0")) {
            return a;
        } else {
            return b;
        }
    }

    public static String aluMultiplexor(String a, String b, String c, String d, String control) {
       try{
           if (control.equals("00")) {
                return a;
           } else if (control.equals("01")) {
                return b;
           } else if (control.equals("10")) {
                return c;
           } else if (control.equals("11")) {
                return d;
           }
           throw new BadControlInput();
       } catch (BadControlInput ex) {
           System.out.println(ex);
            return null;
       }


    }


    private static class BadControlInput extends Exception
    {
        //Parameterless Constructor
        public BadControlInput() {}

        //Constructor that accepts a message
        public BadControlInput(String message)
        {
            super(message);
        }
    }
}
