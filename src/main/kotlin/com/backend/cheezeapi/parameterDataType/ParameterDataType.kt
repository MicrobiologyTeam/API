package com.backend.cheezeapi.parameterDataType

import javax.persistence.*

@Entity
data class ParameterDataType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val name: String? = null
)
