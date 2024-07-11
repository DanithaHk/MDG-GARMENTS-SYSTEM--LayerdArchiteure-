package org.example.bo.custom;


import org.example.bo.SuperBO;
import org.example.bo.custom.impl.*;
//import org.example.bo.custom.impl.ClientBOImpl;


public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CLIENT, PRODUCT, TARGET, ATENDENCE, MATERIAL, SALARY, TARGETDETAIL, AOD, ORDER, EMPLOYEE
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case CLIENT:
                return new ClentBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case TARGET:
                return new TargetBOImpl();
            case ATENDENCE:
                return new AtendenceBOImpl();
            case MATERIAL:
                return new MaterialBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case TARGETDETAIL:
                return new TargetDetailBOImpl();
            case AOD:
                return new AddOrderBOImpl();
            case ORDER:
                return new OrderBOImpl();

            default:
                return null;
        }
    }

}
