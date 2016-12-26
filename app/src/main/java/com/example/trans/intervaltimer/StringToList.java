package com.example.trans.intervaltimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trans on 2016-12-26.
 */
public class StringToList {
    private String mString;
    public StringToList(String str) {
        mString = str.trim();
    }

    public List<Integer> getList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] splitString = mString.split(" +");
        for (String item : splitString) {
            list.add(Integer.valueOf(item));
        }
        return list;
    }
}
