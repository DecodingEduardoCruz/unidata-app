package br.dev.ec.unidata.resources.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DefaultExceptions {
	private int code;
	private String message;
}
