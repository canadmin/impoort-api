package com.impoort.impoortapi.domain.company;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int experienceId;
    private String companyName;
    private String department;
    private boolean stillWork;
    private String companyId;
    private String workerId;

}
