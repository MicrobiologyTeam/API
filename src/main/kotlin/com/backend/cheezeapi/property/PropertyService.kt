package com.backend.cheezeapi.property

import com.backend.cheezeapi.formalParameter.FormalParameterService
import org.springframework.stereotype.Service

@Service
class PropertyService(
    private val propertyRepository: PropertyRepository,
    private val formalParameterService: FormalParameterService
) {
    fun save(propertyDto: PropertyDto): PropertyDto = PropertyDto.toDto(
        propertyRepository.save(
            Property(
                id = propertyDto.id,
                code = propertyDto.code,
                name = propertyDto.name ?: error("Не задано имя для свойства")
            )
        )
    )

    fun delete(id: Long) = propertyRepository.deleteById(id)

    fun getOne(id: Long): PropertyDto = PropertyDto.toDto(propertyRepository.getOne(id))

    fun findAll(): List<PropertyDto> = propertyRepository.findAll().map(PropertyDto::toDto)

    fun findAllWithFormalParameters(): List<PropertyWithFormalParameterDto> {
        return propertyRepository.findAll().map {
            PropertyWithFormalParameterDto.toDto(
                property = it,
                formalParameters = it.id?.let { it1 -> formalParameterService.getByIdProperty(it1) } ?: listOf()
            )
        }
    }
}