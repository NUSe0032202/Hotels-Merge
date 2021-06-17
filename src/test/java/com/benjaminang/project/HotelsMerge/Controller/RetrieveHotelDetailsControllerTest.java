package com.benjaminang.project.HotelsMerge.Controller;

import com.benjaminang.project.HotelsMerge.Dtos.HotelIdsDto;
import com.benjaminang.project.HotelsMerge.Dtos.ResponseDto;
import com.benjaminang.project.HotelsMerge.Dtos.ResponseLocationDto;
import com.benjaminang.project.HotelsMerge.Service.MergeHotelDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RetrieveHotelDetailsController.class})
@ExtendWith(SpringExtension.class)
public class RetrieveHotelDetailsControllerTest {

  @Autowired private MockMvc mvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private MergeHotelDetailsService mergeHotelDetailsService;

  @Test
  public void Given_HotelIDsExist_When_GetHotelDetailsByID_Then_ReturnOk() throws Exception {
    ResponseLocationDto locationDto =
        ResponseLocationDto.builder()
            .address("123 Clementi Ave")
            .city("Singapore")
            .country("Singapore")
            .lat(new BigDecimal(1.264751))
            .lng(new BigDecimal(103.824006))
            .build();
    ResponseDto response =
        ResponseDto.builder()
            .id("ABCD")
            .destination_id(12345)
            .name("ABC Hotel")
            .location(locationDto)
            .description("Hotel Description")
            .amenities(null)
            .images(null)
            .booking_conditions(Arrays.asList("Booking Condition 1", "Booking Condition 2"))
            .build();
    List<ResponseDto> responseDtoList = new ArrayList<>();
    responseDtoList.add(response);
    when(mergeHotelDetailsService.retrieveHotelDetailsById(any())).thenReturn(responseDtoList);
    HotelIdsDto hotelIdsDto = HotelIdsDto.builder().ids(Arrays.asList("ABCD")).build();

    mvc.perform(
            post("/retrieveHotelDetailsByHotelIds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(hotelIdsDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", isA(ArrayList.class)))
        .andExpect(jsonPath("$.*", hasSize(1)))
        .andExpect(jsonPath("$.[0].id", is("ABCD")))
        .andExpect(jsonPath("$.[0].name", is("ABC Hotel")));
  }
}
