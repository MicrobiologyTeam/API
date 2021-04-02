package com.backend.cheezeapi.property

data class PropertyDto(
        var id: Long? = null,
        var code: String? = null,
        var name: String? = null
) {
    companion object {
        fun toDto(property: Property) =
                PropertyDto(
                        id = property.id,
                        code = property.code,
                        name = property.name,
                )
    }
}
