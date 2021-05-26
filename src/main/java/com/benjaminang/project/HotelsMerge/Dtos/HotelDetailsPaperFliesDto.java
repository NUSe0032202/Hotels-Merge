package com.benjaminang.project.HotelsMerge.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class HotelDetailsPaperFliesDto {
    @JsonProperty("hotel_id")
    private String hotelId;
    @JsonProperty("destination_id")
    private Integer destinationId;
    @JsonProperty("hotel_name")
    private String hotelName;
    private LocationDto location;
    private String details;
    private AmenitiesDto amenities;
    private ImageDto images;
    @JsonProperty("booking_conditions")
    private List<String> bookingConditions;
}
