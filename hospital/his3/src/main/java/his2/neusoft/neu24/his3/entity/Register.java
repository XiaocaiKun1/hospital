package his2.neusoft.neu24.his3.entity;

import his2.neusoft.neu24.his3.entity.Medicine;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Register {
    String case_number;
    String name;
    String sex;
    String card_number;
    Long age;
    String home_address;
    Timestamp visit_date;
    String noon;
    String dept_name;
    String emp_name;
    String level_name;
    BigDecimal fee;
    String re_condition;


    public Register(String case_number, String name, String sex, String card_number, Long age, String home_address, Timestamp visit_date, String noon, String dept_name, String emp_name, String level_name, BigDecimal fee, String re_condition) {
        this.case_number = case_number;
        this.name = name;
        this.sex = sex;
        this.card_number = card_number;
        this.age = age;
        this.home_address = home_address;
        this.visit_date = visit_date;
        this.noon = noon;
        this.dept_name = dept_name;
        this.emp_name = emp_name;
        this.level_name = level_name;
        this.fee = fee;
        this.re_condition = re_condition;
    }

    public Register(String case_number, String name) {
        this.case_number = case_number;
        this.name = name;
    }

    public Register(String case_number, String name, String sex, String card_number) {
        this.case_number = case_number;
        this.name = name;
        this.sex = sex;
        this.card_number = card_number;
    }

    public Register(String case_number, String name, String sex, String card_number, Long age, String home_address) {
        this.case_number = case_number;
        this.name = name;
        this.sex = sex;
        this.card_number = card_number;
        this.age = age;
        this.home_address = home_address;
    }

    public Register() {
    }

    public Register(String caseNumber, String name, String sex, String cardNumber, Long age) {
        this.case_number = caseNumber;
        this.name = name;
        this.sex = sex;
        this.card_number = cardNumber;
        this.age = age;
    }

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public Timestamp getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Timestamp visit_date) {
        this.visit_date = visit_date;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getRe_condition() {
        return re_condition;
    }

    public void setRe_condition(String re_condition) {
        this.re_condition = re_condition;
    }
}

