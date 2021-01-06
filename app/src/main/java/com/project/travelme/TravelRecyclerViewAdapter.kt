package com.project.travelme

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
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
        holder.travel = travelList[listPosition]
        source.text = travelList[listPosition].sourceAdders
        destination.text = travelList[listPosition].destinationAddress[0]
        date.text = travelList[listPosition].departureDate
        company.adapter = ArrayAdapter<String>(
            AuthUI.getApplicationContext(),
            android.R.layout.simple_list_item_1,
            travelList[listPosition].serviceProvider.keys.toMutableList()
        )

    }

    override fun getItemCount(): Int = travelList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var source: TextView = itemView.findViewById(R.id.tvSource) as TextView
        var destination: TextView = itemView.findViewById(R.id.tvDestination) as TextView
        var date: TextView = itemView.findViewById(R.id.tvDate) as TextView
        var bConfirm: Button = itemView.findViewById(R.id.bConfirm)
        var bRunning: Button = itemView.findViewById(R.id.bRunning)
        var bFinished: Button = itemView.findViewById(R.id.bFinished)
        var company: Spinner = itemView.findViewById(R.id.sCompany)
        lateinit var travel: Travel

        init {

            bConfirm.setOnClickListener {
                this@ViewHolder.travel
                travel.status = Status.RECEIVED
                travel.serviceProvider[company.selectedItem.toString()] = true
                MainActivity.viewModel.updateTravel(travel)
            }
            bRunning = itemView.findViewById(R.id.bRunning)
            bRunning.setOnClickListener {
                this@ViewHolder.travel
                travel.status = Status.RUNNING
                MainActivity.viewModel.updateTravel(travel)
            }
            bFinished = itemView.findViewById(R.id.bFinished)
            bFinished.setOnClickListener {
                this@ViewHolder.travel
                travel.status = Status.CLOSED
                MainActivity.viewModel.updateTravel(travel)
            }
            company.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("ResourceType")
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {

                    var operatingCompanyItem = selectedItemView as TextView
                    if (this@ViewHolder.travel.serviceProvider.containsValue(true)) {
                        operatingCompanyItem.text =
                            this@ViewHolder.travel.serviceProvider.filterValues { it -> it }.keys.elementAt(
                                0
                            )
                        this@ViewHolder.company.isEnabled = false
                    }
                    if (operatingCompanyItem.text == "Select") {
                        bFinished.isEnabled = false
                        bRunning.isEnabled = false
                        bConfirm.isEnabled = false
                    } else {
                        this@ViewHolder.travel
                        travel.serviceProvider[parentView?.getItemIdAtPosition(position)
                            .toString()] to true
                        bFinished.isEnabled = true
                        bRunning.isEnabled = true
                        bConfirm.isEnabled = true
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