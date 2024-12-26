package com.example.commonintent

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun openBrowser(view: View) {
        val url = findViewById<TextInputEditText>(R.id.url_input).text.toString()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
        startActivity(intent)
    }

    fun openLocation(view: View) {
        val url = findViewById<TextInputEditText>(R.id.adressinput).text.toString()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$url"))
        startActivity(intent)
    }


    fun searchWeb(view: View) {
        val query = findViewById<TextInputEditText>(R.id.searchInput).text.toString()
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, query)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

    }

    fun searchSong(view: View) {

        val query = findViewById<TextInputEditText>(R.id.songInput).text.toString()
        val intent = Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH).apply {
            putExtra(MediaStore.EXTRA_MEDIA_FOCUS, MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE)
            putExtra(MediaStore.EXTRA_MEDIA_ARTIST, query)
            putExtra(SearchManager.QUERY, query)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
            AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes") { _, _ -> super.onBackPressed() }
                .setNegativeButton("No", null)
                .show()
    }
}