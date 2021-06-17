package com.benjaminang.project.HotelsMerge.Service;

import com.benjaminang.project.HotelsMerge.Dtos.HotelDetailsPatagoniaDto;
import com.benjaminang.project.HotelsMerge.Exceptions.APIEndpointException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class RetrieveHotelDetailsServiceTest {

  private WebClient.Builder webClientBuilder = mock(WebClient.Builder.class);
  private WebClient webClient = mock(WebClient.class);

  private WebClient.RequestHeadersSpec requestHeadersSpec =
      mock(WebClient.RequestHeadersSpec.class);
  private WebClient.RequestHeadersUriSpec requestHeadersUriSpec =
      mock(WebClient.RequestHeadersUriSpec.class);
  private WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

  private RetrieveHotelDetailsService retrieveHotelDetailsService;

  @BeforeEach
  public void setUp() {
    when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
    when(webClientBuilder.build()).thenReturn(webClient);
    when(webClient.get()).thenReturn(requestHeadersUriSpec);

    when(requestHeadersUriSpec.uri((Function<UriBuilder, URI>) any()))
        .thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.accept(any())).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

    retrieveHotelDetailsService = new RetrieveHotelDetailsService();
    ReflectionTestUtils.setField(retrieveHotelDetailsService, "webClient", webClient);
  }

  @Test
  public void
      Given_Retrieve_Hotel_Details_When_GetHotelDetailsFromEndPoint2_Then_ShouldReturnData() {
    HotelDetailsPatagoniaDto hotelDetailsPatagoniaDto =
        HotelDetailsPatagoniaDto.builder()
            .id("ABCDE")
            .destination(12345)
            .name("Test Hotel")
            .build();
    HotelDetailsPatagoniaDto[] hotelDetailsPatagoniaDtos =
        new HotelDetailsPatagoniaDto[] {hotelDetailsPatagoniaDto};
    when(responseSpec.bodyToMono(HotelDetailsPatagoniaDto[].class))
        .thenReturn(Mono.just(hotelDetailsPatagoniaDtos));

    HotelDetailsPatagoniaDto[] retrievedHotelDetailsPatagoniaDtos =
        retrieveHotelDetailsService.getHotelDetailsFromEndPoint_2();
    assertThat(retrievedHotelDetailsPatagoniaDtos).hasSize(1);
    assertThat(retrievedHotelDetailsPatagoniaDtos[0].getId()).isEqualTo("ABCDE");
    assertThat(retrievedHotelDetailsPatagoniaDtos[0].getName()).isEqualTo("Test Hotel");
  }

  @Test
  public void
      Given_WebClientResponseException_Is_Returned_When_GetHotelDetailsFromEndPoint2_Then_ShouldThrowError() {
    when(responseSpec.bodyToMono(HotelDetailsPatagoniaDto[].class))
        .thenThrow(WebClientResponseException.create(404, "Not Found", null, null, null, null));
    assertThatThrownBy(() -> retrieveHotelDetailsService.getHotelDetailsFromEndPoint_2())
        .isInstanceOf(APIEndpointException.class)
        .hasMessageContaining("Error encountered while attempting to retrieve data from endpoint");
  }
}
