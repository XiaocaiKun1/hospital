package com.neusoft.neu24.his.neuhospital.dao.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.util.GlobalData;
import com.neusoft.neu24.his.neuhospital.util.ReadPatient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientDaoImpl implements IPatientDao {
    public static String PATIENT_FILE_PATH="D:/Work/neu/his/NEU-Hospital/src/main/java/com/neusoft/neu24/his/neuhospital/patient.json";
    private ObjectMapper objectMapper=new ObjectMapper();
    private static List<Patient> PATIENT_LIST = new ArrayList<>();
    @Override
    public List<Patient> loadPatients() throws IOException
    {
        File file = new File(PATIENT_FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        return objectMapper.readValue(file,
                new TypeReference<List<Patient>>() {});
    }

    @Override
    public void savepatients(List<Patient> patients) throws IOException
    {
        if (patients == null || patients.isEmpty()) {
            patients = new ArrayList<>();
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(PATIENT_FILE_PATH), patients);
    }

    @Override
    public List<Patient> selectAllPatient() {
        return PATIENT_LIST;
    }

    @Override
    public List<Patient> selectPatientByblh(String blh) {
        String filePath = "D:/Work/neu/his/NEU-Hospital/src/main/java/com/neusoft/neu24/his/neuhospital/patient.json"; // 替换为你的JSON文件路径
        GlobalData.patientList = ReadPatient.readPatinetsFromJson(filePath);
        return GlobalData.patientList.stream().
                filter(patient -> patient.getBlh().indexOf(blh)> -1)
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> selectPatientByName(String name) {
        return PATIENT_LIST.stream().filter(patient -> patient.getName().indexOf(name)>-1).collect(Collectors.toList());
    }
}
