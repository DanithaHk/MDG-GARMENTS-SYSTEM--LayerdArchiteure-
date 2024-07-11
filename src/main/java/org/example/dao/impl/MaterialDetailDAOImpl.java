package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.MaterialDetailDAO;
import org.example.entity.Material;
import org.example.entity.MaterialDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDetailDAOImpl implements MaterialDetailDAO {
    @Override
    public ArrayList<MaterialDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(MaterialDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO material_detail (oid,description, mid, qty ) VALUES (?,?,?,?)", entity.getOId(), entity.getDesc(), entity.getMId(),entity.getMaterialQty());

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public MaterialDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(MaterialDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public MaterialDetail findMaterial(String nededSwingCloth) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM material_detail WHERE description=?",nededSwingCloth+"");
        rst.next();
        return new MaterialDetail(rst.getString("oid"),rst.getString("description"),rst.getString("mid"),rst.getInt("qty"));
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        return null;
    }
}
