package com.neusoft.neu24.his.neuhospital.dao;

import com.neusoft.neu24.his.neuhospital.entity.Patient;

import java.io.IOException;
import java.util.List;

public interface IPatientDao {
    public List<Patient> loadPatients() throws IOException;
    public void savepatients(List<Patient> patients) throws IOException;

    List<Patient> selectAllPatient();
    List<Patient> selectPatientByblh(String blh);
    List<Patient> selectPatientByName(String name );

}
