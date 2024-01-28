package com.lilu.misc.stream;

import java.util.ArrayList;
import java.util.List;

public class PeopleService {

    public List<People> peopleList = null;

    public void populatePeople() {
        this.peopleList = new ArrayList<People>(){
            {
                add(new People("Zhangsan", 18, "Xiao san", "Developer"));
                add(new People("Lisi", 13, "Xiao si", "Manager"));
                add(new People("Wangwu", 12, "Xiao wu", "Manager"));
                add(new People("Zhaoliu", 11, "Xiao liu", "Developer"));
                add(new People("Cuiqi", 21, "Xiao qi", "Developer"));
            }
        };

    }

    public List<People> getPeopleList() {
        return this.peopleList;
    }
}
