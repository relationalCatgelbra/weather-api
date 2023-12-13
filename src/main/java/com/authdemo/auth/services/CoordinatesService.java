package com.authdemo.auth.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.authdemo.auth.models.Location;

@Service
public class CoordinatesService {

        // private static final Logger logger =
        // LoggerFactory.getLogger(CoordinatesService.class);

        private String baseUrl = "https://nominatim.openstreetmap.org";

        public Location getCoordinatesByCityAndCountry(
                        String city,
                        String country,
                        String format) {
                UriComponentsBuilder builderUri = UriComponentsBuilder.fromUriString(baseUrl)
                                .pathSegment("search")
                                .queryParam("q", city + "+" + country)
                                .queryParam("format", format);

                String uriString = builderUri.build().toUriString();

                // logger.info(uriString);

                List<Location> client = WebClient.create(uriString)
                                .get()
                                .retrieve()
                                .bodyToFlux(Location.class)
                                .collectList()
                                .block();

                return client.get(0);

        }

}
