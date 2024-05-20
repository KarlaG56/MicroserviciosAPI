from src.Product.Application.UseCase.Products.UpdateCantidadByEvent import UpdateCantidadByEvent as UseCase, Port
import json

class UpdateCantidadByEventController:
    def __init__(self, repository : Port):
        self.__use_case = UseCase(repository)

    def run(self, payload: str):
        self.__use_case.run(json.loads(payload))