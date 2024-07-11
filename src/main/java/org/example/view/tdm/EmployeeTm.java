package org.example.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTm implements  Comparable<EmployeeTm> {
    private String id;
    private String name;
    private String address;
    private String contactNumber;
    private String jobRole;
    private String username;
    @Override
    public int compareTo(EmployeeTm o) {
        return id.compareTo(o.getId());
    }
}
