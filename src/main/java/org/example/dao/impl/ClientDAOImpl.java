package org.example.dao.impl;

import org.example.dao.SQLUtil;
import org.example.dao.custom.ClientDAO;
import org.example.dto.AtendenctDTO;
import org.example.dto.ClientDTO;
import org.example.entity.Client;
import org.example.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public ArrayList<Client> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Client> allClients = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM client");
        while (rst.next()) {
            Client client = new Client(rst.getString("cid"), rst.getString("name"), rst.getString("address"),rst.getString("contactNumber"),rst.getString("email"));

            allClients.add(client);

        }
        return allClients;
    }

    @Override
    public boolean add(Client entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO client (cid,name, address, contactNumber, email) VALUES (?,?,?,?,?)", entity.getId(), entity.getName(), entity.getAddress(),entity.getContactNumber(),entity.getEmail());
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
    public Client search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM client WHERE cid=?",id+"");
        rst.next();
        return new Client(id + "", rst.getString("name"),rst.getString("address"),rst.getString("contactNumber"),rst.getString("email"));
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM client WHERE cid=?", id);
    }

    @Override
    public boolean update(Client entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE client SET name=?, address=?, contactNumber=?, email=? WHERE cid=? ", entity.getName(),entity.getAddress(),entity.getContactNumber(), entity.getEmail(),entity.getId());
    }

}
