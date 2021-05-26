package com.benjaminang.project.HotelsMerge.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class HotelDetailsAcmeDto {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("DestinationId")
    private Integer destinationId;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Latitude")
    private BigDecimal latitude;
    @JsonProperty("Longitude")
    private BigDecimal longitude;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("City")
    private String city;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("PostalCode")
    private String postalCode;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Facilities")
    private List<String> facilities;
}
