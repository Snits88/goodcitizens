package com.goodcitizens.to;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitizenListTO {

    private Integer totalItems;

    private List<CitizenTO> citizens;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<CitizenTO> getCitizens() {
        return Collections.unmodifiableList(citizens);
    }

    public void setCitizens(List<CitizenTO> citizens) {
        this.citizens = citizens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitizenListTO)) return false;
        CitizenListTO that = (CitizenListTO) o;
        return getTotalItems().equals(that.getTotalItems()) &&
                getCitizens().equals(that.getCitizens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalItems(), getCitizens());
    }

    @Override
    public String toString() {
        return "CitizenListTO{" +
                "totalItems=" + totalItems +
                ", citizens=" + citizens +
                '}';
    }
}
