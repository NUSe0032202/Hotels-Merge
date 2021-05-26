package com.benjaminang.project.HotelsMerge.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LocationDto {
    private String address;
    private String country;
}
