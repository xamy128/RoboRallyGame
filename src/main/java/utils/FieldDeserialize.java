package utils;

import models.Field;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by Ammarah on 5/28/2017.
 */
public class FieldDeserialize extends JsonDeserializer<Field>{

    @Override
    public Field deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        final String jsonValue = parser.getText();
        for (final Field enumValue : Field.values()) {
            if (enumValue.toString().equals(jsonValue)) {
                return enumValue;
            }
        }
        return null;
        }
}
