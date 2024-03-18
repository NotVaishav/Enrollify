import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.enrollify.R

object AppFont {
    val Comfortaa = FontFamily(
        Font(R.font.comfortaa_light, FontWeight.Light),
        Font(R.font.comfortaa_regular),
        Font(R.font.comfortaa_medium, FontWeight.Medium),
        Font(R.font.comfortaa_semibold, FontWeight.SemiBold),
        Font(R.font.comfortaa_bold, FontWeight.Bold),
    )
}

private val defaultTypography = Typography()


val MyTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.Comfortaa),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.Comfortaa),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.Comfortaa),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.Comfortaa),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.Comfortaa),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.Comfortaa),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.Comfortaa),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.Comfortaa),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.Comfortaa),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.Comfortaa),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.Comfortaa),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.Comfortaa),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.Comfortaa),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.Comfortaa),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.Comfortaa)
)