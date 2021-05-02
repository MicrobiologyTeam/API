package com.backend.cheezeapi.strain.genus

import com.backend.cheezeapi.strain.type.StrainTypeDto
import com.backend.cheezeapi.strain.type.StrainTypeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("strain-genus")
class StrainGenusController(
    private val strainGenusService: StrainGenusService,
    private val strainTypeService: StrainTypeService
) {
    @PostMapping
    fun save(@RequestBody strainGenusDto: StrainGenusDto): StrainGenusDto =
        strainGenusService.save(strainGenusDto)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = strainGenusService.deleteById(id)

    @GetMapping
    fun findAll(): List<StrainGenusDto> =
        strainGenusService.findAll()

    @GetMapping("{id}/types")
    fun findByGenusId(@PathVariable id: Long): List<StrainTypeDto> = strainTypeService.findByGenusId(id)
}