import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";

// Carregando fontes personalizadas (se desejado)
const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

// Definindo os metadados para o SkillForge
export const metadata: Metadata = {
  title: "SkillForge - Desenvolva suas habilidades",
  description:
    "SkillForge é a plataforma para aprender e aprimorar suas habilidades técnicas e interpessoais de forma interativa e dinâmica.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <head>
        {/* Você pode adicionar mais tags aqui, como <meta> ou <link> */}
        <link rel="icon" href="./SFlogo.svg" />
      </head>
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased bg-[#003F5C] text-white`} // Adicionando tema escuro para o projeto SkillForge
      >
        {/* Renderizando o conteúdo da página */}
        {children}
      </body>
    </html>
  );
}
