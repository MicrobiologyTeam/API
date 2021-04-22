package com.backend.cheezeapi.property

import javax.persistence.*

@Entity
data class Property(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val code: String? = null,

    @Column(unique = true, nullable = false)
    val name: String? = null,

    @Column(nullable = false)
    val isNote: Boolean? = null
)
