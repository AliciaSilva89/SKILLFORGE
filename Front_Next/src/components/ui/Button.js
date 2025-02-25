import React from "react";

const Button = ({ children, className = "", ...props }) => {
  return (
    <button
      className={`bg-[#00B4D8] text-white py-2 px-4 rounded-lg hover:bg-[#0077B6] transition ${className}`}
      {...props}
    >
      {children}
    </button>
  );
};

export default Button;
