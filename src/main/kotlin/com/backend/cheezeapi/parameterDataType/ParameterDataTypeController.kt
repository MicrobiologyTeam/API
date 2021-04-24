package com.backend.cheezeapi.parameterDataType

import com.backend.cheezeapi.formalParameter.FormalParameterDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("data-type")
class ParameterDataTypeController(
    private val parameterDataTypeRepository: ParameterDataTypeRepository
) {
    @GetMapping
    fun findAll(): List<ParameterDataType> =
        parameterDataTypeRepository.findAll()
}