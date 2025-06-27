package com.neusoft.neu24.his.neuhospital.service.impl;

import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.PatientDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Medicine;
import com.neusoft.neu24.his.neuhospital.entity.Patient;
import com.neusoft.neu24.his.neuhospital.entity.Project;
import com.neusoft.neu24.his.neuhospital.service.IPatientService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class PatientServiceImpl implements IPatientService {
    IPatientDao patientDao =  new PatientDaoImpl();

    //private static IPatientDao patientDao;
    public PatientServiceImpl(IPatientDao patientDao)
    {this.patientDao=patientDao;}


//    @Override
//    public void addPatient(String name, String sex, String age, String idNumber, String address, String week, String day, String time, String room, String number, String money, String pay)throws IOException
//    {
//        String newblh = generateNewPatientblh();
//        Patient newpatient=new Patient(newblh,name,sex,age,idNumber,address,week,day,time,room,number,money,pay);
//        List<Patient> patients=patientDao.loadPatients();
//        patients.add(newpatient);
//        patientDao.savepatients(patients);
//    }

    @Override
    public void addPatient(String name, String sex, String age, String idNumber, String address, String week, String day, String time, String room, String number, String money, String pay,String condition, List<Medicine> medicineList, List<Project> projectList) throws IOException {
        String newblh = generateNewPatientblh();
        Patient newpatient2=new Patient(newblh,name,sex,age,idNumber,address,week,day,time,room,number,money,pay,condition,null,null);
        List<Patient> patients=patientDao.loadPatients();
        patients.add(newpatient2);
        patientDao.savepatients(patients);

    }

    @Override
    public String generateNewPatientblh() throws IOException
    {
        List<Patient> allPatients=patientDao.loadPatients();
        if (allPatients.isEmpty()){
            return "NEUP0001";
        }
        String maxblh = allPatients.stream().max(Comparator.comparing(Patient::getBlh)).map(Patient::getBlh).orElse("10000000");
        if (maxblh.length() < 4) {
            maxblh = "NEUP0000";
        }
        int numbericPart = Integer.parseInt(maxblh.substring(4)) + 1;
        return maxblh.substring(0, 4) + String.format("%04d", numbericPart);
    }

    public List<Patient> selectAllPatient()
    {
        return patientDao.selectAllPatient();
    }
    @Override
    public List<Patient> selectPatientByblh(String blh) {
        return patientDao.selectPatientByblh(blh);
    }
    @Override
    public List<Patient> selectPatientByName(String name) {
        return patientDao.selectPatientByName(name);
    }



}
