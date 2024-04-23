package nvmes.exustmousy.buildasubwaytunnel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MenuBluredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_blured)
        val back=findViewById<ImageView>(R.id.backbtn)
        back.setOnClickListener(){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        val exit=findViewById<ImageView>(R.id.exit)
        exit.setOnClickListener(){
            finishAffinity()
        }
        val reward=findViewById<ImageView>(R.id.reward)

    }
}