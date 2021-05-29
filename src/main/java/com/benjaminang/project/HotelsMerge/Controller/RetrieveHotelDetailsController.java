package com.benjaminang.project.HotelsMerge.Controller;


import com.benjaminang.project.HotelsMerge.Dtos.HotelDetailsPaperFliesDto;
import com.benjaminang.project.HotelsMerge.Dtos.ResponseDto;
import com.benjaminang.project.HotelsMerge.Dtos.tempDto;
import com.benjaminang.project.HotelsMerge.Service.MergeHotelDetailsService;
import com.benjaminang.project.HotelsMerge.Service.RetrieveHotelDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RetrieveHotelDetailsController {

   /* @Autowired
    private RetrieveHotelDetailsService retrieveHotelDetailsService;

    @GetMapping(path = "/testRetrieval")
    public ResponseEntity<Object> testRetrieval() {
        List<HotelDetailsPaperFliesDto> hotelDetailsAcmeDtoList = Arrays.asList(retrieveHotelDetailsService.getHotelDetailsFromEndPoint_3());
        if (hotelDetailsAcmeDtoList != null) {
            return new ResponseEntity<>(hotelDetailsAcmeDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error retrieving data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @Autowired
    MergeHotelDetailsService mergeHotelDetailsService;

    @PostMapping(path = "/testMerge")
    public ResponseEntity<Object> getHotelDetailsByID(@RequestBody tempDto receiveDto) {
        System.out.println("Received id: " + receiveDto.getId());

        ResponseDto responseDto = mergeHotelDetailsService.retrieveHotelDetailsById(receiveDto.getId());
        if (responseDto != null) {
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error retrieving data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void getHotelDetailsByDestinationID() {
    }


}
