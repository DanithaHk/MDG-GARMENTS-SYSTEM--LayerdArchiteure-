package org.example.dao;

import org.example.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public  enum DOATypes {
        CLIENT, PRODUCT, TARGET, ATENDENCE, MATERIAL, SALARY, TARGETDETAIL, ORDER, ORDERDEATAIL, MATERIALDEATIL, EMPLOYEE
    }
public SuperDAO getDAO(DOATypes doaTypes) {
    switch (doaTypes) {
        case EMPLOYEE:
            return new EmployeeDAOImpl();
        case CLIENT:
            return new ClientDAOImpl();
        case PRODUCT:
            return new ProductDAOImpl();
        case TARGET:
            return new TargetDAOImpl();
        case ATENDENCE:
            return new AtendenceDAOImpl();
        case MATERIAL:
            return new MatrialDAOImpl();
        case SALARY:
            return new SalaryDAOImpl();
        case TARGETDETAIL:
            return new TargetDeatailDAOImpl();
        case ORDER:
            return new OrderDAOImpl();
        case ORDERDEATAIL:
            return new OrderDeatilsDAOImpl();
        case MATERIALDEATIL:
            return new MaterialDetailDAOImpl();
        default:
            return null;
    }
}
}
