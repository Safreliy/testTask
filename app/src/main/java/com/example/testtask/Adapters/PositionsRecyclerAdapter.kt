package com.example.testtask.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.Model.DishData
import com.example.testtask.R
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.widget.LinearLayout
import com.example.testtask.Model.Position
import java.net.URL


class PositionsRecyclerAdapter(private val dishes: Array<Position?>?, private val categories: Array<String>?) ://как-то с категориями разобраться
    RecyclerView.Adapter<PositionsRecyclerAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dishName: TextView = view.findViewById(R.id.dishName)
        var cost: TextView = view.findViewById(R.id.cost)
        var image: ImageView = view.findViewById(R.id.dishImage)
        var background: LinearLayout = view.findViewById(R.id.dishBackground)
        var categoryName: TextView = view.findViewById(R.id.categoryName)
        init {
            // Define click listener for the ViewHolder's View.
            //textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dish_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if(dishes?.get(position)?.dishId == 0){
            viewHolder.categoryName.visibility = View.VISIBLE;
            viewHolder.categoryName.text = categories?.get(dishes?.get(position)?.category ?: 0)
        }
        else{
            viewHolder.categoryName.visibility = View.GONE;
        }
        val urlImg = URL(dishes?.get(position)?.image)
        val bmpImg = BitmapFactory.decodeStream(urlImg.openConnection().getInputStream())
        viewHolder.image.setImageBitmap(bmpImg)

        val urlBckg = URL(dishes?.get(position)?.image)
        val bmpBckg = BitmapFactory.decodeStream(urlBckg.openConnection().getInputStream())
        viewHolder.image.setImageBitmap(bmpBckg)

        viewHolder.dishName.text = dishes?.get(position)?.name
        viewHolder.cost.text = dishes?.get(position)?.cost.toString()
    }

    override fun getItemCount() = dishes?.size ?: 0
}