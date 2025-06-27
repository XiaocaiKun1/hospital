package com.neusoft.neu24.his.neuhospital.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.neu24.his.neuhospital.entity.Patient;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadPatient {



    public static List<Patient> readPatinetsFromJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Patient.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
