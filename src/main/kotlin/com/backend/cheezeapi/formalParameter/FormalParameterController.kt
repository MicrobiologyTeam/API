package com.backend.cheezeapi.formalParameter

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("formal-parameter")
class FormalParameterController(
    private val formalParameterService: FormalParameterService
) {
    @PostMapping("save")
    fun save(@RequestBody formalParametersDto: FormalParametersDto) =
        formalParameterService.save(formalParametersDto = formalParametersDto)

    @GetMapping("delete")
    fun delete(@RequestParam id: Long) = formalParameterService.delete(id = id)

    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long): FormalParameterDto = formalParameterService.getOne(id = id)

    @GetMapping("")
    fun findAll(): List<FormalParameterDto> =
        formalParameterService.findAll()
}