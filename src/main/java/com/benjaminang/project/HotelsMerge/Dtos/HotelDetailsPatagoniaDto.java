package com.benjaminang.project.HotelsMerge.Dtos;

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
public class HotelDetailsPatagoniaDto {
  private String id;
  private Integer destination;
  private String name;

  @JsonSetter(nulls = Nulls.SKIP)
  private BigDecimal lat;

  @JsonSetter(nulls = Nulls.SKIP)
  private BigDecimal lng;

  @JsonSetter(nulls = Nulls.SKIP)
  private String address;

  @JsonSetter(nulls = Nulls.SKIP)
  private String info;

  @JsonSetter(nulls = Nulls.SKIP)
  private List<String> amenities;

  @JsonSetter(nulls = Nulls.SKIP)
  private ImageDto images;

  public HotelDetailsPatagoniaDto() {
    this.id = "HOTEL_ID_NOT_FOUND";
    this.destination = 0;
    this.name = "NAME_NOT_FOUND";
    this.lat = BigDecimal.ZERO;
    this.lng = BigDecimal.ZERO;
    this.address = "ADDRESS_NOT_FOUND";
    this.info = "DESCRIPTION_NOT_FOUND";
    this.amenities = new ArrayList<>();
    this.images = new ImageDto(new ArrayList<>(), new ArrayList<>());
  }
}
