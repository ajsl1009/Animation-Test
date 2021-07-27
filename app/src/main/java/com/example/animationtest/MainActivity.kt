package com.example.animationtest

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.animationtest.databinding.ActivityMainBinding
import com.example.animationtest.databinding.LayoutCenterBinding
import com.example.animationtest.databinding.LayoutSubBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var centerBinding: LayoutCenterBinding
    private lateinit var subBinding: LayoutSubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        centerBinding = LayoutCenterBinding.bind(mainBinding.root)
        subBinding = LayoutSubBinding.bind(mainBinding.root)
        setContentView(mainBinding.root)

        setListener()
    }

    private fun setListener(){
        subBinding.tvUnfoldLayout.setOnClickListener {
            setGuidePercent(0.8f, 0.2f)
            with(centerBinding){
                fadeOut(clWrapper)
                fadeIn(clTop)
            }
            with(subBinding){
                fadeIn(clSubWrapper)
                fadeOut(clSubTop)
            }
        }

        centerBinding.tvUnfoldLayoutMain.setOnClickListener {
            setGuidePercent(0.2f, 0.8f)
            with(centerBinding){
                fadeIn(clWrapper)
                fadeOut(clTop)
            }
            with(subBinding){
                fadeOut(clSubWrapper)
                fadeIn(clSubTop)
            }
        }
    }

    private fun setGuidePercent(start: Float, end: Float) {
        val guideAnimator = ValueAnimator.ofFloat(start, end)
        guideAnimator.duration = DURATION
        guideAnimator.addUpdateListener { valueAnimator ->
            mainBinding.glEightyOfClMain.setGuidelinePercent(valueAnimator.animatedValue as Float)
        }
        guideAnimator.start()
    }

    private fun fadeOut(view: View) {
        val animation = AlphaAnimation(ALPHAONE, ALPHAZERO)
        animation.duration = DURATION
        view.visibility = View.GONE
        view.animation = animation
    }

    private fun fadeIn(view: View) {
        val animation = AlphaAnimation(ALPHAZERO, ALPHAONE)
        animation.duration = DURATION
        view.visibility = View.VISIBLE
        view.animation = animation
    }

    companion object{
        private const val ALPHAZERO = 0F
        private const val ALPHAONE = 1F
        private const val DURATION = 500L
    }
}