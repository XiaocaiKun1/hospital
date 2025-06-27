package com.neusoft.neu24.his.neuhospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    private String blh;
    private String name;
    private String sex;



    private String age;
    private String idNumber;
    private String address;
    private String week;
    private String day;
    private String time;
    private String room;
    private String number;
    private String money;
    private String pay;

    private String condition;
    private List<Medicine> medicineList;
    private List<Project> projectList;





}
