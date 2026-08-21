package code.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.dtos.UserDto;
import code.models.Setting;
import code.models.User;
import code.repositories.UserRepository;
import code.utils.CryptographyUtils;

@Service
public class UserService {

    protected UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto createAndReadUser(UserDto userDto) throws Exception {
        User user = this.userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (user == null) {
            Setting setting = new Setting();
            setting.setSignature(CryptographyUtils.getInstance().generateSecretKey());
            user = UserDto.toEntity(userDto);
            user.setSetting(setting);
            setting.setUser(user);
            user = this.userRepository.save(user);
        }
        return UserDto.toDto(user);
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) {
        if (!this.userRepository.existsById(userDto.getId())) {
            return null;
        }
        return UserDto.toDto(this.userRepository.save(UserDto.toEntity(userDto)));
    }

}