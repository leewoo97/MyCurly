package com.dldnwls.mycurly.domain.inventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; //상품명

    @Column(name = "price", nullable = false)
    private String price; //가격

    @Column(name = "deliveryMethod", nullable = false)
    private String deliveryMethod; //배송방식(샛별배송 또는 일반배송)

    @Column(name = "gram", nullable = false)
    private String gram; //중량

    @Column(name = "link", nullable = false)
    private String link; //상품 링크
}
