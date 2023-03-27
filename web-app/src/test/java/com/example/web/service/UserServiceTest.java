package com.example.web.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.web.entity.User;
import com.example.web.repository.UserRepository;

import jakarta.persistence.OptimisticLockException;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@MockBean
	UserRepository userRepository;
	
	
	@Test
	@DisplayName("ユーザー更新：正常系")
	void testUpdateUser_Success() throws ParseException {
		// テストデータ
		User user = new User();
		user.setId(1L);
		user.setName("テスト太郎");
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date base = df.parse("2021/01/01 12:00:00");
		user.setCreateDate(base);
		user.setUpdateDate(base);
		user.setDeleteDate(null);
		
		// モック設定
		Mockito.when(userRepository.findOneForUpdate(user.getId())).thenReturn(user);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		// テスト実行
		User actual = userService.updateUser(user);
		
		//検証
		Mockito.verify(userRepository, Mockito.times(1)).findOneForUpdate(user.getId());
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
		assertEquals(user, actual);
		
		// 更新日付だけ、更新されているか
		assertEquals(base, actual.getCreateDate());
		assertNotEquals(base, actual.getUpdateDate());
		
	}
	
	@Test
	@DisplayName("ユーザー更新：異常系")
	void testUpdateUser_OptimisticLockException() throws ParseException {
		
		// テストデータ
		User user = new User();
		user.setId(1L);
		user.setName("テスト太郎");
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date base = df.parse("2021/01/01 12:00:00");
		user.setCreateDate(base);
		user.setUpdateDate(base);
		user.setDeleteDate(null);
		
		// Updated User
		User updatedUser = new User();
		updatedUser.setId(1L);
		updatedUser.setName("テスト太郎");
		updatedUser.setUpdateDate(new Date());
		
		// モック設定
		Mockito.when(userRepository.findOneForUpdate(user.getId())).thenReturn(updatedUser);
		
		// テスト実行
		Throwable e = assertThrows(OptimisticLockException.class, () -> userService.updateUser(user));
		
		//検証
		Mockito.verify(userRepository, Mockito.times(1)).findOneForUpdate(user.getId());
		Mockito.verify(userRepository, Mockito.never()).save(user);
		
		String message = "データが他の方によって更新されたようです。詳細画面に戻ってから再実施してください。";
		assertEquals(message, e.getMessage());
	}

}
