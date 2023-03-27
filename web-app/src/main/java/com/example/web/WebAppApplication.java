package com.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; //クラスが Spring Boot によって自動的に適用できる構成を提供することを示します。

/**
 * Spring Bootアプリケーションのエントリーポイントとなるクラスです。
 * <p>
 * このクラスは {@code @SpringBootApplication} アノテーションが付与されており、Spring
 * Bootアプリケーションのメインクラスとして機能します。
 * </p>
 */
@SpringBootApplication
public class WebAppApplication {

	/**
	 * Spring Bootアプリケーションを起動します。
	 *
	 * @param args 起動引数
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

}
