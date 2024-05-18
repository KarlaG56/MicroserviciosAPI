from src.Product.Application.UseCase.Products.Delete import DeleteUseCase as UseCase, Port


class DeleteController:
    def __init__(self, repository: Port):
        self.__use_case = UseCase(repository)

    def run(self, id):
        return self.__use_case.run(id)