package com.shivam.jsonassert.assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    public static void assertEquals(String fileName, Object actualObject, JSONCompareMode jsonCompareMode) {
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedString = readFile(fileName);
        try {
            String actualString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(actualObject);

            log.info("Expected Output : " + expectedString);
            log.info("Actual Output : " + actualString);
            JSONAssert.assertEquals(expectedString, actualString, jsonCompareMode);
        } catch (JsonProcessingException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String fileName) {
        try {
            return StreamUtils.copyToString(new ClassPathResource(fileName).getInputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read file");
        }
    }
}
