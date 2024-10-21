package com.tatweer.smartdrivingtest.presentation.driveTest.addManualFault

import com.tatweer.smartdrivingtest.domain.model.FaultGroup
import com.tatweer.smartdrivingtest.presentation.base.BaseUIState

data class AddManualFaultDialogState(
    val faultGroups: List<FaultGroup> = listOf(FaultGroup.ForPreview,FaultGroup.ForPreview,FaultGroup.ForPreview),
) : BaseUIState()
