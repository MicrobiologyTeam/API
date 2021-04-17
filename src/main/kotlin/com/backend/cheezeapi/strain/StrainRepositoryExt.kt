package com.backend.cheezeapi.strain

import com.querydsl.core.types.Predicate

interface StrainRepositoryExt{
    fun findStrainsByPredicate(predicate: Predicate): List<Strain>
}