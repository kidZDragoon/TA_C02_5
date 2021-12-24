package com.ta.c02_5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "delivery")
public class DeliveryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delivery", nullable = false)
    private Integer idDelivery;

    @NotNull
    @Column(name = "id_kurir", nullable = false)
    private Integer idKurir;

    @NotNull
    @Column(name = "id_cabang", nullable = false)
    private Integer idCabang;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name= "tanggal_dibuat", nullable = false)
    private Date tanggalDibuat;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name= "tanggal_dikirim")
    private Date tanggalDikirim;

    @NotNull
    @Column(name = "sent", nullable = false)
    private boolean sent;

    // Relasi dengan RequestUpdateItemModel
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_request_update_item", referencedColumnName = "id_request_update_item")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RequestUpdateItemModel requestUpdateItem;

    // Request dengan PegawaiModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PegawaiModel pegawai;

}
