package com.benjaminang.project.HotelsMerge.Service;

import com.benjaminang.project.HotelsMerge.Dtos.HotelDetailsAcmeDto;
import com.benjaminang.project.HotelsMerge.Dtos.HotelDetailsPaperFliesDto;
import com.benjaminang.project.HotelsMerge.Dtos.HotelDetailsPatagoniaDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RetrieveHotelDetailsService {

    private static final String BASE_URL = "https://5f2be0b4ffc88500167b85a0.mockapi.io/suppliers";

    private static final String ENDPOINT_1 = "/acme";

    private static final String ENDPOINT_2 = "/patagonia";

    private static final String ENDPOINT_3 = "/paperflies";

    private WebClient webClient;

    public RetrieveHotelDetailsService() {
        this.webClient = WebClient.builder().baseUrl(BASE_URL).build();
    }

    public HotelDetailsPatagoniaDto[] getHotelDetailsFromEndPoint_2() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(ENDPOINT_2).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HotelDetailsPatagoniaDto[].class)
                .block();
    }

    public HotelDetailsAcmeDto[] getHotelDetailsFromEndPoint_1() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(ENDPOINT_1).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HotelDetailsAcmeDto[].class)
                .block();
    }

    public HotelDetailsPaperFliesDto[] getHotelDetailsFromEndPoint_3() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(ENDPOINT_3).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(HotelDetailsPaperFliesDto[].class)
                .block();
    }


}
