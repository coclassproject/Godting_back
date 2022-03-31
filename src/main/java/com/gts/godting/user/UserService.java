package com.gts.godting.user;

import com.gts.godting.config.redis.RedisUtil;
import com.gts.godting.mail.EmailMessage;
import com.gts.godting.mail.MailService;
import com.gts.godting.user.form.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RedisUtil redisUtil;
    private final MailService mailService;

    public void sendEmail(String email) {
        boolean emailCheck = userRepository.existsByEmail(email);
        //TODO 학교 웹메일 필터
        if (emailCheck) {
            throw new RuntimeException("이미 사용중인 이메일");
        }
        String emailCheckToken = UUID.randomUUID().toString();
        String oauth2Id = (String)redisUtil.get(emailCheckToken);
        if (oauth2Id != null) {
            redisUtil.delete(emailCheckToken);
        }
        redisUtil.set(emailCheckToken, email, 3);
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("Godting, 회원가입 이메일 인증")
                .message("인증 번호 박스에" + emailCheckToken + "을 입력하세요.").build();
        mailService.sendMail(emailMessage);
    }

    public void processEmailCheck(String emailCheckToken) throws TimeoutException {
        String oauth2Id = (String)redisUtil.get(emailCheckToken);
        if (oauth2Id == null) {
            throw new TimeoutException();
        }
    }

    public void saveNewUser(SignUpForm signUpForm) {
        boolean emailCheck = userRepository.existsByEmail(signUpForm.getEmail());
        if (emailCheck) {
            throw new RuntimeException("이미 사용중인 이메일");
        }
        boolean nicknameCheck = userRepository.existsByNickname(signUpForm.getNickname());
        if (nicknameCheck) {
            throw new RuntimeException("이미 사용중인 닉네임");
        }
        User user = modelMapper.map(signUpForm, User.class);
        user.completeSignUp();
        userRepository.save(user);
    }
}
