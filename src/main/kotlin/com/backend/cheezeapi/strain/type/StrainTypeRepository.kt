package com.backend.cheezeapi.strain.type

import org.springframework.data.jpa.repository.JpaRepository

interface StrainTypeRepository : JpaRepository<StrainType, Long> {
    fun findByGenusId(id: Long): List<StrainType>
}