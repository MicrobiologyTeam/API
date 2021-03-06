package com.backend.cheezeapi.strain

import com.backend.cheezeapi.strain.type.StrainType
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Strain(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String? = null,

    @Column(nullable = false)
    val dateReceiving: LocalDate? = null,

    @Column(nullable = false)
    val collectionIndex: String? = null,

    @Column(nullable = false)
    val source: String? = null,

    val creator: String? = null,

    @Column(nullable = false)
    val dateAdded: LocalDate? = null,

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val type: StrainType? = null,

    @Column(nullable = false)
    val obtainingMethod: String? = null
)