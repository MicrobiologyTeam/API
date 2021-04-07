package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameter
import com.backend.cheezeapi.strain.Strain
import org.springframework.stereotype.Service

@Service
class FactParameterService(
        private val factParameterRepository: FactParameterRepository
) {
    fun saveAll(factParameterSet: Set<FactParameterDto>, strain: Strain): List<FactParameterDto> =
            factParameterRepository.saveAll(
                factParameterSet.map {
                    FactParameter(
                            id = null,
                            strain = strain,
                            formalParameter = FormalParameter(
                                    id = it.formalParameter?.id ?: error("Не задан formalParameter.id")
                            ),
                            value = it.value ?: error("Не задано factParameter.value"),
                            reserve = it.reserve ?: error("Не задан factParameter.reserve"),
                            groupId = it.groupId ?: error("Не задан factParameter.groupId")
                    )
                }
        ).map {
            FactParameterDto.toDto(it)
        }

    fun deleteByStrainId(strainId: Long) {
        factParameterRepository.deleteByStrainId(strainId)
    }
}
