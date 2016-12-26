package com.example.trans.intervaltimer;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void stringToListConvert_isCorrect() throws Exception {
        String trials = "20  25 30   25 20      30";
        StringToList sl = new StringToList(trials);
        List<Integer> result = sl.getList();
        assertEquals(6, result.size());
    }
}