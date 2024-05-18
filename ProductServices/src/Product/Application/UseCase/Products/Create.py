from src.Product.Domain.Ports.ProductPort import ProductsPort as Port, Products


class CreateUseCase:
    def __init__(self, repository: Port):
        self.__repository = repository

    def run(self, data):
        productos = [Products(**d) for d in data]
        return self.__repository.create_cant_products(productos)