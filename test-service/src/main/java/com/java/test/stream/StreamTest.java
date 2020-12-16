package com.java.test.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-04-12 13:30
 */
public class StreamTest {
    public static void main(String[] args) {
//        Stream.of(1,2,3).forEach(System.out::println);
//        Stream<Integer> concat = Stream.concat(Stream.of(1, 2, 4), Stream.of(3, 5, 6));
//        concat.sorted().forEach(System.out::println);
        List<String> collect = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        Stream.of("one", "two", "three", "four");
    }
}























