package com.backend.cheezeapi.strain.type

data class StrainTypeDto(
    val id: Long? = null,
    val name: String? = null
) {
    companion object {
        fun toDto(strainType: StrainType): StrainTypeDto =
            StrainTypeDto(
                id = strainType.id,
                name = strainType.name
            )
    }
}