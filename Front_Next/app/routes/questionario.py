from fastapi import APIRouter, Query, HTTPException
from app.services.api_service import create_execution, wait_for_execution_to_complete, create_execution_cleancode, wait_for_execution_to_complete_cleancode
from app.utils.utils import parse_questions

router = APIRouter()

@router.get("/softskills/questions")
def get_softskills_questions(title: str = Query(...)):
    """
    Obtém as perguntas da API externa e retorna para o frontend no formato estruturado.
    """
    input_data = {"input_data": title}
    
    try:
        # Tenta criar a execução e aguardar a conclusão
        execution_id = create_execution(input_data)
        result = wait_for_execution_to_complete(execution_id)
    except HTTPException as e:
        # Se qualquer erro ocorrer, seja na execução ou na consulta, retorna uma mensagem mais detalhada
        raise HTTPException(status_code=e.status_code, detail=f"Erro ao processar a execução: {e.detail}")
    
    # Extrai o campo com as perguntas
    steps = result.get("steps", [])
    raw_text = next((step.get("step_result", {}).get("answer", "") for step in steps if step.get("step_name") == "questionario"), "")
    
    if not raw_text:
        raise HTTPException(status_code=500, detail="Não foi possível obter perguntas.")

    formatted_questions = parse_questions(raw_text)

    return {"questions": formatted_questions}


@router.get("/hardskills/questions")
def get_hardskills_questions(title: str = Query(...)):
    """
    Obtém as perguntas da API externa e retorna para o frontend no formato estruturado.
    """
    input_data = {"input_data": title}
    
    try:
        # Tenta criar a execução e aguardar a conclusão
        execution_id = create_execution_cleancode(input_data)
        result = wait_for_execution_to_complete_cleancode(execution_id)
    except HTTPException as e:
        # Se qualquer erro ocorrer, seja na execução ou na consulta, retorna uma mensagem mais detalhada
        raise HTTPException(status_code=e.status_code, detail=f"Erro ao processar a execução: {e.detail}")
    
    # Extrai o campo com as perguntas
    steps = result.get("steps", [])
    raw_text = next((step.get("step_result", {}).get("answer", "") for step in steps if step.get("step_name") == "questionario"), "")
    
    if not raw_text:
        raise HTTPException(status_code=500, detail="Não foi possível obter perguntas.")

    formatted_questions = parse_questions(raw_text)

    return {"questions": formatted_questions}
