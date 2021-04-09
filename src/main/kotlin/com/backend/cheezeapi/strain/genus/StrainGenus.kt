package com.backend.cheezeapi.strain.genus

import javax.persistence.*

@Entity
data class StrainGenus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String? = null
)