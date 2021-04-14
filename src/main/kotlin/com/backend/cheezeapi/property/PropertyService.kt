package com.backend.cheezeapi.property

import com.backend.cheezeapi.formalParameter.FormalParameterDto
import com.backend.cheezeapi.formalParameter.FormalParameterRepository
import com.backend.cheezeapi.formalParameter.GroupFormalParametersDto
import org.springframework.stereotype.Service

@Service
class PropertyService(
    private val propertyRepository: PropertyRepository,
    private val formalParameterRepository: FormalParameterRepository
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

    fun getOne(id: Long): PropertyWithFormalParameterDto {
        val property = propertyRepository.getOne(id)
        return makePropertyWithFormalParameterDto(property)
    }

    fun findAll(): List<PropertyDto> = propertyRepository.findAll().map(PropertyDto::toDto)

    fun findAllWithFormalParameters(): List<PropertyWithFormalParameterDto> =
        propertyRepository.findAll().map { property ->
            makePropertyWithFormalParameterDto(property)
        }

    private fun makePropertyWithFormalParameterDto(property: Property): PropertyWithFormalParameterDto {
        val formalParameters =
            property.id?.let { formalParameterRepository.findByPropertyId(it).map(FormalParameterDto::toDto) }
                ?: listOf()
        val map = formalParameters.groupBy { it.groupId == null }
        val ungrouped = map[true]
        val groups = map[false]?.groupBy { it.groupId }

        return PropertyWithFormalParameterDto.toDto(
            property = property,
            ungrouped = ungrouped,
            groups = groups?.map { group ->
                GroupFormalParametersDto(
                    groupId = group.key,
                    parameters = group.value
                )
            }
        )
    }
}