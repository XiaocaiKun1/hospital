package his2.neusoft.neu24.his3.entity;

import java.math.BigDecimal;

public class projects {
    private String name;
    private String id;
    private int age;
    private String project_id;
    private String sex;
    private String result;
    private String project_name;
    private BigDecimal fee;

    public projects(String name, String id, int age, String sex, String result, String project_name, BigDecimal fee, String project_id) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.result = result;
        this.project_name = project_name;
        this.fee = fee;
        this.project_id = project_id;
    }

    public projects(String name, String id, int age, String sex) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.sex = sex;
    }

    public projects() {
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}

