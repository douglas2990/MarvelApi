package com.douglas2990.marvelapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.marvelapi.adapter.CharacterAdapter
import com.douglas2990.marvelapi.adapter.MarvelAdapter
import com.douglas2990.marvelapi.databinding.FragmentFirstBinding
import com.douglas2990.marvelapi.screenState.CharacterScreenState
import com.douglas2990.marvelapi.screenState.FirstScreenState
import androidx.core.os.bundleOf
import com.douglas2990.marvelapi.adapter.allComics.AllComicsAdapter
import com.douglas2990.marvelapi.screenState.allcomics.AllComicsScreenState
import com.douglas2990.marvelapi.viewModel.CharacterViewModel
import com.douglas2990.marvelapi.viewModel.MarvelViewModel
import com.douglas2990.marvelapi.viewModel.allComics.AllComicsViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: MarvelViewModel by viewModels()
    private val viewModelComics: AllComicsViewModel by viewModels()
    private val viewModelCharacter: CharacterViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    private val firstListenerMarvelAdapter = object : MarvelAdapter.MarvelInterface{
        override fun onclick(marvelId: String) {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundleOf("id" to marvelId))
        }

    }

    private val firstListenerCharacterAdapter = object : CharacterAdapter.CharacterInterface{
        override fun onclick(characterId: String) {
        //override fun onclick(characterId: Int) {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundleOf("id" to characterId))
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)
        viewModel1()
        //viewModelComics1()


        //ARRUMAR ABAIXO
        //viewModelCharacter1()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun viewModel1(){
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerFirst.isVisible = state is FirstScreenState.Success
                when (state){
                    is FirstScreenState.Loading -> {}
                    is FirstScreenState.Success -> {
                        binding.recyclerFirst.adapter = MarvelAdapter(state.data, firstListenerMarvelAdapter)
                    }
                    is FirstScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }

            }
        )
    }

    fun viewModelCharacter1(){
        viewModelCharacter.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerFirst.isVisible = state is CharacterScreenState.Success
                when (state){
                    is CharacterScreenState.Loading -> {}
                    is CharacterScreenState.Success -> {
                        binding.recyclerFirst.adapter = CharacterAdapter(state.data, firstListenerCharacterAdapter)
                    }
                    is CharacterScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }

            }
        )
    }

    fun viewModelComics1(){
        viewModelComics.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerFirst.isVisible = state is AllComicsScreenState.Success
                when (state){
                    is AllComicsScreenState.Loading -> {}
                    is AllComicsScreenState.Success -> {
                        binding.recyclerFirst.adapter = AllComicsAdapter(state.data)
                    }
                    is AllComicsScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}