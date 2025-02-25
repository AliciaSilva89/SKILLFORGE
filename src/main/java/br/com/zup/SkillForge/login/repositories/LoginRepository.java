package br.com.zup.SkillForge.login.repositories;

import br.com.zup.SkillForge.login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
}