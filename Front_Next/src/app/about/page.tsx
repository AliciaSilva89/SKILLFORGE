"use client";

import React from "react";
import Navbar from "../../components/ui/Navbar";
import Footer from "../../components/ui/Footer";
import HelpButton from "../../components/ui/HelpButton"; // opcional, se desejar manter o botão fixo

const About: React.FC = () => {
  return (
    <div className="min-h-screen bg-gray-100 flex flex-col relative">
      <Navbar />
      <main className="flex-grow flex justify-center items-center p-8">
        <section className="bg-[#0077B6] w-full max-w-[800px] mx-auto rounded-[20px] p-8 shadow-md">
          {/* Título principal da página */}
          <h1 className="text-white text-xl md:text-2xl font-bold mb-8 text-center" aria-live="polite">
            Sobre o SkillForge
          </h1>

          {/* Descrição inicial sobre a plataforma */}
          <p className="text-white text-center text-base md:text-lg mb-6">
            O <strong>SkillForge</strong> é uma plataforma inovadora dedicada ao desenvolvimento
            de habilidades, tanto técnicas quanto interpessoais. Criada com o intuito
            de fornecer um ambiente seguro e dinâmico para o aprimoramento profissional,
            nosso foco é oferecer ferramentas interativas e desafios que ajudem
            usuários a evoluir constantemente.
          </p>

          {/* Seção: Missão */}
          <h2 className="text-white text-lg md:text-xl font-bold mb-4">Nossa Missão</h2>
          <p className="text-white text-center text-base md:text-lg mb-6">
            Nossa missão é transformar o aprendizado em algo empolgante e acessível
            para todos, independentemente do nível de conhecimento. Acreditamos
            no poder do conhecimento prático e da colaboração para construir um
            futuro mais inovador.
          </p>

          {/* Seção: Visão */}
          <h2 className="text-white text-lg md:text-xl font-bold mb-4">Nossa Visão</h2>
          <p className="text-white text-center text-base md:text-lg mb-6">
            Ser reconhecido como a principal plataforma de desenvolvimento de habilidades
            interativas, tanto técnicas quanto sociais, criando um impacto positivo na
            carreira e na vida das pessoas.
          </p>

          {/* Seção: Valores */}
          <h2 className="text-white text-lg md:text-xl font-bold mb-4">Valores</h2>
          <ul className="text-white list-disc list-inside text-base md:text-lg space-y-4 mb-6">
            <li>Inovação constante</li>
            <li>Colaboração e diversidade</li>
            <li>Compromisso com o aprendizado contínuo</li>
            <li>Segurança e ética no ambiente digital</li>
          </ul>

          {/* Seção: A Equipe */}
          <h2 className="text-white text-lg md:text-xl font-bold mb-4">A Equipe</h2>
          <p className="text-white text-center text-base md:text-lg mb-6">
            Somos uma equipe apaixonada por tecnologia, educação e inovação. Juntos,
            trabalhamos para criar uma experiência única para os nossos usuários.
          </p>
        </section>
      </main>
      <Footer />

      {/* Botão de ajuda com descrição acessível */}
      <HelpButton aria-label="Obter ajuda adicional sobre a plataforma SkillForge" />
    </div>
  );
};

export default About;
