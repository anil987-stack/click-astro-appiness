package com.android.appinesstask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.appinesstask.R

class LanguageAdapter(private val languages: List<String>) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languages[position]
        holder.bind(language)
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val langText: TextView = itemView.findViewById(R.id.langText)

        fun bind(language: String) {
            langText.text = language
        }
    }
}
