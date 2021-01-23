package com.project.travelme.Ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.travelme.R
import com.project.travelme.Utils.Enums.Status
import com.project.travelmedrivers.entities.Travel
import kotlin.collections.set


class TravelRecyclerViewAdapter(
    private val travelList: List<Travel>
) : RecyclerView.Adapter<TravelRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.travel_recyclerview, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("RestrictedApi", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val source = holder.source
        val destination = holder.destination
        val date = holder.date
        val company = holder.company
        val bChangeStatus = holder.bChangeStatus
        val bDeleteTravel = holder.bDeleteTravel
        holder.travel = travelList[listPosition]
        source.text = travelList[listPosition].sourceAdders.substringBefore("&")
        destination.text = travelList[listPosition].destinationAddress[0].substringBefore("&")
        date.text = travelList[listPosition].departureDate
        val adapterList = travelList[listPosition].serviceProvider.keys.toMutableList()
        // In the following two lines we make sure that "Select" will be the first appearance in the Spinner items
        val selectIndex = adapterList.indexOf("Select")
        adapterList.let {
            it[selectIndex] = it[0]
            it[0] = "Select"
        }
        company.adapter = ArrayAdapter(
            AuthUI.getApplicationContext(),
            android.R.layout.simple_list_item_1,
            adapterList
        )
        when (holder.travel.status) {
            Status.SENT -> {
                bChangeStatus.text = "Confirm"
                bChangeStatus.setBackgroundColor(Color.rgb(153, 255, 153))
            }
            Status.RECEIVED -> {
                bChangeStatus.text = "Running"
                bChangeStatus.setBackgroundColor(Color.rgb(255, 255, 153))
            }
            Status.RUNNING -> {
                bChangeStatus.text = "Closed"
                bChangeStatus.setBackgroundColor(Color.rgb(255, 102, 102))
            }
            else -> null
        }
        bChangeStatus.text = when (holder.travel.status) {
            Status.SENT -> "Confirm"
            Status.RECEIVED -> "Running"
            Status.RUNNING -> "Closed"
            else -> null
        }
        if (holder.travel.status != Status.SENT)
            bDeleteTravel.visibility = View.GONE

    }

    override fun getItemCount(): Int = travelList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var source: TextView = itemView.findViewById(R.id.tvSource) as TextView
        var destination: TextView = itemView.findViewById(R.id.tvDestination) as TextView
        var date: TextView = itemView.findViewById(R.id.tvDate) as TextView
        var bChangeStatus: Button = itemView.findViewById(R.id.bChangeStatus)
        val bDeleteTravel: FloatingActionButton = itemView.findViewById(R.id.bDeleteTravel)
        var company: Spinner = itemView.findViewById(R.id.sCompany)
        lateinit var travel: Travel

        init {
            bChangeStatus.setOnClickListener {
                this@ViewHolder.travel
                when (bChangeStatus.text) {
                    "Confirm" -> {
                        travel.status = Status.RECEIVED
                        travel.serviceProvider[company.selectedItem.toString()] = true
                        MainActivity.viewModel.updateTravel(travel)
                    }
                    "Running" -> {
                        travel.status = Status.RUNNING
                        MainActivity.viewModel.updateTravel(travel)
                    }
                    "Closed" -> {
                        travel.status = Status.CLOSED
                        MainActivity.viewModel.updateTravel(travel)
                    }
                }
            }
            bDeleteTravel.setOnClickListener {
                this@ViewHolder.travel
                travel.status = Status.CANCELED_BY_CLIENT
                MainActivity.viewModel.cancelTravel(travel)
            }
            company.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("ResourceType")
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {

                    val operatingCompanyItem = selectedItemView as TextView
                    if (this@ViewHolder.travel.serviceProvider.containsValue(true)) {
                        operatingCompanyItem.text =
                            this@ViewHolder.travel.serviceProvider.filterValues { it }.keys.elementAt(
                                0
                            )
                        this@ViewHolder.company.isEnabled = false
                    }
                    if (operatingCompanyItem.text == "Select") {
                        bChangeStatus.isEnabled = false
                        bChangeStatus.setTextColor(Color.WHITE)
                    } else {
                        this@ViewHolder.travel
                        travel.serviceProvider[parentView?.getItemIdAtPosition(position)
                            .toString()] to true
                        bChangeStatus.isEnabled = true
                        bChangeStatus.setTextColor(Color.BLACK)
                        Log.i("a", "a")
                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // your code here
                }
            }
        }


    }
}
