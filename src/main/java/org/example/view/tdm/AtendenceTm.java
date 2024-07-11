package org.example.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AtendenceTm {
    private String id;
    private String name;
    private String date;
    private int presentOrNot;
    private String employeeid;
}
