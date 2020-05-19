package com.example.hvaquest.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.QuestionViewModel
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * A simple [Fragment] subclass.
 */
class LocationFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel
    private val args: LocationFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity as AppCompatActivity).get(QuestionViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = viewModel.getQuestion(args.questionNumber)
        ivLocation.setImageResource(question.clue)

        btnNext.setOnClickListener {
            if(viewModel.isValidIndex(args.questionNumber+1)) {
                val action = LocationFragmentDirections.actionLocationFragmentToQuestionFragment(args.questionNumber + 1)
                findNavController().navigate(action)
            }else{
                findNavController().navigate(R.id.action_locationFragment_to_endFragment)
            }
        }
    }
}
