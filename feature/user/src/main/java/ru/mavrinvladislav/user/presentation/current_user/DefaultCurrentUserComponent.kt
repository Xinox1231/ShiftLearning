package ru.mavrinvladislav.user.presentation.current_user

class DefaultCurrentUserComponent(

) : CurrentUserComponent {

    interface Factory {

        fun create(): DefaultCurrentUserComponent
    }
}