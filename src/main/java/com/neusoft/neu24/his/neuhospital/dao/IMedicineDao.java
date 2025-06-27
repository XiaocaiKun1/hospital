package com.neusoft.neu24.his.neuhospital.dao;

import com.neusoft.neu24.his.neuhospital.entity.Medicine;

import java.util.List;

public interface IMedicineDao {
    List<Medicine> selectAllMedicine();
    List<Medicine> selectMedicineByID(String ID);
    List<Medicine> selectMedicineByZhujima(String zhujima);
    List<Medicine> selectMedicineByName(String name);

}
