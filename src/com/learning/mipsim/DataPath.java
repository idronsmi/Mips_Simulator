package com.learning.mipsim;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * Created by Ian on 4/9/2014.
 */
public class DataPath {
    private Registers registers = new Registers();
    private Memory memory = new Memory();
    private Controls add = new Controls("000000", "100000");
    private ALU alu = new ALU();
    private int pcInt = 0;


    public DataPath(Path filePath) {
        try( Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8) )
        {
            for( String line : (Iterable<String>) lines::iterator )
            {
                for (int i=0;i<32; i+=8) {
                    List<String> temp = new ArrayList<String>();
                    temp.add(line.substring(i,i+8));
                    memory.addInstrMemory(temp);
                }
            }
        }  catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private DataPath(){}

    public static List<String> signExtendHelper(String a , String signBit) {
        List<String> signExtend = Arrays.asList(new String[32]);
        for (int j = 0; j<16; j++) {
            signExtend.set(j,signBit);
        }
        for (int j = 16; j <32; j++){
            signExtend.set(j, a.substring(j - 16, j - 15));
        }
        return signExtend;
    }
    public static List<String> stringToList(String a) {
        List<String> temp = new ArrayList<>();
        for (int i=0;i<32;i++){
            temp.add("0");
        }
        int stringI = a.length()-1;
        for (int i = 31; i>0;i--) {
            if (stringI >=0) {
                temp.set(i, a.substring(stringI, stringI + 1));
                stringI--;
            }
        }
        return temp;
    }

    public static List<String> intToList(int tempInt) {
        String tempString = Integer.toString(tempInt,2);
        while (tempString.length() < 32) {
            tempString = "0" + tempString;
        }
        List<String> tempList = new ArrayList<>();
        for (int i=0; i<32; i++){
            tempList.add(tempString.substring(i, i + 1));
        }
        return  tempList;
    }

    private static List<String> shiftLeftTwo(List<String> a) {
        List<String> temp = new ArrayList<>();
        for (int i=0;i<32;i++){
            temp.add("0");
        }
        for (int i = 0; i < a.size()-2; i++) {
            temp.set(i, a.get(i + 2));
        }
        return temp;
    }

    public static String listToString(List<String> tempList) {
        String result = "";
        for (String string : tempList) {
            result = result + string;
        }
        return result;
    }

    public void run(){
        while ( pcInt < memory.getInstrMemory().size()-3) {

            String instruction = memory.getInstruction(pcInt);

            List<String> pc = alu.Result(pcInt,4,add);

            String opCode = instruction.substring(0,6);
            String rs = instruction.substring(6, 11);
            String rt = instruction.substring(11, 16);
            String rd = instruction.substring(16, 21);
            String immediate = instruction.substring(16, 32);
            String funct = instruction.substring(26, 32);
            String address = instruction.substring(6,32);

            List<String> jump = stringToList(address);
            jump = DataPath.shiftLeftTwo(jump);

            for (int i=0;i<4;i++){
                jump.set(i,pc.get(i));
            }
            Controls controls = new Controls(opCode, funct);

            String signBit = immediate.substring(0,1);

            int writeRegister = registers.findIndex(Multiplexor.stringMutiplexor(rt, rd, controls.RegDst));

            List<String> signExtend = DataPath.signExtendHelper(immediate, signBit);
            List<String> shiftedList = DataPath.shiftLeftTwo(signExtend);

            List<String> dataOne = registers.findReg(rs);
            List<String> dataTwo = registers.findReg(rt);

            List <String> tempDataTwo = Multiplexor.listMultiplexor(dataTwo,signExtend,controls.ALUSrc);

            List<String> aluResult = alu.Result(dataOne, tempDataTwo, controls);
            controls.Zero = ALU.zeroCheck(aluResult);
            List<String> branch = alu.Result(pc,shiftedList, add);

            pc = Multiplexor.listMultiplexor(pc,branch,Gates.andGate(controls.Branch,controls.Zero));
            pc = Multiplexor.listMultiplexor(pc,jump,controls.Jump);

            List<String> memResult = memory.Result(aluResult,dataTwo,controls);
            List<String> writeData = Multiplexor.listMultiplexor(aluResult, memResult, controls.MemToReg);

            if (controls.RegWrite.equals("1")) {
                registers.setRegisters(writeRegister,writeData);
            }

            String pcString = DataPath.listToString(pc);
            pcInt = (int)Long.parseLong(pcString,2);
            registers.displayRegister();

        }

    }


    public static void main(String[] args) {
        DataPath dataPath = new DataPath(Paths.get("C:/", "binary.txt"));
        dataPath.run();
    }
}
