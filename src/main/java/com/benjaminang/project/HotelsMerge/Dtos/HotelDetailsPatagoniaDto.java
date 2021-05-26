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
public class HotelDetailsPatagoniaDto {
    private String id;
    private Integer destination;
    private String name;
    private BigDecimal lat;
    private BigDecimal lng;
    private String address;
    private String info;
    private List<String> amenities;
    private ImageDto images;
}
