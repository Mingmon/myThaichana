package com.example.learningroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learningroom.Database.Word

class WordListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
        //ตัวที่จะแสดงใน recyclerview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
        //recyclerview_item ก็เตือตัวบน แต่อันนี้สร้างมั้ง เหมือนจับตัวบนยัดใส่รีไซเคิล หรอ
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        //words ตอนแรกประกาศเป็น empty
        holder.wordItemView.text = current.word
    }

    internal fun setWords(words: List<Word>){
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size




}