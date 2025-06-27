package com.neusoft.neu24.his.neuhospital.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String code;
    private String name;
    private String scale;
    private String price;
    private String sort;
    private String dept;
}
