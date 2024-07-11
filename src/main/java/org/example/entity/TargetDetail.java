package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetDetail {
    private String id;
    private String name;
    private String date;
    private String tCoverOrNot;
    private String eid;
}
