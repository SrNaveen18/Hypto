package com.example.hypto.ui.landing

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hypto.BR
import com.example.hypto.R
import com.example.hypto.base.BaseFragment
import com.example.hypto.databinding.FragmentLandingBinding
import com.example.hypto.extension.isNetConnected
import com.example.hypto.helper.ApisResponse
import com.example.hypto.model.Questions
import com.example.hypto.ui.landing.adapter.QuestionAdapter
import kotlinx.android.synthetic.main.fragment_landing.*
import org.koin.android.ext.android.inject

class LandingFragment : BaseFragment<FragmentLandingBinding, LandingViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    private val landingViewModel: LandingViewModel by inject()
    override fun getViewModel(): LandingViewModel? = landingViewModel

    override fun getBindingVariable(): Int = BR.landingVM

    override fun getContentView(): Int = R.layout.fragment_landing

    private var adapterQuestions: QuestionAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (checkInternetAvailable()) {
            setAdapter()
            getViewModel()?.getQuestions()?.observe(viewLifecycleOwner, observer)
        }
        swipe_refresh_layout.setOnRefreshListener(this)
    }

    private val observer = Observer<ApisResponse<Questions>> { apiResponse ->
        when (apiResponse) {
            is ApisResponse.Success -> {
                adapterQuestions?.setQuestionList(apiResponse.response.questions)
            }
            is ApisResponse.Error -> {
                Toast.makeText(
                    requireContext(),
                    apiResponse.exception.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            ApisResponse.LOADING -> {
                showProgress()
            }
            ApisResponse.COMPLETED -> {
                hideProgress()
            }
        }
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    private fun setAdapter() {
        adapterQuestions = QuestionAdapter()
        recyclerView.adapter = adapterQuestions
    }

    private fun checkInternetAvailable(): Boolean {
        val hasInternet = requireActivity().isNetConnected()
        return if (!hasInternet) {
            showMessageAlert(getString(R.string.error_no_internet))
            false
        } else {
            true
        }
    }

    private fun showMessageAlert(message: String) {
        val dialog = AlertDialog.Builder(requireActivity())
        dialog.setMessage(message)
        dialog.setPositiveButton(getString(R.string.alert_close)) { dialog, _ ->
            requireActivity().finish()
            dialog?.dismiss()
        }
        dialog.create().show()
    }

    override fun onRefresh() {
        if (checkInternetAvailable()) {
            getViewModel()?.getQuestions()?.observe(viewLifecycleOwner, observer)
        }
        swipe_refresh_layout.isRefreshing = false
    }
}