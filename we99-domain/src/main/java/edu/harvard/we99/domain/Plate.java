package edu.harvard.we99.domain;

import edu.harvard.we99.jaxb.WellAdapter;

import javax.annotation.Generated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A Plate is an instantiation of a PlateTemplate.
 *
 * @author mford
 */
public class Plate extends AbstractPlate<Plate> {

    private Experiment experiment;

    /**
     * Optional user provided barcode for the plate
     */
    private String barcode;

    private List<Label> labels;

    /**
     * A plate consists of Wells in which the compounds we want to test are placed.
     * Each well is uniquely identified by a row,col coordinate within this plate
     * so we'll store them according to their coordinate.
     */
    @XmlJavaTypeAdapter(value=WellAdapter.class) // custom JAXB adapter to convert the map to an array and back again
    private Map<Coordinate, Well> wells = new LinkedHashMap<>();

    /**
     * Every plate has a plate type that identifies its size, orientation, and manufacturer.
     * There may be 100's of these plate types in the system
     */
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PlateType plateType;

    public Plate() {}

    public Plate(String name, PlateType type) {
        setName(name);
        setPlateType(type);
    }

    /**
     * Convenience method for adding wells to this plate
     * @param wells
     * @return
     */
    public Plate withWells(Well...wells) {
        for(Well well : wells) {
            this.wells.put(well.getCoordinate(), well);
        }
        return this;
    }

    @Generated(value = "generated by IDE")
    public String getBarcode() {
        return barcode;
    }

    @Generated(value = "generated by IDE")
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Generated(value = "generated by IDE")
    public Plate withBarcode(final String barcode) {
        this.barcode = barcode;
        return this;
    }

    @Generated(value = "generated by IDE")
    public Map<Coordinate, Well> getWells() {
        return wells;
    }

    @Generated(value = "generated by IDE")
    public void setWells(Map<Coordinate, Well> wells) {
        this.wells = wells;
    }

    @Generated(value = "generated by IDE")
    public Experiment getExperiment() {
        return experiment;
    }

    @Generated(value = "generated by IDE")
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    @Generated(value = "generated by IDE")
    public Plate withExperiment(final Experiment experiment) {
        this.experiment = experiment;
        return this;
    }

    @Generated(value = "generated by IDE")
    public List<Label> getLabels() {
        return labels;
    }

    @Generated(value = "generated by IDE")
    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Generated(value = "generated by IDE")
    public PlateType getPlateType() {
        return plateType;
    }

    @Generated(value = "generated by IDE")
    public void setPlateType(PlateType plateType) {
        this.plateType = plateType;
    }

    @Generated(value = "generated by IDE")
    public Plate withPlateType(final PlateType plateType) {
        this.plateType = plateType;
        return this;
    }
}
