package com.authdemo.auth.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.authdemo.auth.models.WeatherResponseData;

@Repository
public interface WeatherResponseDataRepository extends MongoRepository<WeatherResponseData, ObjectId> {

    @Query(value = "{'country': ?0, 'city':?1}")
    public List<WeatherResponseData> findByCountryAndCity(String countryName, String cityName);

}
