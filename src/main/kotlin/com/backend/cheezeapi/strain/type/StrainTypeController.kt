package com.backend.cheezeapi.strain.type

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("strain-type")
class StrainTypeController(
        private val strainTypeService: StrainTypeService
) {
    @PostMapping("save")
    fun save(@RequestBody strainTypeDto: StrainTypeDto) : StrainTypeDto =
            strainTypeService.save(strainTypeDto)
}