package com.backend.cheezeapi.parameterDataType

data class ParameterDataTypeDto(
        var id: Long? = null,
        var name: String? = null
) {
    companion object {
        fun toDto(parameterDataType: ParameterDataType): ParameterDataTypeDto =
                ParameterDataTypeDto(
                        id = parameterDataType.id,
                        name = parameterDataType.name,
                )
    }
}
