import time
import requests
from fastapi import HTTPException
from app.services.auth import get_token, refresh_token
from app.config.config import CREATE_EXECUTION_URL, CALLBACK_URL_TEMPLATE, CALLBACK_URL_TEMPLATE_FEEDBACK, CREATE_EXECUTION_URL_FEEDBACK

def create_execution(input_data):
    """
    Envia uma requisição POST para iniciar a execução.
    Se retornar 403, obtém um novo token e tenta novamente.
    """
    token = get_token()
    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json"
    }
    
    response = requests.post(CREATE_EXECUTION_URL, headers=headers, json=input_data)
    if response.status_code == 200:
        return response.text.strip()
    elif response.status_code == 403:
        token = refresh_token()
        headers["Authorization"] = f"Bearer {token}"
        response = requests.post(CREATE_EXECUTION_URL, headers=headers, json=input_data)
        if response.status_code == 200:
            return response.text.strip()
    
    raise HTTPException(status_code=response.status_code, detail=f"Erro ao criar execução: {response.text}")

def get_callback(execution_id):
    """
    Consulta a execução pelo execution_id.
    Se retornar 403, obtém um novo token e tenta novamente.
    """
    url = CALLBACK_URL_TEMPLATE.format(execution_id=execution_id.strip('"'))
    token = get_token()
    headers = {"Authorization": f"Bearer {token}"}
    
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        return response.json()
    elif response.status_code == 403:
        token = refresh_token()
        headers["Authorization"] = f"Bearer {token}"
        response = requests.get(url, headers=headers)
        if response.status_code == 200:
            return response.json()
    
    raise HTTPException(status_code=response.status_code, detail=f"Erro ao obter callback: {response.text}")

def wait_for_execution_to_complete(execution_id, delay=15, max_retries=10):
    """
    Aguarda a execução ser concluída, verificando o status periodicamente.
    """
    retries = 0
    while retries < max_retries:
        result = get_callback(execution_id)
        if not result:
            raise HTTPException(status_code=500, detail="Erro ao obter o resultado da execução.")
        
        progress = result.get('progress', {})
        execution_percentage = progress.get('execution_percentage')
        
        if execution_percentage == 1.0:
            return result
        
        time.sleep(delay)
        retries += 1
    
    raise HTTPException(status_code=500, detail="Número máximo de tentativas atingido.")


def create_execution_fedback(input_data):
    """
    Envia uma requisição POST para iniciar a execução.
    Se retornar 403, obtém um novo token e tenta novamente.
    """
    token = get_token()
    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json"
    }
    
    response = requests.post(CREATE_EXECUTION_URL_FEEDBACK, headers=headers, json=input_data)
    if response.status_code == 200:
        return response.text.strip()
    elif response.status_code == 403:
        token = refresh_token()
        headers["Authorization"] = f"Bearer {token}"
        response = requests.post(CREATE_EXECUTION_URL_FEEDBACK, headers=headers, json=input_data)
        if response.status_code == 200:
            return response.text.strip()
    
    raise HTTPException(status_code=response.status_code, detail=f"Erro ao criar execução: {response.text}")

def get_callback_feedback(execution_id):
    """
    Consulta a execução pelo execution_id.
    Se retornar 403, obtém um novo token e tenta novamente.
    """
    url = CALLBACK_URL_TEMPLATE_FEEDBACK.format(execution_id=execution_id.strip('"'))
    token = get_token()
    headers = {"Authorization": f"Bearer {token}"}
    
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        return response.json()
    elif response.status_code == 403:
        token = refresh_token()
        headers["Authorization"] = f"Bearer {token}"
        response = requests.get(url, headers=headers)
        if response.status_code == 200:
            return response.json()
    
    raise HTTPException(status_code=response.status_code, detail=f"Erro ao obter callback: {response.text}")

def wait_for_execution_to_complete(execution_id, delay=15, max_retries=10):
    """
    Aguarda a execução ser concluída, verificando o status periodicamente.
    """
    retries = 0
    while retries < max_retries:
        result = get_callback(execution_id)
        if not result:
            raise HTTPException(status_code=500, detail="Erro ao obter o resultado da execução.")
        
        progress = result.get('progress', {})
        execution_percentage = progress.get('execution_percentage')
        
        if execution_percentage == 1.0:
            return result
        
        time.sleep(delay)
        retries += 1
    
    raise HTTPException(status_code=500, detail="Número máximo de tentativas atingido.")
