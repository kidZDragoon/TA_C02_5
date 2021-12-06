package com.ta.c02_5.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDetail {
    @JsonProperty
    private String uuid;

    @JsonProperty
    private String nama;

    @JsonProperty
    private Integer harga;

    @JsonProperty
    private Integer stok;

    @JsonProperty
    private String kategori;
}
