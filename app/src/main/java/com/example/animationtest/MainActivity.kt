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

    private fun setListener() {
        subBinding.tvUnfoldLayout.setOnClickListener {
            setGuidePercent(GUIDELINE_MAX, GUIDELINE_MIN)
            with(centerBinding) {
                fadeOut(clWrapper)
                fadeIn(clTop)
            }
            with(subBinding) {
                fadeIn(clSubWrapper)
                fadeOut(clSubTop)
            }
        }

        centerBinding.tvUnfoldLayoutMain.setOnClickListener {
            setGuidePercent(GUIDELINE_MIN, GUIDELINE_MAX)
            with(centerBinding) {
                fadeIn(clWrapper)
                fadeOut(clTop)
            }
            with(subBinding) {
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
        val animation = AlphaAnimation(ALPHA_ONE, ALPHA_ZERO)
        animation.duration = DURATION
        view.visibility = View.GONE
        view.animation = animation
    }

    private fun fadeIn(view: View) {
        val animation = AlphaAnimation(ALPHA_ZERO, ALPHA_ONE)
        animation.duration = DURATION
        view.visibility = View.VISIBLE
        view.animation = animation
    }

    companion object {
        private const val ALPHA_ZERO = 0F
        private const val ALPHA_ONE = 1F
        private const val GUIDELINE_MAX = 0.8F
        private const val GUIDELINE_MIN = 0.2F
        private const val DURATION = 500L
    }
}