package com.backend.cheezeapi.strain.genus

import org.springframework.stereotype.Service

@Service
class StrainGenusService(
    private val strainGenusRepository: StrainGenusRepository
) {
    fun save(strainGenusDto: StrainGenusDto): StrainGenusDto =
        StrainGenusDto.toDto(
            strainGenusRepository.save(
                StrainGenus(
                    id = strainGenusDto.id,
                    name = strainGenusDto.name ?: error("Не задано имя для рода штамма")
                )
            )
        )

    fun deleteById(id: Long) = strainGenusRepository.deleteById(id)

    fun findAll(): List<StrainGenusDto> = strainGenusRepository.findAll().map(StrainGenusDto::toDto)
}