package code.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import code.models.Api;

public interface ApiRepository extends JpaRepository<Api, Long>, JpaSpecificationExecutor<Api> {

    public List<Api> findByUserEmail(String email);
    public Optional<Api> findByKeyAndUserEmail(String key, String email);

}