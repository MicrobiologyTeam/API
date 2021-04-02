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
        val property: Property,
        val value: String,
        @ManyToOne(fetch = FetchType.LAZY)
        val parameterDataType: ParameterDataType,
        val groupId: Long
)