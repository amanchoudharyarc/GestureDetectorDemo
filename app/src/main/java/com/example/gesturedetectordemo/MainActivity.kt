package com.example.gesturedetectordemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    companion object{
        var matrix=android.graphics.Matrix()
        var scale=1f
        var imageView:ImageView?=null
        var scaleGestureDetector:ScaleGestureDetector?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val path=Environment.getExternalStorageDirectory().absolutePath+"/asd.jpg"
        val bitmap=BitmapFactory.decodeFile(path)

        imageView=findViewById(R.id.image_view)
        imageView!!.setImageBitmap(bitmap)
        scaleGestureDetector= ScaleGestureDetector(this,ScaleListener())

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector!!.onTouchEvent(event)
        return false
    }


    class ScaleListener: ScaleGestureDetector.SimpleOnScaleGestureListener(){
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scale*= detector!!.scaleFactor
            scale=Math.max(0.1f,Math.min(scale,5.0f))
            matrix.setScale(scale, scale)
            imageView!!.imageMatrix= matrix
            return false
        }
    }
}
