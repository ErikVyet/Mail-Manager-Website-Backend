package code.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.models.Setting;
import code.repositories.SettingRepository;
import code.utils.CryptographyUtils;

@Service
public class SettingService {
    
    protected SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Transactional(readOnly = true)
    public String readSignature(String email) {
        return this.settingRepository.getSignatureByUserEmail(email).orElse(null);
    }

    @Transactional
    public String regenerateSignature(String email) throws Exception {
        Setting setting = this.settingRepository.findByUserEmail(email).orElse(null);
        String signature = CryptographyUtils.getInstance().generateSecretKey();
        setting.setSignature(signature);
        this.settingRepository.save(setting);
        return signature;
    }

}