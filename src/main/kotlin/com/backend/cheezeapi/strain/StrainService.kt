package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameterService
import com.backend.cheezeapi.formalParameter.QFormalParameter.formalParameter
import com.backend.cheezeapi.groupId.GroupIdRepository
import com.backend.cheezeapi.strain.QStrain.strain
import com.backend.cheezeapi.strain.type.StrainType
import com.backend.cheezeapi.utils.PaginationHelper
import com.querydsl.core.types.ExpressionUtils.*
import com.querydsl.core.types.Predicate
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
                                        strain = StrainDto(id = strain.id),
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

    fun getOne(id: Long): StrainDto {
        val strain = StrainDto.toDto(strainRepository.getOne(id))
        val factParameters = factParameterService.findByStrainId(id)

        return strain.copy(properties = factParameters.groupBy { it.formalParameter?.property?.id to it.formalParameter?.property?.name }
                .map { property ->
                    val params = property.value.groupBy { it.groupId == null }
                    StrainPropertiesDto(
                            propertyId = property.key.first,
                            propertyName = property.key.second,
                            ungroupedParameters = params[true]?.map {
                                it.copy(
                                        formalParameter = it.formalParameter?.copy(
                                                property = null
                                        )
                                )
                            },
                            groups = params[false]?.groupBy { it.groupId }?.map { group ->
                                GroupFactParametersDto(
                                        groupId = group.key,
                                        parameters = group.value.map {
                                            it.copy(
                                                    formalParameter = it.formalParameter?.copy(
                                                            property = null
                                                    )
                                            )
                                        })
                            }
                    )
                })
    }

    fun findAll(pageNo: Long, size: Long): List<StrainDto> =
            PaginationHelper.getContent(strainRepository, pageNo, size).map { StrainDto.toDto(it) }


    //TODO: Fix me)
    fun findAllByPredicate(page: Long, size: Long, predicateDto: SearchPredicateDto): List<StrainDto> {
        var predicate: Predicate = strain.id.isNotNull

        // Add formal parameters checking
        /*
        predicateDto.predicates.forEach {
            when(it.condition) {
                "and" -> predicate = and(predicate, )
                "or" -> predicate = or(predicate, )
                "=" -> predicate = eq(predicate, )
            }
            predicate = and(predicate, )
        }
        */

        return strainRepository.findStrainsByPredicate(predicate).map {
            StrainDto.toDto(it)
        }
    }

}