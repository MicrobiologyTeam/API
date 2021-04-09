package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameterDto
import com.backend.cheezeapi.strain.type.StrainTypeDto
import java.util.*

data class StrainDto(
    val id: Long? = null,
    val name: String? = null,
    val dateReceiving: Date? = null,
    val collectionIndex: String? = null,
    val source: String? = null,
    val creator: String? = null,
    val dateAdded: Date? = null,
    val type: StrainTypeDto? = null,
    val obtainingMethod: String? = null,
    val factParameters: Set<FactParameterDto>? = null
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