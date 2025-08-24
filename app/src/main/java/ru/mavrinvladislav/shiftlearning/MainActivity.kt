package ru.mavrinvladislav.shiftlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.mavrinvladislav.system_design.ui.theme.MainTheme
import ru.mavrinvladislav.user.di.DaggerUserComponent
import ru.mavrinvladislav.user.di.UserDependenciesStore
import ru.mavrinvladislav.user.ui.root.UsersRootContent

class MainActivity : ComponentActivity() {

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userFeatureRootFactory =
            DaggerUserComponent.factory().create(UserDependenciesStore.dependencies)
                .getUserRootComponentFactory()

        val componentContext = defaultComponentContext()
        val rootComponent = userFeatureRootFactory.create(
            componentContext = componentContext
        )
        setContent {
            UsersRootContent(rootComponent)
        }
    }
}