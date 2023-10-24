package br.com.scsoftware.estech.infrastructure.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author samuel-cruz
 */
public abstract class AbstractBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(final BigDecimal value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        if (null == value) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.setScale(getScale(), RoundingMode.HALF_EVEN));
        }
    }

    protected abstract Integer getScale();
}
