package com.lilu.misc.stream;

import java.util.*;

public class StreamMethod {
    public static void main(String[] args) {
        PeopleService peopleService = new PeopleService();
        peopleService.populatePeople();
        List<People> peopleList = peopleService.getPeopleList();

        //new Main().filterTest(peopleList);
        //new Main().mapTest(peopleList);
        //new Main().flatMapTest(peopleList);
        //new Main().peekTest(peopleList);
        //new Main().sortTest(peopleList);
        //new Main().distinctTest(peopleList);
        //new Main().skipTest(peopleList);
        //new Main().limitTest(peopleList);

        //new Main().allMatchTest(peopleList);
        //new Main().anyMatchTest(peopleList);
        //new Main().findFirstTest(peopleList);
        //new Main().findAnyTest(peopleList);
        new StreamMethod().maxTest(peopleList);
    }

    // 中间操作 --------------
    // 过滤
    public void filterTest(List<People> peopleList) {
        peopleList.stream()
                .filter(people -> people.title.equals("Manager"))
                .forEach(System.out::println);
    }

    // 元素转换
    public void mapTest(List<People> peopleList) {
        peopleList.stream()
                .map(people -> people.getName())
                .forEach(System.out::println);
    }

    // 将一个元素转成流
    public void flatMapTest(List<People> peopleList) {
        peopleList.stream()
                .flatMap(people -> Arrays.stream(people.getName().split("")))
                .forEach(System.out::println);
    }

    // 中间状态的 forEach 遍历操作
    public void peekTest(List<People> peopleList) {
        peopleList.stream()
                .peek(people -> System.out.println(people.getName()))
                .forEach(System.out::println);
    }

    // 排序操作
    public void sortTest(List<People> peopleList) {
        peopleList.stream()
                .sorted(new Comparator<People>() {
                    @Override
                    public int compare(People o1, People o2) {
                        // Integer.compareTo()
                        return o2.getAge().compareTo(o1.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    // 去重操作
    public void distinctTest(List<People> peopleList) {
        peopleList.stream()
                .map(people -> people.getTitle())
                .distinct()
                .forEach(System.out::println);
    }

    // 跳过元素
    public void skipTest(List<People> peopleList) {
        peopleList.stream()
                .skip(3)
                .forEach(System.out::println);
    }

    // 限制元素
    public void limitTest(List<People> peopleList) {
        peopleList.stream()
                .limit(3)
                .forEach(System.out::println);
    }

    // 终端操作 --------------
    // 是否全部满足，并且是短路操作
    public void allMatchTest(List<People> peopleList) {
        boolean match = peopleList.stream()
                .peek(people -> System.out.println(people.getName())) // 用 peek 验证短路操作
                .allMatch(people -> people.getAge() > 20);
        System.out.println(match);
    }

    // 只要有满足则返回 true
    public void anyMatchTest(List<People> peopleList) {
        boolean match = peopleList.stream()
                .anyMatch(people -> people.getAge() > 20);
        System.out.println(match);
    }

    // 所有都没匹配上返回 true
    public void noneMatchTest(List<People> peopleList) {
        boolean match = peopleList.stream()
                .noneMatch(people -> people.getAge() > 20);
        System.out.println(match);
    }

    // 找到第一个
    public void findFirstTest(List<People> peopleList) {
        Optional<People> match = peopleList.stream()
                .findFirst();
        System.out.println(match.get());
    }

    // 找到任何一个，在并行上效率更高
    public void findAnyTest(List<People> peopleList) {
        Optional<People> match = peopleList.stream()
                .findAny();
        System.out.println(match.get());
    }

    // 求最大值
    public void maxTest(List<People> peopleList) {
        OptionalDouble optionalDouble = peopleList.stream()
                .mapToDouble(People::getAge)
                // .min() 是求最小值
                .max();

        System.out.println(optionalDouble);
    }
}
