package com.natanaelahac.canvas.rectangle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.natanaelahac.canvas.toPx

class CustomRectangleCutoutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val qrScannerWidth = 190.toPx
    private val qrScannerHeight = 190.toPx

    /*Determines vertical position of the center point in the scanner cutout shape
      0f -> Center of scanner cutout shape will be at the top of the Canvas
     0.5f -> Center of scanner cutout shape will be at the middle of the Canvas
     1f -> Center of scanner cutout shape will be at the bottom of the Canvas */
    private val verticalOffset = 0.5f

    /*Determines horizontal position of the center point in the scanner cutout shape
  0f -> Center of scanner cutout shape will be at the top of the Canvas
 0.5f -> Center of scanner cutout shape will be at the middle of the Canvas
 1f -> Center of scanner cutout shape will be at the bottom of the Canvas */
    private val horizontalOffset = 0.5f

    // Edges of QR scanner
    private var xAxisLeftEdge = 0f
    private var xAxisRightEdge = 0f
    private var yAxisTopEdge = 0f
    private var yAxisBottomEdge = 0f

    private val frameStrokeWidth = 2.toPx.toFloat()

    private val backgroundPaint = Paint().apply {
        setARGB(80, 0, 0, 0)
    }

    private val framePaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
        strokeWidth = frameStrokeWidth
        style = Paint.Style.STROKE
    }

    private lateinit var backgroundShape: Path
    private lateinit var qrScannerShape: Path

    private fun createBackgroundPath() = Path().apply {
        lineTo(right.toFloat(), 0f)
        lineTo(right.toFloat(), bottom.toFloat())
        lineTo(0f, bottom.toFloat())
        lineTo(0f, 0f)
        fillType = Path.FillType.EVEN_ODD
    }

    private fun createQrPath() = Path().apply {
        moveTo(xAxisLeftEdge, yAxisTopEdge)
        lineTo(xAxisRightEdge, yAxisTopEdge)
        lineTo(xAxisRightEdge, yAxisBottomEdge)
        lineTo(xAxisLeftEdge, yAxisBottomEdge)
        lineTo(xAxisLeftEdge, yAxisTopEdge)
        fillType = Path.FillType.EVEN_ODD
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            xAxisLeftEdge = width * horizontalOffset - qrScannerWidth / 2f
            xAxisRightEdge = width * horizontalOffset + qrScannerWidth / 2f
            yAxisTopEdge = height * verticalOffset - qrScannerHeight / 2f
            yAxisBottomEdge = height * verticalOffset + qrScannerHeight / 2f

            backgroundShape = createBackgroundPath()
            qrScannerShape = createQrPath()
            backgroundShape.addPath(qrScannerShape)


            drawPath(backgroundShape, backgroundPaint)
            drawPath(qrScannerShape, framePaint)
        }
    }

}