package com.example.evam3.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table (name="film")
class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var title: String? = null
    var director: String? = null
    var duration: Long? = null
    var calification :Long?=null
    var investment: BigDecimal? = null
}