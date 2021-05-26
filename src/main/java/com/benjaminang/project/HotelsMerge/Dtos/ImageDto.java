package com.benjaminang.project.HotelsMerge.Dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ImageDto {
    List<ImageElementDto> rooms;
    @JsonAlias({"site"})
    List<ImageElementDto> amenities;
}
