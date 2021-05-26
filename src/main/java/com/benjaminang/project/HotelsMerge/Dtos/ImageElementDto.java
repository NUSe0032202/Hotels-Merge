package com.benjaminang.project.HotelsMerge.Dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ImageElementDto {
    @JsonAlias({"link"})
    private String url;
    @JsonAlias({"caption"})
    private String description;
}
