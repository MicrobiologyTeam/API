package com.backend.cheezeapi.strain

import org.springframework.data.jpa.repository.JpaRepository

interface StrainRepository : JpaRepository<Strain, Long>, StrainRepositoryExt {
    fun findByTypeId(id: Long): List<Strain>
}
