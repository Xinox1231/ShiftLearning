package ru.mavrinvladislav.system_design.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.system_design.R
import ru.mavrinvladislav.system_design.ui.theme.MainTheme

@Composable
fun Error(
    onTryAgain: () -> Unit,
    modifier: Modifier = Modifier,
    title: Int = R.string.component_error_title,
    description: Int = R.string.component_error_text,
    buttonText: Int = R.string.update_button,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BrandText(
            text = stringResource(id = title),
            textAlign = TextAlign.Center,
            textStyle = TextStyle.MEDIUM_16
        )

        Spacer(modifier = Modifier.height(16.dp))

        BrandText(
            text = stringResource(id = description),
            textAlign = TextAlign.Center,
            textStyle = TextStyle.REGULAR_14,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            text = stringResource(id = buttonText),
            onClick = onTryAgain,
        )
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    MainTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Error(
                onTryAgain = {},
            )
        }
    }
}