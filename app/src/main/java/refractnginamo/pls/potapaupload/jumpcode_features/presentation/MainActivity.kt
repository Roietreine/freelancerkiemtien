package refractnginamo.pls.potapaupload.jumpcode_features.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import refractnginamo.pls.potapaupload.R
import refractnginamo.pls.potapaupload.jumpcode_features.utils.UiState
import refractnginamo.pls.potapaupload.jumpcode_features.utils.isNetworkConnected
import refractnginamo.pls.potapaupload.jumpcode_features.viewmodel.JumpCodeViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[JumpCodeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.urlResponse.observe(this) { state ->
            when (state) {
                is UiState.Success -> {
                    if (state.data.code == "0") {
                        Log.d("JumpCode", state.data.data?.jumpAddress ?: "")
                        if (state.data.data?.jump == true) {
                            toNextActivity(state.data.data.jumpAddress)
                        } else {
                            toNextActivity()
                        }
                    } else errorHandling(state.data.msg ?: "")
                }
                is UiState.Error -> errorHandling(state.exception.localizedMessage ?: "")
            }
        }
        if (isNetworkConnected()) viewModel.getJumpUrl(packageName)
        else toNoInternetActivity()
    }

    private fun toNextActivity(url: String? = null) {
        startActivity(
            WebViewActivity.createIntent(
                this,
                url ?: "file:///android_asset/index.html"
            )
        )

        finish()
    }
    private fun errorHandling(message: String) {
        Log.d("Error", message)
        toNextActivity()
    }
    private fun toNoInternetActivity() {
        startActivity(NoInternetActivity.createIntent(this))
        finish()
    }
}