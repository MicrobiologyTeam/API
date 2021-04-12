package com.backend.cheezeapi.property

import com.backend.cheezeapi.formalParameter.FormalParameterDto

data class PropertyWithFormalParameterDto(
    val id: Long? = null,
    val code: String? = null,
    val name: String? = null,
    val formalParameters: List<FormalParameterDto>? = null
) {
    companion object {
        fun toDto(property: Property, formalParameters: List<FormalParameterDto>) =
            PropertyWithFormalParameterDto(
                id = property.id,
                code = property.code,
                name = property.name,
                formalParameters = formalParameters.map { it.copy(property = null) }
            )
    }
}
