package com.example.demosihappandroidpart.domain.use_cases.use_cases_get_all_workers

import com.example.demosihappandroidpart.core.Resource
import com.example.demosihappandroidpart.data.dto.dto_all_workers.toModelAllWorkers
import com.example.demosihappandroidpart.domain.model.model_all_workers.ModelAllWorkers
import com.example.demosihappandroidpart.domain.repository.SihRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UseCaseGetAllWorkers @Inject constructor(
    private val repo: SihRepo
) {
    suspend operator fun invoke(): Flow<Resource<ModelAllWorkers>> = flow{
        try{
            emit(Resource.Loading())
            val data = repo.getAllWorkers().toModelAllWorkers()
            emit(Resource.Success(data = data))
        }catch (httpExc: HttpException){
            emit(Resource.Error(message = httpExc.localizedMessage?: "Unable to reach server at the moment (HTTP Exception)"))
        }catch (ioExc: IOException){
            emit(Resource.Error(message = ioExc.localizedMessage?: "Check your internet connection or try again later (IO Exception)"))
        }catch (e: Exception){
            emit(Resource.Error(message = e.localizedMessage?: "Unable to reach server (Exception occurred)"))
        }
    }
}