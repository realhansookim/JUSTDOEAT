package com.team5.justdoeat.menu.entity;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "menu_search")
public class MenuSearchEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "si_seq") private Long siSeq;
  @Column(name = "si_name") private String siName;
  @Column(name = "mi_name") @JsonIgnore private String miName;
}
