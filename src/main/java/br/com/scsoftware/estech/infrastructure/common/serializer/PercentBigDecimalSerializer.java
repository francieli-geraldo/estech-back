package br.com.scsoftware.estech.infrastructure.common.serializer;

/**
 * @author samuel-cruz
 */
public class PercentBigDecimalSerializer extends AbstractBigDecimalSerializer {

    @Override
    protected Integer getScale() {
        return 2;
    }
}
