"use client";

import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import Navbar from "../components/ui/Navbar";
import Footer from "../components/ui/Footer";
import HelpButton from "../components/ui/HelpButton";
import Button from "../components/ui/Button";

const Home: React.FC = () => {
  const [activeTab, setActiveTab] = useState("softskills");
  const [userInfo, setUserInfo] = useState<{ email: string | null; id: number | null }>({
    email: null,
    id: null,
  });
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
<<<<<<< HEAD
    const userEmail = localStorage.getItem("userEmail");
    const userId = localStorage.getItem("userId");

    if (!userEmail || !userId) {
      router.push("/auth/login");
    } else {
      setUserInfo({ email: userEmail, id: parseInt(userId, 10) });
    }
    setLoading(false);
  }, [router]);
=======
    // Verifica se o token está presente no localStorage
    const token = localStorage.getItem("token");

    // Se o token não estiver presente, redireciona para a página de login
    if (!token) {
      router.push("/auth/login");
    }
  }, [router]); // O hook será executado ao carregar a página
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4

  const handleGameStart = (title: string) => {
    router.push(`/game?title=${title}`);
  };

  const softSkillsButtons = [
    "Empatia",
    "Comunicacao inclusiva",
    "Pessoas negras",
    "Respeito as diferencas",
    "Vies Inconsciente",
    "Interseccionalidade",
    "Diversidade Cultural",
    "Equidade De Genero",
    "Inclusao De PCD",
    "Racial E Etnica",
    "LGBTQIA E Inclusao",
    "Socioeconomica",
    "Religiao E Espiritualidade",
    "Saude Mental E Inclusao",
  ];

  const hardSkillsButtons = [
    "Funcoes Simples",
    "Comentarios Uteis",
    "Codigo Legivel",
    "Nomes Significativos",
    "Formatacao de Codigo",
    "Principio DRY",
    "Principio SRP",
    "Tratamente de Erros",
    "Reducao de Dependencias",
    "Testabilidade",
    "Principio KISS",
    "Principio YAGNI",
    "Refatoracao Continua",
    "Boas Praticas de POO",
  ];

  const renderButtons = (buttons: string[]) => {
    return buttons.map((title) => (
      <Button
        key={title}
        className="bg-[#00B4D8] text-white w-[250px] h-[50px] rounded-[10px] text-lg px-3 hover:bg-[#009ec3]"
        onClick={() => handleGameStart(title)}
        aria-label={`Iniciar jogo de ${title}`}
      >
        {title}
      </Button>
    ));
  };

  // Extrai o nome do usuário (antes do @)
  const username = userInfo.email ? userInfo.email.split("@")[0] : "";

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-100 flex items-center justify-center">
        <p>Carregando...</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col relative">
      <Navbar />
      <div className="bg-[#FFD166] flex justify-center space-x-4 py-4">
        <button
          className={`text-lg md:text-xl font-bold px-4 py-2 rounded border-[3px] border-transparent hover:border-[#FFA500] ${
            activeTab === "softskills" ? "bg-[#FFA500] text-white" : "text-gray-800"
          }`}
          onClick={() => setActiveTab("softskills")}
          aria-pressed={activeTab === "softskills"}
        >
          Soft Skills
        </button>
        <button
          className={`text-lg md:text-xl font-bold px-4 py-2 rounded border-[3px] border-transparent hover:border-[#FFA500] ${
            activeTab === "hardskills" ? "bg-[#FFA500] text-white" : "text-gray-800"
          }`}
          onClick={() => setActiveTab("hardskills")}
          aria-pressed={activeTab === "hardskills"}
        >
          Hard Skills
        </button>
      </div>

      {/* Barra de boas-vindas com fundo igual ao da Navbar */}
      <div className="bg-[#0077B6] py-1">
        <p className="text-white text-xl text-center">Bem-vindo, {username}</p>
      </div>

      <div className="p-8 flex-grow">
        {activeTab === "softskills" && (
          <div className="bg-[#0077B6] w-full max-w-[800px] mx-auto rounded-[20px] flex flex-col items-center justify-center py-8">
            <h2 className="text-white text-xl md:text-2xl font-bold mb-8 text-center">
              Soft Skills - Diversidade e Inclusão
            </h2>
            <div className="flex flex-wrap justify-center gap-6 md:gap-8">
              {renderButtons(softSkillsButtons)}
            </div>
          </div>
        )}

        {activeTab === "hardskills" && (
          <div className="bg-[#0077B6] w-full max-w-[800px] mx-auto rounded-[20px] flex flex-col items-center justify-center py-8">
            <h2 className="text-white text-xl md:text-2xl font-bold mb-8 text-center">
              Hard Skills - Clean Code
            </h2>
            <div className="flex flex-wrap justify-center gap-6 md:gap-8">
              {renderButtons(hardSkillsButtons)}
            </div>
          </div>
        )}
      </div>

      <Footer />
      <HelpButton />
    </div>
  );
};

export default Home;
