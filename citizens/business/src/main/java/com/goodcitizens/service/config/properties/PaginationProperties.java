package com.goodcitizens.service.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "readlist.pagination")
@Component("paginationProperties")
public class PaginationProperties {

    private Integer offset;

    private Integer limit;

}
