package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameter
import com.backend.cheezeapi.strain.Strain
import org.springframework.stereotype.Service

@Service
class FactParameterService(
    private val factParameterRepository: FactParameterRepository,
) {
    fun saveAll(factParameterSet: Set<FactParameterDto>, strain: Strain): List<FactParameterDto> =
        factParameterRepository.saveAll(
            factParameterSet.map {
                FactParameter(
                    id = it.id,
                    strain = strain,
                    formalParameter = FormalParameter(
                        id = it.formalParameter?.id ?: error("Не задан formalParameter.id")
                    ),
                    value = it.value,
                    reserve = it.reserve,
                    groupId = it.groupId
                )
            }
        ).map {
            FactParameterDto.toDto(it)
        }

    fun save(strainId: Long, ungrouped: List<FactParameterDto>?, groups: List<List<FactParameterDto>>?) {
        val oldFactParams = factParameterRepository.findByStrainId(strainId).toMutableList()
        val newFactParams = mutableListOf<FactParameter>().apply {
            if (ungrouped != null) {
                addAll(ungrouped.map {
                    FactParameter(
                        id = it.id,
                        strain = it.strain?.let { strainDto -> Strain(strainDto.id) },
                        formalParameter = FormalParameter(
                            id = it.formalParameter?.id ?: error("Не задан formalParameter.id")
                        ),
                        value = it.value,
                        reserve = it.reserve,
                        groupId = it.groupId
                    )
                })
            }
            groups?.forEach { group ->
                addAll(group.map {
                    FactParameter(
                        id = it.id,
                        strain = it.strain?.let { strainDto -> Strain(strainDto.id) },
                        formalParameter = FormalParameter(
                            id = it.formalParameter?.id ?: error("Не задан formalParameter.id")
                        ),
                        value = it.value,
                        reserve = it.reserve,
                        groupId = it.groupId
                    )
                })
            }
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
}
