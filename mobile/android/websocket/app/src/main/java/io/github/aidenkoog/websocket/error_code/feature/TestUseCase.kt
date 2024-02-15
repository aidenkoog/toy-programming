package io.github.aidenkoog.websocket.error_code.feature

import io.github.aidenkoog.websocket.error_code.base.CoroutineUseCase
import io.github.aidenkoog.websocket.error_code.base.CustomResultUseCase
import io.github.aidenkoog.websocket.error_code.base.Data
import io.github.aidenkoog.websocket.error_code.base.UseCaseDispatcher.IoDispatcher

class TestUseCase(
    private val repository: TestRepository,
) : CoroutineUseCase, CustomResultUseCase<Data<ResponseModel>, RequestModel>() {
    override suspend fun invoke(params: RequestModel) = invoke(IoDispatcher) {
        repository.testRepository(params)
    }
}