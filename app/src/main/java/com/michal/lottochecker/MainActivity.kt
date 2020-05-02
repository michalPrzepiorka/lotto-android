package com.michal.lottochecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.michal.lottochecker.lottoPlus.LottoPlusActivity
import com.michal.lottochecker.mainLotto.LottoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.mCustomToolbar)
        val img: ImageView = toolbar.findViewById(R.id.toolbarImageView)
        img.setImageResource(R.drawable.lotto)
        val title: TextView = toolbar.findViewById(R.id.toolbarTitle)
        title.text = "Lotto Checker"
        setSupportActionBar(toolbar)
    }

    fun callAuthorActivity(view: View) {
        val intent = Intent(this, AuthorActivity::class.java)
        startActivity(intent)
    }

    fun callLottoActivity(view: View) {
        val intent = Intent(this, LottoActivity::class.java)
        startActivity(intent)
    }

    fun callLottoPlusActivity(view: View) {
        val intent = Intent(this, LottoPlusActivity::class.java)
        startActivity(intent)
    }
}
