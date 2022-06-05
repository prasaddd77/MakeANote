package com.prasad.makeanote.ui.Adapters

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.prasad.makeanote.R
import com.prasad.makeanote.databinding.ItemNotesBinding
import com.prasad.makeanote.model.Notes
import com.prasad.makeanote.ui.Fragments.HomeFragment
import com.prasad.makeanote.ui.Fragments.HomeFragmentDirections

class NotesRVAdapter(val requireContext: Context,var  notesList: List<Notes>) :
    RecyclerView.Adapter<NotesRVAdapter.notesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))



    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.txtTitle1.text = data.title
        holder.binding.txtSubTitle1.text = data.subtitle
        holder.binding.txtDate.text = data.date

        when(data.priority){
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }

        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)


        }

    }

    override fun getItemCount(): Int {
       return notesList.size

    }
}