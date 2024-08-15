package com.example.ai.function;

import java.util.List;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductFunction implements Function<ProductFunction.Request, ProductFunction.Response>{
	public record Product(String year, String model, Integer quantity) {}
	@Override
	public Response apply(Request request) {
		log.info("request:{}-{}",request.year, request.product);
		
		return new Response(List.of(
				new Product("2022", "PD-1405", 12500),
				new Product("2023", "PD-1234", 10000), 
				new Product("2023", "PD-1235", 1500), 
				new Product("2023", "PD-1385", 15000),
				new Product("2024", "PD-1255", 15000),
				new Product("2024", "PD-1300", 12000),
				new Product("2024", "PD-1405", 12500),
				new Product("2024", "PD-1235", 15000),
				new Product("2024", "PD-1385", 15000)
			));
	}
	
	@JsonInclude(Include.NON_NULL)
	@JsonClassDescription("公司產品銷售列表")
	public record Request(
	//參數可帶入年份及產品，目前沒特別處理，參數可放在跟後端請求資料時篩選資料
			@JsonProperty(required = false, value = "year") @JsonPropertyDescription("年分") String year,
			@JsonProperty(required = false, value = "product") @JsonPropertyDescription("產品") String product
			) {
	}
	//回應資料若有多筆，可以使用 List 回傳，AI 也能根據這些資料搭配 Prompt 的問題在提供正確資料給使用者
	public record Response(List<Product> products) {
	}

}
