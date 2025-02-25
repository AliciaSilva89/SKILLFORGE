"use client";

import React from "react";
import Navbar from "../../components/ui/Navbar";
import Footer from "../../components/ui/Footer";
import HelpButton from "../../components/ui/HelpButton"; // opcional, se desejar manter o botão fixo

const Help: React.FC = () => {
  return (
    <div className="min-h-screen bg-gray-100 flex flex-col relative">
      <Navbar />
      <div className="flex-grow flex justify-center items-center p-8">
        <div className="bg-[#0077B6] w-full max-w-[800px] mx-auto rounded-[20px] p-8 shadow-md">
          <h3 className="text-white text-xl md:text-2xl font-bold mb-8 text-center" aria-live="polite">
            Ajuda & Dúvidas
          </h3>
          <p className="text-white text-center text-base md:text-lg mb-6">
            Precisa de ajuda? Aqui você encontra informações úteis para navegar e utilizar o SkillForge de maneira eficiente.
          </p>
          <ul className="text-white list-disc list-inside text-base md:text-lg space-y-4">
            <li>
              <strong>Cadastro/Login:</strong> Caso tenha problemas para acessar sua conta, tente redefinir sua senha ou entre em contato com o suporte.
            </li>
            <li>
              <strong>Hard Skills:</strong> Aprenda e teste suas habilidades técnicas através de desafios interativos.
            </li>
            <li>
              <strong>Soft Skills:</strong> Melhore sua comunicação e tomada de decisões com nossos jogos educativos.
            </li>
            <li>
              <strong>Acessibilidade:</strong> Nossa plataforma é compatível com leitores de tela e pode ser navegada com teclado.
            </li>
          </ul>
          <p className="text-white text-center text-sm md:text-base mt-6">
            Em breve, adicionaremos uma FAQ completa e opções de suporte personalizadas.
          </p>
        </div>
      </div>
      <Footer />
      <HelpButton />
    </div>
  );
};

export default Help;
