package com.backend.cheezeapi.strain.type

data class StrainTypeDto (
        var id: Long? = null,
        var name: String? = null
) {
    companion object {
        fun toDto(strainType: StrainType): StrainTypeDto =
                StrainTypeDto(
                        id = strainType.id,
                        name = strainType.name
                )
    }
}