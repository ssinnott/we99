package edu.harvard.we99.domain;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

/**
 * AbstractWell is the base class for Well's and WellMaps.
 * @author mford
 */
public abstract class AbstractWell<T extends AbstractWell> extends BaseEntity {

    private Long id;

    /**
     * Coordinate identifies the row,col for the Well
     */
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

    public AbstractWell() {}

    public AbstractWell(int row, int col) {
        setCoordinate(new Coordinate(row, col));
    }

    public AbstractWell(Coordinate coord) {
        setCoordinate(coord);
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

    public T withLabel(final String label) {
        this.label = label;
        //noinspection unchecked
        return (T) this;
    }

    public T withType(final WellType type) {
        this.type = type;
        //noinspection unchecked
        return (T) this;
    }

    @Generated(value = "generated by IDE")
    public Long getId() {
        return id;
    }

    @Generated(value = "generated by IDE")
    public void setId(Long id) {
        this.id = id;
    }

    public T withId(final Long id) {
        this.id = id;
        //noinspection unchecked
        return (T) this;
    }


    @Override
    @Generated(value = "generated by IDE")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractWell that = (AbstractWell) o;

        if (!coordinate.equals(that.coordinate)) return false;

        return true;
    }

    @Override
    @Generated(value = "generated by IDE")
    public int hashCode() {
        return coordinate.hashCode();
    }
}
