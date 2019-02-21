package de.auli.weatherapp;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import de.auli.weatherapp.model.Result;

public class UnmarshallService {

    public Result unmarshall(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        //AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        //mapper.setAnnotationIntrospector(introspector);
        Result model = mapper.readValue(json, Result.class);
        return model;
    }

}
