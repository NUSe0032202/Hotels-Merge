package com.benjaminang.project.HotelsMerge.Dtos;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AmenitiesDto {
    private List<String> general;
    private List<String> room;
}
