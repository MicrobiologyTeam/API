package com.backend.cheezeapi.property

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("property")
class PropertyController(
    private val propertyService: PropertyService
) {
    @PostMapping
    fun save(@RequestBody propertyDto: PropertyDto): PropertyDto =
        propertyService.save(propertyDto = propertyDto)

    @DeleteMapping("{id}")
    @ResponseStatus()
    fun delete(@PathVariable id: Long) = propertyService.delete(id = id)

    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long): PropertyWithFormalParameterDto = propertyService.getOne(id = id)

    @GetMapping
    fun findAll(): List<PropertyDto> = propertyService.findAll()

    @GetMapping("/with_parameters")
    fun findAllWithFormalParameters(): List<PropertyWithFormalParameterDto> {
        return propertyService.findAllWithFormalParameters()
    }
}

