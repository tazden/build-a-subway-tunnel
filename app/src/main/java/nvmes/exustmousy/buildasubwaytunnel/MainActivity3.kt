package nvmes.exustmousy.buildasubwaytunnel

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    private lateinit var imageView: FrameLayout
    private lateinit var im: ImageView
    private lateinit var imageView2: LinearLayout
    private lateinit var progressIndicatorLayout: RelativeLayout
    private lateinit var backgroundImageView: ImageView
    private var isBackgroundMovingUp = false

    private lateinit var power: ImageView
    private lateinit var menu: ImageView
    private var rotationAngle: Float = 0f
    private lateinit var progressIndicatorCircle: ImageView
    private var tap: Int = 0
    private lateinit var animation: ObjectAnimator
    private lateinit var progressBar: ProgressBar
    private var progressValue: Int = 0
    private var currentMarginStart = 0
    private var translationYIncrement = 3f // Задаем величину увеличения смещения по оси Y


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        progressBar = findViewById(R.id.progressBar)
        backgroundImageView = findViewById(R.id.yama)

        var text=findViewById<TextView>(R.id.progressIndicatorText)
        imageView = findViewById(R.id.kru)
        imageView2 = findViewById(R.id.buri)
        im = findViewById(R.id.mouse)
        progressIndicatorCircle = findViewById(R.id.progressIndicatorCircle)
        progressIndicatorLayout = findViewById(R.id.progressIndicatorLayout)
        power = findViewById(R.id.power)
        menu = findViewById(R.id.menu)
        menu.setOnClickListener(){


            val intent=Intent(this,MenuBluredActivity::class.java)
            startActivity(intent)
        }
        power.setOnClickListener(){
            if (tap==0) {
                power.setImageResource(R.drawable.property_1_sound_opened_off)
                tap++
            } else if (tap==1){
                power.setImageResource(R.drawable.property_1_sound_opened_min)
                tap++
            } else if (tap==2){
                power.setImageResource(R.drawable.property_1_sound_opened_max)
                tap++
            }else if (tap==3){
                power.setImageResource(R.drawable.sound_power)
                tap=0
            }
        }
        val colorGreen = ColorStateList.valueOf(resources.getColor(R.color.gr, theme))
        progressBar.progressTintList = colorGreen
        imageView2.setOnClickListener {

            updateProgressIndicatorPosition()
            val moveUpAnimator = ObjectAnimator.ofFloat(
                imageView2,
                "translationY",
                imageView2.translationY - translationYIncrement
            )
            moveUpAnimator.duration = 200
            moveUpAnimator.interpolator = AccelerateDecelerateInterpolator()
            moveUpAnimator.start()

            // Анимация вращения imageView
            val rotateAnimator = ObjectAnimator.ofFloat(
                imageView,
                "rotationY",
                rotationAngle,
                rotationAngle + 180f
            )
            rotateAnimator.duration = 100
            rotateAnimator.interpolator = AccelerateDecelerateInterpolator()
            rotateAnimator.start()

            // Анимация перемещения фона вверх или вниз
            val backgroundAnimator = ObjectAnimator.ofFloat(
                backgroundImageView,
                "translationY",
                backgroundImageView.translationY - translationYIncrement-1
            )
            backgroundAnimator.duration = 200
            backgroundAnimator.interpolator = AccelerateDecelerateInterpolator()
            backgroundAnimator.start()

            // Обновление прогресса
            progressValue++
            text.text = progressValue.toString()
            if (progressValue >= 200) {
                imageView2.visibility= View.INVISIBLE
                backgroundImageView.visibility= View.INVISIBLE
                im.visibility= View.VISIBLE
                progressValue = 200
                Handler().postDelayed({
                    val intent = Intent(this, MainActivity4::class.java)
                    startActivity(intent)
                    finish()
                }, 2000)
            }
            progressBar.progress = progressValue
        }


    }

    private fun updateProgressIndicatorPosition() {
        val layoutParams = progressIndicatorCircle.layoutParams as RelativeLayout.LayoutParams
        layoutParams.marginStart = calculateProgressIndicatorMarginStart()
        progressIndicatorCircle.layoutParams = layoutParams
    }

    private fun calculateProgressIndicatorMarginStart(): Int {
        val progress = progressBar.progress
        val progressBarWidth = progressBar.width
        val maxProgress = progressBar.max

        val progressRatio = progress.toFloat() / maxProgress.toFloat()
        return (progressBarWidth * progressRatio).toInt()
    }
}