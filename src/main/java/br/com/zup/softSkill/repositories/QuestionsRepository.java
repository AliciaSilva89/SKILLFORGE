package br.com.zup.softSkill.repositories;

import br.com.zup.softSkill.models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, UUID> {
}