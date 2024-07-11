package org.example.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryTm {
    private String sId;
    private String eId;
    private String name;
    private String date;
    private double basicSalary;
    private double bouns;
    private double total;
}
