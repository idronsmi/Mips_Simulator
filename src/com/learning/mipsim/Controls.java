package com.learning.mipsim;

/**
 * Takes the opcode (always a string of binary with length 6) from a mips instructions and sets the controls necessary for that instruction.
 * Only supports Add, Addi, Subtract, LW, SW, Beq, Jump
 * Created by Ian on 4/11/2014.
 */
public class Controls {
    public String RegDst = "0";
    public String ALUSrc = "0";
    public String MemToReg = "0";
    public String RegWrite = "0";
    public String MemRead = "0";
    public String MemWrite = "0";
    public String Branch = "0";
    public String Jump = "0";
    public String ALUOp0 = "0";
    public String ALUOp1 = "0";
    public String aluControl;
    public String Zero = "0";

    public Controls(String opCode,String functField) {
        switch (opCode) {
            case "000000" :
                //R Type
                RegDst = "1";
                ALUSrc = "0";
                MemToReg = "0";
                RegWrite = "1";
                MemRead = "0";
                MemWrite = "0";
                Branch = "0";
                ALUOp0 = "0";
                ALUOp1 = "1";
            break;

            case  "100011":
                //LW
                RegDst = "0";
                ALUSrc = "1";
                MemToReg = "1";
                RegWrite = "1";
                MemRead = "1";
                MemWrite = "0";
                Branch = "0";
                ALUOp0 = "0";
                ALUOp1 = "0";
                break;

            case "101011":
                //sw
                RegDst = "0";
                ALUSrc = "1";
                MemToReg = "0";
                RegWrite = "0";
                MemRead = "0";
                MemWrite = "1";
                Branch = "0";
                ALUOp0 = "0";
                ALUOp1 = "0";
                break;

            case "000100":
               //beq
                RegDst = "0";
                ALUSrc = "0";
                MemToReg = "0";
                RegWrite = "0";
                MemRead = "0";
                MemWrite = "0";
                Branch = "1";
                ALUOp0 = "1";
                ALUOp1 = "0";
                break;

            case "001000":
                //addi
                RegDst = "0";
                ALUSrc = "1";
                MemToReg = "0";
                RegWrite = "1";
                MemRead = "0";
                MemWrite = "0";
                Branch = "0";
                ALUOp0 = "0";
                ALUOp1 = "0";
                break;

            case "000010":
                //Jump
                Jump = "1";
                break;

            default:
                System.out.println("Not a valid opcode");
                return;
        }
        this.ALUControl(functField);
    }

    private void ALUControl (String functField) {
        if ((ALUOp0.equals("0")) & ((ALUOp1.equals("0")))) {
            //addi
            aluControl = "0010";
        } else if ((ALUOp0.equals("1")) & ((ALUOp1.equals("0")))) {
            //beq
            aluControl = "0110";
        } else if ((ALUOp1.equals("1"))) {
            if (functField.equals("100000")) {
                //add
                aluControl = "0010";
            } else if (functField.equals("100010")) {
                //subtract
                aluControl = "0110";
            } else if (functField.equals("100100")) {
                //and
                aluControl = "0000";
            } else if (functField.equals("100101")) {
                //or
                aluControl = "0001";
            } else if (functField.equals("101010")) {
                //slt
                aluControl = "0111";
            } else if (functField.equals("100111")) {
                //nor
                aluControl = "1100";
            }


        }
    }
}
