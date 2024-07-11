package org.example.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {

    private String oId;
    private String cId;
    private String cNumber;
    private String pId;
    private String pName;
    private double unitPrice;
    private int qty;
    private String date;
    private double total;
    private String materialQtyTotal;
}
