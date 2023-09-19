package com.sachin.nasa.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import coil.api.load
import com.sai.fabula.R
import com.sai.fabula.databinding.ActivityMainBinding
import com.sachin.nasa.viewmodel.MainViewModel
import com.shreyaspatil.MaterialDialog.MaterialDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()


    private lateinit var activityViewBinding: ActivityMainBinding

    val url: String = ""
    val title: String = ""
    val date: String = ""
    val desc: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityViewBinding.root)

        getAPODImage()

        viewModel.APODLiveData.observe(this, Observer {

         //   Log.d("thisdata", it.hdurl.toString())

            activityViewBinding.img.load(it.hdurl){
                placeholder(R.drawable.ic_photo)
                    .error(R.drawable.ic_broken_image)
            }
            activityViewBinding.txtTitle.text = it.title?.trim().toString();
            activityViewBinding.txtdate.text = it.date?.trim().toString();
            activityViewBinding.txtdescription.text = it.explanation?.trim().toString();



            activityViewBinding.swipeetorefresh.isRefreshing = false;

        })


        activityViewBinding.swipeetorefresh.setOnRefreshListener {
            getAPODImage()

        }

        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            if (errorMessage.isNotEmpty()) {

                Log.d("Error", "Error message: $errorMessage")

                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })


    }



    override fun onBackPressed() {
        MaterialDialog.Builder(this)
            .setTitle("Exit?")
            .setMessage("Are you sure want to exit?")
            .setPositiveButton("Yes") { dialogInterface, _ ->
                dialogInterface.dismiss()
                super.onBackPressed()
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .build()
            .show()
    }

    private fun getAPODImage() {
        viewModel.getAPOD()


    }


}
