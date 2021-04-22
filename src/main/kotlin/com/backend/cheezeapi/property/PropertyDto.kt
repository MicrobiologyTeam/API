package com.backend.cheezeapi.property

data class PropertyDto(
    val id: Long? = null,
    val code: String? = null,
    val name: String? = null,
    val isNote: Boolean? = null
) {
    companion object {
        fun toDto(property: Property) =
            PropertyDto(
                id = property.id,
                code = property.code,
                name = property.name,
                isNote = property.isNote
            )
    }
}
