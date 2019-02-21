package de.auli.weatherapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import org.json.JSONArray;
import org.junit.Test;
import org.w3c.dom.Node;

import java.util.Map;

import de.auli.weatherapp.model.CoordMod;
import de.auli.weatherapp.model.WeatherMod;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WeatherUnmarshallTest {
    @Test
    public void test() throws Exception {
        String result = "{\"coord\":{\"lon\":8.81,\"lat\":53.08},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"Ein paar Wolken\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":282.15,\"pressure\":1021,\"humidity\":76,\"temp_min\":282.15,\"temp_max\":282.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":220},\"clouds\":{\"all\":20},\"dt\":1550663400,\"sys\":{\"type\":1,\"id\":1281,\"message\":0.004,\"country\":\"DE\",\"sunrise\":1550644297,\"sunset\":1550681169},\"id\":2944388,\"name\":\"Bremen\",\"cod\":200}";
        //System.out.println(result);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);

//        Map<String, Object> map = mapper.readValue(result, new TypeReference<Map<String,Object>>(){});
//        Object  coordJson = map.get(CoordMod.NAME);

        String node = "";
        JsonNode jsonNodeRoot = mapper.readTree(result);
        JsonNode jsonCoord = jsonNodeRoot.get(CoordMod.NAME);
        JsonNode jsonWeather = jsonNodeRoot.get(WeatherMod.NAME).get(0);

        node = jsonWeather.toString();

        CoordMod coordMod = mapper.readValue(jsonCoord.toString(), CoordMod.class);
        WeatherMod weatherMod = mapper.readValue(jsonWeather.toString(), WeatherMod.class);

        System.out.println(String.format("Model: %s", weatherMod));
        System.out.println("DONE!");
    }
}