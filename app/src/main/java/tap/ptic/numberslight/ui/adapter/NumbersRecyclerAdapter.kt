package tap.ptic.numberslight.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NumbersRecyclerAdapter : RecyclerView.Adapter<NumbersRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, listener: OnNumberClickListener): RecyclerView.ViewHolder(itemView){

    }

    interface OnNumberClickListener{
        fun onItemClick(position: Int, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}