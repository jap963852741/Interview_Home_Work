package com.jap.interviewhomework.Repository

import com.jap.interviewhomework.Repository.bean.UpdateResponse
import com.jap.interviewhomework.Repository.network.UpdateDataSource
import com.jap.interviewhomework.ui.update.Timezone
import io.reactivex.rxjava3.core.Observable

class UpdateRepository(val dataSource: UpdateDataSource) {

    fun update(sessionToken: String , objectId: String , timezone: Timezone): Observable<UpdateResponse> {
        return dataSource.update(sessionToken , objectId , timezone)
    }

}