package com.example.hvaquest.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.QuestionViewModel
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel
    private val args: QuestionFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity as AppCompatActivity).get(QuestionViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val question = viewModel.getQuestion(args.questionNumber)
        tvQuestion.text = question.question

        question.choices.forEach {
            val rb = RadioButton(context)
            rb.id = View.generateViewId()
            rb.text = it
            rgChoices.addView(rb)
        }

        btnConfirm.setOnClickListener {
            if (rgChoices.findViewById<RadioButton>(rgChoices.checkedRadioButtonId).text == question.correctAnswer) {
                Toast.makeText(context, R.string.correct_answer, Toast.LENGTH_SHORT).show()
                val action = QuestionFragmentDirections.actionQuestionFragmentToLocationFragment(args.questionNumber)
                findNavController().navigate(action)
            }else{
                Toast.makeText(context, R.string.wrong_answer, Toast.LENGTH_SHORT).show()
            }

        }
    }
}
