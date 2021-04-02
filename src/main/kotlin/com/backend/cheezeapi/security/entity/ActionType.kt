package com.backend.cheezeapi.security.entity

enum class ActionType(val roleName: String) {
    Action1(Names.Action1),
    Action2(Names.Action2);

    object Names {
        const val Action1 = "Action1"
        const val Action2 = "Action2"
    }
}