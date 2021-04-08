package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.parameterDataType.ParameterDataType
import com.backend.cheezeapi.property.Property
import org.springframework.stereotype.Service

@Service
class FormalParameterService(
    private val formalParameterRepository: FormalParameterRepository
) {
    fun save(formalParameterDto: FormalParameterDto): FormalParameterDto = FormalParameterDto.toDto(
        formalParameterRepository.save(
            FormalParameter(
                id = formalParameterDto.id,
                property = Property(
                    id = formalParameterDto.property?.id ?: error("ИД формального параметра не задан")
                ),
                parameterDataType = ParameterDataType(
                    id = formalParameterDto.parameterDataType?.id ?: error("ИД типа формального параметра не задан")
                ),
                value = formalParameterDto.value ?: error("Не задано название формального параметра"),
                groupId = formalParameterDto.groupId,
                isNote = formalParameterDto.isNote ?: error("Нет задан тип параметра, примечание или нет?")
            )
        )
    )

    fun delete(id: Long) = formalParameterRepository.deleteById(id)

    fun getOne(id: Long): FormalParameterDto = FormalParameterDto.toDto(formalParameterRepository.getOne(id))

    fun findAll(): List<FormalParameterDto> = formalParameterRepository.findAll().map(FormalParameterDto::toDto)
}