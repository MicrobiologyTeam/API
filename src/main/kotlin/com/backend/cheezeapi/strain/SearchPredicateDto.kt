package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameterDto
import com.backend.cheezeapi.formalParameter.FormalParameterDto

data class SearchPredicateDto (
        val predicates: List<PredicateDto>
)

data class PredicateDto (
        val formalParameter: FormalParameterDto,
        val condition: String,
        val value: FactParameterDto,
)