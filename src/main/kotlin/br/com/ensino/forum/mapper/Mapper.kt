package br.com.ensino.forum.mapper

interface Mapper<I, U> {
    fun map(input: I): U
}