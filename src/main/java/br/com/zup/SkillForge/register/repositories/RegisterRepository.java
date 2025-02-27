package br.com.zup.SkillForge.register.repositories;

import br.com.zup.SkillForge.register.models.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterUser, Long> {
}