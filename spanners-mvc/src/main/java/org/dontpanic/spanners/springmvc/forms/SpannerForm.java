package org.dontpanic.spanners.springmvc.forms;

import org.dontpanic.spanners.springmvc.domain.Spanner;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * A form that allows editing of spanner attributes
 * User: Stevie
 * Date: 22/10/13
 */
public class SpannerForm {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_SIZE = "size";

    private Long id;
    @Size(min=1, max=255)
    private String name;
    @Min(1) @Max(99)
    private int size;

    public SpannerForm() {
    }

    /**
     * Create a spanner form with values initialized to given spanner
     */
    public SpannerForm(Spanner spanner) {
        this.id = spanner.getId();
        this.name = spanner.getName();
        this.size = spanner.getSize();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
