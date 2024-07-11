package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.ClientDTO;
import org.example.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientBO extends SuperBO {


    public ArrayList<ClientDTO> getClients() throws SQLException, ClassNotFoundException;
    public boolean addClient(ClientDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateClient(ClientDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteClient(String id) throws SQLException, ClassNotFoundException;

    ClientDTO searchClient(String id) throws SQLException, ClassNotFoundException;
}
