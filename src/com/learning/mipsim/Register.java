package com.learning.mipsim;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian on 4/11/2014.
 */
public class Register {
    private List<String> data;

    public Register() {
        this.data = Arrays.asList(new String[32]);
    }

    public Register(String data) {
        this.data = Arrays.asList(data);
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
