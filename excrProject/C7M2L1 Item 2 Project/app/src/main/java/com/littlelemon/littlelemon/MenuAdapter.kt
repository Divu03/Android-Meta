package com.littlelemon.littlelemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MenuAdapter : Adapter<MenuAdapter.MenuViewHolder>() {
    private val menuItems: MutableList<String> = mutableListOf()

    fun setMenuItems(menuItems: List<String>) {
        this.menuItems.clear()
        this.menuItems.addAll(menuItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MenuViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
    )

    override fun getItemCount() = menuItems.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    class MenuViewHolder(view: View) : ViewHolder(view) {
        private val itemName: TextView by lazy { view.findViewById(R.id.menu_item_name) }
        fun bind(menuItem: String) {
            itemName.text = menuItem
        }
    }
}
