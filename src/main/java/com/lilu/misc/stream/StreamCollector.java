package com.lilu.misc.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollector {
    public static void main(String[] args) {
        PeopleService peopleService = new PeopleService();
        peopleService.populatePeople();
        List<People> peopleList = peopleService.getPeopleList();

        StreamCollector streamCollector = new StreamCollector();
    }

    // 集合收集器
    public void toListTest(List<People> peopleList) {
        List<People> peopleListRes = peopleList.stream()
                .filter(people -> people.getAge() > 15)
                .collect(Collectors.toList());
    }

    // 集合分组
    public void groupTest(List<People> peopleList) {
        Map<Object, List<People>> groupRes = peopleList.stream()
                .collect(Collectors.groupingBy(people -> people.getTitle()));
    }

    // 集合分区
    public void partitionTest(List<People> peopleList) {
        Map<Boolean, List<People>> partitionRes = peopleList.stream()
                .collect(Collectors.partitioningBy(people -> people.getAge() > 15));
    }
}
