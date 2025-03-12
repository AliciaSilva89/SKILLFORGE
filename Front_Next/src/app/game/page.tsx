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
<<<<<<< HEAD
=======
}

interface Feedback {
  feedback_summary: string;
  detailed_feedback: string[];
  motivacao: string;
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
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
<<<<<<< HEAD
  const [selectedOption, setSelectedOption] = useState<string>("");
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [answers, setAnswers] = useState<boolean[]>([]);
  const [isGameFinished, setIsGameFinished] = useState(false);
  const [hasFetched, setHasFetched] = useState(false);
=======
  const [selectedAnswers, setSelectedAnswers] = useState<string[]>([]); // Melhorar o controle das respostas
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [answers, setAnswers] = useState<boolean[]>([]); // Respostas corretas como booleano
  const [isGameFinished, setIsGameFinished] = useState(false);
  const [hasFetched, setHasFetched] = useState(false);
  const [feedback, setFeedback] = useState<Feedback | null>(null); // Feedback tipado
  const [isLoadingFeedback, setIsLoadingFeedback] = useState(false); // Indicador de carregamento
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4

  useEffect(() => {
    if (!title || hasFetched) return;

    setHasFetched(true);

<<<<<<< HEAD
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
=======
    const category = title === "Empatia" ? "softskills" : "hardskills"; // Verifica a categoria

    if (!category) {
      console.error("Título não reconhecido:", title);
      return;
    }

    fetch(`http://54.232.130.28:8000/${category}/questions?title=${title}`)
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
      .then((res) => res.json())
      .then((data) => setQuestions(data.questions))
      .catch((err) => console.error("Erro ao buscar perguntas:", err));
  }, [title, hasFetched]);

<<<<<<< HEAD
=======
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

>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
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

<<<<<<< HEAD
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

=======
  const getScore = () => answers.filter((answer) => answer).length;

>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
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
<<<<<<< HEAD
                    className="block p-4 bg-gray-800 rounded-lg cursor-pointer hover:bg-blue-500 transition"
=======
                    className={`flex items-center bg-[#1f1f1f] bg-opacity-50 p-4 rounded-md cursor-pointer hover:bg-opacity-70 ${selectedAnswers[currentQuestionIndex] === option ? 'bg-[#FF6F00]' : ''}`}
                    aria-selected={selectedAnswers[currentQuestionIndex] === option}
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
                  >
                    <input
                      type="radio"
                      name="gameOption"
<<<<<<< HEAD
                      className="mr-3"
                      checked={selectedOption === option}
                      onChange={() => setSelectedOption(option)}
=======
                      className="form-radio text-[#FFA500] mr-3 focus:ring-2 focus:ring-white"
                      checked={selectedAnswers[currentQuestionIndex] === option}
                      onChange={() => setSelectedAnswers((prev) => {
                        const newAnswers = [...prev];
                        newAnswers[currentQuestionIndex] = option;
                        return newAnswers;
                      })}
                      aria-checked={selectedAnswers[currentQuestionIndex] === option}
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
                    />
                    {option}
                  </label>
                ))}
              </div>
<<<<<<< HEAD
              <div className="flex justify-between mt-8">
                <Button className="bg-blue-500 text-white px-4 py-2 rounded-lg" onClick={() => setCurrentQuestionIndex(Math.max(0, currentQuestionIndex - 1))}>VOLTAR</Button>
                <Button className="bg-green-500 text-white px-4 py-2 rounded-lg" onClick={nextQuestion}>PRÓXIMO</Button>
=======

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
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
              </div>
            </>
          ) : isLoadingFeedback ? (
            <div className="text-center text-xl">Carregando resultados...</div>
          ) : (
<<<<<<< HEAD
            <div className="text-center">
              <h2 className="text-3xl font-bold mb-4">Jogo Finalizado</h2>
              <p>Acertos: {getScore()}, Erros: {questions.length - getScore()}</p>
              <p>{generateFeedback()}</p>
=======
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
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
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
