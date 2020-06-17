package com.example.learningroom

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.learningroom.Database.Word
import com.example.learningroom.Database.WordRoomDatabase
import java.time.LocalDateTime

class WordListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()

    lateinit var wordViewModel: WordViewModel



    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        val dateTimeView: TextView = itemView.findViewById(R.id.datetime)

        val btn: Button = itemView.findViewById(R.id.button)
        //ตัวที่จะแสดงใน recyclerview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return WordViewHolder(itemView)
        //recyclerview_item ก็เตือตัวบน แต่อันนี้สร้างมั้ง เหมือนจับตัวบนยัดใส่รีไซเคิล หรอ
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        //words ตอนแรกประกาศเป็น empty
        holder.wordItemView.text = current.word
        holder.dateTimeView.text = current.datetime

        val checkoutTime = LocalDateTime.now().toString()

        holder.btn.setOnClickListener{
            /*holder.wordItemView.visibility = View.GONE
            holder.dateTimeView.visibility = View.GONE
            holder.btn.visibility = View.GONE*/


//            val word = Word(current.word,current.datetime,checkoutTime)
//            wordViewModel.insert(word)
        }
    }

    internal fun setWords(words: List<Word>){
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size




}