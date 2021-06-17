package com.benjaminang.project.HotelsMerge;

import com.benjaminang.project.HotelsMerge.Dtos.DestinationIdDto;
import com.benjaminang.project.HotelsMerge.Dtos.HotelIdsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RetrieveHotelDetailsIntegrationTestsIT {

  @Autowired private WebTestClient webTestClient;

  @Test
  public void testRetrieveHotelDetails_ById() {
    HotelIdsDto hotelIdsDto = HotelIdsDto.builder().ids(Arrays.asList("iJhz")).build();
    this.webTestClient
        .post()
        .uri("/retrieveHotelDetailsByHotelIds")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(hotelIdsDto), HotelIdsDto.class)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody()
        .jsonPath("$.[0].name")
        .isEqualTo("Beach Villas Singapore")
        .jsonPath("$.[0].location.address")
        .isEqualTo("8 Sentosa Gateway, Beach Villas, 098269");
  }

  @Test
  public void testRetrieveHotelDetails_ByDestinationId() {
    DestinationIdDto destinationIdDto = DestinationIdDto.builder().destinationId(5432).build();
    this.webTestClient
        .post()
        .uri("/retrieveHotelDetailsByDestinationId")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(destinationIdDto), DestinationIdDto.class)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody()
        .jsonPath("$.[?(@.name=='Beach Villas Singapore')]")
        .exists()
        .jsonPath("$.[?(@.location.address=='8 Sentosa Gateway, Beach Villas, 098269')]")
        .exists();
  }
}
