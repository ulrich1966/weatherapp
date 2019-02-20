package de.auli.weatherapp;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WeatherMarshallTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //String result = "{\"coord\":{\"lon\":8.81,\"lat\":53.08},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"Ein paar Wolken\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":282.15,\"pressure\":1021,\"humidity\":76,\"temp_min\":282.15,\"temp_max\":282.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":220},\"clouds\":{\"all\":20},\"dt\":1550663400,\"sys\":{\"type\":1,\"id\":1281,\"message\":0.004,\"country\":\"DE\",\"sunrise\":1550644297,\"sunset\":1550681169},\"id\":2944388,\"name\":\"Bremen\",\"cod\":200}";
        //System.out.println(result);

        Student s = new Student("Walter", "White");
        JAXBContext jc = JAXBContext.newInstance(Student.class);
        Marshaller m = jc.createMarshaller();
    }
}