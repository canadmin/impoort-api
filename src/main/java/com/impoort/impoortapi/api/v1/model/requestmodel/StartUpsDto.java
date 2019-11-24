package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
public class StartUpsDto {

    @JsonProperty("companyId")
    private String userId;
    @JsonProperty("companyName")
    private String firstName;

}
