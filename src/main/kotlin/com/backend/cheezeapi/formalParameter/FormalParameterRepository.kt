package com.backend.cheezeapi.formalParameter

import org.springframework.data.jpa.repository.JpaRepository

interface FormalParameterRepository : JpaRepository<FormalParameter, Long> {
    fun findByPropertyId(id: Long): List<FormalParameter>
}