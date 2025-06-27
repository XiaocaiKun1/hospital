package com.neusoft.neu24.his.neuhospital.service;

import com.neusoft.neu24.his.neuhospital.entity.Medicine;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.entity.Project;

import java.io.IOException;
import java.util.List;

public interface IPatientService {



    //void addPatient(String name, String sex, String age, String idNumber, String address, String week, String day, String time, String room, String number, String money, String pay)throws IOException;

    void addPatient(String name, String sex, String age, String idNumber, String address, String week, String day, String time, String room, String number, String money, String pay, String condition, List<Medicine> medicineList, List<Project> projectList)throws IOException;

    String generateNewPatientblh() throws IOException;

    List<Patient> selectAllPatient();
    List<Patient> selectPatientByblh(String blh);
    List<Patient> selectPatientByName(String name);

}
