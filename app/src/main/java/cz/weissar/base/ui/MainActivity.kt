package cz.weissar.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.weissar.base.R
import cz.weissar.base.common.prefs.PrefManager
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val prefs by inject<PrefManager>()
    //private val prefs by inject<PrefManager>(named("MySecondPrefs")) // named

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs.isItGood
    }
}
