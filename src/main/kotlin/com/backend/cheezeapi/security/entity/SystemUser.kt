package com.backend.cheezeapi.security.entity

import javax.persistence.*

@Entity
class SystemUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val password: String,
    val enabled: Boolean,
    @ManyToOne(fetch = FetchType.LAZY)
    val role: Role
)
