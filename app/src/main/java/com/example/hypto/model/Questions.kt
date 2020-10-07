package com.example.hypto.model


import com.google.gson.annotations.SerializedName

data class Questions(@SerializedName("questions")
                    val questions: List<QuestionsItem>?,
                    @SerializedName("course_process_id")
                    val courseProcessId: Int = 0,
                    @SerializedName("status")
                    val status: Boolean = false)


data class QuestionsItem(@SerializedName("error_message")
                         val errorMessage: String? = "",
                         @SerializedName("multi_select_flag")
                         val multiSelectFlag: Int? = 0,
                         @SerializedName("multi_option_flag")
                         val multiOptionFlag: Int? = 0,
                         @SerializedName("question")
                         val question: String? = "",
                         @SerializedName("answers")
                         val answers: String? = "",
                         @SerializedName("image_flag")
                         val imageFlag: Int? = 0,
                         @SerializedName("placeholder")
                         val placeholder: String? = "",
                         @SerializedName("type")
                         val type: String? = "",
                         @SerializedName("mandatory")
                         val mandatory: Int? = 0,
                         @SerializedName("url")
                         val url: String? = "",
                         @SerializedName("values")
                         val values: List<Values>? = listOf(),
                         @SerializedName("question_id")
                         val questionId: Int? = 0)


data class Values(@SerializedName("image_url")
                  val imageUrl: String? = "",
                  @SerializedName("id")
                  val id: Int? = 0,
                  @SerializedName("value")
                  val value: Any? = 0,
                  @SerializedName("selected")
                  val selected: Int? = 0)





