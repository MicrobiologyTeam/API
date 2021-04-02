package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameterService
import com.backend.cheezeapi.strain.type.StrainType
import org.springframework.stereotype.Service

@Service
class StrainService(
        private val strainRepository: StrainRepository,
        private val factParameterService: FactParameterService
) {
    fun save(strainDto: StrainDto): StrainDto =
            StrainDto.toDto(
                    strainRepository.save(
                            Strain(
                                    id = null,
                                    name = strainDto.name ?: error("Не задан name"),
                                    dateReceiving = strainDto.dateReceiving ?: error("Не задан dateReceiving"),
                                    collectionIndex = strainDto.collectionIndex ?: error("Не задан collectionIndex"),
                                    source = strainDto.source ?: error("Не задан source"),
                                    creator = strainDto.creator ?: error("Не задан creator"),
                                    dateAdded = strainDto.dateAdded ?: error("Не задан dateAdded"),
                                    type = strainDto.type?.let {
                                        StrainType(
                                                name = it.name ?: error("Не задан type.name")
                                        )
                                    } ?: error("Не задан type"),
                                    obtainingMethod = strainDto.obtainingMethod ?: error("Не задан obtainingMethod"),
                            )
                    )
            )


    fun deleteById(strainId: Long) {
        factParameterService.deleteByStrainId(strainId)
        strainRepository.deleteById(strainId)
    }
}