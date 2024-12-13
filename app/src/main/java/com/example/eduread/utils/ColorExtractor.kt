package com.example.eduread.utils

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.palette.graphics.Palette

object ColorExtractor {

    data class PaletteColors(
        val dominantColor: Int,
        val vibrantColor: Int,
        val mutedColor: Int,
        val lightVibrantColor: Int,
        val darkVibrantColor: Int,
        val lightMutedColor: Int,
        val darkMutedColor: Int,
        val softDarkVibrantColor: Int
    )

    fun extractColors(imageView: ImageView, onColorsExtracted: (PaletteColors) -> Unit) {
        val drawable = imageView.drawable as? BitmapDrawable ?: return
        val bitmap = drawable.bitmap

        Palette.from(bitmap).generate { palette ->
            if (palette != null) {
                val darkVibrantColor = palette.getDarkVibrantColor(0xFFFFFF)
                val softDarkVibrantColor = lightenColor(darkVibrantColor, 0.9f)
                val colors = PaletteColors(
                    dominantColor = palette.getDominantColor(0xFFFFFF),
                    vibrantColor = palette.getVibrantColor(0xFFFFFF),
                    mutedColor = palette.getMutedColor(0xFFFFFF),
                    lightVibrantColor = palette.getLightVibrantColor(0xFFFFFF),
                    darkVibrantColor = darkVibrantColor,
                    lightMutedColor = palette.getLightMutedColor(0xFFFFFF),
                    darkMutedColor = palette.getDarkMutedColor(0xFFFFFF),
                    softDarkVibrantColor = softDarkVibrantColor // Asignar el nuevo color
                )
                onColorsExtracted(colors)
            }
        }
    }
    private fun lightenColor(color: Int, factor: Float): Int {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)

        return Color.rgb(
            (red + (255 - red) * factor).coerceAtMost(255f).toInt(),
            (green + (255 - green) * factor).coerceAtMost(255f).toInt(),
            (blue + (255 - blue) * factor).coerceAtMost(255f).toInt()
        )
    }

}