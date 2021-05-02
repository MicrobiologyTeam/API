package com.backend.cheezeapi.strain.genus

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("strain-genus")
class StrainGenusController(
    private val strainGenusService: StrainGenusService
) {
    @PostMapping
    fun save(@RequestBody strainGenusDto: StrainGenusDto): StrainGenusDto =
        strainGenusService.save(strainGenusDto)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = strainGenusService.deleteById(id)

    @GetMapping
    fun findAll(): List<StrainGenusDto> =
        strainGenusService.findAll()
}