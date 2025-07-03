package his2.neusoft.neu24.his3.entity;

public class MedicalRecord {
    private String readme;
    private String present;

    public MedicalRecord(String readme, String present) {
        this.readme = readme;
        this.present = present;
    }

    public String getReadme() {
        return readme;
    }

    public String getPresent() {
        return present;
    }
}
