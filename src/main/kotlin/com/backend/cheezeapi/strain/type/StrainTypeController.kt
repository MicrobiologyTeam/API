package com.backend.cheezeapi.strain.type

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("strain-type")
class StrainTypeController(
    private val strainTypeService: StrainTypeService
) {
    @PostMapping("save")
    fun save(@RequestBody strainTypeDto: StrainTypeDto): StrainTypeDto =
        strainTypeService.save(strainTypeDto)

    @GetMapping("delete")
    fun delete(@RequestParam id: Long) = strainTypeService.deleteById(id)

    @GetMapping("")
    fun findAll(): List<StrainTypeDto> =
        strainTypeService.findAll()
}