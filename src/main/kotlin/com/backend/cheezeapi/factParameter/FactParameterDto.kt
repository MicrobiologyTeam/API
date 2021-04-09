package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameterDto
import com.backend.cheezeapi.strain.StrainDto

data class FactParameterDto(
    val id: Long? = null,
    val strain: StrainDto? = null,
    val formalParameter: FormalParameterDto? = null,
    val value: String? = null,
    val reserve: String? = null,
    val groupId: Long? = null
) {
    companion object {
        fun toDto(factParameter: FactParameter): FactParameterDto =
            FactParameterDto(
                id = factParameter.id,
                strain = null,
                formalParameter = factParameter.formalParameter?.let { FormalParameterDto.toDto(it) },
                value = factParameter.value,
                reserve = factParameter.reserve,
                groupId = factParameter.groupId
            )
    }
}
