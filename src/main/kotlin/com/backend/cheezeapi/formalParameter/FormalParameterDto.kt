package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.parameterDataType.ParameterDataTypeDto
import com.backend.cheezeapi.property.PropertyDto

data class FormalParameterDto(
    val id: Long? = null,
    val property: PropertyDto? = null,
    val name: String? = null,
    val parameterDataType: ParameterDataTypeDto? = null,
    val groupId: Long? = null,
    val isNote: Boolean? = null
) {
    companion object {
        fun toDto(formalParameter: FormalParameter): FormalParameterDto =
            FormalParameterDto(
                id = formalParameter.id,
                property = formalParameter.property?.let { PropertyDto.toDto(it) },
                name = formalParameter.name,
                parameterDataType = formalParameter.parameterDataType?.let { ParameterDataTypeDto.toDto(it) },
                groupId = formalParameter.groupId
            )
    }
}

data class FormalParametersDto(
    val id: Long? = null,
    val ungrouped: List<FormalParameterDto>? = null,
    val groups: List<GroupFormalParametersDto>? = null
)

data class GroupFormalParametersDto(
    val groupId: Long? = null,
    val parameters: List<FormalParameterDto>? = null
)