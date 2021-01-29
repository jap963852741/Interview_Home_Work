package com.jap.interviewhomework.ui.update

import com.jap.interviewhomework.Repository.bean.UpdateResponse
import java.io.Serializable

/**
 * User details post authentication that is exposed to the UI
 */
data class UpdateDataResult(
        val updateResponse: UpdateResponse
        //... other Repository fields that may be accessible to the UI
): Serializable