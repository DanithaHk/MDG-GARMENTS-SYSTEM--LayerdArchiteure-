package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class
OrderDetailDTO implements Serializable {
    private String oid;
    private String cid;
    private String cNumber;
    private String pid;
    private String pName;
    private double unitPrice;
    private int qty;
    private String date;
    private String nededSwingCloth;
    private double total;
}
