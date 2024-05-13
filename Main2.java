package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, -2, 3, 5, 4, 9, 8, 4, 7, -6, 3, -2, 8);

        //sort ascending
        values.stream().sorted().forEach(System.out::println);

        //sort descending
        values.stream().sorted((a, b)->b-a).forEach(System.out::println);

        //distinct elements
        values.stream().distinct().forEach(System.out::println);

        //get max value
        Integer val = values.stream().reduce(Integer.MIN_VALUE, (a, b)->a>b?a:b);
        System.out.println(val);

        //get sum
        Integer sum = values.stream().reduce(0,(a,b)->a+b);
        System.out.println(sum);

        //summay statistic in stream
        double avg = values.stream().mapToInt(Integer::intValue).summaryStatistics().getAverage();
        System.out.println(avg);
        //you can use getMin, getMax, getCount etc.

        String input = "abefafcbgcahdedc";

        //frequency of each element
        Map<String, Long> output = Arrays.stream(input.split("")).collect(Collectors.groupingBy(a->a, Collectors.counting()));
        System.out.println(output);

        //find duplicate elements
        List<String> duplicats = Arrays.stream(input.split("")).collect(Collectors.groupingBy(a->a, Collectors.counting()))
                .entrySet().stream().filter(x->x.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(duplicats);
        //to find unique elements use filter(x->x.getValue()==1)

        //first non repeating element
        String nonRepeat = Arrays.stream(input.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(x->x.getValue() == 1).findFirst().get().getKey();
        System.out.println(nonRepeat);

        //joining
        String str = Arrays.stream(input.split("")).collect(Collectors.joining("-"));
        System.out.println(str);

        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        //sort a map by key ascending
        Map<String, Integer> sortedMap = unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(a,b)->a,LinkedHashMap::new));
        System.out.println(sortedMap);

        //sort a map by value descending
        Map<String, Integer> sortedMapDesc = unsortMap.entrySet().stream().sorted(Map.Entry.comparingByValue((a,b)->b-a))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(a,b)->a,LinkedHashMap::new));
        System.out.println(sortedMapDesc);
    }
}
