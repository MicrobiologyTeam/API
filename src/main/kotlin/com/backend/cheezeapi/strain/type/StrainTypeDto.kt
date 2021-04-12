package com.backend.cheezeapi.strain.type

import com.backend.cheezeapi.strain.genus.StrainGenusDto

data class StrainTypeDto(
    val id: Long? = null,
    val name: String? = null,
    val genus: StrainGenusDto? = null
) {
    companion object {
        fun toDto(strainType: StrainType): StrainTypeDto =
            StrainTypeDto(
                id = strainType.id,
                name = strainType.name,
                genus = strainType.genus?.let { StrainGenusDto.toDto(it) }
            )
    }
}