package com.ta.c02_5.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUpdateItemDetail {
    @JsonProperty("idItem")
    private String id_item;

    @JsonProperty("idKategori")
    private int id_kategori;

    @JsonProperty("tambahanStok")
    private int tambahan_stok;

    @JsonProperty("idCabang")
    private int id_cabang;

}
