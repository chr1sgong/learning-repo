package io.chr1s.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2023-05-10
 */
public class StreamTest {

    private static final void testStreamFlatMap() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> res = Lists.partition(list, 3)
                .stream()
                .peek(System.out::println)
                .flatMap(List::stream)
                .sorted(Comparator.reverseOrder())
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(res);
    }


    public static void main(String[] args) {
        testStreamFlatMap();
    }
}
