import React from "react";

interface ButtonProps {
  onClick: () => void;
  className?: string; // Adicionando className como opcional
  children: React.ReactNode;
}

const Button: React.FC<ButtonProps> = ({ onClick, className, children }) => {
  return (
    <button
      onClick={onClick}
      className={`btn ${className}`} // Usando className passado como prop
    >
      {children}
    </button>
  );
};

export default Button;
