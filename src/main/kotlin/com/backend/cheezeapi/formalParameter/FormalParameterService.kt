package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.groupId.GroupIdRepository
import com.backend.cheezeapi.parameterDataType.ParameterDataType
import com.backend.cheezeapi.property.Property
import org.springframework.stereotype.Service

@Service
class FormalParameterService(
    private val formalParameterRepository: FormalParameterRepository,
    private val groupIdRepository: GroupIdRepository
) {
    fun saveAll(groupFormalParametersDto: GroupFormalParametersDto): MutableList<FormalParameterDto> {
        val result = mutableListOf<FormalParameterDto>()

        val groupId = groupFormalParametersDto.groupId ?: groupIdRepository.getNewGroupIdFormalParameter()

        groupFormalParametersDto.parameters?.forEach {
            result.add(
                FormalParameterDto.toDto(
                    formalParameterRepository.save(
                        FormalParameter(
                            id = it.id,
                            property = Property(
                                id = it.property?.id ?: error("ИД свойства не задан")
                            ),
                            parameterDataType = ParameterDataType(
                                id = it.parameterDataType?.id ?: error("ИД типа формального параметра не задан")
                            ),
                            value = it.value ?: error("Не задано название формального параметра"),
                            groupId = groupId,
                            isNote = it.isNote ?: error("Нет задан тип параметра, примечание или нет?")
                        )
                    )
                )
            )
        }
        return result
    }

    fun delete(id: Long) = formalParameterRepository.deleteById(id)

    fun getOne(id: Long): FormalParameterDto = FormalParameterDto.toDto(formalParameterRepository.getOne(id))

    fun findAll(): List<FormalParameterDto> = formalParameterRepository.findAll().map(FormalParameterDto::toDto)

    fun getByIdProperty(id: Long): List<FormalParameterDto> =
        formalParameterRepository.findByPropertyId(id).map(FormalParameterDto::toDto)
}