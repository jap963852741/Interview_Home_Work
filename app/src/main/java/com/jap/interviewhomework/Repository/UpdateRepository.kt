package com.jap.interviewhomework.Repository

import com.jap.interviewhomework.Repository.remotedatasource.UpdateResponse
import io.reactivex.rxjava3.core.Observable

class UpdateRepository(val dataSource: UpdateDataSource) {

    fun update(sessionToken: String , objectId: String): Observable<UpdateResponse> {
        return dataSource.update(sessionToken , objectId)
    }

}