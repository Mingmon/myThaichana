package com.example.learningroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningroom.Database.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var newWordActivityRequestCode = 1

    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, 1)
        }

        val historyBtn = findViewById<Button>(R.id.history)

        historyBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,HistoryActivity::class.java)
            startActivity(intent)
        }




/*        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)*/
        val adapter = WordListAdapter(this)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var tempTitle = data?.getStringExtra("title")
        var tempYear = data?.getStringExtra("year")
        var tempHere = data?.getStringExtra("here")


        val youAreAt = findViewById<TextView>(R.id.u_r_at)
        youAreAt.visibility = View.VISIBLE


        val textView = findViewById<TextView>(R.id.textView2)
        textView.text = tempHere


        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
