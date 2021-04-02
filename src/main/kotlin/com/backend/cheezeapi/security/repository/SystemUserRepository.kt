package com.backend.cheezeapi.security.repository

import com.backend.cheezeapi.security.entity.SystemUser
import org.springframework.data.jpa.repository.JpaRepository

interface SystemUserRepository : JpaRepository<SystemUser, Long>