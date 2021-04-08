package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.parameterDataType.ParameterDataType
import com.backend.cheezeapi.property.Property
import javax.persistence.*

@Entity
data class FormalParameter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val property: Property? = null,

    @Column(nullable = false)
    val value: String? = null,

    val groupId: Long? = null,

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val parameterDataType: ParameterDataType? = null,

    @Column(nullable = false)
    val isNote: Boolean? = null
)