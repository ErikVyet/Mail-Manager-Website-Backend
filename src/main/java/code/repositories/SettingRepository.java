package code.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import code.models.Setting;

public interface SettingRepository extends JpaRepository<Setting, Long> {
    
    public Optional<Setting> findByUserEmail(String email);

    @Query("""
        SELECT s.signature
        FROM Setting s
        WHERE s.user.email = :email        
    """)
    public Optional<String> getSignatureByUserEmail(@Param(value = "email") String email);

}