package com.example.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.web.entity.User;
import com.example.web.repository.UserRepository;

import jakarta.persistence.OptimisticLockException;

/**
 * ユーザー情報の登録・更新・削除、取得を行うサービスクラスです。
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	/**
	 * 全てのユーザー情報を取得します。
	 *
	 * @return 全てのユーザー情報
	 */
	public List<User> searchAll() {
		return userRepository.findAll();
	}
	
	/**
	 * 指定されたユーザーIDに対応するユーザー情報を取得します。
	 *
	 * @param id ユーザーID
	 * @return 指定されたユーザーIDに対応するユーザー情報
	 */
	public User search(Long id) {
		return userRepository.findById(id).get();
	}

	/**
	 * 指定されたユーザー情報を登録します。
	 *
	 * @param user 登録するユーザー情報
	 * @return 登録されたユーザー情報
	 */
	public User createUser(User user) {
		Date now = new Date();
		user.setCreateDate(now);
		user.setUpdateDate(now);
		return userRepository.save(user);
	}

	/**
	 * 指定されたユーザーIDに対応するユーザー情報を削除します。
	 *
	 * @param id 削除するユーザーID
	 */
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	/**
	 * 指定されたユーザー情報を更新します。 <br>
	 * 更新時に排他制御（オプティミスティックロック）を行います。
	 *
	 * @param user 更新するユーザー情報
	 * @return 更新されたユーザー情報
	 * @throws OptimisticLockException 他のユーザーによってデータが更新されている場合にスローされます。
	 */
	@Transactional
	public User updateUser(User user) throws OptimisticLockException {
		// 排他制御（更新チェック）
		User currentUser = userRepository.findOneForUpdate(user.getId());

		if (currentUser.getUpdateDate().getTime() == user.getUpdateDate().getTime()) {
			Date now = new Date();
			user.setUpdateDate(now);
			return userRepository.save(user);
		} else {
			String message = "データが他の方によって更新されたようです。詳細画面に戻ってから再実施してください。";
			throw new OptimisticLockException(message);
		}
	}

}