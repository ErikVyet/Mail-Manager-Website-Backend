package code.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.dtos.UserDto;
import code.enums.LabelType;
import code.models.Label;
import code.models.Setting;
import code.models.User;
import code.repositories.UserRepository;
import code.utils.CryptographyUtils;

@Service
public class UserService {

    private final String[] SYSTEM_LABEL_NAME_POOL;

    protected UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.SYSTEM_LABEL_NAME_POOL = new String[]{
            "Inbox", "Starred", "Sent", "Drafts", "Spam", "Trash"
        };
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

            List<Label> initialLabels = new ArrayList<>();
            for(String name : this.SYSTEM_LABEL_NAME_POOL) {
                Label label = new Label();
                label.setName(name);
                label.setType(LabelType.System);
                label.setUser(user);
                initialLabels.add(label);
            }
            user.setLabels(initialLabels);
            
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