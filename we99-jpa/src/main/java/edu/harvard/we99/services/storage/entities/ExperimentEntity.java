package edu.harvard.we99.services.storage.entities;

import edu.harvard.we99.domain.ExperimentStatus;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mford
 */
@Entity
@Table(name = "experiments")
public class ExperimentEntity {
    /**
     * The primary key for this entity is generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User provided name for the experiment
     */
    @NotNull
    private String name;

    private String description;

    private ExperimentStatus status;

    /**
     * Datetime for the creation of the experiment
     */
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(updatable = false)
    private DateTime created;

    /**
     * Reference to the protocol that governs this experiment
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private ProtocolEntity protocol;

    /**
     * Users that are permitted to view/modify data for this Experiment
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @MapKey(name = "email")
    private Map<String,UserEntity> members = new HashMap<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LabelEntity> labels = new HashSet<>();

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "experiment")
    @MapKey(name="id")
    private Map<Long,PlateEntity> plates = new HashMap<>();

    public ExperimentEntity() {}

    public ExperimentEntity(String name) {
        this.name = name;
    }

    @PrePersist
    private void prePersist() {
        this.created = DateTime.now();
    }

    public void addUser(UserEntity user) {
        members.put(user.getEmail(), user);
    }

    public void removeUser(UserEntity user) {
        members.remove(user.getEmail());
    }

    @Generated(value = "generated by IDE")
    public DateTime getCreated() {
        return created;
    }

    @Generated(value = "generated by IDE")
    public void setCreated(DateTime created) {
        this.created = created;
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
    public Map<String, UserEntity> getMembers() {
        return members;
    }

    @Generated(value = "generated by IDE")
    public void setMembers(Map<String, UserEntity> members) {
        this.members = members;
    }

    @Generated(value = "generated by IDE")
    public String getName() {
        return name;
    }

    @Generated(value = "generated by IDE")
    public void setName(String name) {
        this.name = name;
    }

    @Generated(value = "generated by IDE")
    public ProtocolEntity getProtocol() {
        return protocol;
    }

    @Generated(value = "generated by IDE")
    public void setProtocol(ProtocolEntity protocol) {
        this.protocol = protocol;
    }

    @Generated(value = "generated by IDE")
    public ExperimentEntity withCreated(final DateTime created) {
        this.created = created;
        return this;
    }

    @Generated(value = "generated by IDE")
    public ExperimentEntity withId(final Long id) {
        this.id = id;
        return this;
    }

    @Generated(value = "generated by IDE")
    public ExperimentEntity withName(final String name) {
        this.name = name;
        return this;
    }

    @Generated(value = "generated by IDE")
    public ExperimentEntity withProtocol(final ProtocolEntity protocol) {
        this.protocol = protocol;
        return this;
    }

    @Generated(value = "generated by IDE")
    public ExperimentEntity withMembers(final Map<String, UserEntity> members) {
        this.members = members;
        return this;
    }

    @Generated(value = "generated by IDE")
    public String getDescription() {
        return description;
    }

    @Generated(value = "generated by IDE")
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(value = "generated by IDE")
    public ExperimentStatus getStatus() {
        return status;
    }

    @Generated(value = "generated by IDE")
    public void setStatus(ExperimentStatus status) {
        this.status = status;
    }

    @Generated(value = "generated by IDE")
    public ExperimentEntity withDescription(final String description) {
        this.description = description;
        return this;
    }

    @Generated(value = "generated by IDE")
    public ExperimentEntity withStatus(final ExperimentStatus status) {
        this.status = status;
        return this;
    }

    @Generated(value = "generated by IDE")
    public Set<LabelEntity> getLabels() {
        return labels;
    }

    @Generated(value = "generated by IDE")
    public void setLabels(Set<LabelEntity> labels) {
        this.labels = labels;
    }

    public ExperimentEntity withLabels(List<LabelEntity> labels) {
        this.labels.addAll(labels);
        return this;
    }

    @Generated(value = "generated by IDE")
    public Map<Long, PlateEntity> getPlates() {
        return plates;
    }

    @Generated(value = "generated by IDE")
    public void setPlates(Map<Long, PlateEntity> plates) {
        this.plates = plates;
    }
}
