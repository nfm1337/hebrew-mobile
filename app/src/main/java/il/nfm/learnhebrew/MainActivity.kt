package il.nfm.learnhebrew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import il.nfm.learnhebrew.ui.preview.DesignSystemPreview
import il.nfm.learnhebrew.ui.theme.LearnHebrewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnHebrewTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DesignSystemPreview()
                }
            }
        }
    }
}
