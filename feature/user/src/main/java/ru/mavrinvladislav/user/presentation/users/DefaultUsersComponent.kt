package ru.mavrinvladislav.user.presentation.users

class DefaultUsersComponent : UsersComponent {

    interface Factory {

        fun create(): DefaultUsersComponent
    }
}