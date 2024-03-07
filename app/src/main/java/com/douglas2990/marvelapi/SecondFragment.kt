package com.douglas2990.marvelapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.douglas2990.marvelapi.databinding.FragmentSecondBinding
import com.douglas2990.marvelapi.screenState.DetailScreenState
import com.douglas2990.marvelapi.viewModel.DetailFactory
import com.douglas2990.marvelapi.viewModel.DetailViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    private val viewModel by viewModels<DetailViewModel>{
        DetailFactory(arguments?.getString("id","") ?: "")
        //DetailFactory((arguments?.getInt(id.toString(),0) ?: "") as Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, { state ->
            //presentLoading(state is DetailScreenState.Loading)

            when(state){
                is DetailScreenState.Loading -> {}
                is DetailScreenState.Success -> {
                    binding.textviewSecond.text = state.data[0].name

                    //binding.textviewSecond.text = state.data[0].thumbnail.path +'.'+ state.data[0].thumbnail.extension
                    binding.buttonSecond.text = state.data[0].name
                    val path = state.data[0].thumbnail.path.toString().replace("http","https")
                    val extension = state.data[0].thumbnail.extension.toString()

                    binding.draweeViewCharacter.setImageURI("$path.$extension")
                    //binding.draweeViewCharacter.setImageURI("https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg")
                    //binding.draweeViewCharacter.setImageURI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + "150" + ".png")



                }
            }


        })

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}