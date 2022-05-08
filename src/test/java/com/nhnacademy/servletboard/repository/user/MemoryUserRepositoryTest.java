package com.nhnacademy.servletboard.repository.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.servletboard.domain.user.User;
import com.nhnacademy.servletboard.exception.MemberExistException;
import com.nhnacademy.servletboard.exception.MemberNotExistException;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MemoryUserRepositoryTest {

    UserRepository userRepository;
    User user;
    String id;

    @BeforeEach
    void setUp() {
        this.userRepository = new MemoryUserRepository();
        this.user = mock(User.class);
        id = "id";
        when(user.getId()).thenReturn(id);
    }

    @Test
    @DisplayName("사용자 추가")
    void add() {
        userRepository.add(user);
        verify(user, Mockito.times(2)).getId();
    }

    @Test
    @DisplayName("이미 존재하는 아이디로 사용자 추가")
    void add_fail() {
        userRepository.add(user);

        assertThatThrownBy(() -> userRepository.add(user))
            .isInstanceOf(MemberExistException.class)
            .hasMessageContaining(user.getId());
    }

    @Test
    @DisplayName("사용자 정보 수정")
    void modify() {
        userRepository.modify(user);
        verify(user, Mockito.times(2)).getId();
    }

    @Test
    @DisplayName("없는 사용자 수정")
    void modify_fail() {
        assertThatThrownBy(() -> userRepository.modify(user))
            .isInstanceOf(MemberNotExistException.class);
    }

    @Test
    @DisplayName("사용자 삭제")
    void remove() {
        userRepository.add(user);

        User remove = userRepository.remove(id);

        assertThat(remove.getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("사용자 ID로 사용자 찾기 - 없는 사용자")
    void getUserFail() {
        assertThat(userRepository.getUser(id)).isNull();
    }

    @Test
    @DisplayName("사용자 ID로 사용자 찾기 - 성공")
    void getUser() {
        userRepository.add(user);

        User findUser = userRepository.getUser(this.user.getId());

        assertThat(findUser).isEqualTo(user);
    }

    @Test
    @DisplayName("사용자 목록 찾기")
    void getUsers() {
        List<User> users = userRepository.getUsers();

        verify(user, times(users.size())).getId();
    }
}