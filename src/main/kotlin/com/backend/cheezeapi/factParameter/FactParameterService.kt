package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameter
import com.backend.cheezeapi.formalParameter.FormalParameterRepository
import com.backend.cheezeapi.property.Property
import com.backend.cheezeapi.strain.Strain
import org.hibernate.Session
import org.springframework.stereotype.Service

@Service
class FactParameterService(
    private val factParameterRepository: FactParameterRepository,
) {
    fun saveAll(factParameterSet: Set<FactParameterDto>, strain: Strain): List<FactParameterDto> =
        factParameterRepository.saveAll(
            factParameterSet.map {
                toSave(it)
            }
        ).map {
            FactParameterDto.toDto(it)
        }

    fun save(
        strainId: Long,
        ungrouped: List<FactParameterDto>?,
        groups: List<List<FactParameterDto>>?
    ) {
        val oldFactParams = factParameterRepository.findByStrainId(strainId).toMutableList()
        val newFactParams = mutableListOf<FactParameter>().apply {
            if (ungrouped != null) addAll(ungrouped.map { toSave(it) })
            groups?.forEach { group -> addAll(group.map { toSave(it) }) }
        }.toMutableList()

        newFactParams.forEach { factParam ->
            oldFactParams.removeIf { it.id == factParam.id }
        }

        factParameterRepository.saveAll(newFactParams)
        factParameterRepository.deleteAll(oldFactParams)

    }

    fun deleteByStrainId(strainId: Long) {
        factParameterRepository.deleteByStrainId(strainId)
    }

    private fun toSave(it: FactParameterDto) = FactParameter(
        id = it.id,
        strain = it.strain?.let { strainDto -> Strain(strainDto.id) },
        formalParameter = FormalParameter(
            id = it.formalParameter?.id ?: error("Не задан formalParameter.id"),
            property = Property(id = it.formalParameter.property?.id),
        ),
        value = it.value,
        reserve = it.reserve,
        groupId = it.groupId
    )
}
