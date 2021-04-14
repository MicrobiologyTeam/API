package com.backend.cheezeapi.factParameter

import com.backend.cheezeapi.formalParameter.FormalParameter
import org.springframework.data.jpa.repository.JpaRepository

interface FactParameterRepository : JpaRepository<FactParameter, Long> {
    fun deleteByStrainId(id: Long): List<FactParameter>
    fun findByStrainId(id: Long): List<FactParameter>
}
