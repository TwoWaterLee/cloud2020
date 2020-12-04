package com.naver.cloud2020.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {

	private Long id;
	private Long productId;
	private Integer total;
	private Integer used;
	private Integer residue;
}
