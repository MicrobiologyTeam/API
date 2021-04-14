package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.property.Property
import org.springframework.data.jpa.repository.JpaRepository

interface FormalParameterRepository : JpaRepository<FormalParameter, Long> {
    fun findByPropertyId(id: Long): List<FormalParameter>
}