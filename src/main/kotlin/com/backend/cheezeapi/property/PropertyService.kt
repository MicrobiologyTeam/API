package com.backend.cheezeapi.property

import com.backend.cheezeapi.formalParameter.*
import com.backend.cheezeapi.groupId.GroupIdRepository
import com.backend.cheezeapi.parameterDataType.ParameterDataType
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class PropertyService(
        private val propertyRepository: PropertyRepository,
        private val formalParameterRepository: FormalParameterRepository,
        private val groupIdRepository: GroupIdRepository,
        private val propertyService: PropertyService
) {
    fun save(formalParametersDto: FormalParametersDto): PropertyWithFormalParameterDto {
        val oldFormalParameters =
                formalParameterRepository.findByPropertyId(formalParametersDto.propertyId ?: error("propertyId не задан"))
                        .toMutableList()

        val ungrouped = formalParametersDto.ungroupedParameters
                ?.map { it.copy(property = PropertyDto(formalParametersDto.propertyId)) }

        val groups = formalParametersDto.groups?.map { group ->
            val groupId = group.groupId ?: groupIdRepository.getNewGroupIdFormalParameter()
            group.parameters?.map {
                it.copy(
                        property = PropertyDto(formalParametersDto.propertyId),
                        groupId = groupId
                )
            }
        }

        val newFormalParameters = mutableListOf<FormalParameter>().apply {
            if (ungrouped != null) addAll(ungrouped.map { toSave(it) })
            groups?.forEach { group -> if (group != null) addAll(group.map { toSave(it) }) }
        }

        newFormalParameters.forEach { formalParam ->
            oldFormalParameters.removeIf { it.id == formalParam.id }
        }

        formalParameterRepository.saveAll(newFormalParameters)
        formalParameterRepository.deleteAll(oldFormalParameters)

        return propertyService.getOne(formalParametersDto.propertyId)
    }

    private fun toSave(it: FormalParameterDto) = FormalParameter(
            id = it.id,
            property = Property(
                    id = it.property?.id ?: error("ИД свойства не задан")
            ),
            parameterDataType = ParameterDataType(
                    id = it.parameterDataType?.id ?: error("ИД типа формального параметра не задан")
            ),
            name = it.name ?: error("Не задано название формального параметра"),
            groupId = it.groupId
    )

    fun save(propertyDto: PropertyDto): PropertyDto = PropertyDto.toDto(
            propertyRepository.save(
                    Property(
                            id = propertyDto.id,
                            code = propertyDto.code,
                            name = propertyDto.name ?: error("Не задано имя для свойства"),
                            isNote = propertyDto.isNote ?: error("Нет задан тип параметра, примечание или нет?")
                    )
            )
    )

    fun delete(id: Long) {
        if (formalParameterRepository.findByPropertyId(id).isEmpty()) {
            propertyRepository.deleteById(id)
        } else {
            throw ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Свойство с ID=${id} имеет формальные параметры"
            )
        }
    }

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