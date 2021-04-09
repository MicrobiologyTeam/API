package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameter
import com.backend.cheezeapi.strain.Strain
import javax.persistence.*

@Entity
data class FactParameter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val strain: Strain? = null,

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val formalParameter: FormalParameter? = null,

    val value: String? = null,

    val reserve: String? = null,

    val groupId: Long? = null
)