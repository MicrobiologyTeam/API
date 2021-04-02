package com.backend.cheezeapi.security

import com.backend.cheezeapi.security.entity.ActionType
import com.backend.cheezeapi.security.repository.RoleRepository
import com.backend.cheezeapi.security.repository.SystemUserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.security.RolesAllowed

@RestController
class TestController(
    private val systemUserRepository: SystemUserRepository,
    private val roleRepository: RoleRepository
) {
    @GetMapping("change_actions")
    fun changeActions(): List<ActionType> {
//        val systemUser = systemUserRepository.getOne(1)
//        systemUser.role.actions.addAll(listOf(ActionType.Action1, ActionType.Action2))
//        systemUserRepository.save(systemUser)
//
//        val systemUser2 = systemUserRepository.getOne(2)
//        systemUser2.role.actions.addAll(listOf(ActionType.Action2))
//        systemUserRepository.save(systemUser2)

        return enumValues<ActionType>().toList()
    }

    @PreAuthorize("hasAuthority('${ActionType.Names.Action1}')")
    @GetMapping("test1")
    fun deleteUser() = "test1"

    @PreAuthorize("hasAuthority('${ActionType.Names.Action2}')")
    @GetMapping("test2")
    fun test2() = "test2"
}