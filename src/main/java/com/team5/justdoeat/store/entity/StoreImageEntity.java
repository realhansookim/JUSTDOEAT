package com.team5.justdoeat.store.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "store_image")
public class StoreImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="simg_seq") private Long simgSeq;
  @Column(name ="simg_url") private String simgUrl;
  @Column(name ="simg_order") private Integer simgOrder;
  @Column(name ="simg_si_seq") private Long simgSiSeq;
  // @ManyToOne @JoinColumn(name = "simg_si_seq") StoreInfoEntity storeInfo;

}
