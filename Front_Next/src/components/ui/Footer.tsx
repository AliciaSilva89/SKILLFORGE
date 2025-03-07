"use client";

import React from "react";

const Footer: React.FC = () => {
  return (
    <footer className="bg-[#003F5C] text-white text-center py-4 text-sm md:text-base">
      <p className="space-x-2">
        @2025 por {" "}
        <a
          href="https://www.linkedin.com/in/mariaesantos6657?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
          className="text-[#00B4D8] hover:underline"
          aria-label="Visite o LinkedIn de Maria Santos"
          target="_blank"
          rel="noopener noreferrer"
        >
          Maria Santos 
        </a>{" "} &
        <a
          href="https://www.linkedin.com/in/alicia-feliciano-85a7a1220?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
          className="text-[#00B4D8] hover:underline"
          aria-label="Visite o LinkedIn de Alicia Feliciano"
          target="_blank"
          rel="noopener noreferrer"
        >
          Alicia Feliciano 
        </a>{" "}
      </p>
    </footer>
  );
};

export default Footer;
