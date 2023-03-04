package com.yvkalume.gifapp.data.model.mapper

abstract class Mapper<I,O> {
		abstract fun map(input: I) : O

		fun mapList(inputs: List<I>) : List<O> {
				return inputs.map { item -> this.map(item) }
		}
}