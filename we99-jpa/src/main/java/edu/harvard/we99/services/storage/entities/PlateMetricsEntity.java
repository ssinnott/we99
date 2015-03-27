package edu.harvard.we99.services.storage.entities;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mford
 */
@Entity
public class PlateMetricsEntity {
    /**
     * Primary key field is auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private Double avgPositive;
    private Double avgNegative;
    private Double zee;
    private Double zeePrime;

    @Generated("generated by IDE")
    public Long getId() {
        return id;
    }

    @Generated("generated by IDE")
    public PlateMetricsEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Generated("generated by IDE")
    public String getLabel() {
        return label;
    }

    @Generated("generated by IDE")
    public PlateMetricsEntity setLabel(String label) {
        this.label = label;
        return this;
    }

    @Generated("generated by IDE")
    public Double getAvgPositive() {
        return avgPositive;
    }

    @Generated("generated by IDE")
    public PlateMetricsEntity setAvgPositive(Double avgPositive) {
        this.avgPositive = avgPositive;
        return this;
    }

    @Generated("generated by IDE")
    public Double getAvgNegative() {
        return avgNegative;
    }

    @Generated("generated by IDE")
    public PlateMetricsEntity setAvgNegative(Double avgNegative) {
        this.avgNegative = avgNegative;
        return this;
    }

    @Generated("generated by IDE")
    public Double getZee() {
        return zee;
    }

    @Generated("generated by IDE")
    public PlateMetricsEntity setZee(Double zee) {
        this.zee = zee;
        return this;
    }

    @Generated("generated by IDE")
    public Double getZeePrime() {
        return zeePrime;
    }

    @Generated("generated by IDE")
    public PlateMetricsEntity setZeePrime(Double zeePrime) {
        this.zeePrime = zeePrime;
        return this;
    }
}
