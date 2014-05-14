package com.learning.mipsim;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OneBitAdderTest {
    OneBitAdder oneBitAdder;

    @Before
    public void setUp() throws Exception {
        oneBitAdder = new OneBitAdder();
    }

    @After
    public void tearDown() throws Exception {
        oneBitAdder = null;
    }

    @Test
    public void testAddSummTruthTable() throws Exception {
        String[] one = {"0","0","0","0","1","1","1","1"};
        String[] two = {"0","0","1","1","0","0","1","1"};
        String[] three = {"0","1","0","1","0","1","0","1"};
        String[] sumString = {"0","1","1", "0","1", "0", "0" , "1"};
        List<String> a = Arrays.asList(one);
        List<String> b = Arrays.asList(two);
        List<String> carryBit = Arrays.asList(three);
        List<String> sum = Arrays.asList(sumString);
        List<String> result =  new ArrayList<>();

        for (int i = 0; i<8; i++) {
            oneBitAdder.setCarryBit(carryBit.get(i));
            result.add(oneBitAdder.add(a.get(i), b.get(i)));
        }
        assertEquals("Failure - Truth Table for the sum bit is not equal", sum,result);
    }
    @Test
    public void testAddCarryOutTruthTable() throws Exception {
        String[] one = {"0","0","0","0","1","1","1","1"};
        String[] two = {"0","0","1","1","0","0","1","1"};
        String[] three = {"0","1","0","1","0","1","0","1"};
        String[] carryString = {"0","0","0", "1","0", "1", "1" , "1"};
        List<String> a = Arrays.asList(one);
        List<String> b = Arrays.asList(two);
        List<String> carryBit = Arrays.asList(three);
        List<String> carryOut = Arrays.asList(carryString);
        List<String> result =  new ArrayList<>();

        for (int i = 0; i<8; i++) {
            oneBitAdder.setCarryBit(carryBit.get(i));
            oneBitAdder.add(a.get(i), b.get(i));
            result.add(oneBitAdder.getCarryBit());
        }
        assertEquals("Failure - Truth Table for the carry out bit is not equal", carryOut,result);
    }
}