package com.example.learningroom

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.example.learningroom.Database.Word
import kotlinx.android.synthetic.main.recyclerview_item.*
import java.time.LocalDateTime


class HistoryActivity : AppCompatActivity() {

    lateinit var wordViewModel: WordViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        val clickBtn = findViewById<Button>(R.id.button)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewHistory)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.notcheckout.observe(this, Observer { words ->
            words?.let {

                adapter.setWords(it)


            }
        })


//        wordViewModel.allWords.observe(this, Observer { words ->
//            words?.let { adapter.setWords(it) }
//        })

//        val checkoutTime = LocalDateTime.now().toString()

//        clickBtn.setOnClickListener {
////            val word = Word(textView.toString(),datetime.toString(),checkoutTime)
//            Log.i("tag","textView.toString()")
////            wordViewModel.insert(word)
//        }


    }
}
