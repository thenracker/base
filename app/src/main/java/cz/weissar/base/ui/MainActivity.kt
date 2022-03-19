package cz.weissar.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import cz.weissar.base.R
import cz.weissar.base.databinding.ActivityMainBinding
import cz.weissar.base.ui.spacex.SpaceFlightsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            ActivityMainBinding.inflate(layoutInflater)
                .also { _binding = it }
                .root
        )

        _binding.btnCompose.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    SpaceFlightsFragment.newInstance(),
                    SpaceFlightsFragment::class.java.simpleName,
                )
                .commit()
            it.isVisible = false
        }
    }
}
