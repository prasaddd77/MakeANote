package com.prasad.makeanote.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prasad.makeanote.R
import com.prasad.makeanote.databinding.FragmentEditNoteBinding
import com.prasad.makeanote.model.Notes
import com.prasad.makeanote.viewmodel.NotesViewModel
import java.util.*

class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding: FragmentEditNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.etEditTitle.setText(oldNotes.datafromcreatenote.title)
        binding.etEditSubtitle.setText(oldNotes.datafromcreatenote.subtitle)
        binding.etEditNote.setText(oldNotes.datafromcreatenote.notes)

        when(oldNotes.datafromcreatenote.priority){
            "1" -> {
                priority = "1"
                binding.imgGreenDot1.setImageResource(R.drawable.ic_baseline_done_24)
                binding.imgRedDot1.setImageResource(0)
                binding.imgYelloDot1.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.imgYelloDot1.setImageResource(R.drawable.ic_baseline_done_24)
                binding.imgRedDot1.setImageResource(0)
                binding.imgGreenDot1.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.imgRedDot1.setImageResource(R.drawable.ic_baseline_done_24)
                binding.imgYelloDot1.setImageResource(0)
                binding.imgGreenDot1.setImageResource(0)
            }

        }

        binding.imgGreenDot1.setOnClickListener {
            priority = "1"
            binding.imgGreenDot1.setImageResource(R.drawable.ic_baseline_done_24)
            binding.imgRedDot1.setImageResource(0)
            binding.imgYelloDot1.setImageResource(0)

        }

        binding.imgYelloDot1.setOnClickListener {
            priority = "2"
            binding.imgYelloDot1.setImageResource(R.drawable.ic_baseline_done_24)
            binding.imgRedDot1.setImageResource(0)
            binding.imgGreenDot1.setImageResource(0)

        }

        binding.imgRedDot1.setOnClickListener {
            priority = "3"
            binding.imgRedDot1.setImageResource(R.drawable.ic_baseline_done_24)
            binding.imgYelloDot1.setImageResource(0)
            binding.imgGreenDot1.setImageResource(0)
        }

        binding.fabEditSavedNote.setOnClickListener {
            updateNotes(it)
        }


        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.etEditTitle.text
        val subtitle = binding.etEditSubtitle.text
        val notes = binding.etEditNote.text

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMM d, yyyy",d.time)
        val data = Notes(oldNotes.datafromcreatenote.id,
            title = title.toString(),
            notes = notes.toString(),
            subtitle = subtitle.toString(),
            date = notesDate.toString(),
            priority = priority
        )

        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Note Updated Successfully", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete){
            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val txtYes = bottomSheet.findViewById<TextView>(R.id.txtYes)
            val txtNo = bottomSheet.findViewById<TextView>(R.id.txtNo)


            txtYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.datafromcreatenote.id!!)
                bottomSheet.dismiss()

            }

            txtNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }


}