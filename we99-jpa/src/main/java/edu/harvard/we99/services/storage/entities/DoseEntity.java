package edu.harvard.we99.services.storage.entities;

import edu.harvard.we99.domain.Amount;

import javax.annotation.Generated;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author mford
 */
@Entity
@Table(name = "doses")
public class DoseEntity {
    /**
     * Primary key for this entity is generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Embedded
    private Amount amount;

    /**
     * The compound that is in the well. If a Well is empty, it shouldn't have
     * a dose.
     *
     * todo - do we need some type of "unknown" compound
     */
    @ManyToOne
    @NotNull
    private CompoundEntity compound;

    /**
     * A back pointer to the parent well in which this Dose lives
     */
    @ManyToOne @JoinColumn(updatable = false)
    @XmlTransient
    private WellEntity well;

    @Generated("generated by IDE")
    public Long getId() {
        return id;
    }

    @Generated("generated by IDE")
    public DoseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Generated("generated by IDE")
    public Amount getAmount() {
        return amount;
    }

    @Generated("generated by IDE")
    public DoseEntity setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    @Generated("generated by IDE")
    public CompoundEntity getCompound() {
        return compound;
    }

    @Generated("generated by IDE")
    public DoseEntity setCompound(CompoundEntity compound) {
        this.compound = compound;
        return this;
    }

    @Generated("generated by IDE")
    public WellEntity getWell() {
        return well;
    }

    @Generated("generated by IDE")
    public DoseEntity setWell(WellEntity well) {
        this.well = well;
        return this;
    }
}
