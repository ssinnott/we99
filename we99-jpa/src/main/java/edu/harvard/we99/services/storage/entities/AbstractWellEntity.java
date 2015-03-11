package edu.harvard.we99.services.storage.entities;

import edu.harvard.we99.domain.Coordinate;
import edu.harvard.we99.domain.WellType;

import javax.annotation.Generated;
import javax.persistence.Embedded;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AbstractWellEntity<T extends AbstractWellEntity> {
    /**
     * Primary key for this entity is generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Coordinate identifies the row,col for the Well
     */
    @Embedded
    private Coordinate coordinate;

    /**
     * User provided label for the Well
     */
    private String label;

    /**
     * The well type tells us if this is a regular measured dose of a compound
     * or a special well like a positive, negative control or empty.
     */
    @NotNull
    private WellType type;

    public AbstractWellEntity(){}

    public AbstractWellEntity(Coordinate coord) {
        setCoordinate(coord);
    }

    public AbstractWellEntity(int row, int col) {
        this(new Coordinate(row, col));
    }

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
    public String getLabel() {
        return label;
    }

    @Generated(value = "generated by IDE")
    public void setLabel(String label) {
        this.label = label;
    }

    @Generated(value = "generated by IDE")
    public WellType getType() {
        return type;
    }

    @Generated(value = "generated by IDE")
    public void setType(WellType type) {
        this.type = type;
    }

    @Override
    @Generated(value = "generated by IDE")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractWellEntity that = (AbstractWellEntity) o;

        if (!coordinate.equals(that.coordinate)) return false;

        return true;
    }

    @Override
    @Generated(value = "generated by IDE")
    public int hashCode() {
        return coordinate.hashCode();
    }

    @Generated(value = "generated by IDE")
    public T withCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
        return (T) this;
    }

    @Generated(value = "generated by IDE")
    public T withId(final Long id) {
        this.id = id;
        return (T) this;
    }

    @Generated(value = "generated by IDE")
    public T withLabel(final String label) {
        this.label = label;
        return (T) this;
    }

    @Generated(value = "generated by IDE")
    public T withType(final WellType type) {
        this.type = type;
        return (T) this;
    }
}
