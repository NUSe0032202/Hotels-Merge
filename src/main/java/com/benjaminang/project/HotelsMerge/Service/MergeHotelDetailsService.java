package com.benjaminang.project.HotelsMerge.Service;

import com.benjaminang.project.HotelsMerge.Dtos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Service
public class MergeHotelDetailsService implements CommandLineRunner {
    @Autowired
    private RetrieveHotelDetailsService retrieveHotelDetailsService;
    private Set<String> hotelIdKeys = new HashSet<>();
    private ArrayList<String> objKeys = new ArrayList<String>(
            Arrays.asList("id", "destination_id", "name", "location", "description", "amenities", "images", "booking_conditions")
    );
    private HashMap<String, HotelDetailsAcmeDto> hotelDetailsAcmeDtoHashMap = new HashMap<String, HotelDetailsAcmeDto>();
    private HashMap<String, HotelDetailsPaperFliesDto> hotelDetailsPaperFliesDtoHashMap = new HashMap<String, HotelDetailsPaperFliesDto>();
    private HashMap<String, HotelDetailsPatagoniaDto> hotelDetailsPatagoniaDtoHashMap = new HashMap<String, HotelDetailsPatagoniaDto>();
    private HashMap<String, ResponseDto> mergedHotelDetailsMap = new HashMap<>();
    private HashMap<Integer, List<String>> destinationIdToHotelIdMap = new HashMap<>();

    public void mergeHotelDetails() {

        log.info("Attempting to retrieve hotel data from specified endpoints");
        this.retrieveHotelDetails();

        for (String hotelId : hotelIdKeys) {
            log.info("Attempting data merge for hotel id: " + hotelId);
            ResponseDto responseDto = new ResponseDto();
            for (String objKeys : objKeys) {
                switch (objKeys) {

                    case "id":
                        responseDto.setId(hotelId);
                        break;

                    case "name":
                        String hotelName = "NAME_NOT_FOUND";
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId)) {
                            hotelName = hotelDetailsAcmeDtoHashMap.get(hotelId).getName();
                        }
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId) && hotelName.equals("NAME_NOT_FOUND")) {
                            hotelName = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getHotelName();
                        }
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId) && hotelName.equals("NAME_NOT_FOUND")) {
                            hotelName = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getName();
                        }
                        responseDto.setName(hotelName);
                        log.info("Merge: Hotel Name: " + responseDto.toString());
                        break;

                    case "destination_id":
                        int destinationId = 0;
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId)) {
                            destinationId = hotelDetailsAcmeDtoHashMap.get(hotelId).getDestinationId();
                        }
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId) && (destinationId == 0)) {
                            destinationId = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getDestinationId();
                        }
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId) && (destinationId == 0)) {
                            destinationId = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getDestination();
                        }
                        responseDto.setDestination_id(destinationId);
                        log.info("Merge: Destination Id: " + responseDto.toString());
                        break;

                    case "booking_conditions":
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId)) {
                            responseDto.setBooking_conditions(hotelDetailsPaperFliesDtoHashMap.get(hotelId).getBookingConditions());
                        }
                        log.info("Merge: Booking Condition: " + responseDto.toString());
                        break;

                    case "location":
                        BigDecimal latitude = BigDecimal.ZERO;
                        BigDecimal longitude = BigDecimal.ZERO;
                        ResponseLocationDto locationDto = new ResponseLocationDto();
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId)) {
                            latitude = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getLat();
                            longitude = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getLng();
                        }
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId) && (latitude == BigDecimal.ZERO || longitude == BigDecimal.ZERO)) {
                            latitude = hotelDetailsAcmeDtoHashMap.get(hotelId).getLatitude();
                            longitude = hotelDetailsAcmeDtoHashMap.get(hotelId).getLongitude();
                        }
                        locationDto.setLng(longitude);
                        locationDto.setLat(latitude);

                        String address = "ADDRESS_NOT_FOUND";
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId)) {
                            address = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getLocation().getAddress();
                        }
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId) && address.equals("ADDRESS_NOT_FOUND")) {
                            address = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getAddress();
                        }
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId) && address.equals("ADDRESS_NOT_FOUND")) {
                            address = hotelDetailsAcmeDtoHashMap.get(hotelId).getAddress();
                        }
                        locationDto.setAddress(address);

                        String city = "CITY_NOT_FOUND";
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId)) {
                            city = hotelDetailsAcmeDtoHashMap.get(hotelId).getCity();
                        }
                        locationDto.setCity(city);

                        String country = "COUNTRY_NOT_FOUND";
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId)) {
                            country = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getLocation().getCountry();
                        }
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId) && country.equals("COUNTRY_NOT_FOUND")) {
                            country = hotelDetailsAcmeDtoHashMap.get(hotelId).getCountry();
                        }
                        locationDto.setCountry(country);
                        responseDto.setLocation(locationDto);
                        log.info("Merge: Location: " + responseDto.toString());
                        break;

                    case "description":
                        String description = "DESCRIPTION_NOT_FOUND";
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId)) {
                            description = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getDetails();
                        }
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId) && (description.equals("DESCRIPTION_NOT_FOUND"))) {
                            description = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getInfo();
                        }
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId) && (description.equals("DESCRIPTION_NOT_FOUND"))) {
                            description = hotelDetailsAcmeDtoHashMap.get(hotelId).getDescription();
                        }
                        responseDto.setDescription(description);
                        log.info("Merge: Description: " + responseDto.toString());
                        break;

                    case "amenities":
                        List<String> general = null;
                        List<String> room = null;
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId)) {
                            AmenitiesDto amenitiesDto = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getAmenities();
                            general = amenitiesDto.getGeneral();
                            room = amenitiesDto.getRoom();
                        }
                        List<String> facilitiesAcme = null;
                        List<String> amenitiesPatagonia = null;
                        if (hotelDetailsAcmeDtoHashMap.containsKey(hotelId)) {
                            facilitiesAcme = hotelDetailsAcmeDtoHashMap.get(hotelId).getFacilities();
                        }
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId)) {
                            amenitiesPatagonia = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getAmenities();
                        }
                        if (general != null && facilitiesAcme != null) {
                            for (String facilityAcme : facilitiesAcme) {
                                Boolean flag = false;
                                for (String generalFacility : general) {
                                    if (Pattern.compile(Pattern.quote(facilityAcme), Pattern.CASE_INSENSITIVE).matcher(generalFacility).find()) {
                                        flag = true;
                                    }
                                }
                                if (flag == false) {
                                    general.add(facilityAcme);
                                }
                            }
                        }
                        if (room != null && amenitiesPatagonia != null) {
                            for (String amenityPatagonia : amenitiesPatagonia) {
                                Boolean flag = false;
                                for (String amenityRoom : room) {
                                    if (Pattern.compile(Pattern.quote(amenityPatagonia), Pattern.CASE_INSENSITIVE).matcher(amenityRoom).find()) {
                                        flag = true;
                                    }
                                }
                                if (flag == false) {
                                    room.add(amenityPatagonia);
                                }
                            }
                        }
                        responseDto.setAmenities(new AmenitiesDto(general, room));
                        log.info("Merge: Amenities: " + responseDto.toString());
                        break;


                    case "images":
                        List<ImageElementDto> site = null;
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId)) {
                            site = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getImages().getSite();
                        }
                        List<ImageElementDto> amenities = null;
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId)) {
                            amenities = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getImages().getAmenities();
                        }
                        List<ImageElementDto> rooms = null;
                        List<ImageElementDto> roomsPatagonia = null;
                        if (hotelDetailsPaperFliesDtoHashMap.containsKey(hotelId)) {
                            rooms = hotelDetailsPaperFliesDtoHashMap.get(hotelId).getImages().getRooms();
                        }
                        if (hotelDetailsPatagoniaDtoHashMap.containsKey(hotelId)) {
                            roomsPatagonia = hotelDetailsPatagoniaDtoHashMap.get(hotelId).getImages().getRooms();
                        }
                        if (rooms != null && roomsPatagonia != null) {
                            for (ImageElementDto roomElementPatagonia : roomsPatagonia) {
                                Boolean flag = false;
                                for (ImageElementDto roomElementList : rooms) {
                                    if (roomElementList.getLink().equals(roomElementPatagonia.getLink())) {
                                        flag = true;
                                    }
                                }
                                if (flag == false) {
                                    rooms.add(roomElementPatagonia);
                                }
                            }
                        }
                        responseDto.setImages(new ResponseImagesDto(rooms, site, amenities));
                        log.info("Merge: Images: " + responseDto.toString());
                        break;
                }
            }
            mergedHotelDetailsMap.put(hotelId, responseDto);
            if (destinationIdToHotelIdMap.containsKey(responseDto.getDestination_id())) {
                List<String> list = destinationIdToHotelIdMap.get(responseDto.getDestination_id());
                list.add(hotelId);
                destinationIdToHotelIdMap.put(responseDto.getDestination_id(), list);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(hotelId);
                destinationIdToHotelIdMap.put(responseDto.getDestination_id(), newList);
            }
        }
    }

    private void retrieveHotelDetails() {

        List<HotelDetailsAcmeDto> hotelDetailsAcmeDtoList = Arrays.asList(retrieveHotelDetailsService.getHotelDetailsFromEndPoint_1());
        List<HotelDetailsPaperFliesDto> hotelDetailsPaperFliesDtoList = Arrays.asList(retrieveHotelDetailsService.getHotelDetailsFromEndPoint_3());
        List<HotelDetailsPatagoniaDto> hotelDetailsPatagoniaDtoList = Arrays.asList(retrieveHotelDetailsService.getHotelDetailsFromEndPoint_2());

        for (HotelDetailsAcmeDto hotelDetailsAcmeDto : hotelDetailsAcmeDtoList) {
            hotelDetailsAcmeDtoHashMap.put(hotelDetailsAcmeDto.getId(), hotelDetailsAcmeDto);
            hotelIdKeys.add(hotelDetailsAcmeDto.getId());
        }

        for (HotelDetailsPaperFliesDto hotelDetailsPaperFliesDto : hotelDetailsPaperFliesDtoList) {
            hotelDetailsPaperFliesDtoHashMap.put(hotelDetailsPaperFliesDto.getHotelId(), hotelDetailsPaperFliesDto);
            hotelIdKeys.add(hotelDetailsPaperFliesDto.getHotelId());
        }

        for (HotelDetailsPatagoniaDto hotelDetailsPatagoniaDto : hotelDetailsPatagoniaDtoList) {
            hotelDetailsPatagoniaDtoHashMap.put(hotelDetailsPatagoniaDto.getId(), hotelDetailsPatagoniaDto);
            hotelIdKeys.add(hotelDetailsPatagoniaDto.getId());
        }
    }

    public List<ResponseDto> retrieveHotelDetailsById(List<String> hotelIds) {
        List<ResponseDto> filteredHotelDetails = new ArrayList<>();
        for (String hotelId : hotelIds) {
            filteredHotelDetails.add(mergedHotelDetailsMap.get(hotelId));
        }
        return filteredHotelDetails;
    }

    public List<ResponseDto> retrieveHotelDetailsByDestinationId(int destinationId) {
        //Get the list of hotel ids that has been mapped to the destination id
        List<String> hotelIds = destinationIdToHotelIdMap.get(destinationId);
        List<ResponseDto> filteredHotelDetails = new ArrayList<>();
        for (String hotelId : hotelIds) {
            filteredHotelDetails.add(mergedHotelDetailsMap.get(hotelId));
        }
        return filteredHotelDetails;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Attempting to merge hotel details");
        this.mergeHotelDetails();
    }
}
