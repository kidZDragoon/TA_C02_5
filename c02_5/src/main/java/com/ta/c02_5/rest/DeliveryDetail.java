package com.ta.c02_5.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryDetail {
    @JsonProperty
    private String idCabang;

    @JsonProperty
    private String alamat;
}