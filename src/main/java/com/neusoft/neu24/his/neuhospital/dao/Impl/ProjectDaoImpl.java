package com.neusoft.neu24.his.neuhospital.dao.Impl;

import com.neusoft.neu24.his.neuhospital.dao.IProjectDao;
import com.neusoft.neu24.his.neuhospital.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements IProjectDao {

   public static final List<Project> PROJECT_LIST = new ArrayList<>();
    public static final List<Project> PROJECT_LIST1 = new ArrayList<>();
   public static final List<Project> PROJECT_LIST2 = new ArrayList<>();


    static {
        PROJECT_LIST.add(new Project("JSDMZYS","脊椎动脉造影术","江北","31.08","检查","脑科"));
        PROJECT_LIST.add(new Project("CTCX","CT成像","江北","188.88","检查","脑科"));
        PROJECT_LIST1.add(new Project("DNFYBWQ","大脑发育不完全","江北","31.08","检查","神经科"));
        PROJECT_LIST1.add(new Project("XNWQBFY","小脑完全不发育","江北","188.88","检查","神经科"));
        PROJECT_LIST2.add(new Project("JSDMZYS","最1","江北","31.08","检查","内科"));
        PROJECT_LIST2.add(new Project("CTCX","最2","江北","188.88","检查","内科"));
    }

    @Override
    public List<Project> selectAllProject() {
        return PROJECT_LIST;
    }
}
