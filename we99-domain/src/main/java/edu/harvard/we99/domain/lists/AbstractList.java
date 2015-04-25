package edu.harvard.we99.domain.lists;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mford
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractList<T,L> {

    private Long totalCount;
    private int page;
    private int pageSize;

    private List<T> values = new ArrayList<>();

    public AbstractList(Long totalCount, int page, int pageSize, List<T> values) {
        this.values.addAll(values);
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
    }

    public AbstractList() {
    }

    public int size() {return values.size();}

    @Generated("generated by IDE")
    public Long getTotalCount() {
        return totalCount;
    }

    @Generated("generated by IDE")
    public L setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        return (L) this;
    }

    @Generated("generated by IDE")
    public int getPage() {
        return page;
    }

    @Generated("generated by IDE")
    public L setPage(int page) {
        this.page = page;
        return (L) this;
    }

    @Generated("generated by IDE")
    public List<T> getValues() {
        return values;
    }

    @Generated("generated by IDE")
    public L setValues(List<T> values) {
        this.values = values;
        return (L) this;
    }

    @Generated("generated by IDE")
    public int getPageSize() {
        return pageSize;
    }

    @Generated("generated by IDE")
    public L setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return (L) this;
    }
}
