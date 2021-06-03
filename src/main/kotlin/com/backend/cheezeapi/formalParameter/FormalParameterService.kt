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
    private val factParameterRepository: FactParameterRepository
) {
    fun delete(id: Long) = formalParameterRepository.deleteById(id)

    fun getOne(id: Long): FormalParameterDto = FormalParameterDto.toDto(formalParameterRepository.getOne(id))

    fun findAll(): List<FormalParameterDto> = formalParameterRepository.findAll().map(FormalParameterDto::toDto)

    fun getByIdProperty(id: Long): List<FormalParameterDto> =
        formalParameterRepository.findByPropertyId(id).map(FormalParameterDto::toDto)

    fun inUse(id: Long): Boolean = factParameterRepository.findByFormalParameterId(id).isNotEmpty()

    fun inUseBatch(ids: List<Long>): Map<Long, Boolean> =
        mutableMapOf<Long, Boolean>().apply {
            ids.forEach {
                this[it] = inUse(it)
            }
        }
}