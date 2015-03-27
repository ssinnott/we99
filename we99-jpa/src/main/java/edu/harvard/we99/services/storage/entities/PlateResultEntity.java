package edu.harvard.we99.services.storage.entities;

import edu.harvard.we99.domain.Coordinate;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mford
 */
@Entity
@Table(name = "plateresults")
public class PlateResultEntity {
    /**
     * Primary key field is auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Creation time
     */
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(updatable = false)
    private DateTime created;

    /**
     * Time of the last modification (computed when the bean is saved)
     */
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModified;

    /**
     * Optional comments from the user about this plate or its changes
     */
    private String comments;

    /**
     * Reference to the plate that these results belong to
     */
    @OneToOne(fetch = FetchType.EAGER, optional = false, mappedBy = "results")
    @NotNull
    @JoinColumn(updatable = false)
    private PlateEntity plate;

    /**
     * The results by coordinate.
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @MapKey(name="coordinate")
    private Map<Coordinate,WellResultsEntity> wellResults = new HashMap<>();

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(updatable = false)
    @NotNull
    private String source;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, PlateMetricsEntity> metrics = new HashMap<>();

    public void add(WellResultsEntity result) {
        wellResults.put(result.getCoordinate(), result);
    }

    @PrePersist
    private void prePersist() {
        this.created = new DateTime();
    }

    @PreUpdate
    private void preUpdate() {
        this.lastModified = new DateTime();
    }

    @Generated("generated by IDE")
    public Long getId() {
        return id;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Generated("generated by IDE")
    public DateTime getCreated() {
        return created;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setCreated(DateTime created) {
        this.created = created;
        return this;
    }

    @Generated("generated by IDE")
    public DateTime getLastModified() {
        return lastModified;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    @Generated("generated by IDE")
    public String getComments() {
        return comments;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setComments(String comments) {
        this.comments = comments;
        return this;
    }

    @Generated("generated by IDE")
    public PlateEntity getPlate() {
        return plate;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setPlate(PlateEntity plate) {
        this.plate = plate;
        return this;
    }

    @Generated("generated by IDE")
    public Map<Coordinate, WellResultsEntity> getWellResults() {
        return wellResults;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setWellResults(Map<Coordinate, WellResultsEntity> wellResults) {
        this.wellResults = wellResults;
        return this;
    }

    @Generated("generated by IDE")
    public String getSource() {
        return source;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setSource(String source) {
        this.source = source;
        return this;
    }

    @Generated("generated by IDE")
    public Map<String, PlateMetricsEntity> getMetrics() {
        return metrics;
    }

    @Generated("generated by IDE")
    public PlateResultEntity setMetrics(Map<String, PlateMetricsEntity> metrics) {
        this.metrics = metrics;
        return this;
    }
}
