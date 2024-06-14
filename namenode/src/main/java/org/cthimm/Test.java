package org.cthimm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        Map map = new HashMap();
        map.put("tes5","test");
        System.out.println(jsonMapper.writeValueAsString(map));
    }
}
