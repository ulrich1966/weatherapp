package de.auli.weatherapp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import de.auli.weatherapp.model.Result;

public class MapperService {
    ObjectMapper mapper = new ObjectMapper();

    public MapperService() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
    }

    public Map<String, Object> jsonToMap(String json) throws Exception {
        Map<String,Object> result = mapper.readValue(json, HashMap.class);
        return result;
    }

    public <T> Result unmarshall(String json) throws Exception {
        //AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
        //mapper.setAnnotationIntrospector(introspector);
        Result model = mapper.readValue(json, Result.class);
        return model;
    }

}
