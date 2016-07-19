package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.USER_NAME;
import static com.thoughtworks.ketsu.support.TestHelper.userJsonForTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserRespositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_and_get_user() {
        Map userInfo = userJsonForTest(USER_NAME);

        User user = userRepository.save(userInfo);
        Optional<User> fetched = userRepository.findById(7987l);

        assertThat(fetched.isPresent(), is(true));
    }
}
