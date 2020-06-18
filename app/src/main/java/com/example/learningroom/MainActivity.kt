package com.example.learningroom

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.learningroom.Database.Word
import com.example.learningroom.model.JsonPlaceHolderApi
import com.example.learningroom.model.Merchant
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.log

class MainActivity : AppCompatActivity(),clickitemListener {

    private var newWordActivityRequestCode = 1

    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, ScanActivity::class.java)
            startActivityForResult(intent, 1)
        }

        val historyBtn = findViewById<Button>(R.id.history)

        historyBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,HistoryActivity::class.java)
            startActivity(intent)
        }



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewMain)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        Log.i("tag", wordViewModel.allWords.toString())

        val listUser = wordViewModel.allWords

        val adapter = WordListAdapter(listUser,this)

        val adapter1 = listUser.let { WordListAdapter(it,this) }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
//

        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it)}
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var tempHere = data?.getStringExtra("here")


        val youAreAt = findViewById<TextView>(R.id.u_r_at)
//        youAreAt.visibility = View.VISIBLE


        val textView = findViewById<TextView>(R.id.textView2)




        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(ScanActivity.EXTRA_REPLY)?.let {
//                val currentDateTime = LocalDateTime.now().toString()



//                Log.i("tag",formatted)
//                val word = Word(it,formatted)
//                wordViewModel.insert(word)

                var realPlace = ""



                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api-customer.thaichana.com/shop/0001/")
                    .build()

                val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

                val mycall:Call<Merchant> = jsonPlaceHolderApi.getMerchant(tempHere!!)

                mycall.enqueue(object :Callback<Merchant>{
                    override fun onFailure(call: Call<Merchant>, t: Throwable) {
                        Log.e("ERROR",t.message.toString())
                    }

                    override fun onResponse(call: Call<Merchant>, response: Response<Merchant>) {


                        Log.i("tag","b4")

                        val shop :Merchant = response.body()!!

                        realPlace = shop.shopName

//                        textView.text = realPlace

                        Log.i("tag",shop.shopName)

                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss ")
                        val formatted = current.format(formatter)

                        Log.i("tag",realPlace)

                        val word = Word(realPlace!!,formatted)
                        wordViewModel.insert(word)

                        Log.i("tag","save")



                    }
                })











            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()

            textView.text = "please scan again"
        }






    }

    override fun onItemClick(iten: Word, position: Int) {
        Log.i("tag","clickedddddd")
        Log.i("tag",position.toString())
    }
}
