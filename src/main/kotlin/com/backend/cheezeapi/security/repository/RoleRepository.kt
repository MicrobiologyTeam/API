package com.backend.cheezeapi.security.repository

import com.backend.cheezeapi.security.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>