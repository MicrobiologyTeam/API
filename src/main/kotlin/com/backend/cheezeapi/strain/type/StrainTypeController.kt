package com.backend.cheezeapi.strain.type

import com.backend.cheezeapi.strain.StrainService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("strain-type")
class StrainTypeController(
    private val strainTypeService: StrainTypeService,
    private val strainService: StrainService
) {
    @PostMapping
    fun save(@RequestBody strainTypeDto: StrainTypeDto): StrainTypeDto =
        strainTypeService.save(strainTypeDto)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = strainTypeService.deleteById(id)

    @GetMapping
    fun findAll(): List<StrainTypeDto> =
        strainTypeService.findAll()

    @GetMapping("{id}/strains")
    fun findByTypeId(@PathVariable id: Long) = strainService.findByTypeId(id)
}