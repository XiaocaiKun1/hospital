package com.neusoft.neu24.his.neuhospital.Controller;

import com.neusoft.neu24.his.neuhospital.dao.IPatientDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.PatientDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Dept;
import com.neusoft.neu24.his.neuhospital.entity.Medicine;
import com.neusoft.neu24.his.neuhospital.entity.Project;
import com.neusoft.neu24.his.neuhospital.service.IPatientService;
import com.neusoft.neu24.his.neuhospital.service.impl.PatientServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class GuahaoController {
    IPatientDao patientDao=new PatientDaoImpl();

    @FXML
    private ComboBox cb_age;

    @FXML
    private ComboBox cb_day;

    @FXML
    private ComboBox cb_doctor;

    @FXML
    private ComboBox cb_number;

    @FXML
    private ComboBox cb_pay;

    @FXML
    private ComboBox cb_room;

    @FXML
    private ComboBox cb_sex;

    @FXML
    private ComboBox cb_week;

    @FXML
    private DatePicker dp_time;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_age;

    @FXML
    private TextField tf_alnumber;

    @FXML
    private TextField tf_bgnumber;

    @FXML
    private TextField tf_blh;

    @FXML
    private TextField tf_idnumber;

    @FXML
    private TextField tf_money;

    @FXML
    private TextField tf_name;


    @FXML
    CheckBox cb_blb;

    private IPatientService patientService = new PatientServiceImpl(patientDao);

    @FXML
    public void initialize() {
        cb_sex.getItems().addAll(Dept.getSex());
        cb_age.getItems().addAll(Dept.getAge());
        cb_week.getItems().addAll(Dept.getWeek());
        cb_day.getItems().addAll(Dept.getDay());
        cb_room.getItems().addAll(Dept.getRoom());
        cb_number.getItems().addAll(Dept.getNumber());
        cb_doctor.getItems().addAll(Dept.getDoctor());
        cb_pay.getItems().addAll(Dept.getPay());

        tf_alnumber.setText("0");

        try{
            tf_blh.setText(patientService.generateNewPatientblh());
            tf_blh.setEditable(false);
        }
        catch (Exception e){
            e.printStackTrace();
        }




    }

    public void cb_ChoiceNumber() throws IOException{
        if( cb_number.getValue()!=null &&  cb_number.getValue().equals("教授")){
            tf_money.clear();
            tf_money.setText("30");
        }
        else if ( cb_number.getValue()!=null &&  cb_number.getValue().equals("专家")){
            tf_money.clear();
            tf_money.setText("20");
        }
        else if ( cb_number.getValue()!=null &&  cb_number.getValue().equals("普通")){
            tf_money.clear();
            tf_money.setText("10");
        }
    }

    public void cb_ChoiceRoom() throws IOException{
        if( cb_room.getValue()!=null &&  cb_room.getValue().equals("骨科")){
            cb_doctor.getItems().clear();
            cb_doctor.getItems().add("刘书涵");
            cb_doctor.getItems().add("刘武涵");
        }
        else if ( cb_room.getValue()!=null &&  cb_room.getValue().equals("儿科")){
            cb_doctor.getItems().clear();
            cb_doctor.getItems().add("王凯北");
            cb_doctor.getItems().add("王凯南");

        }
        else if ( cb_room.getValue()!=null &&  cb_room.getValue().equals("神经科")){
            cb_doctor.getItems().clear();
            cb_doctor.getItems().add("刘奕泽");
            cb_doctor.getItems().add("刘二泽");

        }
        else if ( cb_room.getValue()!=null &&  cb_room.getValue().equals("脑科")){
            cb_doctor.getItems().clear();
            cb_doctor.getItems().add("黄文聪");
            cb_doctor.getItems().add("黄文明");

        }
    }





    @FXML
    void btn_clear() throws IOException {

        tf_name.setText("");
        tf_age.setText("");
        tf_idnumber.setText("");
        tf_address.setText("");
        dp_time.setValue(null);
        tf_bgnumber.setText("");
        tf_money.setText("");
        cb_blb.setSelected(false);
        cb_sex.setValue(null);
        cb_age.setValue(null);
        cb_week.setValue(null);
        cb_day.setValue(null);
        cb_room.setValue(null);
        cb_number.setValue(null);
        cb_doctor.setValue(null);
        cb_pay.setValue(null);
    }

    @FXML
    public void btn_guahao() throws IOException {
        String name = tf_name.getText();
        String sex = cb_sex.getValue() != null ? cb_sex.getValue().toString() : "";
        String age = tf_age.getText();
        String idNumber = tf_idnumber.getText();
        String address = tf_address.getText();
        String week = cb_week.getValue() != null ? cb_week.getValue().toString() : "";
        String day = cb_day.getValue() != null ? cb_day.getValue().toString() : "";
        String time = dp_time.getValue() != null ? dp_time.getValue().toString() : "";
        String room = cb_room.getValue() != null ? cb_room.getValue().toString() : "";
        String number = cb_number.getValue() != null ? cb_number.getValue().toString() : "";
        String money = tf_money.getText();
        String pay = cb_pay.getValue() != null ? cb_pay.getValue().toString() : "";

        String condition = "未发药";
        List<Medicine> medicineList = null;
        List<Project> projectList = null;

        patientService.addPatient(name,sex,age,idNumber,address,week,day,time,room,number,money,pay,condition,medicineList,projectList);


        // 获取当前号额
        int currentNumber = Integer.parseInt(tf_alnumber.getText());

        // 号额减一，但不能小于0
        int newNumber = Math.min(currentNumber + 1, 2);

        // 更新TextField显示新的号额
        tf_alnumber.setText(String.valueOf(newNumber));
        tf_blh.setText(patientService.generateNewPatientblh());
        tf_blh.setEditable(false);

    }


}