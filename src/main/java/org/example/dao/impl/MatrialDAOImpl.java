package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.MaterialDAO;
import org.example.entity.Employee;
import org.example.entity.Material;
import org.example.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatrialDAOImpl implements MaterialDAO {
    @Override
    public ArrayList<Material> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Material> materials = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM material");
        while (rst.next()) {
            Material material = new Material(rst.getString("mid"), rst.getString("description"), rst.getInt("qty"), rst.getDouble("costPerOne"),rst.getString("username") );

            materials.add(material);

        }
        return materials ;

    }

    @Override
    public boolean add(Material entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO material (mid,description, qty, costPerOne ,username) VALUES (?,?,?,?,?,?)", entity.getId(), entity.getDescription(), entity.getQty(),entity.getCostPerOne(),entity.getUsername());

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
    public Material search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * from material where mid =?", id+"");
        resultSet.next();
        return new Material(id +"",resultSet.getString("description"),resultSet.getInt("qty"),resultSet.getDouble("costPerOne"),resultSet.getString("username"));
//
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM material WHERE mid=?", id);
    }

    @Override
    public boolean update(Material entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE material SET description = ?,qty =?,costPerOne = ?,username =? WHERE mid = ?"  , entity.getDescription(),entity.getQty(),entity.getCostPerOne(),entity.getUsername(),entity.getId());

    }
}
