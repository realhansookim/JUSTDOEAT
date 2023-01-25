package com.team5.justdoeat.menu.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.justdoeat.store.entity.StoreInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_info")
@Builder
public class MenuInfoEntity {
  // @ManyToMany(mappedBy = "mccMiSeq") List<MenuCateConnectEntity> category = new ArrayList<>();
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mi_seq") private Long miSeq;
  @Column(name = "mi_name") private String miName;
  @Column(name = "mi_additional_ex") private String miAdditionalEx;
  @Column(name = "mi_price") private Integer miPrice;
  @Column(name = "mi_img") private String miImg;
  @Column(name = "mi_si_seq") private Long miSiSeq;
  // @OneToMany(mappedBy = "moMiSeq") List<MenuOptionEntity> option = new ArrayList<>();
  // @ManyToOne @JoinColumn(name = "mi_si_seq") StoreInfoEntity storeInfo;  
}
