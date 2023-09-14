package com.example.intentactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentactivity.MainActivity.Companion.EXTRA_NAME
import com.example.intentactivity.databinding.ActivitySecondBinding
import com.example.intentactivity.databinding.ActivityThirdBinding
import androidx.activity.result.contract.ActivityResultContracts

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data

                val name = data?.getStringExtra(EXTRA_NAME)
                val address = data?.getStringExtra(ThirdActivity.EXTRA_ADDRESS)

                if (!name.isNullOrEmpty() && !address.isNullOrEmpty()) {
                    binding.txtName.text = "Hello $name di $address"
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)

        with(binding) {
            txtName.text = "Hello $name"

            btnToThird.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                    .apply { putExtra(EXTRA_NAME, name) }
                launcher.launch(intent)
            }
        }
    }
}
