package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.QFactParameter.factParameter
import com.backend.cheezeapi.formalParameter.QFormalParameter.formalParameter
import com.backend.cheezeapi.strain.QStrain.strain
import com.querydsl.core.types.Predicate
import com.querydsl.jpa.impl.JPAQuery
import javax.persistence.EntityManager

class StrainRepositoryExtImpl(
        private val em: EntityManager
) : StrainRepositoryExt {
    override fun findStrainsByPredicate(predicate: Predicate): List<Strain>{
        val query: JPAQuery<Strain> = JPAQuery(this.em)

        return query
                .from(strain)
                .rightJoin(factParameter).on(strain.id.eq(factParameter.strain.id))
                .rightJoin(formalParameter).on(factParameter.formalParameter.id.eq(formalParameter.id))
                .where(predicate)
                .distinct()
                .fetch()
    }
}