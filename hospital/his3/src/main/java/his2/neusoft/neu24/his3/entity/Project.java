package his2.neusoft.neu24.his3.entity;

import java.math.BigDecimal;

public class Project {
    private String id;
    private String name;
    private BigDecimal fee;

    public Project(String id, String name, BigDecimal fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
    }

    public Project() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
