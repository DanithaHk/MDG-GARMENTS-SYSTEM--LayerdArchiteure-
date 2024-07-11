package org.example.bo.custom.impl;

import org.example.bo.custom.ClientBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.ClientDAO;
import org.example.dto.ClientDTO;
import org.example.dto.EmployeeDTO;
import org.example.entity.Client;
import org.example.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClentBOImpl implements ClientBO {
    ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DOATypes.CLIENT);

    @Override
    public ArrayList<ClientDTO> getClients() throws SQLException, ClassNotFoundException {
        ArrayList <ClientDTO> clientDTOS = new ArrayList<>();
        ArrayList <Client> clients = clientDAO.getAll();
        for(Client c : clients) {
            ClientDTO clientDTO = new ClientDTO(c.getId(),c.getName(),c.getAddress(),c.getContactNumber(),c.getEmail());
            clientDTOS.add((clientDTO));
        }

        return clientDTOS;
    }

    @Override
    public boolean addClient(ClientDTO dto) throws SQLException, ClassNotFoundException {
        return clientDAO.add(new Client(dto.getId(), dto.getName(), dto.getAddress(),dto.getContactNumber(), dto.getEmail()));

    }

    @Override
    public boolean updateClient(ClientDTO dto) throws SQLException, ClassNotFoundException {
        return clientDAO.update(new Client(dto.getId(), dto.getName(), dto.getAddress(), dto.getContactNumber(), dto.getEmail()));
    }

    @Override
    public boolean deleteClient(String id) throws SQLException, ClassNotFoundException {
        return clientDAO.delete(id);
    }

    @Override
    public ClientDTO searchClient(String id) throws SQLException, ClassNotFoundException {
       Client c = clientDAO.search(id);
       return new ClientDTO(c.getId(), c.getName(), c.getAddress(), c.getContactNumber(), c.getEmail());
    }

}
