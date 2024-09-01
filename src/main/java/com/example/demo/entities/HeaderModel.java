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
public class HeaderModel {

    @Id
    private Long id;

    private String logoUrl;

    private String deliveryInfo;
    private String workingHours;

    private String city;

    private String phoneNumber;
    private String callbackRequest;

    @ElementCollection
    private List<String> navMenuItems;

    @ElementCollection
    private List<String> navMenuLinks;
}
