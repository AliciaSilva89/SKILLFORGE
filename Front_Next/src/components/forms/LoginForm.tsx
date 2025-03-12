"use client";

import React, { useState } from "react";

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Tentativa de login:", { email, password });
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div>
        <label className="block text-white text-sm font-medium mb-1">Email:</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          className="w-full p-2 rounded border border-gray-300 focus:outline-none focus:border-[#FFA500] text-black"
        />
      </div>
      <div>
        <label className="block text-white text-sm font-medium mb-1">Senha:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          className="w-full p-2 rounded border border-gray-300 focus:outline-none focus:border-[#FFA500] text-black"
        />
      </div>
      <button type="submit" className="w-full py-2 bg-[#FFA500] text-white rounded hover:bg-[#e69500]">
        Entrar
      </button>
    </form>
  );
};

export default LoginForm;
