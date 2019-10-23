package com.impoort.impoortapi.domain.company;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int companyId;
    private String companyName;
    private String department;

}
