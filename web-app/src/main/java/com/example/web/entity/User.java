package com.example.web.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ユーザー情報を表すEntityクラスです。
 * <p>
 * このクラスは、データベースのuserテーブルと対応しています。
 * </p>
 */
@Entity
@Data
@Table(name = "user")
public class User {

	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 名前
	 */
	@Column(name = "name")
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	private String name;

	/**
	 * 住所
	 */
	@Column(name = "address")
	@Size(max = 100, message = "住所は100文字以内で入力してください")
	private String address;

	/**
	 * 電話番号
	 */
	@Column(name = "phone")
	@Pattern(regexp = "|\\d{1,4}-\\d{1,4}-\\d{4}", message = "電話番号の形式（xxxx-xxxx-xxxx）で入力してください")
	private String phone;

	/**
	 * 更新日時
	 */
	@Column(name = "update_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date updateDate;

	/**
	 * 登録日時
	 */
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date createDate;

	/**
	 * 削除日時
	 */
	@Column(name = "delete_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date deleteDate;
}
