package com.team5.justdoeat.menu.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_cate_connect")
public class MenuCateConnectEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcc_seq") private Long mccSeq;
    // @OneToMany(mappedBy = "mcaSeq") List<MenuCategoryEntity> category = new ArrayList<>();
    @ManyToOne @JoinColumn(name = "mcc_mca_seq") MenuCategoryEntity menuCategory;
    // @Column(name = "mcc_mca_seq") private Long mccMcaSeq;
    @ManyToOne @JoinColumn(name = "mcc_mi_seq") MenuInfoEntity menuInfo;
    // @Column(name = "mcc_mi_seq") @JsonIgnore private Long mccMiSeq;
    // @OneToMany(mappedBy = "miSeq") List<MenuInfoEntity> menu = new ArrayList<>();
}