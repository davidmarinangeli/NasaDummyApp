package com.example.nasabucket.ui.notifications

import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nasabucket.utils.PowerConnectionReceiver
import com.example.nasabucket.R
import com.example.nasabucket.viewmodels.NotificationsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var textview: TextView
    private lateinit var textview2: TextView
    private lateinit var powerReceiver: PowerConnectionReceiver
    private var disposables=  CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        powerReceiver = PowerConnectionReceiver()

        activity?.registerReceiver(
            powerReceiver, IntentFilter().also {
                it.addAction("android.intent.action.ACTION_POWER_CONNECTED")
                it.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        textview = root.findViewById(R.id.text_notifications1)
        textview2 = root.findViewById(R.id.text_notifications2)
        notificationsViewModel.text.observe(this, Observer {
            textview.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        powerReceiver.observable
            .map {
                when (it) {
                    0 -> "Disconnected"
                    1 -> "Connected"
                    else -> "Boh"
                }
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textview.text = it
            }.also { disposables.add(it) }

        powerReceiver.observable
            .map {
                when (it) {
                    0 -> "Disconnected"
                    1 -> "Connected"
                    else -> "Boh"
                }
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textview2.text = it
            }.also { disposables.add(it) }
    }

    override fun onDestroy() {
        disposables.clear()
        activity?.unregisterReceiver(powerReceiver)
        super.onDestroy()
    }
}