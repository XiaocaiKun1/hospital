package his2.neusoft.neu24.his3.entity;

public class Medicine {
    private String ID;
    private String drug_name;
    private String mnemonic_code;
    private String drug_type;
    private String manufacturer;
    private String drug_price;
    private String drug_code;
    private String drug_condition;



    public Medicine(String ID, String drug_name, String mnemonic_code, String drug_type, String manufacturer, String drug_price) {
        this.ID = ID;
        this.drug_name = drug_name;
        this.mnemonic_code = mnemonic_code;
        this.drug_type = drug_type;
        this.manufacturer = manufacturer;
        this.drug_price = drug_price;
    }

    public Medicine(String ID, String drug_name, String drug_type, String manufacturer, String drug_price, String drug_code, String drug_condition) {
        this.ID = ID;
        this.drug_name = drug_name;
        this.drug_type = drug_type;
        this.manufacturer = manufacturer;
        this.drug_price = drug_price;
        this.drug_code = drug_code;
        this.drug_condition = drug_condition;
    }

    public Medicine() {
    }

    public String getDrug_code() {
        return drug_code;
    }

    public void setDrug_code(String drug_code) {
        this.drug_code = drug_code;
    }

    public String getDrug_condition() {
        return drug_condition;
    }

    public void setDrug_condition(String drug_condition) {
        this.drug_condition = drug_condition;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getMnemonic_code() {
        return mnemonic_code;
    }

    public void setMnemonic_code(String mnemonic_code) {
        this.mnemonic_code = mnemonic_code;
    }

    public String getDrug_type() {
        return drug_type;
    }

    public void setDrug_type(String drug_type) {
        this.drug_type = drug_type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDrug_price() {
        return drug_price;
    }

    public void setDrug_price(String drug_price) {
        this.drug_price = drug_price;
    }
}