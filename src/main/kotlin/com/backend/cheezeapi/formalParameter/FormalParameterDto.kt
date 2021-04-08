package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.parameterDataType.ParameterDataTypeDto
import com.backend.cheezeapi.property.PropertyDto

data class FormalParameterDto(
    val id: Long? = null,
    val property: PropertyDto? = null,
    val value: String? = null,
    val parameterDataType: ParameterDataTypeDto? = null,
    val groupId: Long? = null,
    val isNote: Boolean? = null
) {
    companion object {
        fun toDto(formalParameter: FormalParameter): FormalParameterDto =
            FormalParameterDto(
                id = formalParameter.id,
                property = formalParameter.property?.let { PropertyDto.toDto(it) },
                value = formalParameter.value,
                parameterDataType = formalParameter.parameterDataType?.let { ParameterDataTypeDto.toDto(it) },
                groupId = formalParameter.groupId,
                isNote = formalParameter.isNote
            )
    }
}
