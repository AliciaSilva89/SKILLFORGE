package br.com.zup.SkillForge.softSkill.repositories;

import br.com.zup.SkillForge.softSkill.models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
}
