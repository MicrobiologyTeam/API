package com.backend.cheezeapi.groupId

interface GroupIdRepository {
    fun getNewGroupIdFormalParameter(): Long
    fun getNewGroupIdFactParameter(): Long
}