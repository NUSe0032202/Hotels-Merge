package com.benjaminang.project.HotelsMerge.Dtos;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseDto {
    private String id;
    private Integer destination_id;
    private String name;
    private ResponseLocationDto location;
    private String description;
    private AmenitiesDto amenities;
    private ResponseImagesDto images;
    private List<String> booking_conditions;
}
