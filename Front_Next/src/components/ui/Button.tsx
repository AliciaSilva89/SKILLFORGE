import React from "react";

interface ButtonProps {
  onClick: () => void;
<<<<<<< HEAD
  className?: string;
=======
  className?: string; // Adicionando className como opcional
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
  children: React.ReactNode;
}

const Button: React.FC<ButtonProps> = ({ onClick, className, children }) => {
  return (
    <button
      onClick={onClick}
<<<<<<< HEAD
      className={`btn ${className}`}
=======
      className={`btn ${className}`} // Usando className passado como prop
>>>>>>> 8d87d68873921fb1b060998f401d6d9edfb489b4
    >
      {children}
    </button>
  );
};

export default Button;
