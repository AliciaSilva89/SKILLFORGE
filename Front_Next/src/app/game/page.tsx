"use client";

import React, { useState, useEffect } from "react";
import { useSearchParams } from "next/navigation";
import Navbar from "@/components/ui/Navbar";
import Footer from "@/components/ui/Footer";
import HelpButton from "@/components/ui/HelpButton";
import Button from "@/components/ui/Button";

interface Question {
  question: string;
  options: string[];
  correct_answer: number;
}

const softSkillsTitles = [
  "Empatia", "Comunicacao inclusiva", "Pessoas negras", "Respeito as diferencas",
  "Vies Inconsciente", "Interseccionalidade", "Diversidade Cultural",
  "Equidade De Genero", "Inclusao De PCD", "Racial E Etnica",
  "LGBTQIA E Inclusao", "Socioeconomica", "Religiao E Espiritualidade",
  "Saude Mental E Inclusao",
];

const hardSkillsTitles = [
  "Funcoes Simples", "Comentarios Uteis", "Codigo Legivel", "Nomes Significativos",
  "Formatacao de Codigo", "Principio DRY", "Principio SRP", "Tratamente de Erros",
  "Reducao de Dependencias", "Testabilidade", "Principio KISS",
  "Principio YAGNI", "Refatoracao Continua", "Boas Praticas de POO",
];

const GamePage: React.FC = () => {
  const searchParams = useSearchParams();
  const title = searchParams?.get("title");

  const [questions, setQuestions] = useState<Question[]>([]);
  const [selectedOption, setSelectedOption] = useState<string>(""); 
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [answers, setAnswers] = useState<boolean[]>([]);
  const [isGameFinished, setIsGameFinished] = useState(false);
  const [hasFetched, setHasFetched] = useState(false);

  useEffect(() => {
    if (!title || hasFetched) return;

    setHasFetched(true);

    // Verifica se o título pertence a Hard Skills ou Soft Skills
    const category = softSkillsTitles.includes(title)
      ? "softskills"
      : hardSkillsTitles.includes(title)
      ? "hardskills"
      : null;

    if (!category) {
      console.error("Título não reconhecido:", title);
      return;
    }

    fetch(`http://127.0.0.1:8000/${category}/questions?title=${title}`)
      .then((res) => res.json())
      .then((data) => setQuestions(data.questions))
      .catch((err) => console.error("Erro ao buscar perguntas:", err));
  }, [title, hasFetched]);

  if (questions.length === 0 || !questions[currentQuestionIndex]) {
    return <div className="text-center text-lg">Carregando perguntas...</div>;
  }

  const currentQuestion = questions[currentQuestionIndex];

  const checkAnswer = (selectedOption: string) => {
    const correctOption = currentQuestion.options[currentQuestion.correct_answer];
    const isCorrect = selectedOption === correctOption;
    setAnswers((prevAnswers) => [...prevAnswers, isCorrect]);
  };

  const nextQuestion = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
    } else {
      setIsGameFinished(true);
      sendFeedback();
    }
  };

  const getScore = () => answers.filter((answer) => answer).length;

  const sendFeedback = () => {
    const score = getScore();
    fetch("http://127.0.0.1:8000/game/feedback", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, score }),
    })
      .then((res) => res.json())
      .then((data) => console.log("Feedback enviado:", data))
      .catch((err) => console.error("Erro ao enviar feedback:", err));
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col relative">
      <Navbar />
      <div
        className="flex-grow bg-cover bg-center flex justify-center items-center p-4"
        style={{ backgroundImage: 'url("/seu-background.jpg")' }}
      >
        <div className="bg-[#003F5C] bg-opacity-80 w-full max-w-[800px] p-6 md:p-8 rounded-md text-white shadow-md">
          {!isGameFinished ? (
            <>
              <h2 id="question-title" className="text-center text-2xl md:text-3xl font-bold mb-4">
                {currentQuestion.question}
              </h2>

              <div role="radiogroup" aria-labelledby="question-title" className="space-y-4">
                {currentQuestion.options.map((option, index) => (
                  <label
                    key={index}
                    className="flex items-center bg-[#1f1f1f] bg-opacity-50 p-4 rounded-md cursor-pointer hover:bg-opacity-70"
                  >
                    <input
                      type="radio"
                      name="gameOption"
                      className="form-radio text-[#FFA500] mr-3 focus:ring-2 focus:ring-white"
                      checked={selectedOption === option}
                      onChange={() => setSelectedOption(option)}
                      aria-checked={selectedOption === option}
                    />
                    <span className="text-base md:text-lg">{option}</span>
                  </label>
                ))}
              </div>

              <div className="flex flex-col md:flex-row md:justify-between gap-4 mt-8">
                <Button
                  className="bg-[#FFA500] text-white w-full max-w-[200px] h-[50px] rounded-[10px] text-lg hover:bg-[#FF6F00]"
                  onClick={() => setCurrentQuestionIndex(Math.max(0, currentQuestionIndex - 1))}
                >
                  VOLTAR
                </Button>
                <Button
                  className="bg-[#0077B6] text-white w-full max-w-[200px] h-[50px] rounded-[10px] text-lg hover:bg-[#005b80]"
                  onClick={() => {
                    checkAnswer(selectedOption);
                    nextQuestion();
                  }}
                >
                  PRÓXIMO
                </Button>
              </div>
            </>
          ) : (
            <div className="text-center">
              <h2 className="text-2xl font-bold mb-4">Jogo Finalizado</h2>
              <p className="text-xl">Pontuação final: {getScore()} de {questions.length}</p>
              <p className="text-lg">Acertos: {getScore()}</p>
              <p className="text-lg">Erros: {questions.length - getScore()}</p>
            </div>
          )}
        </div>
      </div>
      <Footer />
      <HelpButton />
    </div>
  );
};

export default GamePage;
