package com.codegym.dto;


import com.codegym.model.entity.about_service.RentType;
import com.codegym.model.entity.about_service.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private String serviceId;
    private String serviceName;
    private Integer serviceArea;
    private Double serviceCost;
    private Integer serviceMaxPeople;
    private RentType rentType;
    private ServiceType serviceType;
    private String standardRoom;
    private String descriptionOtherConvenience;
    private Double poolArea;
    private Integer numberOfFloors;
    private boolean isDeleted;
}
