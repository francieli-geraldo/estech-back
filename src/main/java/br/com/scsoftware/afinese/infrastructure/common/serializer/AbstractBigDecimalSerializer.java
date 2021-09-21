package br.com.scsoftware.afinese.infrastructure.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author samuel-cruz
 */
public abstract class AbstractBigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(final BigDecimal value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        if (null == value) {
            gen.writeNull();
        } else {
            final String pattern = getPattern();
            final DecimalFormat myFormatter = new DecimalFormat(pattern);
            gen.writeNumber(myFormatter.format(value));
        }
    }

    protected abstract String getPattern();
}
