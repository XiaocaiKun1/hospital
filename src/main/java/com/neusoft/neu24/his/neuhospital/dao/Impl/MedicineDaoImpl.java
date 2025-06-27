package com.neusoft.neu24.his.neuhospital.dao.Impl;


import com.neusoft.neu24.his.neuhospital.dao.IMedicineDao;
import com.neusoft.neu24.his.neuhospital.entity.Medicine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicineDaoImpl implements IMedicineDao {

    public final static List<Medicine> MEDICINE_LIST =new ArrayList<>();
    public final static List<Medicine> MEDICINE_LIST1 =new ArrayList<>();
    public final static List<Medicine> MEDICINE_LIST2 =new ArrayList<>();
    public final static List<Medicine> MEDICINE_LIST3 =new ArrayList<>();


    static {
        MEDICINE_LIST.add(new Medicine("NEUM001","注射用甲氨喋呤","ZSYJADL","1g*1支","江苏恒瑞医药股份有限公司","15.73","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM002","氟康挫氯化钠注射液","FKCLHNZSY","200mg*100ml/瓶","辉瑞制药有限公司","7.01","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM003","50%葡萄糖注射液","PTTZSY","10:20ml*1支","中国大冢制药有限公司","25.16","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM004","盐酸特比蔡芬阴道泡腾片","YSTBCFYDPTP","50mg*7片/盒","齐鲁制药有限公司","40.62","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM005","红芮","HR","50mb*7片/盒","北京红瑞制药有限公司","15.73","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM006","盐酸氨酮戊酸散 (外用）","YSATWSSWY","118mg*1瓶","上海复且张江生物医药股份有限公司","19.51","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM007","盐酸美金刚片(易贝申)","YSMJGPYBS","10mg*28片/盒","丹麦灵北药厂","22.05","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM008","磷酸臭司他韦胶囊(达菲)","LSCSTWJNDF","75mg*10粒/盒","盒上海罗氏制药有限公司","60.96","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM009","利妥昔片","LTXP","10mg*10片/盒","上海利妥昔制药有限公司","20.01","未发"));
        MEDICINE_LIST.add(new Medicine("NEUM010","泽泻颗粒","zxkl","1g/10g/袋","江阴天江药业有限公司","0.09","未发"));




        //MEDICINE_LIST.add(new Medicine());


    }






    @Override
    public List<Medicine> selectAllMedicine() {
        return MEDICINE_LIST;
    }

    @Override
    public List<Medicine> selectMedicineByID(String ID) {
        return MEDICINE_LIST.stream().
                filter(patient -> patient.getID().indexOf(ID)> -1)
                .collect(Collectors.toList());

    }

    @Override
    public List<Medicine> selectMedicineByZhujima(String zhujima) {
        return MEDICINE_LIST.stream().
                filter(patient -> patient.getZhujima().indexOf(zhujima)> -1)
                .collect(Collectors.toList());

    }

    @Override
    public List<Medicine> selectMedicineByName(String name) {
        return MEDICINE_LIST.stream().
                filter(patient -> patient.getName().indexOf(name)> -1)
                .collect(Collectors.toList());

    }
}
