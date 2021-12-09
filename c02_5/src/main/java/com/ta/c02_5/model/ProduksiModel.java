package com.ta.c02_5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "produksi")
public class ProduksiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produksi", nullable = false)
    private Long idProduksi;

    @NotNull
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id_item", nullable = false)
    private String idItem;

    @NotNull
    @Column(name = "id_kategori", nullable = false)
    private Integer idKategori;

    @NotNull
    @Column(name = "id_cabang", nullable = false)
    private Integer idCabang;

    @NotNull
    @Column(name = "tambahan_stok", nullable = false)
    private Integer tambahanStok;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_produksi", nullable = false)
    private Date tanggalProduksi;

    // Relasi dengan PegawaiModel
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id_pegawai")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PegawaiModel pegawai;

    // Relasi dengan RequestUpdateItemModel
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_request_update_item", referencedColumnName = "id_request_update_item")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RequestUpdateItemModel requestUpdateItem;

    // Relasi dengan MesinModel
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mesin", referencedColumnName = "id_mesin")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MesinModel mesin;
}
