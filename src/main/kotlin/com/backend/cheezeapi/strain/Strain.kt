package com.backend.cheezeapi.strain

import com.backend.cheezeapi.strain.type.StrainType
import java.util.*
import javax.persistence.*

@Entity
data class Strain(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        val dateReceiving: Date,
        val collectionIndex: String,
        val source: String,
        val creator: String,
        val dateAdded: Date,
        @ManyToOne(fetch = FetchType.LAZY)
        val type: StrainType,
        val obtainingMethod: String
)