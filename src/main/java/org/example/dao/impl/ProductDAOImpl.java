package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.ProductDAO;
import org.example.dto.AtendenctDTO;
import org.example.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Product> allProducts = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM product");
        while (rst.next()) {
            Product product = new Product(rst.getString("pid"), rst.getString("description"), rst.getDouble("costPerOneShirt"));

            allProducts.add(product);

        }
        return allProducts ;
    }

    @Override
    public boolean add(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO product (pid,description,costPerOneShirt) VALUES (?,?,?)", entity.getId(), entity.getDescription(), entity.getCostPerOne());
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT pid FROM product WHERE pid=?", id);
        return rst.next();
    }

    @Override
    public List<String> getIds() {
        return null;
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM product WHERE pid=?",id+"");
        rst.next();
        return new Product(id + "", rst.getString("description"),rst.getDouble("costPerOneShirt"));
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM product WHERE pid=?", id);
    }

    @Override
    public boolean update(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE product SET description=?, costPerOneShirt=? WHERE pid=? ", entity.getDescription(),entity.getCostPerOne(),entity.getId());
    }
}
