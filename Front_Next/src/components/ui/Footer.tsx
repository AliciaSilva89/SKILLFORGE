"use client";

import React from "react";

const Footer: React.FC = () => {
  return (
    <footer className="bg-[#003F5C] text-white text-center py-4 text-sm md:text-base">
      <p className="space-x-2">
        @2025 por Maria Santos & Alicia Feliciano |{" "}
        <a
          href="https://linkedin.com"
          className="text-[#00B4D8] hover:underline"
          aria-label="Visite o LinkedIn de Maria Santos e Alicia Feliciano"
          target="_blank"
          rel="noopener noreferrer"
        >
          LinkedIn
        </a>{" "}
        |{" "}
        <a
          href="https://twitter.com"
          className="text-[#00B4D8] hover:underline"
          aria-label="Visite o Twitter de Maria Santos e Alicia Feliciano"
          target="_blank"
          rel="noopener noreferrer"
        >
          Twitter
        </a>
      </p>
    </footer>
  );
};

export default Footer;
