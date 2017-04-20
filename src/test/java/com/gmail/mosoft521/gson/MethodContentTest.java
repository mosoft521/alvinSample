package com.gmail.mosoft521.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by root on 2017/4/20 0020.
 */
public class MethodContentTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Test
    public void testSetDeserialization() {
        String methocContent = "[\n" +
                "    {\n" +
                "        \"Id\": \"1414684652570000100\",\n" +
                "        \"Name\": \"面积修正\",\n" +
                "        \"Code\": \"Areacorrection\",\n" +
                "        \"PWeight\": \"1.00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Id\": \"1414684652570000200\",\n" +
                "        \"Name\": \"主朝向修正\",\n" +
                "        \"Code\": \"Orientation\",\n" +
                "        \"PWeight\": \"2.00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Id\": \"1414684652570000300\",\n" +
                "        \"Name\": \"竣工年份\",\n" +
                "        \"Code\": \"CompletionDate\",\n" +
                "        \"PWeight\": \"3.00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Id\": \"1414720298680000100\",\n" +
                "        \"Name\": \"楼层修正\",\n" +
                "        \"Code\": \"Floorcorrection\",\n" +
                "        \"PWeight\": \"4.00\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Id\": \"8220632328759672896\",\n" +
                "        \"Name\": \"采光情况\",\n" +
                "        \"Code\": \"Daylightconditions\",\n" +
                "        \"PWeight\": \"5.00\"\n" +
                "    }\n" +
                "]";
        List<MethodContent> methodContentList = gson.fromJson(methocContent, new TypeToken<List<MethodContent>>() {
        }.getType());
        for (MethodContent methodContent : methodContentList) {
            System.out.println(methocContent);
        }
    }

}
