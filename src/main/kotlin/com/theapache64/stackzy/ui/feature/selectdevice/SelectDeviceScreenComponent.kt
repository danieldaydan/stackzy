package com.theapache64.stackzy.ui.feature.selectdevice

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.theapache64.stackzy.data.local.AndroidDevice
import com.theapache64.stackzy.ui.navigation.Component
import javax.inject.Inject

class SelectDeviceScreenComponent(
    private val componentContext: ComponentContext,
    private val onDeviceSelected: (AndroidDevice) -> Unit
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var selectDeviceViewModel: SelectDeviceViewModel

    init {
        DaggerSelectDeviceComponent
            .create()
            .inject(this)
    }

    @Composable
    override fun render() {
        // Start watching
        selectDeviceViewModel.watchConnectedDevices()

        SelectDeviceScreen(
            selectDeviceViewModel,
            onDeviceSelected = {
                // Stop watching
                selectDeviceViewModel.removeConnectionWatcher()

                onDeviceSelected(it)
            }
        )
    }

}