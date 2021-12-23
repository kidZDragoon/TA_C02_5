package com.ta.c02_5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter @Getter
@Table(name = "requestupdateitem")
public class RequestUpdateItemModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request_update_item", nullable = false)
    private Integer idRequestUpdateItem;

    @NotNull
    @Column(name = "id_item", nullable = false)
    private String idItem;

    @NotNull
    @Column(name = "id_kategori", nullable = false)
    private Integer idKategori;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private Integer tambahanStok;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name= "tanggal_request", nullable = false)
    private Date tanggalRequest;

    @NotNull
    @Column(name = "id_cabang", nullable = false)
    private Integer idCabang;

    @Column(name = "executed")
    private boolean executed;

    @Column(name = "id_delivery")
    private Integer idDelivery;

    // Relasi dengan ProduksiModel
    @OneToOne(mappedBy = "requestUpdateItem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProduksiModel produksi;

    // Relasi dengan DeliveryModel
    @OneToOne(mappedBy = "requestUpdateItem")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DeliveryModel delivery;
}
