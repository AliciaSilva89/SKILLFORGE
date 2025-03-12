"use client"; 

import React, { useState, useEffect } from "react";
import { useSearchParams } from "next/navigation";
import Navbar from "@/components/ui/Navbar";
import Footer from "@/components/ui/Footer";
import HelpButton from "@/components/ui/HelpButton";
import Button from "@/components/ui/Button";
import { Suspense } from "react";

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

    const category = softSkillsTitles.includes(title)
      ? "softskills"
      : hardSkillsTitles.includes(title)
      ? "hardskills"
      : null;

    if (!category) {
      console.error("Título não reconhecido:", title);
      setIsGameFinished(true);
      return;
    }

    fetch(`http://18.231.117.6:8000/${category}/questions?title=${title}`)
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
    if (selectedOption) {
      checkAnswer(selectedOption);
      setSelectedOption("");
    }

    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
    } else {
      setIsGameFinished(true);
    }
  };

  const getScore = () => {
    if (answers.length !== questions.length) {
      console.warn(`Erro: Esperado ${questions.length} respostas, mas recebeu ${answers.length}`);
    }
    return answers.filter((answer) => answer).length;
  };

  const generateFeedback = () => {
    const score = getScore();
    const total = questions.length;

    if (score === total) {
      return "Incrível! Você acertou todas as questões e demonstrou um domínio completo do conteúdo.";
    } else if (score >= total * 0.8) {
      return "Parabéns! Você teve um excelente desempenho, continue assim.";
    } else if (score >= total * 0.6) {
      return "Muito bom! Você acertou a maioria das questões. Há ainda alguns pontos para revisar.";
    } else if (score >= total * 0.4) {
      return "Você teve um bom desempenho, mas há espaço para melhorias. Tente revisar os temas que apresentou dificuldades.";
    } else {
      return "Não desanime! Use este resultado como uma oportunidade para revisar e aprender ainda mais.";
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col relative">
      <Navbar />
      <div
        className="flex-grow bg-cover bg-center flex justify-center items-center p-4"
        style={{ backgroundImage: 'url("/seu-background.jpg")' }}
      >
        <div className="bg-[#003F5C] bg-opacity-90 w-full max-w-[800px] p-8 rounded-2xl text-white shadow-lg">
          {!isGameFinished ? (
            <>
              <h2 className="text-center text-3xl font-bold mb-6">{currentQuestion.question}</h2>
              <div className="space-y-4">
                {currentQuestion.options.map((option, index) => (
                  <label
                    key={index}
                    className="block p-4 bg-gray-800 rounded-lg cursor-pointer hover:bg-blue-500 transition"
                  >
                    <input
                      type="radio"
                      name="gameOption"
                      className="mr-3"
                      checked={selectedOption === option}
                      onChange={() => setSelectedOption(option)}
                    />
                    {option}
                  </label>
                ))}
              </div>
              <div className="flex justify-between mt-8">
                <Button className="bg-blue-500 text-white px-4 py-2 rounded-lg" onClick={() => setCurrentQuestionIndex(Math.max(0, currentQuestionIndex - 1))}>VOLTAR</Button>
                <Button className="bg-green-500 text-white px-4 py-2 rounded-lg" onClick={nextQuestion}>PRÓXIMO</Button>
              </div>
            </>
          ) : (
            <div className="text-center">
              <h2 className="text-3xl font-bold mb-4">Jogo Finalizado</h2>
              <p>Acertos: {getScore()}, Erros: {questions.length - getScore()}</p>
              <p>{generateFeedback()}</p>
            </div>
          )}
        </div>
      </div>
      <Footer />
      <HelpButton />
    </div>
  );
};

export default function Game() {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <GamePage />
    </Suspense>
  );
}
