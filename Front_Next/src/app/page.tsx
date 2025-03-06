"use client";

import React, { useState } from "react";
import { useRouter } from "next/navigation";
import Navbar from "../components/ui/Navbar";
import Footer from "../components/ui/Footer";
import HelpButton from "../components/ui/HelpButton";
import Button from "../components/ui/Button";

const Home: React.FC = () => {
  const [activeTab, setActiveTab] = useState("softskills");
  const router = useRouter();

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
