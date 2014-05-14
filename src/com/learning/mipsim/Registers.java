package com.learning.mipsim;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian on 4/9/2014.
 */
public class Registers {
    public List<List<String>> registers = new ArrayList<List<String>>();

    public Registers() {

        for (int i=0; i<32; i++){
            List<String> temp = new ArrayList<>();
            registers.add(temp);
            for (int j=0;j<32;j++){
                temp.add("0");
            }
        }
    }

    public List<String> findReg(String string) {
        int index = Integer.parseInt(string, 2);
        return registers.get(index);
    }
    public int findIndex(String string) {
         return Integer.parseInt(string, 2);
    }
    public void setRegisters(int index ,List<String> writeData) {
        List<String> temp = registers.get(index);
        for (int i=0;i<32;i++) {
            temp.set(i,writeData.get(i));
        }
    }

    public void displayRegister() {
        System.out.print("$zero: ");
        System.out.println(DataPath.listToString(registers.get(0)));
        System.out.print("$at: ");
        System.out.println(DataPath.listToString(registers.get(1)));
        System.out.print("$v0: ");
        System.out.println(DataPath.listToString(registers.get(2)));
        System.out.print("$v1: ");
        System.out.println(DataPath.listToString(registers.get(3)));
        System.out.print("$a0: ");
        System.out.println(DataPath.listToString(registers.get(4)));
        System.out.print("$a1: ");
        System.out.println(DataPath.listToString(registers.get(5)));
        System.out.print("$a2: ");
        System.out.println(DataPath.listToString(registers.get(6)));
        System.out.print("$a3: ");
        System.out.println(DataPath.listToString(registers.get(7)));
        System.out.print("$t0: ");
        System.out.println(DataPath.listToString(registers.get(8)));
        System.out.print("$t1: ");
        System.out.println(DataPath.listToString(registers.get(9)));
        System.out.print("$t2: ");
        System.out.println(DataPath.listToString(registers.get(10)));
        System.out.print("$t3: ");
        System.out.println(DataPath.listToString(registers.get(11)));
        System.out.print("$t4: ");
        System.out.println(DataPath.listToString(registers.get(12)));
        System.out.print("$t5: ");
        System.out.println(DataPath.listToString(registers.get(13)));
        System.out.print("$t6: ");
        System.out.println(DataPath.listToString(registers.get(14)));
        System.out.print("$t7: ");
        System.out.println(DataPath.listToString(registers.get(15)));
        System.out.print("$s0: ");
        System.out.println(DataPath.listToString(registers.get(16)));
        System.out.print("$s1: ");
        System.out.println(DataPath.listToString(registers.get(17)));
        System.out.print("$s2: ");
        System.out.println(DataPath.listToString(registers.get(18)));
        System.out.print("$s3: ");
        System.out.println(DataPath.listToString(registers.get(19)));
        System.out.print("$s4: ");
        System.out.println(DataPath.listToString(registers.get(20)));
        System.out.print("$s5: ");
        System.out.println(DataPath.listToString(registers.get(21)));
        System.out.print("$s6: ");
        System.out.println(DataPath.listToString(registers.get(22)));
        System.out.print("$s7: ");
        System.out.println(DataPath.listToString(registers.get(23)));
        System.out.print("$t8: ");
        System.out.println(DataPath.listToString(registers.get(24)));
        System.out.print("$t9: ");
        System.out.println(DataPath.listToString(registers.get(25)));
        System.out.print("$k0: ");
        System.out.println(DataPath.listToString(registers.get(26)));
        System.out.print("$k`: ");
        System.out.println(DataPath.listToString(registers.get(27)));
        System.out.print("$gp: ");
        System.out.println(DataPath.listToString(registers.get(28)));
        System.out.print("$sp: ");
        System.out.println(DataPath.listToString(registers.get(29)));
        System.out.print("$fp: ");
        System.out.println(DataPath.listToString(registers.get(30)));
        System.out.print("$ra: ");
        System.out.println(DataPath.listToString(registers.get(30)));
        System.out.println();
    }
}

