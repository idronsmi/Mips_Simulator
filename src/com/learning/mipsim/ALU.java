package com.learning.mipsim;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian on 4/10/2014.
 */
public  class ALU {


    public List<String> Result(int intOne, int intTwo, Controls controls){

        return this.Result(DataPath.intToList(intOne), DataPath.intToList(intTwo),controls);
    }

    public List<String> Result(List<String> a, List<String> b, Controls controls){
        String less = "0";
        String aInvert = "0";
        String bInvert = "0";
        OneBitAdder oneBitAdder = new OneBitAdder();
        List<String> result = new ArrayList<String>();
        for (int i=0;i<32;i++){
            result.add("0");
        }
        String tempA, tempB, andResult,orResult, adderResult, CarryIn;
        if (controls.aluControl.substring(0,2).equals("01")){
            oneBitAdder.setCarryBit("1");
            bInvert = "1";
        } else if(controls.aluControl.substring(0,2).equals("11")) {
            oneBitAdder.setCarryBit("1");
            bInvert = "1";
            aInvert = "1";
        }
        for (int i=31;i>0;i--) {
            tempA = Gates.xorGate(a.get(i), aInvert);
            tempB = Gates.xorGate(b.get(i), bInvert);
            andResult = Gates.andGate(tempA,tempB);
            orResult = Gates.orGate(tempA,tempB);
            adderResult = oneBitAdder.add(tempA,tempB);
            result.set(i,Multiplexor.aluMultiplexor(andResult, orResult, adderResult, less, controls.aluControl.substring(2)));
        }
        tempA = Gates.xorGate(a.get(0), aInvert);
        tempB = Gates.xorGate(b.get(0), bInvert);

        andResult = Gates.andGate(tempA,tempB);
        orResult = Gates.orGate(tempA,tempB);
        CarryIn = oneBitAdder.getCarryBit();
        adderResult = oneBitAdder.add(tempA,tempB);

        result.set(0,Multiplexor.aluMultiplexor(andResult, orResult, adderResult, less, controls.aluControl.substring(2)));
        if (controls.aluControl.substring(2).equals("11")) {
            result.set(31,adderResult);
        }
        if (controls.aluControl.substring(2).equals("10")) {
            overFlowDetect(CarryIn,oneBitAdder.getCarryBit());
        }
        return result;
    }
    private void overFlowDetect(String carryIn,String carryOut) {
        try {
            if (Gates.xorGate(carryIn, carryOut).equals("1")) {
                throw new ArithmeticOverFlow("ArithmeticOverflow: your numbers are too large or too negative, try again with smaller or larger numbers, respectively");
            }
        } catch (ArithmeticOverFlow ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    public static String zeroCheck(List<String> temp) {
        for (String string : temp) {
            if (string.equals("1")) {
                return "0";
            }
        }
        return "1";
    }

    private class ArithmeticOverFlow extends Exception
    {
        //Parameterless Constructor
        public ArithmeticOverFlow() {}

        //Constructor that accepts a message
        public ArithmeticOverFlow(String message)
        {
            super(message);
        }
    }











}
