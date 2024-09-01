package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
@Entity
public class FooterModel {

    @Id
    private Long id;

    private String logoUrl;

    private String companyName;
    private String inn;
    private String bankDetails;

    @ElementCollection
    private List<String> addresses;

    private String email;
    private String phone;

    @ElementCollection
    private List<String> socialNetworks;

}
