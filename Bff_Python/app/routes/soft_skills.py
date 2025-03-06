from fastapi import APIRouter, Query, HTTPException
from app.services.api_service import create_execution, wait_for_execution_to_complete
from app.utils.utils import parse_questions

router = APIRouter()

@router.get("/game/questions")
def get_questions(title: str = Query(...)):
    """
    Obtém as perguntas da API externa e retorna para o frontend no formato estruturado.
    """
    input_data = {"input_data": title}
    execution_id = create_execution(input_data)
    result = wait_for_execution_to_complete(execution_id)

    # Extrai o campo com as perguntas
    steps = result.get("steps", [])
    raw_text = ""
    for step in steps:
        if step.get("step_name") == "questionario":
            raw_text = step.get("step_result", {}).get("answer", "")

    if not raw_text:
        raise HTTPException(status_code=500, detail="Não foi possível obter perguntas.")

    formatted_questions = parse_questions(raw_text)

    return {"questions": formatted_questions}
