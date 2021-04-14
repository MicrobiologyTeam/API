package com.backend.cheezeapi.property

import com.backend.cheezeapi.formalParameter.FormalParameterDto
import com.backend.cheezeapi.formalParameter.GroupFormalParametersDto

data class PropertyWithFormalParameterDto(
    val id: Long? = null,
    val code: String? = null,
    val name: String? = null,
    val ungrouped: List<FormalParameterDto>? = null,
    val groups: List<GroupFormalParametersDto>? = null
) {
    companion object {
        fun toDto(property: Property, ungrouped: List<FormalParameterDto>?, groups: List<GroupFormalParametersDto>?) =
            PropertyWithFormalParameterDto(
                id = property.id,
                code = property.code,
                name = property.name,
                ungrouped = ungrouped?.map { it.copy(property = null) },
                groups = groups?.map { group ->
                    GroupFormalParametersDto(
                        groupId = group.groupId,
                        parameters = group.parameters?.map { it.copy(property = null) }
                    )
                }
            )
    }
}
