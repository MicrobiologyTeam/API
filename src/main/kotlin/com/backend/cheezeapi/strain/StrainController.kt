package com.backend.cheezeapi.strain

import com.backend.cheezeapi.factParameter.FactParameter
import com.backend.cheezeapi.factParameter.FactParameterRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("strain")
class StrainController(
    private val strainService: StrainService
) {
    @PostMapping("save")
    fun save(@RequestBody strainDto: StrainDto): StrainDto =
        strainService.save(strainDto)

    @GetMapping("delete")
    fun deleteById(@RequestParam id: Long) =
        strainService.deleteById(id)

    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long): StrainDto = strainService.getOne(id = id)

    @GetMapping("")
    fun findAll(@RequestParam(defaultValue = "0") page: Long ,
                @RequestParam(defaultValue = "50") size: Long) =
            strainService.findAll(page, size)
}