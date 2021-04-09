package com.backend.cheezeapi.strain.genus

data class StrainGenusDto(
    val id: Long? = null,
    val name: String? = null
) {
    companion object {
        fun toDto(strainGenus: StrainGenus): StrainGenusDto =
            StrainGenusDto(
                id = strainGenus.id,
                name = strainGenus.name
            )
    }
}