package io.github.golok56.footballmatchscore.usecase

abstract class UseCase<Input, Output> {
    abstract suspend fun execute(data: Input): Output?
}