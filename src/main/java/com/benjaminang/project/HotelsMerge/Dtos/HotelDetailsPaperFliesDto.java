package com.benjaminang.project.HotelsMerge.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Builder
public class HotelDetailsPaperFliesDto {
  @JsonProperty("hotel_id")
  private String hotelId;

  @JsonProperty("destination_id")
  private Integer destinationId;

  @JsonProperty("hotel_name")
  private String hotelName;

  @JsonSetter(nulls = Nulls.SKIP)
  private LocationDto location;

  @JsonSetter(nulls = Nulls.SKIP)
  private String details;

  @JsonSetter(nulls = Nulls.SKIP)
  private AmenitiesDto amenities;

  @JsonSetter(nulls = Nulls.SKIP)
  private ImagePaperFliesDto images;

  @JsonProperty("booking_conditions")
  @JsonSetter(nulls = Nulls.SKIP)
  private List<String> bookingConditions;

  public HotelDetailsPaperFliesDto() {
    this.hotelId = "HOTEL_ID_NOT_FOUND";
    this.destinationId = 0;
    this.hotelName = "NAME_NOT_FOUND";
    this.location = new LocationDto("ADDRESS_NOT_FOUND", "COUNTRY_NOT_FOUND");
    this.details = "DESCRIPTION_NOT_FOUND";
    this.amenities = new AmenitiesDto(new ArrayList<>(), new ArrayList<>());
    this.images = new ImagePaperFliesDto(new ArrayList<>(), new ArrayList<>());
    this.bookingConditions = new ArrayList<>();
  }
}
