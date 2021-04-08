package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.parameterDataType.ParameterDataTypeDto
import com.backend.cheezeapi.property.PropertyDto

data class FormalParameterDto(
    var id: Long? = null,
    var property: PropertyDto? = null,
    var value: String? = null,
    var parameterDataType: ParameterDataTypeDto? = null,
    var groupId: Long? = null
) {
    companion object {
        fun toDto(formalParameter: FormalParameter): FormalParameterDto =
            FormalParameterDto(
                id = formalParameter.id,
                property = PropertyDto.toDto(formalParameter.property ?: error("Не задан Property")),
                value = formalParameter.value,
                parameterDataType = ParameterDataTypeDto.toDto(
                    formalParameter.parameterDataType
                        ?: error("Не задан parameterDataType")
                ),
                groupId = formalParameter.groupId,
            )
    }
}
