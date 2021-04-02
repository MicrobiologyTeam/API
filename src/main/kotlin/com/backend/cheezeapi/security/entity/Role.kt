package com.backend.cheezeapi.security.entity

import javax.persistence.*

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val name: String,

    @ElementCollection(targetClass = ActionType::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ActionType")
    @Enumerated(EnumType.STRING)
    val actions: MutableSet<ActionType>
)