package com.gmail.mosoft521.gson;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * Created by root on 2017/4/20 0020.
 */
public class CollectionTest extends TestCase {
    private Gson gson;

    @Before
    protected void setUp() throws Exception {
        gson = new Gson();
    }

    private static class Entry {
        @SerializedName("custom_value")
        int value;

        Entry(int value) {
            this.value = value;
        }
    }

    @Test
    public void testSetDeserialization() {
        String json = "[{custom_value:1},{custom_value:2}]";
        Type type = new TypeToken<Set<Entry>>() {
        }.getType();
        Set<Entry> set = gson.fromJson(json, type);
        assertEquals(2, set.size());
        for (Entry entry : set) {
            assertTrue(entry.value == 1 || entry.value == 2);
        }
    }
}
