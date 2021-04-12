package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameterService
import com.backend.cheezeapi.groupId.GroupIdRepository
import com.backend.cheezeapi.strain.type.StrainType
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class StrainService(
    private val strainRepository: StrainRepository,
    private val factParameterService: FactParameterService,
    private val groupIdRepository: GroupIdRepository
) {
    fun save(strainDto: StrainDto): StrainDto {
        val strain = StrainDto.toDto(
            strainRepository.save(
                Strain(
                    id = strainDto.id,
                    name = strainDto.name ?: error("Не задан name"),
                    dateReceiving = strainDto.dateReceiving ?: error("Не задан dateReceiving"),
                    collectionIndex = strainDto.collectionIndex ?: error("Не задан collectionIndex"),
                    source = strainDto.source ?: error("Не задан source"),
                    creator = strainDto.creator,
                    dateAdded = strainDto.dateAdded ?: error("Не задан dateAdded"),
                    type = StrainType(
                        id = strainDto.type?.id ?: error("Не задан id type strain")
                    ),
                    obtainingMethod = strainDto.obtainingMethod ?: error("Не задан obtainingMethod"),
                )
            )
        )

        val ungrouped = strainDto.properties?.stream()
            ?.map { properties -> properties.ungroupedParameters }
            ?.map { list -> list?.map { it.copy(strain = StrainDto(id = strain.id)) } }
            ?.flatMap { it?.stream() }
            ?.collect(Collectors.toList())

        val groups = strainDto.properties?.stream()
            ?.map { properties -> properties.groups }
            ?.flatMap { it?.stream() }
            ?.map { groupFactParameterDto ->
                val groupId =
                    groupFactParameterDto.groupId ?: groupIdRepository.getNewGroupIdFactParameter()
                groupFactParameterDto.parameters
                    ?.map {
                        it.copy(
                            strain = StrainDto(id = groupFactParameterDto.groupId),
                            groupId = groupId
                        )
                    }
            }
            ?.collect(Collectors.toList())?.filterNotNull()

        strain.id?.let { factParameterService.save(strainId = it, ungrouped = ungrouped, groups = groups) }

        return strain
    }

    fun deleteById(strainId: Long) {
        factParameterService.deleteByStrainId(strainId)
        strainRepository.deleteById(strainId)
    }

    fun getOne(id: Long): StrainDto = StrainDto.toDto(strainRepository.getOne(id))

    fun findAll(): List<StrainDto> =
        strainRepository.findAll().map { StrainDto.toDto(it) }
}