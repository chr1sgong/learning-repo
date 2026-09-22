package io.chr1s.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author gongqi <gongqi@kuaishou.com>
 * Created on 2021-04-12
 */
public class ReadFileToString {

    public static void main(String[] args) {
        System.out.println(Paths.get(".").toAbsolutePath().toString());
        String filePath = "data/test-data1.txt";
        System.out.println("read file to string using java8: ");
        System.out.println(readFileLineByLineJava8(filePath));

        System.out.println("read file to string using java7: ");
        System.out.println(readFileToStringJava7(filePath));

        System.out.println("read file to string using java6: ");
        System.out.println(readFileToStringUsingBufferReader(filePath));
    }

    private static String readFileLineByLineJava8(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    private static String readFileToStringJava7(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content;
    }

    private static String readFileToStringUsingBufferReader(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String currLine;
            while ((currLine = bufferedReader.readLine()) != null) {
                contentBuilder.append(currLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
