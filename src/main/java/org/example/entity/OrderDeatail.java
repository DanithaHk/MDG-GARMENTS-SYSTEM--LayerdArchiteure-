package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDeatail {
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
