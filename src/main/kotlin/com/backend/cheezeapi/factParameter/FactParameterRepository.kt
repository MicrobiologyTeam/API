package com.backend.cheezeapi.factParameter

import org.springframework.data.jpa.repository.JpaRepository

interface FactParameterRepository: JpaRepository<FactParameter, Long> {
    fun deleteByStrainId(id: Long)
    fun findByStrainId(id: Long): List<FactParameter>
}
