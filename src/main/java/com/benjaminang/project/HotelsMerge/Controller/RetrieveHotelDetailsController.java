package com.benjaminang.project.HotelsMerge.Controller;


import com.benjaminang.project.HotelsMerge.Dtos.DestinationIdDto;
import com.benjaminang.project.HotelsMerge.Dtos.ResponseDto;
import com.benjaminang.project.HotelsMerge.Dtos.HotelIdsDto;
import com.benjaminang.project.HotelsMerge.Service.MergeHotelDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class RetrieveHotelDetailsController {

    @Autowired
    MergeHotelDetailsService mergeHotelDetailsService;

    @PostMapping(path = "/retrieveHotelDetailsByHotelIds")
    public ResponseEntity<Object> getHotelDetailsByID(@RequestBody HotelIdsDto receivedDto) {
        List<ResponseDto> responseDtoList = mergeHotelDetailsService.retrieveHotelDetailsById(receivedDto.getIds());
        if (responseDtoList != null) {
            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error retrieving hotel details by ids", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/retrieveHotelDetailsByDestinationId")
    public ResponseEntity<Object> getHotelDetailsByDestinationID(@RequestBody DestinationIdDto receivedDto) {
        List<ResponseDto> responseDtoList = mergeHotelDetailsService.retrieveHotelDetailsByDestinationId(receivedDto.getDestinationId());
        if (responseDtoList != null) {
            return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("EError retrieving hotel details by destination id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
