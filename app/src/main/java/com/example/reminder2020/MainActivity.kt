package com.example.reminder2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabOpened = false

        fab.setOnClickListener {

            if (!fabOpened) {

                fabOpened = true

                fab_map.animate().translationY(-resources.getDimension(R.dimen.standard_66))
                fab_time.animate().translationY(-resources.getDimension(R.dimen.standard_166))


            } else {

                fabOpened = false
                fab_map.animate().translationY(0f)
                fab_time.animate().translationY(0f)

            }


        }



        fab_time.setOnClickListener {

            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)

        }

        fab_map.setOnClickListener {

            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }






    }

    override fun onResume() {
        super.onResume()
refrechlist ()


    }

    private fun refrechlist() {

        doAsync {
            val db = Room.databaseBuilder(
                applicationContext, AppDatabase::class.java, "reminder"
            ).build()
            val reminders = db.reminderDao().getReminders()
            db.close()

            uiThread {
                if (reminders.isNotEmpty()) {
                    val adapter = ReminderAdapter(applicationContext, reminders)
                    list.adapter = adapter
                } else {

                    toast("No reminder yet")
                }
            }


        }

    }
}
