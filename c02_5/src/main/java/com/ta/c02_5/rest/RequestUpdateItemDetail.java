package com.ta.c02_5.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUpdateItemDetail {
    @JsonProperty
    private int id_kategori;

    @JsonProperty
    private int tambahan_stok;

    @JsonProperty
    private String tanggal_request;

    @JsonProperty
    private int id_cabang;

    @JsonProperty
    private String id_item;

    @JsonProperty
    private int id_delivery;
}
