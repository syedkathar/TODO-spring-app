package com.deltaa.todo.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
	
	OPEN {
		@Override
		@JsonValue
		public String toString() {
			return "OPEN";
		}
	},
	
	COMPLETED {
		@Override
		@JsonValue
		public String toString() {
			return "COMPLETED";
		}
	}

}
