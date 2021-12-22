package com.ta.c02_5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemModel {
    private String nama;

    private Integer harga;

    private Integer stok;

    private String kategori;
}
