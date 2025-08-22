package ru.mavrinvladislav.shiftlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.mavrinvladislav.shiftlearning.ui.theme.ShiftLearningTheme
import ru.mavrinvladislav.user.presentation.root.DefaultUserRootComponent
import ru.mavrinvladislav.user.ui.root.UsersRootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    val component by lazy {
        (application as App).component
    }

    @Inject
    lateinit var userRootComponentFactory: DefaultUserRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        val componentContext = defaultComponentContext()
        val component = userRootComponentFactory.create(
            component,
            componentContext
        )
        setContent {
            ShiftLearningTheme {
                UsersRootContent(component)
            }
        }
    }
}