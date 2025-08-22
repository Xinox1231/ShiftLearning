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
import kotlinx.coroutines.launch
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.shiftlearning.ui.theme.ShiftLearningTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    val component by lazy {
        (application as App).component
    }

    @Inject
    lateinit var source: UserLocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            val users = source.getUsersPage(10, 10).collect {
                Log.d("it", it.toString())
            }
        }

        setContent {
            ShiftLearningTheme {

            }
        }
    }
}