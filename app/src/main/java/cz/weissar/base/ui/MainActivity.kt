package cz.weissar.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.weissar.base.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ActivityMainBinding.inflate(layoutInflater)
                .also { _binding = it }
                .root
        )
    }
}
