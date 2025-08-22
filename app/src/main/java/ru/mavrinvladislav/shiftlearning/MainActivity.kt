package ru.mavrinvladislav.shiftlearning

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import kotlinx.coroutines.launch
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.shiftlearning.ui.theme.ShiftLearningTheme
import ru.mavrinvladislav.user.presentation.DefaultUserRootComponent
import ru.mavrinvladislav.user.presentation.UserRootComponent
import ru.mavrinvladislav.user.ui.UsersRootContent
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