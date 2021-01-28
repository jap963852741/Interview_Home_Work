package com.jap.interviewhomework.Repository

import com.jap.interviewhomework.Repository.remotedatasource.UpdateResponse
import com.jap.interviewhomework.ui.update.Timezone
import io.reactivex.rxjava3.core.Observable

class UpdateRepository(val dataSource: UpdateDataSource) {

    fun update(sessionToken: String , objectId: String , timezone: Timezone): Observable<UpdateResponse> {
        return dataSource.update(sessionToken , objectId , timezone)
    }

}