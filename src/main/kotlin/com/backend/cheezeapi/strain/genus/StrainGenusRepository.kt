package com.backend.cheezeapi.strain.genus

import org.springframework.data.jpa.repository.JpaRepository

interface StrainGenusRepository :JpaRepository<StrainGenus, Long> {
}