package com.authdemo.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Location {

    private long placeId;

    private String license;

    private String osmType;

    private long osmId;

    private String lat;

    private String lon;

    private String className;

    private String type;

    private int placeRank;

    private double importance;

    private String addressType;

    private String name;

    private String displayName;

    private List<String> boundingBox;

}
