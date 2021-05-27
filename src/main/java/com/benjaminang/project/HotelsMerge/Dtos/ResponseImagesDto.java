package com.benjaminang.project.HotelsMerge.Dtos;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseImagesDto {
    List<ImageElementDto> rooms;
    List<ImageElementDto> site;
    List<ImageElementDto> amenities;
}
