package com.example.reminder2025

import android.view.View



import android.view.ViewGroup



import android.widget.BaseAdapter



import android.widget.SimpleAdapter



import kotlinx.android.synthetic.main.list_view_item.view.*



import java.text.SimpleDateFormat



import java.util.*



class ReminderAdapter (context: Context, private val list: Array<String> ): BaseAdapter(){

    class ReminderAdapter (context: Context, private val list: List<Reminder> ): BaseAdapter(){


        private val inflater: LayoutInflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


            val row= inflater.inflate(R.layout.list_view_item ,parent, false)


            row.itemMessage.text = list[position]



            row.itemTrigger.text= "hello"



            row.itemMessage.text = list[position].message







            if (list[position].time!=null) {



                val sdf =SimpleDateFormat("HH:mm dd.MM.yyyy")



                sdf.timeZone = TimeZone.getDefault()







                val time= list[position].time



                val readableTime = sdf.format(time)



                row.itemTrigger.text =readableTime







            } else {



                row.itemTrigger.text="location"



            }







            return row
        }