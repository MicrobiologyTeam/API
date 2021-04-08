package com.backend.cheezeapi.parameterDataType

data class ParameterDataTypeDto(
    val id: Long? = null,
    val name: String? = null
) {
    companion object {
        fun toDto(parameterDataType: ParameterDataType): ParameterDataTypeDto =
            ParameterDataTypeDto(
                id = parameterDataType.id,
                name = parameterDataType.name,
            )
    }
}
