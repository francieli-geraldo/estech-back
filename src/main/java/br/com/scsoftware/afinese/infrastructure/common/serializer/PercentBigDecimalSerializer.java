package br.com.scsoftware.afinese.infrastructure.common.serializer;

/**
 * @author samuel-cruz
 */
public class PercentBigDecimalSerializer extends AbstractBigDecimalSerializer {

    @Override
    protected Integer getScale() {
        return 2;
    }
}
