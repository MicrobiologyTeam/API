package com.backend.cheezeapi.property

import org.springframework.data.jpa.repository.JpaRepository

interface PropertyRepository : JpaRepository<Property, Long> {
}