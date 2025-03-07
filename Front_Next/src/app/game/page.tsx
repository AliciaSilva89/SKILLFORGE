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

interface Feedback {
  feedback_summary: string;
  detailed_feedback: string[];
  motivacao: string;
}

const GamePage: React.FC = () => {
  const searchParams = useSearchParams();
  const title = searchParams?.get("title");

  const [questions, setQuestions] = useState<Question[]>([]);
  const [selectedAnswers, setSelectedAnswers] = useState<string[]>([]); // Melhorar o controle das respostas
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [answers, setAnswers] = useState<boolean[]>([]); // Respostas corretas como booleano
  const [isGameFinished, setIsGameFinished] = useState(false);
  const [hasFetched, setHasFetched] = useState(false);
  const [feedback, setFeedback] = useState<Feedback | null>(null); // Feedback tipado
  const [isLoadingFeedback, setIsLoadingFeedback] = useState(false); // Indicador de carregamento

  useEffect(() => {
    if (!title || hasFetched) return;

    setHasFetched(true);

    const category = title === "Empatia" ? "softskills" : "hardskills"; // Verifica a categoria

    if (!category) {
      console.error("Título não reconhecido:", title);
      return;
    }

    fetch(`http://54.232.130.28:8000/${category}/questions?title=${title}`)
      .then((res) => res.json())
      .then((data) => setQuestions(data.questions))
      .catch((err) => console.error("Erro ao buscar perguntas:", err));
  }, [title, hasFetched]);

  useEffect(() => {
    if (isGameFinished) {
      const score = answers.filter((answer) => answer).length;
      setIsLoadingFeedback(true); // Inicia o carregamento do feedback

      fetch(`http://54.232.130.28:8000/feedback?title=${title}&score=${score}`, {
        method: "GET",
      })
        .then((res) => res.json())
        .then((data: Feedback) => {
          setFeedback(data);
          setIsLoadingFeedback(false); // Finaliza o carregamento do feedback
        })
        .catch((err) => {
          console.error("Erro ao buscar feedback:", err);
          setIsLoadingFeedback(false);
        });
    }
  }, [isGameFinished, answers, title]);

  if (questions.length === 0 || !questions[currentQuestionIndex]) {
    return <div className="text-center text-lg">Carregando perguntas...</div>;
  }

  const currentQuestion = questions[currentQuestionIndex];

  const checkAnswer = (selectedOption: string) => {
    const correctOption = currentQuestion.options[currentQuestion.correct_answer];
    const isCorrect = selectedOption === correctOption;
    setAnswers((prevAnswers) => [...prevAnswers, isCorrect]);

    // Armazenando a resposta selecionada
    setSelectedAnswers((prevSelectedAnswers) => [
      ...prevSelectedAnswers,
      selectedOption,
    ]);
  };

  const nextQuestion = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
    } else {
      setIsGameFinished(true);
    }
  };

  const getScore = () => answers.filter((answer) => answer).length;

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
                    className={`flex items-center bg-[#1f1f1f] bg-opacity-50 p-4 rounded-md cursor-pointer hover:bg-opacity-70 ${selectedAnswers[currentQuestionIndex] === option ? 'bg-[#FF6F00]' : ''}`}
                    aria-selected={selectedAnswers[currentQuestionIndex] === option}
                  >
                    <input
                      type="radio"
                      name="gameOption"
                      className="form-radio text-[#FFA500] mr-3 focus:ring-2 focus:ring-white"
                      checked={selectedAnswers[currentQuestionIndex] === option}
                      onChange={() => setSelectedAnswers((prev) => {
                        const newAnswers = [...prev];
                        newAnswers[currentQuestionIndex] = option;
                        return newAnswers;
                      })}
                      aria-checked={selectedAnswers[currentQuestionIndex] === option}
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
                    checkAnswer(selectedAnswers[currentQuestionIndex]);
                    nextQuestion();
                  }}
                >
                  PRÓXIMO
                </Button>
              </div>
            </>
          ) : isLoadingFeedback ? (
            <div className="text-center text-xl">Carregando resultados...</div>
          ) : (
            <div className="space-y-4 text-center">
              <h2 className="text-2xl font-bold mb-4">Feedback Final</h2>
              <p className="text-lg">Pontuação final: {getScore()} de {questions.length}</p>
              <p className="text-lg">Acertos: {getScore()}</p>
              <p className="text-lg">Erros: {questions.length - getScore()}</p>

              <div className="bg-[#003F5C] text-white p-6 rounded-lg shadow-lg">
                <h3 className="font-semibold text-xl mb-2">Resumo do Feedback</h3>
                <p className="text-sm">{feedback?.feedback_summary || "Resumo não disponível."}</p>

                <div className="mt-4">
                  <h3 className="font-semibold text-lg mb-2">Detalhes do Feedback</h3>
                  <div className="space-y-2 text-sm">
                    {feedback?.detailed_feedback && feedback.detailed_feedback.length > 0 ? (
                      feedback.detailed_feedback.map((item, index) => (
                        <p key={index} className="whitespace-pre-line">{item}</p>
                      ))
                    ) : (
                      <p className="text-gray-500">Nenhum detalhe disponível.</p>
                    )}
                  </div>
                </div>
                
                <div className="mt-6">
                  <h3 className="font-semibold text-lg mb-2">Motivação</h3>
                  <p className="text-sm">{feedback?.motivacao || "Motivação não disponível."}</p>
                </div>
              </div>
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
