package his2.neusoft.neu24.his3.entity;

import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private Medicine medicine;
    private String number;
    private String condition;

    public Prescription(Medicine medicine, String number, String condition) {
        this.medicine = medicine;
        this.number = number;
        this.condition = condition;
    }

    public Prescription() {
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
