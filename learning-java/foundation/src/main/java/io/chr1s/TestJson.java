package io.chr1s;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2023-01-18
 */
public class TestJson {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/gongqi/Desktop/test.json");
//        String content = Files.readString(Paths.get("/Users/gongqi/Desktop/test.json"));
//        System.out.println(content);
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj = objectMapper.readValue(file, Object.class);
        long curr = System.currentTimeMillis();
        byte[] bytes = objectMapper.writeValueAsBytes(obj);
        System.out.println("cost: " + (System.currentTimeMillis() - curr));
    }
}
