package br.com.scsoftware.afinese.infrastructure.common.serializer;

/**
 * @author samuel-cruz
 */
public class PercentBigDecimalSerializer extends AbstractBigDecimalSerializer {
    @Override
    protected String getPattern() {
        return "0.00";
    }
}
