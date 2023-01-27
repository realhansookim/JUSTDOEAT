package com.team5.justdoeat.store.entity;



import org.hibernate.annotations.DynamicInsert;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "store_image")
public class StoreImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="simg_seq") private Long simgSeq;
  @Column(name = "simg_file_name") private String simgFileName;
  @Column(name ="simg_uri") private String simgUri;

}
