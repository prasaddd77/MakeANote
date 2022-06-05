package com.prasad.makeanote.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.prasad.makeanote.R
import com.prasad.makeanote.databinding.FragmentCreateNoteBinding
import com.prasad.makeanote.model.Notes
import com.prasad.makeanote.viewmodel.NotesViewModel
import java.util.*


class CreateNoteFragment : Fragment() {

    lateinit var binding1 : FragmentCreateNoteBinding
    lateinit var etTitle : EditText
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding1 = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        binding1.imgGreenDot.setImageResource(R.drawable.ic_baseline_done_24)

        binding1.imgGreenDot.setOnClickListener {
            priority = "1"
            binding1.imgGreenDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding1.imgRedDot.setImageResource(0)
            binding1.imgYellowDot.setImageResource(0)

        }

        binding1.imgYellowDot.setOnClickListener {
            priority = "2"
            binding1.imgYellowDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding1.imgRedDot.setImageResource(0)
            binding1.imgGreenDot.setImageResource(0)

        }

        binding1.imgRedDot.setOnClickListener {
            priority = "3"
            binding1.imgRedDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding1.imgYellowDot.setImageResource(0)
            binding1.imgGreenDot.setImageResource(0)
        }

        binding1.fabSaveNote.setOnClickListener {
            createNotes(it)
        }
        // Inflate the layout for this fragment
        return binding1.root
    }

    private fun createNotes(it: View?) {

        val title = binding1.etTitle.text
        val subtitle = binding1.etSubtitle.text
        val notes = binding1.etNote.text

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMM d, yyyy",d.time)
        val data = Notes(null,
            title = title.toString(),
            notes = notes.toString(),
            subtitle = subtitle.toString(),
            date = notesDate.toString(),
            priority = priority
        )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Note Created Successfully", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)


    }
}