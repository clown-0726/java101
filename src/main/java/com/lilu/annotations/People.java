package com.lilu.annotations;

import com.lilu.annotations.annotation.Column;
import com.lilu.annotations.annotation.Table;
import lombok.Data;

@Table("user")
@Data
public class People {
    @Column("id")
    private int id;
    @Column("name")
    private String name;
    @Column("age")
    private int age;
}
