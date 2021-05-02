package com.backend.cheezeapi.strain.type

import com.backend.cheezeapi.strain.genus.StrainGenus
import javax.persistence.*

@Entity
data class StrainType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val name: String? = null,

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val genus: StrainGenus? = null
)