from src.Product.Domain.Ports.ProductPort import ProductsPort as Port


class DeleteUseCase:
    def __init__(self, repository: Port):
        self.__repository = repository

    def run(self, id):
        return self.__repository.delete_products(id)
