package com.benjaminang.project.HotelsMerge.Dtos;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseLocationDto {
    private BigDecimal lat;
    private BigDecimal lng;
    private String address;
    private String city;
    private String country;
}
