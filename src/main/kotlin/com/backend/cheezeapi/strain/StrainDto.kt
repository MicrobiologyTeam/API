package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameterDto
import com.backend.cheezeapi.strain.type.StrainTypeDto
import java.time.LocalDate

data class StrainDto(
    val id: Long? = null,
    val name: String? = null,
    val dateReceiving: LocalDate? = null,
    val collectionIndex: String? = null,
    val source: String? = null,
    val creator: String? = null,
    val dateAdded: LocalDate? = null,
    val type: StrainTypeDto? = null,
    val obtainingMethod: String? = null,
    val properties: List<StrainPropertiesDto>? = null
) {
    companion object {
        fun toDto(strain: Strain): StrainDto =
            StrainDto(
                id = strain.id,
                name = strain.name,
                dateReceiving = strain.dateReceiving,
                collectionIndex = strain.collectionIndex,
                source = strain.source,
                creator = strain.creator,
                dateAdded = strain.dateAdded,
                type = strain.type?.let { StrainTypeDto.toDto(it) },
                obtainingMethod = strain.obtainingMethod
            )
    }
}

data class StrainPropertiesDto(
    val propertyId: Long? = null,
    val propertyName: String? = null,
    val ungroupedParameters: List<FactParameterDto>? = null,
    val groups: List<GroupFactParametersDto>? = null
)

data class GroupFactParametersDto(
    val groupId: Long? = null,
    val parameters: List<FactParameterDto>? = null
)