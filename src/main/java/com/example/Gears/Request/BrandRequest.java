package com.example.Gears.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequest extends BaseRequest {
	private long brandID;
	private String brandName;
}
