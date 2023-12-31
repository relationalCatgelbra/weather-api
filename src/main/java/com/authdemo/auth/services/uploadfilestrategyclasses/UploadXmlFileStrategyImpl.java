package com.authdemo.auth.services.uploadfilestrategyclasses;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.authdemo.auth.models.WeatherResponseData;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UploadXmlFileStrategyImpl implements UploadFileStrategyInterface {

    public List<WeatherResponseData> execute(MultipartFile file) throws IOException,
            ParserConfigurationException, SAXException {

        byte[] fileBytes = file.getBytes();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(inputStream);

        NodeList entryList = doc.getElementsByTagName("entry");

        List<WeatherResponseData> weatherResponseDataList = new ArrayList<>();

        for (int i = 0; i < entryList.getLength(); i++) {
            Element entryElement = (Element) entryList.item(i);

            Double temperature = Double.parseDouble(getElementValue(entryElement, "temperature"));
            Double windDirection = Double.parseDouble(getElementValue(entryElement, "windDirection"));
            Double windSpeed = Double.parseDouble(getElementValue(entryElement, "windSpeed"));
            Double meanSeaLevel = Double.parseDouble(getElementValue(entryElement, "meanSeaLevel"));
            Double seaLevelPressure = Double.parseDouble(getElementValue(entryElement, "seaLevelPressure"));
            Double amountPrecipitation = Double.parseDouble(getElementValue(entryElement, "amountPrecipitation"));
            Double uvRays = Double.parseDouble(getElementValue(entryElement, "uvRays"));
            Double sunriseTime = Double.parseDouble(getElementValue(entryElement, "sunriseTime"));
            Double sunsetTime = Double.parseDouble(getElementValue(entryElement, "sunsetTime"));
            String country = getElementValue(entryElement, "country");
            String city = getElementValue(entryElement, "city");
            String date = getElementValue(entryElement, "date");
            String time = getElementValue(entryElement, "time");

            WeatherResponseData weatherResponseData = new WeatherResponseData();

            weatherResponseData.setTemperature(temperature);
            weatherResponseData.setWindDirection(windDirection);
            weatherResponseData.setWindSpeed(windSpeed);
            weatherResponseData.setMeanSeaLevel(meanSeaLevel);
            weatherResponseData.setSeaLevelPressure(seaLevelPressure);
            weatherResponseData.setAmountPrecipitation(amountPrecipitation);
            weatherResponseData.setUvRays(uvRays);
            weatherResponseData.setSunriseTime(sunriseTime);
            weatherResponseData.setSunsetTime(sunsetTime);
            weatherResponseData.setCountry(country);
            weatherResponseData.setCity(city);
            weatherResponseData.setDate(date);
            weatherResponseData.setTime(time);

            weatherResponseDataList.add(weatherResponseData);

        }

        return weatherResponseDataList;

    }

    private String getElementValue(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);

        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();

        } else {
            return "0";
        }

    }

}
