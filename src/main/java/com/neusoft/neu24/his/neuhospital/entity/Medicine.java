package com.neusoft.neu24.his.neuhospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Medicine {
    public String ID;
    public String name;
    public String zhujima;
    public String type;
    public String producer;
    public String price;
    public String Mcondition;

}
