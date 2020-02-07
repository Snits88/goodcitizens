package com.goodcitizens.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitizenListTO {

    private Long totalItems;

    private List<CitizenTO> citizens;

}
