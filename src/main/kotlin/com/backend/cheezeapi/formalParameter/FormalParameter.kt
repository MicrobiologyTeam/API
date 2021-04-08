package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.parameterDataType.ParameterDataType
import com.backend.cheezeapi.property.Property
import javax.persistence.*

@Entity
data class FormalParameter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    val property: Property? = null,
    val value: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val parameterDataType: ParameterDataType? = null,
    val groupId: Long? = null,
    val isNote: Boolean
)