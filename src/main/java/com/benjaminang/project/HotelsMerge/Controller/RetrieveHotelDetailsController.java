package com.benjaminang.project.HotelsMerge.Controller;


import com.benjaminang.project.HotelsMerge.Dtos.HotelDetailsPaperFliesDto;
import com.benjaminang.project.HotelsMerge.Service.RetrieveHotelDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RetrieveHotelDetailsController {

    @Autowired
    private RetrieveHotelDetailsService retrieveHotelDetailsService;

    @GetMapping(path = "/testRetrieval")
    public ResponseEntity<Object> testRetrieval() {
        List<HotelDetailsPaperFliesDto> hotelDetailsAcmeDtoList = Arrays.asList(retrieveHotelDetailsService.getHotelDetailsFromEndPoint_3());
        if (hotelDetailsAcmeDtoList != null) {
            return new ResponseEntity<>(hotelDetailsAcmeDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error retrieving data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void getHotelDetailsByID() {
    }

    public void getHotelDetailsByDestinationID() {
    }


}
