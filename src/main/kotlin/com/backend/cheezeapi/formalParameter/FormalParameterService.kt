package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.factParameter.FactParameterRepository
import com.backend.cheezeapi.groupId.GroupIdRepository
import com.backend.cheezeapi.parameterDataType.ParameterDataType
import com.backend.cheezeapi.property.Property
import com.backend.cheezeapi.property.PropertyDto
import com.backend.cheezeapi.property.PropertyService
import com.backend.cheezeapi.property.PropertyWithFormalParameterDto
import org.springframework.stereotype.Service

@Service
class FormalParameterService(
    private val formalParameterRepository: FormalParameterRepository,
    private val factParameterRepository: FactParameterRepository,
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

    fun delete(id: Long) = formalParameterRepository.deleteById(id)

    fun getOne(id: Long): FormalParameterDto = FormalParameterDto.toDto(formalParameterRepository.getOne(id))

    fun findAll(): List<FormalParameterDto> = formalParameterRepository.findAll().map(FormalParameterDto::toDto)

    fun getByIdProperty(id: Long): List<FormalParameterDto> =
        formalParameterRepository.findByPropertyId(id).map(FormalParameterDto::toDto)

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

    fun inUse(id: Long): Boolean = factParameterRepository.findByFormalParameterId(id).isNotEmpty()
}