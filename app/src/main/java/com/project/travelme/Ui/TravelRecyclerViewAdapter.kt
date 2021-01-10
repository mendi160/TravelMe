package com.project.travelme.Ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
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

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val source = holder.source
        val destination = holder.destination
        val date = holder.date
        val company = holder.company
        val bChangeStatus = holder.bChangeStatus
        holder.travel = travelList[listPosition]
        source.text = travelList[listPosition].sourceAdders
        destination.text = travelList[listPosition].destinationAddress[0]
        date.text = travelList[listPosition].departureDate
        company.adapter = ArrayAdapter<String>(
            AuthUI.getApplicationContext(),
            android.R.layout.simple_list_item_1,
            travelList[listPosition].serviceProvider.keys.toMutableList()
        )
        bChangeStatus.text = when (holder.travel.status) {
            Status.SENT -> "Confirm"
            Status.RECEIVED -> "Running"
            Status.RUNNING -> "Closed"
            else -> null
        }

    }

    override fun getItemCount(): Int = travelList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var source: TextView = itemView.findViewById(R.id.tvSource) as TextView
        var destination: TextView = itemView.findViewById(R.id.tvDestination) as TextView
        var date: TextView = itemView.findViewById(R.id.tvDate) as TextView
        var bChangeStatus: Button = itemView.findViewById(R.id.bChangeStatus)
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
                            this@ViewHolder.travel.serviceProvider.filterValues { it -> it }.keys.elementAt(
                                0
                            )
                        this@ViewHolder.company.isEnabled = false
                    }
                    if (operatingCompanyItem.text == "Select") {
                        bChangeStatus.isEnabled = false
                    } else {
                        this@ViewHolder.travel
                        travel.serviceProvider[parentView?.getItemIdAtPosition(position)
                            .toString()] to true
                        bChangeStatus.isEnabled = true
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
