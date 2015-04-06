package edu.harvard.we99.services.storage.entities;


import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import edu.harvard.we99.domain.Well;
import edu.harvard.we99.domain.results.DoseResponseResult;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alan orcharton
 */

@Entity
@Table(name = "doseresponseresults")
public class DoseResponseResultEntity {

    /**
     * Primary key files is auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    /**
     * Creation time
     */
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(updatable = false)
    private DateTime created;


    /**
     * Time of the last modification (computed when the bean is saved
     */
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModified;

    /**
     * Reference to the Experiment
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(updatable = false)
    @NotNull
    private ExperimentEntity experiment;

    /**
     * Reference to the Compound that the dose response is for
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private CompoundEntity compound;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Map<Long, WellEntity> doseWells = new HashMap<>();

    @OneToMany
    private List<ExperimentPointEntity> experimentPoints = new ArrayList<>();

    /**
     * Optional comments from the user about this plate of its changes
     */
    private String comments;


    public void addWell(Long id, WellEntity we){
        doseWells.put(id, we);
    }

    public void addExperimentPoint(ExperimentPointEntity epe) { experimentPoints.add(epe);}

    @Generated("generated by IDE")
    public String getComments() {
        return comments;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setComments(String comments) {
        this.comments = comments;
        return this;
    }


    @Generated("generated by IDE")
    public Long getId() {
        return Id;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setId(Long id) {
        Id = id;
        return this;
    }

    @Generated("generated by IDE")
    public DateTime getCreated() {
        return created;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setCreated(DateTime created) {
        this.created = created;
        return this;
    }

    @Generated("generated by IDE")
    public DateTime getLastModified() {
        return lastModified;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    @Generated("generated by IDE")
    public CompoundEntity getCompound() {
        return compound;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setCompound(CompoundEntity compound) {
        this.compound = compound;
        return this;
    }

    @Generated("generated by IDE")
    public Map<Long, WellEntity> getDoseWells() {
        return doseWells;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setDoseWells(Map<Long, WellEntity> doseWells) {
        this.doseWells = doseWells;
        return this;
    }

    @Generated("generated by IDE")
    public List<ExperimentPointEntity> getExperimentPoints() {
        return experimentPoints;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setExperimentPoints(List<ExperimentPointEntity> experimentPoints) {
        this.experimentPoints = experimentPoints;
        return this;
    }

    @Generated("generated by IDE")
    public DoseResponseResultEntity setExperiment(ExperimentEntity experiment) {
        this.experiment = experiment;
        return this;
    }

    @Generated("generated by IDE")
    public ExperimentEntity getExperiment() {
        return experiment;
    }

}
