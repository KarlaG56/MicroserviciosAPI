from sqlalchemy import Update

from src.Product.Domain.Ports.ProductPort import ProductsPort as Port

class UpdateCantidadByEvent:
    def __init__(self, repository : Port):
        self.repository = repository

    def run(self, data):
        self.repository.update_Quantity(data['id'], data['quantity'])

