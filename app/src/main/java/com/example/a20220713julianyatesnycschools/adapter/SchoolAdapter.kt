package com.example.a20220713julianyatesnycschools.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220713julianyatesnycschools.data.model.School
import com.example.a20220713julianyatesnycschools.databinding.ItemSchoolBinding

/*class SchoolAdapter(recyclerView: RecyclerView) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    var onItemClick : ((School) -> Unit)? = null

    inner class SchoolViewHolder(val binding: ItemSchoolBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<School>() {
        override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
            return oldItem.dbn == newItem.dbn
        }

        override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var schools: List<School>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = schools.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(ItemSchoolBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {


        holder.binding.apply {
            val school = schools[position]
            tvSchoolname.text = school.school_name
            tvSchoolneighborhood.text = school.neighborhood
            tvDbnItemlist.text = school.dbn
            /*holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailFragment::class.java)
                intent.putExtra("position", position)
                intent.putExtra("dbn", school.dbn)
                intent.putExtra("school_name", school.school_name)
                intent.putExtra("neighborhood", school.neighborhood)
                holder.itemView.context.startActivity(intent)
            }*/
        }
    }
}*/
class SchoolAdapter(private val onItemClicked: (School) -> Unit):
        ListAdapter<School, SchoolAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSchoolBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolAdapter.ViewHolder, position: Int) {

        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)

    }

    class ViewHolder(private var binding: ItemSchoolBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(school: School) {
            binding.apply {
                tvSchoolname.text = school.school_name
                tvSchoolneighborhood.text = school.neighborhood
                tvDbnItemlist.text = school.dbn

            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<School>() {
            override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem.school_name == newItem.school_name
            }
        }
    }


        }