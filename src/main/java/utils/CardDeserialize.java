package utils;

import models.Card;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by Ammarah on 5/24/2017.
 */
public class CardDeserialize extends JsonDeserializer<Card> {

    @Override
    public Card deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        final String jsonValue = parser.getText();
        for (final Card enumValue : Card.values()) {
            if (enumValue.toString().equals(jsonValue)) {
                return enumValue;
            }
        }
        return null;
    }
}
