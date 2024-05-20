from src.Product.Domain.Entities.Product import Products
from abc import ABC, abstractmethod


class ProductsPort(ABC):
    @abstractmethod
    def __init__(self):
        pass

    @abstractmethod
    def get_products(self):
        pass

    @abstractmethod
    def create_cant_products(self, products: list[Products]):
        pass

    @abstractmethod
    def delete_products(self, id):
        pass

    @abstractmethod
    def update_Quantity(self, id, lessQuantity):
        pass