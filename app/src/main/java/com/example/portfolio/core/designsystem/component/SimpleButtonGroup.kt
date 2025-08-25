package com.example.portfolio.core.designsystem.component

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.PackageManagerCompat
import com.example.portfolio.core.designsystem.icon.ApplicationIcons
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SimpleButtonGroup(
    modifier: Modifier = Modifier,
    options: List<String>,
    optionLink: List<String>,
    style: TextStyle = MaterialTheme.typography.labelMedium,
    icon: ImageVector = ApplicationIcons.Arrow,
    ) {
    val context = LocalContext.current

    options.forEachIndexed { index, label ->
        Button(
            onClick = {
                when (index) {
                    0 -> {
                        val intent = Intent(Intent.ACTION_VIEW,
                            "https://${optionLink[index]}".toUri())
                        context.startActivity(intent)
                    }

                    1 -> {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = "mailto:${optionLink[index]}".toUri()
                        }
                        context.startActivity(intent)
                    }

                    2 -> {
                        val intent = Intent(Intent.ACTION_VIEW,
                            "https://${optionLink[index]}".toUri())
                        context.startActivity(intent)
                    }
                }
            },
            modifier = Modifier
                .wrapContentWidth()
                .height(34.dp),
            shapes = ButtonDefaults.shapes(),
        ) {
            Text(
                text = label,
                style = style,
                softWrap = false,
            )
            Icon(
                imageVector = icon,
                contentDescription = "ButtonIcon",
                modifier = modifier
            )
        }
    }
}