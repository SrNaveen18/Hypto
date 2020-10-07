package com.example.hypto.ui.landing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hypto.R
import com.example.hypto.databinding.*
import com.example.hypto.helper.Constants
import com.example.hypto.model.QuestionsItem

const val TYPE_TEXT = 0
const val TYPE_EMAIL_TEXT = 1
const val TYPE_IMAGE = 2
const val TYPE_RADIO = 3
const val TYPE_MULTI_SELECT = 4
const val TYPE_DROPDOWN = 5

class QuestionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var questions: List<QuestionsItem>? = null

    fun setQuestionList(questionsItem: List<QuestionsItem>?) {
        this.questions = questionsItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_TEXT -> {
                QuestionTextViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.question_text_adapter,
                        parent, false
                    )
                )
            }
            TYPE_EMAIL_TEXT -> {
                QuestionEmailTextViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.question_emailtext_adapter,
                        parent, false
                    )
                )
            }
            TYPE_RADIO -> {
                QuestionRadioViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.question_radio_adapter,
                        parent, false
                    )
                )
            }
            TYPE_DROPDOWN -> {
                QuestionDropDownViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.question_dropdown_adapter,
                        parent, false
                    )
                )
            }
            TYPE_IMAGE -> {
                QuestionImageViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.question_image_adapter,
                        parent, false
                    )
                )
            }
            TYPE_MULTI_SELECT ->
                QuestionMultiSelectViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.question_multiselect_adapter,
                        parent, false
                    )
                )
            else -> {
                QuestionTextViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.question_text_adapter,
                        parent, false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is QuestionImageViewHolder -> {
                holder.onBind(questions?.get(position))
            }
            is QuestionTextViewHolder -> {
                holder.onBind(questions?.get(position))
            }
            is QuestionEmailTextViewHolder -> {
                holder.onBind(questions?.get(position))
            }
            is QuestionDropDownViewHolder -> {
                holder.onBind(questions?.get(position))
            }
            is QuestionMultiSelectViewHolder -> {
                holder.onBind(questions?.get(position))
            }
            is QuestionRadioViewHolder -> {
                holder.onBind(questions?.get(position))
            }
        }
    }

    override fun getItemCount(): Int = questions?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return when (questions?.get(position)?.type) {
            Constants.QUESTION_TYPE_TEXT -> TYPE_TEXT
            Constants.QUESTION_TYPE_EMAIL_TEXT -> TYPE_EMAIL_TEXT
            Constants.QUESTION_TYPE_IMAGE -> TYPE_IMAGE
            Constants.QUESTION_TYPE_RADIO -> TYPE_RADIO
            Constants.QUESTION_TYPE_MULTI_SELECT -> TYPE_MULTI_SELECT
            Constants.QUESTION_TYPE_DROPDOWN -> TYPE_DROPDOWN
            else -> TYPE_TEXT
        }
    }
}

class QuestionTextViewHolder(private var questionTextAdapterBinding: QuestionTextAdapterBinding) :
    RecyclerView.ViewHolder(questionTextAdapterBinding.root) {
    fun onBind(questionItems: QuestionsItem?) {
        questionTextAdapterBinding.questionItems = questionItems
    }
}

class QuestionEmailTextViewHolder(private var questionEmailtextAdapterBinding: QuestionEmailtextAdapterBinding) :
    RecyclerView.ViewHolder(questionEmailtextAdapterBinding.root) {
    fun onBind(questionItems: QuestionsItem?) {
        questionEmailtextAdapterBinding.questionItems = questionItems
    }

}

class QuestionDropDownViewHolder(private var questionDropdownAdapterBinding: QuestionDropdownAdapterBinding) :
    RecyclerView.ViewHolder(questionDropdownAdapterBinding.root) {
    fun onBind(questionItems: QuestionsItem?) {
        questionDropdownAdapterBinding.questionItems = questionItems
        val string =
            questionItems?.values?.let {
                val spinnerItem = ArrayList<Any>()
                it.forEach { item ->
                    item.value?.let {
                        spinnerItem.add(it)
                    }
                }
                val adapter: ArrayAdapter<Any> = ArrayAdapter<Any>(
                    questionDropdownAdapterBinding.root.context,
                    android.R.layout.simple_spinner_dropdown_item, spinnerItem
                )
                questionDropdownAdapterBinding.spinner.adapter = adapter
            }
    }
}

class QuestionImageViewHolder(private var questionImageAdapterBinding: QuestionImageAdapterBinding) :
    RecyclerView.ViewHolder(questionImageAdapterBinding.root) {
    fun onBind(questionItems: QuestionsItem?) {
        questionImageAdapterBinding.questionItems = questionItems
    }
}

class QuestionRadioViewHolder(private var questionRadioAdapterBinding: QuestionRadioAdapterBinding) :
    RecyclerView.ViewHolder(questionRadioAdapterBinding.root) {
    fun onBind(questionItems: QuestionsItem?) {
        questionRadioAdapterBinding.questionItems = questionItems

        if (questionItems?.values?.isNotEmpty() == true) {
            questionRadioAdapterBinding.radioGroup.removeAllViews()
            for (items in questionItems.values.indices) {
                val radioButton = RadioButton(questionRadioAdapterBinding.root.context)
                radioButton.text = questionItems.values[items].value.toString()
                questionItems.values[items].id?.let {
                    radioButton.id = it
                }
                questionRadioAdapterBinding.radioGroup.addView(radioButton)
            }
        }
    }
}

class QuestionMultiSelectViewHolder(private var questionMultiselectAdapterBinding: QuestionMultiselectAdapterBinding) :
    RecyclerView.ViewHolder(questionMultiselectAdapterBinding.root) {
    fun onBind(questionItems: QuestionsItem?) {
        questionMultiselectAdapterBinding.questionItems = questionItems

        if (questionItems?.values?.isNotEmpty() == true) {
            questionMultiselectAdapterBinding.lnrCheckBox.removeAllViews()
            for (items in questionItems.values.indices) {
                val checkBox = CheckBox(questionMultiselectAdapterBinding.root.context)
                checkBox.text = questionItems.values[items].value.toString()
                questionItems.values[items].id?.let {
                    checkBox.id = it
                }
                questionMultiselectAdapterBinding.lnrCheckBox.addView(checkBox)
            }
        }
    }

}