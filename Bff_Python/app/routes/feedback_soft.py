from fastapi import APIRouter, HTTPException
from pydantic import BaseModel

# Defina um modelo Pydantic para os dados de feedback
class Feedback(BaseModel):
    title: str
    score: int

router = APIRouter()

# Rota POST para receber o feedback
@router.post("/game/feedback")
async def receive_feedback(feedback: Feedback):
    """
    Recebe o feedback do jogo, que inclui o título e a pontuação.
    """
    try:
        # Aqui você pode processar os dados, como salvar no banco de dados ou fazer outro tipo de operação
        print(f"Recebido feedback: Título: {feedback.title}, Pontuação: {feedback.score}")

        # Retorne uma resposta de sucesso
        return {"message": "Feedback recebido com sucesso"}
    
    except Exception as e:
        # Caso ocorra algum erro, lance uma exceção HTTP
        raise HTTPException(status_code=500, detail=f"Erro ao processar feedback: {str(e)}")
