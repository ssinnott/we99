package edu.harvard.we99.services.storage.entities;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

/**
 * @author mford
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractPlateEntity<T extends AbstractPlateEntity> {
    /**
     * Primary key field is auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name provided by the user must be unique within the db
     */
    @NotNull
    @Column(unique = true)
    private String name;

    /**
     * Optional user provided description
     */
    @Column(length = 1024)
    private String description;

    @Generated(value = "generated by IDE")
    public String getDescription() {
        return description;
    }

    @Generated(value = "generated by IDE")
    public void setDescription(String description) {
        this.description = description;
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
    public String getName() {
        return name;
    }

    @Generated(value = "generated by IDE")
    public void setName(String name) {
        this.name = name;
    }

    @Generated(value = "generated by IDE")
    public T withDescription(final String description) {
        this.description = description;
        return (T) this;
    }

    @Generated(value = "generated by IDE")
    public T withId(final Long id) {
        this.id = id;
        return (T) this;
    }

    @Generated(value = "generated by IDE")
    public T withName(final String name) {
        this.name = name;
        return (T) this;
    }
}
