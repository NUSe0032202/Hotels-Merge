package com.benjaminang.project.HotelsMerge.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
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
  @JsonSetter(nulls = Nulls.SKIP)
  private BigDecimal latitude;

  @JsonProperty("Longitude")
  @JsonSetter(nulls = Nulls.SKIP)
  private BigDecimal longitude;

  @JsonProperty("Address")
  @JsonSetter(nulls = Nulls.SKIP)
  private String address;

  @JsonProperty("City")
  @JsonSetter(nulls = Nulls.SKIP)
  private String city;

  @JsonProperty("Country")
  @JsonSetter(nulls = Nulls.SKIP)
  private String country;

  @JsonProperty("PostalCode")
  @JsonSetter(nulls = Nulls.SKIP)
  private String postalCode;

  @JsonProperty("Description")
  @JsonSetter(nulls = Nulls.SKIP)
  private String description;

  @JsonProperty("Facilities")
  @JsonSetter(nulls = Nulls.SKIP)
  private List<String> facilities;

  public HotelDetailsAcmeDto() {
    this.id = "HOTEL_ID_NOT_FOUND";
    this.destinationId = 0;
    this.name = "NAME_NOT_FOUND";
    this.latitude = BigDecimal.ZERO;
    this.longitude = BigDecimal.ZERO;
    this.address = "ADDRESS_NOT_FOUND";
    this.city = "CITY_NOT_FOUND";
    this.country = "COUNTRY_NOT_FOUND";
    this.postalCode = "Postal Code Not Found";
    this.description = "DESCRIPTION_NOT_FOUND";
    this.facilities = new ArrayList<>();
  }
}
