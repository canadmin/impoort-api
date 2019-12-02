package com.impoort.impoortapi.api.v1.model.requestmodel.comment;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class IDCommentRequestDTO {
	@NotNull
	private String commentId;
	
	//Update icin diger kısımlar eklenecek.
}
