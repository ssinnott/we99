package edu.harvard.we99.services.storage.entities;

import edu.harvard.we99.domain.Coordinate;
import edu.harvard.we99.domain.WellType;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LabelEntity> labels = new HashSet<>();

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

    @Generated("generated by IDE")
    public Long getId() {
        return id;
    }

    @Generated("generated by IDE")
    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    @Generated("generated by IDE")
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Generated("generated by IDE")
    public T setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return (T) this;
    }

    @Generated("generated by IDE")
    public Set<LabelEntity> getLabels() {
        return labels;
    }

    @Generated("generated by IDE")
    public T setLabels(Set<LabelEntity> labels) {
        this.labels = labels;
        return (T) this;
    }

    @Generated("generated by IDE")
    public WellType getType() {
        return type;
    }

    @Generated("generated by IDE")
    public T setType(WellType type) {
        this.type = type;
        return (T) this;
    }

    public T setLabel(String name, String value) {
        labels.add(new LabelEntity(name, value));
        //noinspection unchecked
        return (T) this;
    }

    public T setLabels(List<LabelEntity> labels) {
        labels.forEach(this.labels::add);
        //noinspection unchecked
        return (T) this;
    }

}
