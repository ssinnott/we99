package edu.harvard.we99.services.storage.entities;

import edu.harvard.we99.domain.Coordinate;
import edu.harvard.we99.domain.results.ResultStatus;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mford
 */
@Entity
@Table(name = "wellresults")
public class WellResultsEntity {
    /**
     * Primary key field is auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The coordinate of the well for these results
     */
    @Embedded
    private Coordinate coordinate;

    private ResultStatus resultStatus = ResultStatus.INCLUDED;

    /**
     * All of the samples associated w/ this well's results. This may be multiple
     * samples from a single analysis or they may be the same analysis but at
     * different periods of time.
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<SampleEntity> samples = new ArrayList<>();

    @Generated(value = "generated by IDE")
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Generated(value = "generated by IDE")
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Generated(value = "generated by IDE")
    public Long getId() {
        return id;
    }

    @Generated(value = "generated by IDE")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value = "generated by IDE")
    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    @Generated(value = "generated by IDE")
    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    @Generated(value = "generated by IDE")
    public List<SampleEntity> getSamples() {
        return samples;
    }

    @Generated(value = "generated by IDE")
    public void setSamples(List<SampleEntity> samples) {
        this.samples = samples;
    }

    @Generated(value = "generated by IDE")
    public WellResultsEntity withCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    @Generated(value = "generated by IDE")
    public WellResultsEntity withId(final Long id) {
        this.id = id;
        return this;
    }

    @Generated(value = "generated by IDE")
    public WellResultsEntity withResultStatus(final ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
        return this;
    }

    @Generated(value = "generated by IDE")
    public WellResultsEntity withSamples(final List<SampleEntity> samples) {
        this.samples = samples;
        return this;
    }
}