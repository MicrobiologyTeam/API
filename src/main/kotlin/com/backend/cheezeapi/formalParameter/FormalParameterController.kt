package com.backend.cheezeapi.formalParameter

import com.backend.cheezeapi.property.PropertyWithFormalParameterDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("formal-parameter")
class FormalParameterController(
    private val formalParameterService: FormalParameterService
) {
    @PostMapping
    fun save(@RequestBody formalParametersDto: FormalParametersDto): PropertyWithFormalParameterDto =
        formalParameterService.save(formalParametersDto = formalParametersDto)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = formalParameterService.delete(id = id)

    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long): FormalParameterDto = formalParameterService.getOne(id = id)

    @GetMapping
    fun findAll(): List<FormalParameterDto> =
        formalParameterService.findAll()
}