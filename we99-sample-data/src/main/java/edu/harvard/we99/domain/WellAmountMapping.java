package edu.harvard.we99.domain;

import javax.annotation.Generated;

/**
 * @author alan orcharton
 */
public class WellAmountMapping {
    private WellType type;
    private String wellLabelName;
    private int row;
    private int col;
    private Double number;
    private DoseUnit units;


    @Generated("generated by IDE")
    public Double getNumber() {
        return number;
    }

    @Generated("generated by IDE")
    public void setNumber(Double number) {
        this.number = number;
    }

    @Generated("generated by IDE")
    public DoseUnit getUnits() {
        return units;
    }

    @Generated("generated by IDE")
    public void setUnits(DoseUnit units) {
        this.units = units;
    }

    @Generated("generated by IDE")
    public WellType getType() {
        return type;
    }

    @Generated("generated by IDE")
    public void setType(WellType type) {
        this.type = type;
    }

    @Generated("generated by IDE")
    public String getWellLabelName() {
        return wellLabelName;
    }

    @Generated("generated by IDE")
    public void setWellLabelName(String wellLabelName) {
        this.wellLabelName = wellLabelName;
    }


    @Generated("generated by IDE")
    public int getRow() {
        return row;
    }

    @Generated("generated by IDE")
    public void setRow(int row) {
        this.row = row;
    }

    @Generated("generated by IDE")
    public int getCol() {
        return col;
    }

    @Generated("generated by IDE")
    public void setCol(int col) {
        this.col = col;
    }
}