package com.learning.mipsim;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian on 5/12/2014.
 */
public class OneBitAdder {
    private String carryBit;

    protected OneBitAdder() {
        this.carryBit = "0";
    }

    protected String getCarryBit() {
        return carryBit;
    }

    protected void setCarryBit(String carryBit) {
        this.carryBit = carryBit;
    }

    protected String add(String a, String b) {
        String temp1, temp2, temp3, result;
        temp1 = Gates.xorGate(b, a);
        temp2 = Gates.andGate(a, b);
        temp3 = Gates.andGate(this.carryBit, temp1);
        result = Gates.xorGate(temp1, this.carryBit);

        this.carryBit = Gates.orGate(temp2, temp3);
        return result;
    }

}
