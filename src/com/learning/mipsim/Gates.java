package com.learning.mipsim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 4/9/2014.
 */
public class Gates {

    public static  String andGate(String a, String b) {
        if (("1".equals(a)) & ("1".equals(b))) {
            return "1";
        } else if (("0".equals(a)) & ("1".equals(b))) {
            return "0";
        } else if (("1".equals(a)) & ("0".equals(b))) {
            return "0";
        } else if (("0".equals(a)) & ("0".equals(b))) {
            return "0";
        } else {
            return null;
        }
    }

    public static  String orGate(String a, String b) {
        if (("1".equals(a)) & ("1".equals(b))) {
            return "1";
        } else if (("0".equals(a)) & ("1".equals(b))) {
            return "1";
        } else if (("1".equals(a)) & ("0".equals(b))) {
            return "1";
        } else if (("0".equals(a)) & ("0".equals(b))) {
            return "0";
        } else {
            return null;
        }
    }

    public static  String xorGate(String a, String b) {
        if (("1".equals(a)) & ("1".equals(b))) {
            return "0";
        } else if (("0".equals(a)) & ("1".equals(b))) {
            return "1";
        } else if (("1".equals(a)) & ("0".equals(b))) {
            return "1";
        } else if (("0".equals(a)) & ("0".equals(b))) {
            return "0";
        } else {
            return null;
        }
    }

    public static  String notGate(String a) {
        if ("1".equals(a)) {
            return "0";
        } else if ("0".equals(a)) {
            return "1";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();

        a.add("hiro");
        a.add(" is");
        a.add(" cute");
        b.add("berb");
        b.add(" is way");
        b.add(" cuter");
        System.out.println(DataPath.listToString(a));
        System.out.println(DataPath.listToString(b));
        List<String> c = a;
        System.out.println();
        System.out.println(DataPath.listToString(c));
        System.out.println();
        a.set(0,"BERBALERB");
        System.out.println(DataPath.listToString(c));
        System.out.println(DataPath.listToString(a));

    }



}
