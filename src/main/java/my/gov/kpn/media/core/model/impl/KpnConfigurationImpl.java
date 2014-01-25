package my.gov.kpn.media.core.model.impl;

import my.gov.kpn.media.core.model.KpnConfiguration;
import my.gov.kpn.media.core.model.KpnMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author rafizan.baharum
 * @since 11/27/13
 */
@Entity(name = "KpnConfiguration")
@Table(name = "KPN_CFGN")
public class KpnConfigurationImpl implements KpnConfiguration, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_KPN_CFGN")
    @SequenceGenerator(name = "SEQ_KPN_CFGN", sequenceName = "SEQ_KPN_CFGN", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CONFIG_KEY", nullable = false)
    private String key;

    @Column(name = "CONFIG_VALUE")
    private String value;

    @Column(name = "DESCRIPTION")
    private String description;

    @Embedded
    private KpnMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KpnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(KpnMetadata metadata) {
        this.metadata = metadata;
    }

    @Transient
    public Integer getValueAsInteger() {
        if (null != getValue())
            return new Integer(getValue());
        else return 0;
    }

    @Transient
    public Double getValueAsDouble() {
        if (null != getValue())
            return new Double(getValue());
        else return 0d;
    }

    @Transient
    public Long getValueAsLong() {
        if (null != getValue())
            return new Long(getValue());
        else return 0l;
    }

    @Transient
    public BigDecimal getValueAsBigDecimal() {
        if (null != getValue())
            return new BigDecimal(getValue());
        else return BigDecimal.ZERO;
    }

}
