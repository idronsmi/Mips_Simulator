package com.learning.mipsim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian on 4/14/2014.
 */
public class Memory {
    private List<List<String>> dataMemory = new ArrayList<List<String>>();
    private List<List<String>> instrMemory = new ArrayList<List<String>>();

    public Memory() {
        for (int i=0;i<1024;i++) {
            String[] tempString = new String[]{"0", "0", "0", "0", "0", "0", "0", "0"};
            List<String> tempList =  new ArrayList<>( Arrays.asList(tempString));
            dataMemory.add(tempList);
        }
    }

    public List<String> getMemory(int i) {
        return dataMemory.get(i);
    }

    public void setMemory(List<String> memory, int index) {
        this.dataMemory.set(index, memory);
    }

    public List<List<String>> getDataMemory() {
        return dataMemory;
    }

    public List<List<String>> getInstrMemory() {
        return instrMemory;
    }

    public void addInstrMemory(List<String> instrMemory) {
        this.instrMemory.add(instrMemory);
    }

    public List<String> Result(List<String> address, List<String> writeData, Controls controls) {
        String adr = DataPath.listToString(address);
        List<String> result = new ArrayList<>();
        int index = (int)Long.parseLong(adr,2);

        if (controls.MemWrite.equals("1")) {
            memoryCheck(index);
            int count = 0;
            for (int k=0; k<4;k++) {
                List<String> a = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    a.add(writeData.get(count));
                    count++;
                }
                dataMemory.set(index+k,a);
            }
            return address;
        } else if (controls.MemRead.equals("1")) {
            memoryCheck(index);
            for (int i=0;i<4;i++){
                List<String> a = new ArrayList<>(dataMemory.get(index + i));
                for (int j=0; j<8;j++) {
                    result.add(a.get(j));
                }
            }
            return result;
        }
        return null;
    }
    public String getInstruction(int index) {
        String instruction = "";
        for (int i=0; i<4;i++){
            for (String string : instrMemory.get(index + i)) {
                instruction = instruction + string;
            }
        }
        return instruction;
    }
    public void printInstructionMemory() {
        int count = 1;
        for (List<String> temp : instrMemory) {
            for (String string : temp) {
                System.out.print(string);
            }
            if (count%4 == 0) {
                System.out.println();
            }
            count++;
        }
    }
    private void memoryCheck(int index) {
        try {
            if (index >= 1024) {
                throw new MemeoryOverFlow("MemoryOverflow: Only 1 kilobyte of memory is simulated, make sure memory addresses are less than 1024");
            }
        }
        catch (MemeoryOverFlow ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    private class MemeoryOverFlow extends Exception
    {
        //Parameterless Constructor
        public MemeoryOverFlow() {}

        //Constructor that accepts a message
        public MemeoryOverFlow(String message)
        {
            super(message);
        }
    }
}
