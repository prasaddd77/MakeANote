package com.prasad.makeanote.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.prasad.makeanote.R
import com.prasad.makeanote.databinding.FragmentHomeBinding
import com.prasad.makeanote.model.Notes
import com.prasad.makeanote.ui.Adapters.NotesRVAdapter
import com.prasad.makeanote.viewmodel.NotesViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel1 : NotesViewModel by viewModels()
    var oldMyNotes= arrayListOf<Notes>()
    lateinit var adapter: NotesRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.recyclerView1.layoutManager = staggeredGridLayoutManager


        viewModel1.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesRVAdapter(requireContext(),notesList)
            binding.recyclerView1.adapter = adapter

        }

        binding.imgFilter.setOnClickListener{
            viewModel1.getAllNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesRVAdapter(requireContext(),notesList)
                binding.recyclerView1.adapter = adapter

            }
        }

        binding.txtHigh.setOnClickListener {

            viewModel1.getHightNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesRVAdapter(requireContext(),notesList)
                binding.recyclerView1.adapter = adapter
            }
        }

        binding.txtMedium.setOnClickListener {
            viewModel1.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesRVAdapter(requireContext(),notesList)
                binding.recyclerView1.adapter = adapter
            }
        }


        binding.txtLow.setOnClickListener {
            viewModel1.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesRVAdapter(requireContext(),notesList)
                binding.recyclerView1.adapter = adapter
            }

        }


        binding.fabAddNote.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.menuSearch)

        val searchView = item.actionView as SearchView

        searchView.queryHint = "Enter Note Here..."
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?){
        val newFilteredList = arrayListOf<Notes>()

        for(i in oldMyNotes){
            if(i.title.contains(p0!!) || i.subtitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)

    }
}