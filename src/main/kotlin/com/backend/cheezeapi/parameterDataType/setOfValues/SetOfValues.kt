package com.backend.cheezeapi.parameterDataType.setOfValues

import com.backend.cheezeapi.formalParameter.FormalParameter
import javax.persistence.*

@Entity
data class SetOfValues(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        val value: String? = null,

        @JoinColumn(nullable = false)
        @ManyToOne(fetch = FetchType.LAZY)
        val formalParameter: FormalParameter? = null
)
