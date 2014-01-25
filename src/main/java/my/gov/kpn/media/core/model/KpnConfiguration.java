package my.gov.kpn.media.core.model;

import java.math.BigDecimal;

/**
 * @author rafizan.baharum
 * @since 1/26/14
 */
public interface KpnConfiguration extends KpnMetaObject {

    String getKey();

    void setKey(String value);

    String getValue();

    void setValue(String value);

    String getDescription();

    void setDescription(String description);

    Integer getValueAsInteger();

    Double getValueAsDouble();

    Long getValueAsLong();

    BigDecimal getValueAsBigDecimal();

}
