package de.auli.weatherapp;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import org.junit.Test;

import java.io.File;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class StudentMarshallTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //String result = "{\"coord\":{\"lon\":8.81,\"lat\":53.08},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"Ein paar Wolken\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":282.15,\"pressure\":1021,\"humidity\":76,\"temp_min\":282.15,\"temp_max\":282.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":220},\"clouds\":{\"all\":20},\"dt\":1550663400,\"sys\":{\"type\":1,\"id\":1281,\"message\":0.004,\"country\":\"DE\",\"sunrise\":1550644297,\"sunset\":1550681169},\"id\":2944388,\"name\":\"Bremen\",\"cod\":200}";
        //System.out.println(result);

        Student user = new Student("Walter", "White");

        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        mapper.setAnnotationIntrospector(introspector);

        String result = mapper.writeValueAsString(user);

        System.out.println(result);


        user = mapper.readValue(result, Student.class);

        System.out.println(user.toString());

    }
}