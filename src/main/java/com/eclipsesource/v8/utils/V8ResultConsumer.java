package com.eclipsesource.v8.utils;

import com.eclipsesource.v8.V8;

import java.util.function.Consumer;
import java.util.function.Function;

public class V8ResultConsumer {
	private Consumer<Object> consumer;
	private Function<V8, Object> retriever;
	private String resultVar;

	public V8ResultConsumer(Consumer<Object> consumer, Function<V8, Object> retriever) {
		this.consumer = consumer;
		this.retriever = retriever;
	}

	public V8ResultConsumer(Consumer<Object> consumer, String resultVar) {
		this.consumer = consumer;
		this.resultVar = resultVar;
	}

	void apply(V8 runtime) {
		if (consumer != null) {
			Object result = null;
			if (retriever != null) {
				result = retriever.apply(runtime);
			} else if (resultVar != null)
				result = runtime.get(resultVar);

			consumer.accept(result);
		}
	}
}
