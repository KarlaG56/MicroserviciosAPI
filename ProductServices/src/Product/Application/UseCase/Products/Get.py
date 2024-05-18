from src.Product.Domain.Ports.ProductPort import ProductsPort as Port

class GetUseCase:
    def __init__(self, repository: Port):
        self.__repository = repository

    def run(self):
        return self.__repository.get_products()