package com.neusoft.neu24.his.neuhospital.service.impl;

import com.neusoft.neu24.his.neuhospital.dao.IProjectDao;
import com.neusoft.neu24.his.neuhospital.dao.Impl.ProjectDaoImpl;
import com.neusoft.neu24.his.neuhospital.entity.Project;
import com.neusoft.neu24.his.neuhospital.service.IProjectService;

import java.util.List;

public class ProjectServiceImpl implements IProjectService {

    private IProjectDao ProjectDao=  new ProjectDaoImpl();
    @Override
    public List<Project> selectAllProject()
    {
        return ProjectDao.selectAllProject();
    }

}
