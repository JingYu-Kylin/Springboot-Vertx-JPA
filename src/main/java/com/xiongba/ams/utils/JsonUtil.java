package com.xiongba.ams.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JsonUtil {
        private static JsonUtil ju;
        private static JsonFactory jf;
        private static ObjectMapper mapper;

        public static JsonUtil getInstance() {
            if (ju == null)
                ju = new JsonUtil();
            return ju;
        }

        public static ObjectMapper getMapper() {
            if (mapper == null){
                mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
            return mapper;
        }

        public static JsonFactory getFactory() {
            if (jf == null)
                jf = new JsonFactory();
            return jf;
        }

        public String toJson(Object obj) {
            getInstance();
            getFactory();
            getMapper();
            StringWriter out = new StringWriter();
            JsonGenerator jg = null;
            try {
                jg = jf.createJsonGenerator(out);
                mapper.writeValue(jg, obj);
                return (out.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (jg != null) {
                    try {
                        jg.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        public <T> T fromJson(String json, Class<T> clz) {
            getMapper();
            try {
                return mapper.readValue(json, clz);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public <T> T fromJson(String json, TypeReference<T> typeReference) {
            getMapper();
            try {
                return mapper.readValue(json, typeReference);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}
