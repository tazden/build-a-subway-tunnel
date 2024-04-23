package nvmes.exustmousy.buildasubwaytunnel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val pl=findViewById<ImageView>(R.id.play)
        val ex=findViewById<ImageView>(R.id.exit)
        pl.setOnClickListener(){
            val intent= Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
        ex.setOnClickListener(){
            finishAffinity()
        }
    }
}