package tap.ptic.numberslight.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.number_item.view.*
import tap.ptic.numberslight.R
import tap.ptic.numberslight.data.db.entity.NumberEntityItem

class NumbersRecyclerAdapter(
    private val context: Context,
    private val isDualPane: Boolean
) : RecyclerView.Adapter<NumbersRecyclerAdapter.ViewHolder>() {

    private var listOfNumbers = ArrayList<NumberEntityItem>()
    private val listOfSelectedNumbers = mutableListOf<NumberEntityItem>()
    private lateinit var listener: OnNumberClickListener
    private var selected: NumberEntityItem? = null

    inner class ViewHolder(itemView: View, listener: OnNumberClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val numberItemName: TextView = itemView.numberItem_name
        private val numberItemImageView: ImageView = itemView.numberItem_image
        private val rootLayout: CardView = itemView.numberItem_rootLayout
        private val constraintLayout: ConstraintLayout = itemView.numberItem_constrLayout

        init {
            rootLayout.setOnClickListener { view ->
                val position = adapterPosition
                listener.onItemClick(position = position, view = view)
            }
        }

        fun setItems(numberEntityItem: NumberEntityItem, isSelected: Boolean) {
            numberItemName.text = numberEntityItem.name
            Picasso.with(context)
                .load(numberEntityItem.image)
                .into(numberItemImageView)
            if (isSelected && listOfSelectedNumbers.contains(numberEntityItem)) {
                constraintLayout.setBackgroundColor(context.resources.getColor(R.color.colorSelected))
            } else constraintLayout.setBackgroundDrawable(context.resources.getDrawable(R.drawable.custom_ripple))
        }
    }

    fun setNumbers(numbers: List<NumberEntityItem>) {
        listOfNumbers.clear()
        listOfNumbers = numbers as ArrayList<NumberEntityItem>
        notifyDataSetChanged()
    }

    fun initListener(listener: OnNumberClickListener) {
        this.listener = listener
    }

    interface OnNumberClickListener {
        fun onItemClick(position: Int, view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.number_item, parent, false)
        return ViewHolder(layout, listener)
    }

    override fun getItemCount(): Int {
        return listOfNumbers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isSelected = when (isDualPane) {
            true -> listOfNumbers[holder.adapterPosition] == selected
            false -> false
        }
        listOfSelectedNumbers.clear()
        if (isSelected) listOfSelectedNumbers.add(listOfNumbers[position])
        Log.d("LISTNOPE", "List size: ${listOfSelectedNumbers.size}")
        holder.setItems(listOfNumbers[position], isSelected)
    }

    fun setSelected(selectedNumber: NumberEntityItem) {
        selected = selectedNumber
        notifyDataSetChanged()
    }
}