package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameterDto
import com.backend.cheezeapi.strain.type.StrainTypeDto
import java.util.*

data class StrainDto(
        var id: Long? = null,
        var name: String? = null,
        var dateReceiving: Date? = null,
        var collectionIndex: String? = null,
        var source: String? = null,
        var creator: String? = null,
        var dateAdded: Date? = null,
        var type: StrainTypeDto? = null,
        var obtainingMethod: String? = null,
        var factParameters: Set<FactParameterDto>? = null
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
                    type = StrainTypeDto.toDto(strain.type),
                    obtainingMethod = strain.obtainingMethod
            )
    }
}