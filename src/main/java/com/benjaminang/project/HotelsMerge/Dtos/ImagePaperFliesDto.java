package com.benjaminang.project.HotelsMerge.Dtos;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ImagePaperFliesDto {
    List<ImageElementDto> rooms;
    List<ImageElementDto> site;
}