package com.backend.cheezeapi.groupId

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class GroupIdRepositoryImpl(
    private val entityManager: EntityManager
) : GroupIdRepository {
    override fun getNewGroupIdFormalParameter(): Long =
        entityManager.createNativeQuery("select nextval('formalparametergroupidsequence')").singleResult.toString()
            .toLong()

    override fun getNewGroupIdFactParameter(): Long =
        entityManager.createNativeQuery("select nextval('factparametergroupidsequence')").singleResult.toString()
            .toLong()
}