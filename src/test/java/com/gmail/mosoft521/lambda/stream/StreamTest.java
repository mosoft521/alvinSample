package com.gmail.mosoft521.lambda.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StreamTest {
    @Test
    public void fromCollections() {
        List<String> list = Collections.emptyList();
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();
    }

    @Test
    public void fromArrays() {
        int[] array = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(array);
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void fromArraysStatic() {
        int[] array = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(array);
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void fromFiles() {
        Path path = Paths.get("");
        try {
            Stream<Path> stream = Files.walk(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generateByYourself() {
        Random random = new Random(System.currentTimeMillis());
        Supplier<Integer> supplierRandom = random::nextInt;
        Stream.generate(supplierRandom).limit(10).forEach((e) -> System.out.println(e));
    }

    @Test
    public void generateByExpandSupplier() {
        class Member {
            private int id;
            private String name;

            public Member(int id, String name) {
                Member.this.id = id;
                Member.this.name = name;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }
        class MemberSupplier implements Supplier<Member> {
            private int index = 0;
            private Random random = new Random(System.currentTimeMillis());

            @Override
            public Member get() {
                return new Member(index = random.nextInt(100), "Member#" + index);
            }
        }
        Stream.generate(new MemberSupplier()).limit(20).forEach((m) -> System.out.println(m.getId() + ":" + m.getName()));
    }

    @Test
    public void testMap() {
        int[] array = {1, 2, 3, 4, 5};
        IntStream.of(array).map(e -> e * 10).forEach((e) -> System.out.println(e));
    }

    @Test
    public void testFilter() {
        Integer[] array = {1, 2, 3, 4, 5};
        List<Integer> result = Stream.of(array).filter(e -> e > 3).collect(Collectors.toList());
        assertEquals(2, result.size());
    }

    @Test
    public void testDistinct() {
        Integer[] array = {1, 1, 2, 3, 4, 5, 6, 5};
        assertEquals(8, array.length);
        List<Integer> result = Stream.of(array).distinct().collect(Collectors.toList());
        assertEquals(6, result.size());
    }

    @Test
    public void testSorted() {
        Integer[] array = {3, 4, 6, 1, 8, 2};
        Stream.of(array).sorted().forEach(e -> System.out.println(e));
    }

    @Test
    public void testPeek() {
        Integer[] array = {3, 4, 6, 1, 8, 2};
        List<Integer> result = Stream.of(array).filter(e -> e % 2 == 0).peek(e -> System.out.println(e)).collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void testLimit() {
        Integer[] array = {3, 4, 6, 1, 8, 2};
        Stream.of(array).limit(4).forEach(e -> System.out.println(e));
    }

    @Test
    public void testSkip() {
        Integer[] array = {3, 4, 6, 1, 8, 2};
        Stream.of(array).skip(3).forEach(e -> System.out.println(e));
    }

    @Test
    public void testParallel() {
        Integer[] array = {3, 4, 6, 1, 8, 2};
        Stream.of(array).parallel().forEach(e -> System.out.println(e));
    }

    @Test
    public void testDistinctSequential() {
        Integer[] array = {1, 1, 2, 3, 4, 5, 6, 5};
        assertEquals(8, array.length);
        List<Integer> result = Stream.of(array).distinct().collect(Collectors.toList());
        assertEquals(6, result.size());
    }

    @Test
    public void testForEachOrdered() {
        Integer[] array = {1, 2, 3, 4, 5, 6};
        Object[] result = Stream.of(array).filter(e -> e > 4).toArray();
        assertEquals(2, result.length);
    }

    @Test
    public void testReduce() {
        String reduceResult = Stream.of("W", "a", "n", "g").reduce("", String::concat);
        assertEquals("Wang", reduceResult);
    }

    @Test
    public void testAnyMatch() {
        Integer[] array = {1, 2, 3, 4, 5};
        boolean result = Stream.of(array).anyMatch(e -> e > 3);
        assertTrue(result);
    }

    @Test
    public void testFindFirst() {
        Integer[] array = {1, 2, 3, 4, 5};
        Integer result = Stream.of(array).findFirst().get();
        assertEquals(1, result.intValue());
    }

    @Test
    public void testFindAny() {
        Integer[] array = {1, 2, 3, 4, 5};
        Integer result = Stream.of(array).findAny().get();
        System.out.println(result);
    }

    @Test
    public void testIterator() {
        Integer[] array = {1, 2, 3, 4, 5};
        Iterator<Integer> iterator = Stream.of(array).iterator();
    }
}
