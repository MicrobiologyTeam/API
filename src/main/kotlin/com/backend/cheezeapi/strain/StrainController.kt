package com.backend.cheezeapi.strain

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("strain")
class StrainController(
        private val strainService: StrainService
) {
    @PostMapping("save")
    fun save(@RequestBody strainDto: StrainDto): StrainDto =
            strainService.save(strainDto)

    @DeleteMapping("delete")
    fun deleteById(@RequestParam strainId: Long) =
            strainService.deleteById(strainId)

    @GetMapping("")
    fun findAll() = strainService.findAll()
}