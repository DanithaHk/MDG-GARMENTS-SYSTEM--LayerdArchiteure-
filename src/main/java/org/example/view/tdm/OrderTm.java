package org.example.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderTm {
    private String oId;
    private String name;
    private String date;
    private int qty;
    private String cId;


}
