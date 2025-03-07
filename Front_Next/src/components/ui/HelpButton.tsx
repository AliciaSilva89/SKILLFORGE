"use client";

import React from "react";
import { useRouter } from "next/navigation";

const HelpButton: React.FC = () => {
  const router = useRouter();

  return (
    <div className="fixed bottom-16 right-4 z-40">
      <button
        onClick={() => router.push("/help")}
        className="flex items-center bg-[#FFA500] text-white px-6 py-3 rounded-[10px] hover:bg-[#e69500] shadow-lg transition-all"
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
            d="M8.257 5.099c.765-1.36 2.702-1.36 3.466 0 .75 1.338.75 3.206 0 4.544-.688 1.228-1.828 1.948-2.466 2.448-.632.492-1.002.985-1.002 1.958v.5m2-9h.01"
          />
        </svg>
        <span className="ml-3 text-lg hidden sm:inline">Dúvidas?</span>
      </button>
    </div>
  );
};

export default HelpButton;
