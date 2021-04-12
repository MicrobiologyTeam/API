package com.backend.cheezeapi.strain.type

import com.backend.cheezeapi.strain.genus.StrainGenus
import org.springframework.stereotype.Service

@Service
class StrainTypeService(
    private val strainTypeRepository: StrainTypeRepository
) {
    fun save(strainTypeDto: StrainTypeDto): StrainTypeDto =
        StrainTypeDto.toDto(
            strainTypeRepository.save(
                StrainType(
                    id = strainTypeDto.id,
                    name = strainTypeDto.name ?: error("Не задано имя для типа штамма"),
                    genus = StrainGenus(
                        id = strainTypeDto.genus?.id ?: error("Не задан id рода")
                    )
                )
            )
        )

    fun deleteById(id: Long) = strainTypeRepository.deleteById(id)

    fun findAll(): List<StrainTypeDto> =
        strainTypeRepository.findAll().map {
            StrainTypeDto.toDto(it)
        }

}