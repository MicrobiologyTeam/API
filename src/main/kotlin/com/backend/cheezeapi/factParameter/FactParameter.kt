package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameter
import com.backend.cheezeapi.strain.Strain
import javax.persistence.*

@Entity
data class FactParameter(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @ManyToOne(fetch = FetchType.LAZY)
        val strain: Strain,
        @ManyToOne(fetch = FetchType.LAZY)
        val formalParameter: FormalParameter,
        val value: String,
        val reserve: String,
        val groupId: Long
)