package com.example.myapplication.Flash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun rotateIcon(trigger:Int): Float {
    val angle by animateFloatAsState(
        targetValue = trigger*180F,
        animationSpec = tween(
            durationMillis = 500, // 动画持续时间
            easing = LinearEasing // 线性缓和函数
        ),
        //finishedListener = { rotate = false } // 动画完成后重置状态
    )
    return angle;
}
