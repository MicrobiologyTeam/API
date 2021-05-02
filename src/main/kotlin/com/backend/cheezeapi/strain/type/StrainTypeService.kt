package com.backend.cheezeapi.strain.type

import com.backend.cheezeapi.strain.genus.StrainGenus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StrainTypeService(
    private val strainTypeRepository: StrainTypeRepository
) {
    @Transactional
    fun save(strainTypeDto: StrainTypeDto): StrainTypeDto =
        StrainTypeDto.toDto(
            strainTypeRepository.saveAndFlush(
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

    fun findByGenusId(id: Long): List<StrainTypeDto> = strainTypeRepository.findByGenusId(id).map {
        StrainTypeDto.toDto(it).copy(genus = null)
    }
}