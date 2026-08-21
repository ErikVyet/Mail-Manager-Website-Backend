package code.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import code.dtos.ApiDto;
import code.models.Api;
import code.models.User;
import code.repositories.ApiRepository;
import code.repositories.UserRepository;
import code.utils.CryptographyUtils;

@Service
public class ApiService {

    protected ApiRepository apiRepository;
    protected UserRepository userRepository;

    public ApiService(ApiRepository apiRepository, UserRepository userRepository) {
        this.apiRepository = apiRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<ApiDto> readUserApiKeys(String email, OffsetDateTime fromDate, OffsetDateTime toDate) {
        Specification<Api> specs = Specification.unrestricted();
        specs = specs.and((root, query, cb) -> root.get("user").get("email").equalTo(email));
        if (fromDate != null) {
            specs = specs.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), fromDate));
        }
        if (toDate != null) {
            specs = specs.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), toDate));
        }
        return this.apiRepository.findAll(specs, Sort.by("createdAt").ascending()).stream().map(ApiDto::toDto).toList();
    }

    @Transactional
    public ApiDto createApiKey(String email) throws Exception {
        User user = this.userRepository.findByEmail(email).orElseThrow();
        Api api = new Api();
        api.setUser(user);
        api.setKey(CryptographyUtils.getInstance().generateApiKey());
        return ApiDto.toDto(this.apiRepository.save(api));
    }

    @Transactional
    public void deleteApiKey(String email, String key) {
        Api api = this.apiRepository.findByKeyAndUserEmail(key, email).orElseThrow();
        this.apiRepository.delete(api);
    }

}