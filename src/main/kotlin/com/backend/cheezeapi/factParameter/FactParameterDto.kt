package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameterDto
import com.backend.cheezeapi.strain.StrainDto

data class FactParameterDto(
        var id: Long? = null,
        var strain: StrainDto? = null,
        var formalParameter: FormalParameterDto? = null,
        var value: String? = null,
        var reserve: String? = null,
        var groupId: Long? = null
) {
    companion object {
        fun toDto(factParameter: FactParameter): FactParameterDto =
                FactParameterDto(
                        id = factParameter.id,
                        strain = null,
                        formalParameter = FormalParameterDto.toDto(factParameter.formalParameter),
                        value = factParameter.value,
                        reserve = factParameter.reserve,
                        groupId = factParameter.groupId
                )
    }
}
