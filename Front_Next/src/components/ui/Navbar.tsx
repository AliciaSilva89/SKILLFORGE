"use client";

import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

const Navbar: React.FC = () => {
  const router = useRouter();
  const [showMenu, setShowMenu] = useState(false);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsAuthenticated(!!token);
  }, []);

  const toggleMenu = () => setShowMenu(!showMenu);

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === "Enter" || e.key === " ") {
      toggleMenu();
    }
  };

  return (
    <nav className="bg-[#FFA500] shadow-[0_6px_2px_rgba(0,0,0,0.3)] h-[60px] flex items-center justify-between px-8">
      {/* Logo com Ícone */}
      <h1
        className="text-white text-xl md:text-2xl font-bold cursor-pointer flex items-center space-x-2"
        onClick={() => router.push("/")}
        onKeyDown={(e) => {
          if (e.key === "Enter" || e.key === " ") {
            router.push("/");
          }
        }}
        tabIndex={0} // Permite acessar com o teclado
        aria-label="Ir para a página inicial"
      >
        <img src="/SFlogo.svg" alt="Ícone SkillForge" className="w-12 h-12" />
        <span>SkillForge</span>
      </h1>

      {/* Link Sobre Nós */}
      <p
        className="text-white text-xl md:text-2xl font-bold cursor-pointer"
        onClick={() => router.push("/about")}
        onKeyDown={(e) => {
          if (e.key === "Enter" || e.key === " ") {
            router.push("/about");
          }
        }}
        tabIndex={0} // Permite acessar com o teclado
        aria-label="Sobre Nós"
      >
        Sobre Nós
      </p>

      {/* Menu de Usuário */}
      <div className="relative flex items-center space-x-4">
        <button
          className="w-10 h-10 rounded-full border-2 border-white flex items-center justify-center cursor-pointer"
          onClick={toggleMenu}
          onKeyDown={handleKeyDown} // Permite que o menu seja aberto pelo teclado
          aria-label={showMenu ? "Fechar menu" : "Abrir menu"}
          aria-expanded={showMenu ? "true" : "false"}
          aria-controls="menu"
          tabIndex={0} // Permite acessar com o teclado
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth={2}
              d="M5.121 17.804A4 4 0 015 15.5V15a4 4 0 014-4h6a4 4 0 014 4v.5a4 4 0 01-.121 2.304M15 11a4 4 0 10-6 0m6 0a4 4 0 01-6 0"
            />
          </svg>
        </button>

        {showMenu && (
          <div
            id="menu"
            className="absolute right-0 top-[52px] bg-[#FFA500] shadow-lg rounded-lg w-48"
          >
            <ul className="py-2">
              {isAuthenticated ? (
                <>
                  <li
                    className="px-4 py-2 text-white hover:text-[#00B4D8] cursor-pointer"
                    onClick={() => router.push("/profile")}
                    onKeyDown={(e) => {
                      if (e.key === "Enter" || e.key === " ") {
                        router.push("/profile");
                      }
                    }}
                    tabIndex={0}
                    aria-label="Ir para o perfil"
                  >
                    Meu Perfil
                  </li>
                  <li
                    className="px-4 py-2 text-white hover:text-[#00B4D8] cursor-pointer"
                    onClick={() => router.push("/settings")}
                    onKeyDown={(e) => {
                      if (e.key === "Enter" || e.key === " ") {
                        router.push("/settings");
                      }
                    }}
                    tabIndex={0}
                    aria-label="Ir para configurações"
                  >
                    Configurações
                  </li>
                  <li
                    className="px-4 py-2 text-white hover:text-[#00B4D8] cursor-pointer"
                    onClick={() => {
                      localStorage.removeItem("token");
                      setIsAuthenticated(false);
                      router.push("/");
                    }}
                    onKeyDown={(e) => {
                      if (e.key === "Enter" || e.key === " ") {
                        localStorage.removeItem("token");
                        setIsAuthenticated(false);
                        router.push("/");
                      }
                    }}
                    tabIndex={0}
                    aria-label="Sair"
                  >
                    Sair
                  </li>
                </>
              ) : (
                <>
                  <li
                    className="px-4 py-2 text-white hover:text-[#00B4D8] cursor-pointer"
                    onClick={() => router.push("/auth/login")}
                    onKeyDown={(e) => {
                      if (e.key === "Enter" || e.key === " ") {
                        router.push("/auth/login");
                      }
                    }}
                    tabIndex={0}
                    aria-label="Fazer login"
                  >
                    Login
                  </li>
                  <li
                    className="px-4 py-2 text-white hover:text-[#00B4D8] cursor-pointer"
                    onClick={() => router.push("/auth/register")}
                    onKeyDown={(e) => {
                      if (e.key === "Enter" || e.key === " ") {
                        router.push("/auth/register");
                      }
                    }}
                    tabIndex={0}
                    aria-label="Cadastrar-se"
                  >
                    Cadastrar
                  </li>
                </>
              )}
            </ul>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
